package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArmUp extends Command {
	public void UpperArm() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.Winch);
	}
	
    // Called just before this Command runs the first time
    protected void initialize() {	
    }
	
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Winch.oneDirection();
    }
	
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;
    }
	
    // Called once after isFinished returns true
    protected void end() {
    	Robot.Winch.stop();
    }
	
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
    

