package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI extends RobotMap {
	public static XboxController driver = new XboxController(DriverUSB.driverController);
	public static RobotDrive drive = new RobotDrive(PWM.leftFrontDriveMotor,PWM.leftRearDriveMotor,PWM.rightFrontDriveMotor,PWM.rightRearDriveMotor);
	//New objects go here
	
	public static void refreshAll(){
		driver.refresh();
		//All refresh calls for the objects above go in here
	}
}

