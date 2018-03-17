package org.usfirst.frc.team6351.robot.auto.commands;

import org.usfirst.frc.team6351.robot.auto.routines.Auto_SwitchRoutine;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Auto_SwitchInception extends CommandGroup {

    public Auto_SwitchInception() {
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
    	addSequential(new Auto_SwitchRoutine());
    }
}
