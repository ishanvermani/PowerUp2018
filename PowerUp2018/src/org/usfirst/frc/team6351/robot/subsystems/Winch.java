package org.usfirst.frc.team6351.robot.subsystems;

import org.usfirst.frc.team6351.robot.commands.UpperArm;

public class Winch extends subsystem {
  
  	// Put methods for controlling this subsystem
    // here. Call these from Commands.
  
  public void initDefaultCommand {
    setDefaultCommand(new UpperArm());
  }
}
