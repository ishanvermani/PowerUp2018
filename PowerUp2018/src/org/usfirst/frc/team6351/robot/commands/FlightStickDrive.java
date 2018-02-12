package org.usfirst.frc.team6351.robot.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class FlightStickDrive extends Command {

    public FlightStickDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double joystickZ = Robot.m_oi.joystickAxisValue(RobotMap.Joy_Z_Axis);
    	double joystickY = Robot.m_oi.joystickAxisValue(RobotMap.Joy_Y_Axis);
    	double joystickThottle = Robot.m_oi.joystickAxisValue(RobotMap.Joy_Throttle);
    	
    	double speedMultiplier;
    	
    	if (joystickThottle < 0) {
    		speedMultiplier = (0.5-(joystickThottle * -0.5));
    	}
    	if (joystickThottle > 0) { 
    		speedMultiplier = (0.5+(joystickThottle * 0.5));
    	}
    	if (joystickThottle == 0) {
    		speedMultiplier = 0.5;
    	}
    	
    	double leftMotors;
    	double rightMotors;

    		leftMotors = (joystickY + joystickZ)*RobotMap.Drive_Scaling_Teleop;
    		rightMotors = (joystickY - joystickZ)*RobotMap.Drive_Scaling_Teleop*(-1);
    		
    	if (leftMotors > RobotMap.MAX_ROBOT_SPEED) {
 		   
    		leftMotors = RobotMap.MAX_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors > RobotMap.MAX_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.MAX_ROBOT_SPEED;
    		   
    	}
    	   if (leftMotors < RobotMap.MIN_ROBOT_SPEED) {
    		   
    		leftMotors = RobotMap.MIN_ROBOT_SPEED;
    		   
    	}
    	if (rightMotors < RobotMap.MIN_ROBOT_SPEED) {
    		   
    		rightMotors = RobotMap.MIN_ROBOT_SPEED;
    		   
    	}
    	Robot.driveTrain.setLeft(leftMotors);
    	Robot.driveTrain.setRight(rightMotors);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
