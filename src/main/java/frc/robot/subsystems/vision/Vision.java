package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase {
    private VisionIO io;
    private final VisionIOInputsAutoLogged inputs = new VisionIOInputsAutoLogged();

    private Pose2d estimate;

    public Vision(VisionIO io) {

    }

    @Override
    public void periodic() {
        io.updateInputs(inputs, estimate);
        Logger.processInputs("Vision", inputs);
    }

    public void setEstimate(Pose2d pose) {
        estimate = pose;
    }
}
