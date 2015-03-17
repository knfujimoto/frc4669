package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain1 extends Subsystem {
    CANTalon leftMotor, rightMotor;
    RobotDrive drivetrain;
    
    public DriveTrain1() {
    	leftMotor = new CANTalon(4);
    	rightMotor = new CANTalon(1);
    	leftMotor.enableControl();
    	rightMotor.enableControl();
    	leftMotor.setPID(1.0, 0.0, 0.0);
    	rightMotor.setPID(1.0, 0.0, 0.0);
    	leftMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	rightMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
    	drivetrain = new RobotDrive(leftMotor, rightMotor);
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void joystickDrive(double left, double right) {
		drivetrain.tankDrive(left, right, true);
	}
	
	public void stickArcadeDrive(Joystick stick) {
		drivetrain.arcadeDrive(stick);
	}
	
	public void joystickDrive(Joystick leftStick, Joystick rightStick) {
		joystickDrive(leftStick.getY(), rightStick.getY());
	}
	
	public void turn90Left() {
		leftMotor.changeControlMode(CANTalon.ControlMode.Position);
		leftMotor.set(leftMotor.getPosition() - 2160);
		rightMotor.changeControlMode(CANTalon.ControlMode.Position);
		rightMotor.set(rightMotor.getPosition() + 2160);
		leftMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		leftMotor.set(0);
		rightMotor.changeControlMode(CANTalon.ControlMode.PercentVbus);
		rightMotor.set(0);
	}
	
	public void stop() {
		joystickDrive(0,0);
	}
	
	public double getLeftPos() {
		return leftMotor.getPosition();
	}
	
	public double getRightPos() {
		return rightMotor.getPosition();
	}
	
	public void setControlType(CANTalon.ControlMode control) {
		leftMotor.changeControlMode(control);
		leftMotor.set(0);
		rightMotor.changeControlMode(control);
		rightMotor.set(0);
	}
	
	public void set(double num) {
		leftMotor.set(leftMotor.getPosition() + num);
		rightMotor.set(rightMotor.getPosition() + num);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new  DriveWithJoysticks());
    }
}

