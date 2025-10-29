package frc.snailLib.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.DoubleSupplier;
import org.littletonrobotics.junction.Logger;

public class SnailMotorSubsystem<T extends MotorInputsAutoLogged, IO extends MotorIO>
    extends SubsystemBase {

  protected IO subsystemIO;
  protected T subsystemInputs;

  protected double positionSetpoint = 0.0;

  public SnailMotorSubsystem(IO io, T inputs, String name) {
    super(name);
    this.subsystemIO = io;
    this.subsystemInputs = inputs;
  }

  @Override
  public void periodic() {
    subsystemIO.updateInputs(subsystemInputs);
    Logger.processInputs(getName(), subsystemInputs);
  }

  protected void setPositionSetpointImplementation(
      double positionUnits, double feedforward, ClosedLoopSlot slot) {
    this.positionSetpoint = positionUnits;
    subsystemIO.setPositionSetpoint(positionUnits, feedforward, slot);
  }

  protected void setVelocitySetpointImplementation(
      double velocityUnitsPerSecond, double feedforward, ClosedLoopSlot slot) {
    subsystemIO.setVelocitySetpoint(velocityUnitsPerSecond, feedforward, slot);
  }

  protected void setOpenLoopDutyCycleImplementation(double dutyCycle) {
    subsystemIO.setOpenLoopDutyCycle(dutyCycle);
  }

  protected void setVoltageOutputImplementation(double volts) {
    subsystemIO.setVoltageOutput(volts);
  }

  public Command dutyCycleCommand(DoubleSupplier dutyCycle) {
    return runEnd(
            () -> setOpenLoopDutyCycleImplementation(dutyCycle.getAsDouble()),
            () -> setOpenLoopDutyCycleImplementation(0.0))
        .withName(getName() + " Duty Cycle Command");
  }

  public Command voltageCommand(DoubleSupplier volts) {
    return runEnd(
            () -> setVoltageOutputImplementation(volts.getAsDouble()),
            () -> setVoltageOutputImplementation(0.0))
        .withName(getName() + " Voltage Command");
  }

  public Command velocitySetpointCommand(
      DoubleSupplier velocity, DoubleSupplier feedforward, ClosedLoopSlot slot) {
    return run(() ->
            setVelocitySetpointImplementation(
                velocity.getAsDouble(), feedforward.getAsDouble(), slot))
        .withName(getName() + " Velocity Setpoint Command");
  }

  public Command positionSetpointCommand(
      DoubleSupplier position, DoubleSupplier feedforward, ClosedLoopSlot slot) {
    return run(() ->
            setPositionSetpointImplementation(
                position.getAsDouble(), feedforward.getAsDouble(), slot))
        .withName(getName() + " Position Setpoint Command");
  }
}
