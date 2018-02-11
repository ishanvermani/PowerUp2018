package org.usfirst.frc.team6351.robot;


import org.usfirst.frc.team6351.robot.commands.GrabberSolenoids; 
import org.usfirst.frc.team6351.robot.commands.LowerArmSolenoids;
import org.usfirst.frc.team6351.robot.commands.ArmUp;
import org.usfirst.frc.team6351.robot.commands.ArmDown;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick driver1 = new Joystick(0);
	public Joystick controller1 = new Joystick(2);
	public Joystick flightstick1 = new Joystick(1);
	
	
	// Logitech Controller for Secondary Driver
	
	public Button controllerA = new JoystickButton(controller1, RobotMap.Controller2_A_Button);
	public Button controllerB = new JoystickButton(controller1, RobotMap.Controller2_B_Button);
	public Button controllerX = new JoystickButton(controller1, RobotMap.Controller2_X_Button);
	public Button controllerY = new JoystickButton(controller1, RobotMap.Controller2_Y_Button);
	
	public Button controllerLeftBumper = new JoystickButton(controller1, RobotMap.Controller1_Left_Bumper);
	public Button controllerRightBumper = new JoystickButton(controller1, RobotMap.Controller1_Right_Bumper);
	
	public Button controllerLeftTrigger = new JoystickButton(controller1, RobotMap.Controller2_Left_Trigger);
	public Button controllerRightTrigger = new JoystickButton(controller1, RobotMap.Controller2_Right_Trigger);

	// Microsoft Controller for Primary Driver
	
	public Button driverA = new JoystickButton(driver1, 1);
	public Button driverB = new JoystickButton(driver1, 2);
	public Button driverX = new JoystickButton(driver1, 3);
	public Button driverY = new JoystickButton(driver1, 4);
		
	public Button driverLeftBumper = new JoystickButton(driver1, RobotMap.Controller1_Left_Bumper);
	public Button driverRightBumper = new JoystickButton(driver1, RobotMap.Controller1_Right_Bumper);
		
	//Logitech Flight Stick
	
	public Button joystick1 = new JoystickButton(flightstick1, RobotMap.Joy_Button_1);
	public Button joystick3 = new JoystickButton(flightstick1, RobotMap.Joy_Button_3);
	public Button joystick7 = new JoystickButton(flightstick1, RobotMap.Joy_Button_7);
	public Button joystick11 = new JoystickButton(flightstick1, RobotMap.Joy_Button_11);
	public Button joystick10 = new JoystickButton(flightstick1, RobotMap.Joy_Button_10);
	public Button joystick12 = new JoystickButton(flightstick1, RobotMap.Joy_Button_12);
	
	public OI() {
		driverA.toggleWhenPressed(new ArmUp());
		driverB.toggleWhenPressed(new ArmDown());
		driverY.whenPressed(new LowerArmSolenoids());
		driverX.whenPressed(new GrabberSolenoids());	
	}
	
	//Method for getting an axis value on the driver joystick
		public double controllerDriverAxisValue (int axis) {
			
			return driver1.getRawAxis(axis);
			
		}
		public double controllerControllerAxisValue (int axis) {
//			if (controller1.getRawAxis(axis) > RobotMap.Deadzone_Minimum)
			return controller1.getRawAxis(axis);
			
		}
		public double joystickAxisValue (int axis) {
			
			return flightstick1.getRawAxis(axis);
			
		}
		public double joystickPOVAngle (int axis) {
			
			return flightstick1.getPOV(); 
			
		}
}

