package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

public class ArmDown extends Command {
	public UpperArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.winch);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {	
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.winch.otherDirection();
    }
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }
	
    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.stop();
    }
	
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
