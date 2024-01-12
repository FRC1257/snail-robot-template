package frc.robot.subsystems.drive;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;

public class DriveIOCIM implements DriveIO {
  private final CANSparkMax leftLeader;
  private final CANSparkMax rightLeader;
  private final CANSparkMax leftFollower;
  private final CANSparkMax rightFollower;

  private final GyroIOReal gyro;

  public DriveIOCIM() {
    leftLeader = new CANSparkMax(17, MotorType.kBrushed);
    rightLeader = new CANSparkMax(4, MotorType.kBrushed); // coast
    leftFollower = new CANSparkMax(11, MotorType.kBrushed);
    rightFollower = new CANSparkMax(10, MotorType.kBrushed); // coast

    leftLeader.setIdleMode(IdleMode.kBrake);
    leftFollower.setIdleMode(IdleMode.kCoast);
    rightLeader.setIdleMode(IdleMode.kBrake);
    rightFollower.setIdleMode(IdleMode.kCoast);

    leftLeader.restoreFactoryDefaults();
    rightLeader.restoreFactoryDefaults();
    leftFollower.restoreFactoryDefaults();
    rightFollower.restoreFactoryDefaults();

    leftLeader.setInverted(true);
    rightLeader.setInverted(false);
    leftFollower.follow(leftLeader, false);
    rightFollower.follow(rightLeader, false);

    leftLeader.enableVoltageCompensation(12.0);
    rightLeader.enableVoltageCompensation(12.0);
    leftLeader.setSmartCurrentLimit(30);
    rightLeader.setSmartCurrentLimit(30);

    leftLeader.burnFlash();
    rightLeader.burnFlash();
    leftFollower.burnFlash();
    rightFollower.burnFlash();

    gyro = GyroIOReal.getInstance();
  }

  @Override
  public void updateInputs(DriveIOInputs inputs) {
    inputs.leftPositionRad = 0;
    inputs.rightPositionRad = 0;
    inputs.leftVelocityRadPerSec = leftLeader.getAppliedOutput();
    inputs.rightVelocityRadPerSec = rightLeader.getAppliedOutput();
    inputs.gyroYawRad = gyro.getYawAngle();
    inputs.gyroRollPitchYawRad[0] = gyro.getRollAngle();
    inputs.gyroRollPitchYawRad[1] = gyro.getPitchAngle();
    inputs.gyroRollPitchYawRad[2] = gyro.getYawAngle();
  }

  @Override
  public void setVoltage(double leftVolts, double rightVolts) {
    leftLeader.setVoltage(leftVolts);
    rightLeader.setVoltage(rightVolts);
  }

  @Override
  public double getLeftPositionMeters() {
    return 0;
    // return leftEncoder.getPosition() * leftEncoder.getPositionConversionFactor();
  }

  @Override
  public double getRightPositionMeters() {
    // return rightEncoder.getPosition() * rightEncoder.getPositionConversionFactor();
    return 0;
  }

  @Override
  public void setVelocity(DifferentialDriveWheelSpeeds wheelSpeeds) {
    // leftPIDController.setReference(wheelSpeeds.leftMetersPerSecond, ControlType.kVelocity, DRIVE_VEL_SLOT);
    // rightPIDController.setReference(wheelSpeeds.rightMetersPerSecond, ControlType.kVelocity, DRIVE_VEL_SLOT);
  }

  @Override
  public void zero() {
    GyroIOReal.getInstance().zeroAll();
    // leftEncoder.setPosition(0);
    // rightEncoder.setPosition(0);
  }
}
