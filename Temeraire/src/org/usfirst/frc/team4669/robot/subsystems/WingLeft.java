package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WingLeft extends Subsystem {
    CANTalon motor;
    
    public WingLeft() {
    	motor = new CANTalon(2);
    	motor.enableControl();
    	motor.changeControlMode(CANTalon.ControlMode.Position);
    	motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	motor.setPID(0.1, 0.0001, 0.0);
    	motor.enableBrakeMode(true);
    	motor.setForwardSoftLimit(13824);
    	motor.enableForwardSoftLimit(true);
    	motor.setReverseSoftLimit(0);
    	motor.enableReverseSoftLimit(true);
    }
    	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void setPoint(double setPoint) {
    	motor.set(setPoint);
    }
    
    public void reset() {
    	motor.setPosition(0);
    	motor.set(0);
    }
    
    public double getPosition() {
    	return motor.getPosition();
    }
    
    public void log() {
    	SmartDashboard.putNumber("Left Wing Height", motor.getPosition());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

