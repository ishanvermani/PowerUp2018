package org.usfirst.frc.team6351.robot.subsystems;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
  
  	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	Spark winch_Motor = new Spark(0);

@Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    // Movement Commands
    public void oneDirection() {
    	winch_Motor.set(0.6);
    }
    
    public void otherDirection() {
    	winch_Motor.set(-0.6);
    }
    
    public void stop() {
    	winch_Motor.set(0);
    }
}