package org.usfirst.frc.team6351.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team6351.robot.commands.GTADrive;
import org.usfirst.frc.team6351.robot.commands.GyroTurnToAngle;
import org.usfirst.frc.team6351.robot.subsystems.DriveTrain;
import org.usfirst.frc.team6351.robot.subsystems.Pneumatics;
import org.usfirst.frc.team6351.robot.subsystems.Sensors;
import org.usfirst.frc.team6351.robot.subsystems.Winch;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

public class Robot extends TimedRobot {
	public static final DriveTrain driveTrain = new DriveTrain();
	public static final Sensors sensors = new Sensors();
	public static final Pneumatics pneumatics = new Pneumatics();
	public static final Winch winch = new Winch();
	public static OI m_oi;
	
	Command m_autonomousCommand;
	SendableChooser<Command> m_autonomousChooser = new SendableChooser<>();
	SendableChooser<Command> driveMode = new SendableChooser<>();

	static NetworkTableInstance networktables = NetworkTableInstance.getDefault();
	public static final NetworkTable limelight = networktables.getTable("limelight");
	public static double targetX;
	public static double targetY;
	public static double targetArea;
	
	public static String fms_gameData;
	boolean fms_dataFound = false;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		m_oi = new OI();
//		m_chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		
		m_autonomousChooser.addDefault("Turn 90 Degrees", new GyroTurnToAngle(90));
		SmartDashboard.putData("Auto mode", m_autonomousChooser);
		
//	    driveMode.addObject("Flight Stick Control", new FlightStickDrive());
	    driveMode.addDefault("Two-Person GTA Control", new GTADrive());
	    SmartDashboard.putData("Drive Control Mode", driveMode);

	    fms_dataFound = false;
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		getFMSData();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_autonomousChooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		getFMSData();
		if (fms_gameData == "NONE") {
			DriverStation.reportError("No FMS Data Retrived During Autonomous Initiliazation: Attempting Looped Search...", false);
			fms_dataFound = false;
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		getLimeLight();	
		if (fms_dataFound == false) {
			getFMSData();
			if (fms_gameData != "NONE") {
				DriverStation.reportWarning("FMS Data Retrived", false);
				fms_dataFound = true;
				Scheduler.getInstance().run();
			}
		} else {
			Scheduler.getInstance().run();
		}
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		getLimeLight();
		DriverStation.reportError("Pressure"+pneumatics.getPressureSwitch(), false);
	}
	
	public void getLimeLight() {
		targetX = limelight.getEntry("tx").getDouble(0);
		targetY = limelight.getEntry("ty").getDouble(0);
		targetArea = limelight.getEntry("ta").getDouble(0);
	}

	public void getFMSData() {
		String fms_rawData;
		fms_rawData = DriverStation.getInstance().getGameSpecificMessage();
		if (fms_rawData.length() == 0) {
			fms_gameData = "NONE";
		} else {
			fms_gameData = fms_rawData;
			fms_dataFound = true;
		}
	}
}
