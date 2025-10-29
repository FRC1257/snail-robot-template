package frc.robot.subsystems.intake;

import frc.snailLib.subsystems.MotorIO;
import frc.snailLib.subsystems.MotorInputsAutoLogged;
import frc.snailLib.subsystems.SnailMotorSubsystem;

public class Intake extends SnailMotorSubsystem<MotorInputsAutoLogged, MotorIO> {
    public MotorIO motorIO;

    public Intake(MotorIO io) {
        super(io, new MotorInputsAutoLogged(), "Intake");
        this.motorIO = io;
    }
}
