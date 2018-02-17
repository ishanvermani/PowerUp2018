package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LowerArmSolenoids extends Command {
	
    public LowerArmSolenoids() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.pneumatics);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	boolean currentState = Robot.pneumatics.getDouble();
		if (currentState == true) {
			Robot.pneumatics.lowerBoom();
		} else {
			Robot.pneumatics.raiseBoom();
		}
    		
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
