package frc.snailLib.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;

public class SparkMotorIO implements MotorIO {
  protected final SparkBase sparkMotor;
  protected final RelativeEncoder sparkEncoder;
  protected final SparkClosedLoopController sparkController;

  public SparkMotorIO(SparkBase sparkMotor, SparkBaseConfig config) {
    this.sparkMotor = sparkMotor;
    this.sparkEncoder = sparkMotor.getEncoder();
    this.sparkController = sparkMotor.getClosedLoopController();

    applyConfig(config);
  }

  private void applyConfig(SparkBaseConfig config) {

    SparkBaseConfig sparkConfig;

    if (sparkMotor instanceof SparkMax) {
      sparkConfig = new SparkMaxConfig();
    } else if (sparkMotor instanceof SparkFlex) {
      sparkConfig = new SparkFlexConfig();
    } else {
      throw new IllegalArgumentException("Unsupported Spark motor type");
    }

    sparkConfig.apply(config);
    sparkMotor.configure(
        sparkConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
