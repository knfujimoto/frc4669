package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.MoveElevator;
import org.usfirst.frc.team4669.robot.commands.StateChange;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
    CANTalon frontChain, rearChain;
    private static double GP, T1, T2, T3;
    String state;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
     * Constructor for the elevator
     */
	public Elevator () {
    	// Set up the front motor
    	frontChain = new CANTalon(2);
    	frontChain.enableControl();
    	frontChain.changeControlMode(CANTalon.ControlMode.Position);
    	frontChain.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	frontChain.setPID(0.25, 0.0, 0.0);
    	frontChain.setF(1.0);
    	frontChain.enableBrakeMode(true);
    	frontChain.setPosition(0.0);
    	
    	// Set up the rear motor
    	rearChain = new CANTalon(3);
    	rearChain.disableControl();
    	rearChain.changeControlMode(CANTalon.ControlMode.Position);
    	rearChain.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	rearChain.setPID(0.1, 0.0, 0.0);
    	rearChain.setF(1.0);
    	rearChain.enableBrakeMode(true);
    	
    	// Set state
    	
    }
    
    
    
    public void setTop(double num) {
    		frontChain.set(num);
    }
    
    public void setBottom(double num) {
    	rearChain.set(num);
    }
    
    public boolean getRevLimitSwitch() {
    	return frontChain.isRevLimitSwitchClosed();
    }
    
    public double getTop () {
    	return frontChain.getPosition();
    }
    
    public double getBottom () {
    	return rearChain.getPosition();
    }
    
    public void log () {
    	SmartDashboard.putNumber("Top", getTop());
    	SmartDashboard.putNumber("Bottom", getBottom());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new MoveElevator());
    }
}

