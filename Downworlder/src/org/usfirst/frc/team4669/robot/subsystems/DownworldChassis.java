package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.JoystickDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DownworldChassis extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController leftMotors, rightMotors;
	private RobotDrive engine;
	private double speedConstantLeft;
	private double speedConstantRight;
	
	public DownworldChassis()
	{
		leftMotors = new Talon(0);
		rightMotors = new Talon(1);
		engine = new RobotDrive(leftMotors, rightMotors);
		speedConstantLeft = 0.7;
		speedConstantRight = 0.7;
	}
	
	public void driveStraight()
	{
		engine.tankDrive(0.5,0.5);
	}
	
	public void turnLeft()
	{
		engine.arcadeDrive(0.0,0.5);
	}
	
	public void joystickDrive(double left, double right)
	{
		engine.tankDrive(left, right);
	}
	
	public void joystickDrive(Joystick left, Joystick right)
	{
		joystickDrive(left.getY() * speedConstantLeft, right.getY() * speedConstantRight);
	}
	
	public void lowerLeftConstant()
	{
		if (speedConstantLeft > 0)
		{
			speedConstantLeft -= 0.1;
		}
	}
	
	public void raiseLeftConstant()
	{
		if (speedConstantLeft < 1)
		{
			speedConstantLeft += 0.1;
		}
	}
	
	public void lowerRightConstant()
	{
		if (speedConstantRight > 0)
		{
			speedConstantRight -= 0.1;
		}
	}
	
	public void raiseRightConstant()
	{
		if (speedConstantRight < 1)
		{
			speedConstantRight += 0.1;
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new JoystickDrive());
		
    }
}

