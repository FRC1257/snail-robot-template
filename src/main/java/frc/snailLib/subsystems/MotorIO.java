package frc.snailLib.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;
import org.littletonrobotics.junction.AutoLog;

public interface MotorIO {

  @AutoLog
  public class MotorInputs {
    public double velocityUnitsPerSecond = 0.0;
    public double unitPosition = 0.0;
    public double appliedVolts = 0.0;
    public double outputCurrent = 0.0;
  }

  void updateInputs(MotorInputs inputs);

  void setOpenLoopDutyCycle(double dutyCycle);

  void setPositionSetpoint(double positionUnits, double arbFeedforward, ClosedLoopSlot slot);

  void setVelocitySetpoint(double velocity, double arbFeedforward, ClosedLoopSlot slot);

  void setVoltageOutput(double volts);
}
