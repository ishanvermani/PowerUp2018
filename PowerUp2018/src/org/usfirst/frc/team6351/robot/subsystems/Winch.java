package org.usfirst.frc.team6351.robot.subsystems;

import org.usfirst.frc.team6351.robot.commands.UpperArm;

public class Winch extends subsystem {
  
// Put methods for controlling this subsystem
// here. Call these from Commands.
  Spark winchMotor = new Spark(0);
  
  public void oneDirection() {
    winchMotor.set(0.6);
  }
  public void otherDirection() {
    winchMotor.set(-0.6);
  }
  public void stop() {
    winchMotor.set(0);
  }
}
