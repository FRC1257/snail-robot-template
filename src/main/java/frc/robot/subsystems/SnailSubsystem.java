package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 * Class that all subsystems on our robot should inherit so that the following functions are called at the right times
 */
public abstract class SnailSubsystem extends SubsystemBase {

    public abstract void outputValues();
    public abstract void setUpConstantTuning();
    public abstract void getConstantTuning();
}
