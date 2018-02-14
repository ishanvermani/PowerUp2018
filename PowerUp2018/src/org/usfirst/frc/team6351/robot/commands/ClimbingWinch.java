package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbingWinch extends Command {
	
	public ClimbingWinch() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.winch);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {	
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.winch.armUp();
    	Robot.winch.winchClimb();
    }
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
	
    // Called once after isFinished returns true
    protected void end() {
    	// Command will only be run while button is held (in OI)
    }
	
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
