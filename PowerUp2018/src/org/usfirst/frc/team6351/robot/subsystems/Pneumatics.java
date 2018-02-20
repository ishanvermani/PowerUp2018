package org.usfirst.frc.team6351.robot.subsystems;

import org.usfirst.frc.team6351.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Pneumatics extends Subsystem {
	static Compressor compressor;
	static Solenoid s0 = new Solenoid(0);
	static Solenoid s1 = new Solenoid(1);
	static DoubleSolenoid s23 = new DoubleSolenoid(3, 2);
	  
	public Pneumatics(){
		compressor = new Compressor();
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void start() {
		if (Robot.isReal()) {
			compressor.start();
			compressor.setClosedLoopControl(true);
			
		}
	}
    
/*
* Error Functions
*/
    
    public boolean getEnabled() {
    	return compressor.enabled();
    }
    public boolean getCurrentFault() {
    	return compressor.getCompressorCurrentTooHighFault();
    }
    public boolean getConnectionFault() {
    	return compressor.getCompressorNotConnectedFault();
    }
    public boolean getShortFault() {
    	return compressor.getCompressorShortedFault();
    }
    public boolean getPressureSwitch() {
    	return compressor.getPressureSwitchValue();
    }
    
/*
* Operation Functions
*/
    
    public void startCompressor() {
    	compressor.start();
    }
    public void stopCompressor() {
    	compressor.stop();
    }

    
    public void moveClimberArm(boolean op) {
    	s1.set(op);
    }
    
    public void moveGrabber(boolean op) {
    	s0.set(op);
    }
    
    public void raiseBoom() {
    	s23.set(DoubleSolenoid.Value.kForward);
    }
    
    public void lowerBoom() {
    	s23.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void kOff() {
    	s23.set(DoubleSolenoid.Value.kOff);
    }
    
    public boolean getSolenoidState(int solenoid) {
    	boolean currentState = false;
    	switch (solenoid) {
    		case 0: currentState = s0.get();
    			break;
    		case 1: currentState = s1.get();
    			break;
    	}
    	return currentState;	
    }
    
    public boolean getDouble() {
    	boolean s23State = false;
    	switch (s23.get()) {
    		case kForward: s23State = true;
    			break;
    		case kReverse: s23State = false;
    			break;
    		case kOff: 
    			break;
    	}
    	return s23State;
    	}
}
