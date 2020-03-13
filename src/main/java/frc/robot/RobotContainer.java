package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SnailSubsystem;
import frc.robot.util.SnailController;

import java.util.ArrayList;

import static frc.robot.Constants.ElectricalLayout.CONTROLLER_DRIVER_ID;
import static frc.robot.Constants.ElectricalLayout.CONTROLLER_OPERATOR_ID;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the Robot
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    private final SnailController driveController;
    private final SnailController operatorController;
    
    private final ArrayList<SnailSubsystem> subsystems;

    private int outputCounter;

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        driveController = new SnailController(CONTROLLER_DRIVER_ID);
        operatorController = new SnailController(CONTROLLER_OPERATOR_ID);

        // declare each of the subsystems here

        subsystems = new ArrayList<>();
        // add each of the subsystems to the arraylist here

        configureAutoChoosers();
        configureButtonBindings();
        outputCounter = 0;

        SmartDashboard.putBoolean("Testing", false);
    }

    /**
     * Define button -> command mappings.
     */
    private void configureButtonBindings() {
        
    }

    /**
     * Set up the choosers on shuffleboard for autonomous
     */
    public void configureAutoChoosers() {
        
    }

    /**
     * Do the logic to return the auto command to run
     */
    public Command getAutoCommand() {
        return null;
    }

    public void outputValues() {
        if(outputCounter % 3 == 0) {
            subsystems.get(outputCounter / 3).outputValues();
        }

        outputCounter = (outputCounter + 1) % (subsystems.size() * 3);
    }

    public void setConstantTuning() {
        for(SnailSubsystem subsystem : subsystems) {
            subsystem.setUpConstantTuning();
        }
    }

    public void getConstantTuning() {
        if(outputCounter % 3 == 0) {
            subsystems.get(outputCounter / 3).getConstantTuning();
        }
    }
}
