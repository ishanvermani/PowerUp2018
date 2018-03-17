package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class Auto_DriveStraight extends Command {
	
	double spd, tme, dst;
	boolean encoderDrive;
	
    public Auto_DriveStraight(double speed, double time, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	requires(Robot.sensors);
    	spd = speed;
    	
    	if (time == 0.0) {
    		encoderDrive = true;
    		dst = distance;
    	} else {
        	tme = time;
    	}
    	
    }
    
    public Auto_DriveStraight(double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this(0.4, 0.0, distance);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (encoderDrive == true) {
    		Robot.sensors.driveEncoderLeft.reset();
    		Robot.driveTrain.setLeft(spd*RobotMap.Curve_Reduction_Factor);
    		Robot.driveTrain.setRight((spd)*(-1));
    		
    		
    	} else {
    		Robot.driveTrain.setLeft(spd*RobotMap.Curve_Reduction_Factor);
    		Robot.driveTrain.setRight((spd)*(-1));
    		Timer.delay(tme);
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (encoderDrive == true) {
    		double currentDistance = Robot.sensors.getDriveEncoderDistance();
    		if (currentDistance >= dst - 3) {
    			Robot.driveTrain.setLeft(-0.07);
        		Robot.driveTrain.setRight(0.07);
        		Timer.delay(0.4);
    			Robot.driveTrain.setLeft(0.0);
        		Robot.driveTrain.setRight(0.0);
    		}
    	} else {
    		Robot.driveTrain.setLeft(0.0);
    		Robot.driveTrain.setRight(0.0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (encoderDrive == true) {
    		double currentDistance = Robot.sensors.getDriveEncoderDistance();
    		if (currentDistance >= dst - 3) {
    			return true;
    		} else {
    			return false;
    		}
    	} else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeft(0.0);
		Robot.driveTrain.setRight(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeft(0.0);
		Robot.driveTrain.setRight(0.0);
    }
}
