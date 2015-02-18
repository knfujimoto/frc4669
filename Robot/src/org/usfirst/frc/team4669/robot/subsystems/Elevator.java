package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.StateChange;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    	frontChain.changeControlMode(CANTalon.ControlMode.Position);
    	frontChain.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	frontChain.setPID(0.1, 0.0, 0.0);
    	frontChain.setF(1.0);
    	frontChain.enableBrakeMode(true);
    	
    	// Set up the rear motor
    	rearChain = new CANTalon(1);
    	rearChain.changeControlMode(CANTalon.ControlMode.Position);
    	rearChain.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	rearChain.setPID(0.1, 0.0, 0.0);
    	rearChain.setF(1.0);
    	rearChain.enableBrakeMode(true);
    	
    	// Set state
    	state = "AD";
    }
    
    /**
     * Sets the height of both chains for the elevator
     * @param front Where you want the front chain to go
     * @param rear Where you want the rear chain to go
     */
    public void setChain(double front, double rear) {
    	if (front - rear < 0) {
    	}
    	if (front - rearChain.get() < 0) {
    		rearChain.set(rear);
    		frontChain.set(front);
    	}
    	else {
    		frontChain.set(front);
    		rearChain.set(rear);
    	}
    }
    
    public void stateChangeDown() {
    	if (state.equals("AD")) {
    		
    	}
    	else if (state.equals("LB")) {
    		state = "AD";
    	}
    	else if (state.equals("T1")) {
    		state = "LB";
    	}
    	else if (state.equals("T2")) {
    		state = "T1";
    	}
    	else if (state.equals("T3")) {
    		state = "T2";
    	}
    	updateState();
    }
    
    public void stateChangeUp() {
    	if (state.equals("AD")) {
    		state = "LB";
    	}
    	else if (state.equals("LB")) {
    		state = "T1";
    	}
    	else if (state.equals("T1")) {
    		state = "T2";
    	}
    	else if (state.equals("T2")) {
    		state = "T3";
    	}
    	else if (state.equals("T3")) {
    		
    	}
    	updateState();
    }
    
    private void updateState(){
    	if (state.equals("AD")){ 
    		setChain(0.0,0.0);
    	}
    	else if (state.equals("LB")){ 
    		setChain(0.0,0.0); 
    	}
    	else if (state.equals("T1")) {
    		state = "T2";
    	}
    	else if (state.equals("T2")) {
    		state = "T3";
    	}
    	else if (state.equals("T3")) {
    	}
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new StateChange());
    }
}

