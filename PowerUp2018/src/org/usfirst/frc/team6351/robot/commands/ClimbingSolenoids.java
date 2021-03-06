package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbingSolenoids extends Command {
	
    public ClimbingSolenoids() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    		requires(Robot.pneumatics);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	boolean currentState = Robot.pneumatics.getSolenoidState(1);
		if (currentState == true) {
			Robot.pneumatics.moveClimberArm(false);
		} else {
			Robot.pneumatics.moveClimberArm(true);
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
