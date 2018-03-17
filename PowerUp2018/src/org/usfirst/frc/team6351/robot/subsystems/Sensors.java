package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;

public class Sensors extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public Gyro gyro;
	public Accelerometer accel;
	public AnalogInput ultrasonic;
	public Encoder driveEncoderLeft;
	public Encoder armEncoder;
	public AnalogInput stopSwitch;
	
	public Sensors() {
		
		gyro = new ADXRS450_Gyro();
		accel = new BuiltInAccelerometer();
		ultrasonic = new AnalogInput(0);
		driveEncoderLeft = new Encoder(0,1,true,Encoder.EncodingType.k4X);
		armEncoder = new Encoder(2,3,true,Encoder.EncodingType.k4X);
		stopSwitch = new AnalogInput(1);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double getGyroAngle() {
    	return gyro.getAngle();
    }
    public double getXAccel() {
    	return accel.getX();
    }
    public double getYAccel() {
    	return accel.getY();
    }
    public double getUltrasonicDistance() {
    	// Convert the volts from the function to millivolts
    	double voltstomilivolts = ultrasonic.getVoltage() * 1000;
    	// Convert to mm using scaling factor ~0.977mV/mm
    	double distance = (voltstomilivolts/(4.592/4700));
    	return distance;
    }
    public double getDriveEncoderDistance() {
    	//Convert counts to centimeters
    	double distanceIN = (driveEncoderLeft.get()) / (19.1667);
    	return distanceIN ;
    }
    public double getDriveEncoderRaw() {
    	return driveEncoderLeft.get();
    }
    public double getArmEndcoderRaw() {
    	//Convert counts to centimeters
    	double raw = armEncoder.get();
    	return raw;
    }
    public double getStopSwitchVoltage() {
    	//Convert counts to centimeters
    	double volts = stopSwitch.getVoltage();
    	return volts;
    }
    
}

