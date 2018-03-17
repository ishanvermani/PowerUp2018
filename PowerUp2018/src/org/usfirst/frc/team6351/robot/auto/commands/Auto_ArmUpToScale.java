package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_ArmUpToScale extends Command {

	double m_position;
	
    public Auto_ArmUpToScale() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    	requires(Robot.sensors);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.armUp();
    	DriverStation.reportError("AUTO WINCH", false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double volts = Robot.sensors.getStopSwitchVoltage();
		if (volts > 4.00) {
			return true;
		} else {	
			return false;
		}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.winch.armStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.winch.armStop();
    }
}
