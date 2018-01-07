package org.usfirst.frc.team6351.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	//Left Motors
    VictorSP frontLeft = new VictorSP(1);
    VictorSP backLeft = new VictorSP(2);
    //Right Motors
    VictorSP frontRight = new VictorSP(3);
    VictorSP backRight = new VictorSP(4);

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GTADrive());
    }
    public void setLeft (double speed) {
    		frontLeft.set(speed);
    		backLeft.set(speed);
    	

    }
    public void setRight (double speed) {
    		frontRight.set(speed);
    		backRight.set(speed);
    	
    }
}

