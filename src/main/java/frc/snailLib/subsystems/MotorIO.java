package frc.snailLib.subsystems;

import com.revrobotics.spark.ClosedLoopSlot;

public interface MotorIO {

  void updateInputs(MotorInputs inputs);

  void setOpenLoopDutyCycle(double dutyCycle);

  void setPositionSetpoint(double positionUnits, double arbFeedforward, ClosedLoopSlot slot);

  void setVelocitySetpoint(double velocity, double arbFeedforward, ClosedLoopSlot slot);

  void setVoltageOutput(double volts);
}
