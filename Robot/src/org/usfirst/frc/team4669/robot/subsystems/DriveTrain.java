package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
    CANTalon leftMotor, rightMotor;
    RobotDrive drivetrain;
    /**
     * Creates all the motors for the chassi
     */
    public DriveTrain() {
    	leftMotor = new CANTalon(1);
    	rightMotor = new CANTalon(4);
    	leftMotor.enableControl();
    	rightMotor.enableControl();
    	leftMotor.enableBrakeMode(true);
    	rightMotor.enableBrakeMode(true);
    	leftMotor.setPID(1.0, 0.0, 0.0);
    	rightMotor.setPID(1.0, 0.0, 0.0);
    	leftMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	rightMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	drivetrain = new RobotDrive(leftMotor, rightMotor);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
     * Uses double to set the speed of left and right motors
     * @param left Ranges from -1.0 to 1.0
     * @param right Ranges from -1.0 to 1.0
     */
	public void joystickDrive(double left, double right) {
		drivetrain.tankDrive(-left, -right, true);
	}
	
	/**
	 * Converts joystick axis into double and runs in the double data type joystickDrive
	 * @param leftStick The left joystick to get the y-axis from
	 * @param rightStick The right joystick to get the y-axis from
	 */
	public void joystickDrive(Joystick leftStick, Joystick rightStick) {
		joystickDrive(leftStick.getY(), rightStick.getY());
	}
	
	/**
	 * Stops both of the motors by setting both speeds to 0.0
	 */
	public void stop() {
		joystickDrive(0,0);
	}
	
	/**
	 * Get encoder position of the left motor
	 * @return Encoder position
	 */
	public double getLeftPos() {
		return leftMotor.getPosition();
	}
	
	/**
	 * Get encoder position of the right motor
	 * @return Encoder position
	 */
	public double getRightPos() {
		return rightMotor.getPosition();
	}
	
	/**
	 * Method for changing the control type of the motors
	 * @param control PercentVbus, Follower, Voltage, Position, Speed, Current, Disabled
	 */
	public void setControlType(CANTalon.ControlMode control) {
		leftMotor.changeControlMode(control);
		leftMotor.set(0);
		rightMotor.changeControlMode(control);
		rightMotor.set(0);
	}
	
	/**
	 * Adds the num to the current position of the encoders to move a set amount
	 * @param num the ticks you want it to move
	 */
	public void set(double num) {
		leftMotor.set(leftMotor.getPosition() + num);
		rightMotor.set(rightMotor.getPosition() + num);
	}
	
	public double getLeft () {
    	return leftMotor.getEncPosition();
    }
    
    public double getRight () {
    	return rightMotor.getEncPosition();
    }
    
    public void log () {
    	SmartDashboard.putNumber("Left", getLeft());
    	SmartDashboard.putNumber("Right", getRight());
    }
    
    public void reset() { 
    	leftMotor.setPosition(0);
    	leftMotor.set(0);
    	rightMotor.setPosition(0);
    	rightMotor.set(0);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new  DriveWithJoysticks());
    }
}

