package frc.snailLib.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.config.SparkBaseConfig;

public class SparkMotorIO implements MotorIO {
  protected final SparkBase sparkMotor;
  protected final RelativeEncoder sparkEncoder;
  protected final SparkClosedLoopController sparkController;

  public SparkMotorIO(SparkBase sparkMotor, SparkBaseConfig config) {
    this.sparkMotor = sparkMotor;
    this.sparkEncoder = sparkMotor.getEncoder();
    this.sparkController = sparkMotor.getClosedLoopController();

    this.sparkMotor.configure(
        config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(MotorInputs inputs) {
    inputs.velocityUnitsPerSecond = sparkEncoder.getVelocity();
    inputs.unitPosition = sparkEncoder.getPosition();
    inputs.appliedVolts = sparkMotor.getAppliedOutput() * sparkMotor.getBusVoltage();
    inputs.outputCurrent = sparkMotor.getOutputCurrent();
  }

  @Override
  public void setOpenLoopDutyCycle(double dutyCycle) {
    sparkController.setReference(dutyCycle, ControlType.kDutyCycle);
  }

  @Override
  public void setPositionSetpoint(
      double positionUnits, double arbFeedforward, ClosedLoopSlot slot) {
    sparkController.setReference(positionUnits, ControlType.kPosition, slot, arbFeedforward);
  }

  @Override
  public void setVelocitySetpoint(double velocity, double arbFeedforward, ClosedLoopSlot slot) {
    sparkController.setReference(velocity, ControlType.kVelocity, slot, arbFeedforward);
  }

  @Override
  public void setVoltageOutput(double volts) {
    sparkController.setReference(volts, ControlType.kVoltage);
  }
}
