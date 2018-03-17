package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Auto_ArmToPosition extends Command {

	double m_position;
	
    public Auto_ArmToPosition(double position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.winch);
    	requires(Robot.sensors);
    	
    	m_position = position;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.winch.armUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double currentPosition = Robot.sensors.getArmEndcoderRaw();
		if (currentPosition >= m_position - 2) {
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
