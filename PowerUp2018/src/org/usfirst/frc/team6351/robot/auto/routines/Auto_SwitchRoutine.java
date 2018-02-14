package org.usfirst.frc.team6351.robot.auto.routines;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_DriveStraight;
import org.usfirst.frc.team6351.robot.commands.GyroTurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_SwitchRoutine extends CommandGroup {

    public Auto_SwitchRoutine() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	if (Robot.fms_gameData.substring(0,1) == "R") {
    		
    		addSequential(new Auto_DriveStraight(40));
    		addSequential(new GyroTurnToAngle(90));
    		addSequential(new Auto_DriveStraight(34));
    		addSequential(new GyroTurnToAngle(-90));
    		
    		//ADD VISION
    		
    		addSequential(new Auto_DriveStraight(60));
    		
    		//ADD GRABBER CODE
    		
    	} else if (Robot.fms_gameData.substring(0,1) == "L") {
    		
    		addSequential(new Auto_DriveStraight(40));
    		addSequential(new GyroTurnToAngle(-90));
    		addSequential(new Auto_DriveStraight(69));
    		addSequential(new GyroTurnToAngle(90));
    		
    		//ADD VISION
    		
    		addSequential(new Auto_DriveStraight(60));
    		
    		//ADD GRABBER CODE
    	}
    	
    }
}
