package org.usfirst.frc.team3571.robot;

public class Teleop extends OI {
	
	public static void initial(){
		//The code here runs only once when teleop starts
	}
	
	public static void periodic(){
		//The code here runs until the robot is switched from teleop or disabled
		
		drive.arcadeDrive(driver.LeftStick.Y,driver.LeftStick.X);//Example drive
	}
}
