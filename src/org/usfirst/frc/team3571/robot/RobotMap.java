package org.usfirst.frc.team3571.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	/**
	 * This is everything that is connected to the PWM ports on the RoboRIO
	 */
	static class PWM {
		public static final int leftFrontDriveMotor = 0;
		public static final int leftRearDriveMotor = 1;
		public static final int rightFrontDriveMotor = 2;
		public static final int rightRearDriveMotor = 3;
		//4-9 Free
	}

	/**
	 * This is everything that is connected to the Digital ports on the RoboRIO
	 */
	static class Digital {
		//0-9 Free
	}

	/**
	 * This is everything that is connected to the Relay ports on the RoboRIO
	 */
	static class Relay {
		//0-3 Free
	}

	/**
	 * This is everything that is connected to the Solenoid ports on the RoboRIO
	 */
	static class Solenoid {
		//0-7 Free
	}
		//Driver Station USB

	/**
	 * This is everything that is connected to the USB's on the driver station
	 */
	static class DriverUSB {
		public static final int driverController = 0;
	}
	
	
	
}
