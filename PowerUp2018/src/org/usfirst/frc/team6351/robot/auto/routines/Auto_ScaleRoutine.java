package org.usfirst.frc.team6351.robot.auto.routines;

import org.usfirst.frc.team6351.robot.Robot;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_ArmUpToScale;
import org.usfirst.frc.team6351.robot.auto.commands.Auto_DriveStraight;
import org.usfirst.frc.team6351.robot.auto.commands.GyroTurnToAngle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_ScaleRoutine extends CommandGroup {

    public Auto_ScaleRoutine() {
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
    	
		addSequential(new Auto_DriveStraight(235));
		addSequential(new GyroTurnToAngle(90));
		addSequential(new Auto_DriveStraight(191));
		addSequential(new GyroTurnToAngle(-90));
		addSequential(new Auto_ArmUpToScale());
		addSequential(new Auto_DriveStraight(64));
    	
//    	if (Robot.fms_gameData.substring(1,2) == "R") {
//    		
//    		addSequential(new Auto_DriveStraight(235));
//    		addSequential(new GyroTurnToAngle(90));
//    		addSequential(new Auto_DriveStraight(191));
//    		addSequential(new GyroTurnToAngle(-90));
//    		addSequential(new Auto_DriveStraight(64));
//    		
//    		//ADD GRABBER CODE
//    		
//    	} else if (Robot.fms_gameData.substring(1,2) == "L") {
//    		
//    		addSequential(new Auto_DriveStraight(304));
//    		addSequential(new GyroTurnToAngle(90));
//    		addSequential(new Auto_DriveStraight(24));
//    		
//    		//ADD GRABBER CODE
//    		
//    	}
    }
}
