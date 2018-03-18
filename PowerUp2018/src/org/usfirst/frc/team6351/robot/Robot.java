package org.usfirst.frc.team6351.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmToPosition;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmUpToScale;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_DriveStraight;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_SwitchInception;
import org.usfirst.frc.team6351.robot.auto.commands.GyroTurnToAngle;
import org.usfirst.frc.team6351.robot.auto.routines.Auto_MasterRoutine;
import org.usfirst.frc.team6351.robot.auto.routines.Auto_SwitchRoutine;
import org.usfirst.frc.team6351.robot.commands.GTADrive;
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
	SendableChooser<Command> m_autonomousTestingChooser = new SendableChooser<>();
	SendableChooser<String> m_autonomousRoutine = new SendableChooser<>();
	SendableChooser<String> m_autonomousStartingPosition = new SendableChooser<>();
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
		fms_dataFound = false;
		
//		m_autonomousTestingChooser.addObject("Arm Up To Scale", new Auto_ArmUpToScale());
//		m_autonomousTestingChooser.addObject("Arm Up To Switch", new Auto_ArmToPosition(1750));
//		m_autonomousTestingChooser.addObject("Drive Forward 108 inches", new Auto_DriveStraight(108));
//		m_autonomousTestingChooser.addObject("Switch Auto Routine", new Auto_SwitchRoutine());
//		m_autonomousTestingChooser.addDefault("Turn 90 Degrees", new GyroTurnToAngle(90));
//		SmartDashboard.putData("Auto mode", m_autonomousTestingChooser);
		
		m_autonomousRoutine.addDefault("Switch/Scale", "switchscale");
		m_autonomousRoutine.addObject("Cross Line (MUST BE IN LEFT OR RIGHT DS)", "cross");
		SmartDashboard.putData("Auto Routine", m_autonomousRoutine);
		m_autonomousStartingPosition.addDefault("Left Position", "L");
		m_autonomousStartingPosition.addObject("Middle Position", "M");
		m_autonomousStartingPosition.addObject("Right Position", "R");
		
		SmartDashboard.putData("Starting Postion", m_autonomousStartingPosition);
		
//	    driveMode.addObject("Flight Stick Control", new FlightStickDrive());
	    driveMode.addDefault("Two-Person GTA Control", new GTADrive());
	    SmartDashboard.putData("Drive Control Mode", driveMode);

	    
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
//		pneumatics.start();
		
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
		String routine = m_autonomousRoutine.getSelected();
		String position = m_autonomousStartingPosition.getSelected();
		int retries = 100;
		
		getFMSData();
		
		if (fms_gameData == "NONE") {
			DriverStation.reportError("No FMS Data Retrived During Autonomous Initiliazation: Attempting Looped Search...", false);
			fms_dataFound = false;
		}
		while (fms_gameData == "NONE" && retries > 0) {
			retries--;
			getFMSData();
			DriverStation.reportError("Searching for FMS Data...", false);
			Timer.delay(0.1);
		}
		if (fms_gameData != "NONE") {
			DriverStation.reportWarning("Data Found! Starting Auto", false);
			
			DriverStation.reportWarning("DATA FROM FMS: "+fms_gameData, false);
			DriverStation.reportWarning("DATA FROM SMARTDASHBOARD: "+routine+" and "+position, false);
			
			fms_dataFound = true;
			
			m_autonomousCommand = new Auto_MasterRoutine(fms_gameData.substring(0,1), fms_gameData.substring(1,2), routine, position);
			m_autonomousCommand.start();
			
			
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		getLimeLight();	
		
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("Drive Encoder", sensors.getDriveEncoderDistance());
		SmartDashboard.putNumber("Drive Encoder RAW", sensors.getDriveEncoderRaw());
		SmartDashboard.putNumber("gYRO", sensors.getGyroAngle());
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
		SmartDashboard.putNumber("Arm Encoder", sensors.getArmEndcoderRaw());
		SmartDashboard.putNumber("Drive Encoder", sensors.getDriveEncoderRaw());
		SmartDashboard.putNumber("Stop Switch Voltage", sensors.getStopSwitchVoltage());
		SmartDashboard.putNumber("TIME LEFT IN TELEOP", Timer.getMatchTime());
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
