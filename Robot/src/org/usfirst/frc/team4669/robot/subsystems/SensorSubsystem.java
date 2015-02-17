package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SensorSubsystem extends Subsystem {
	Ultrasonic usSensor1;
    Ultrasonic usSensor2;
    AnalogInput aiSensor1;
    AnalogInput aiSensor2;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    /**
     * Creates the four sensors
     */
    public SensorSubsystem() {
    	usSensor1 = new Ultrasonic(0,1);
        usSensor2 = new Ultrasonic(2,3);
        aiSensor1 = new AnalogInput(0);
        aiSensor2 = new AnalogInput(1);
    }
    
    /**
     * Used for putting data on the liveWindow
     */
    public void log() {
    	usSensor1.setEnabled(true);
        usSensor1.startLiveWindowMode();
        usSensor1.updateTable();
        usSensor1.ping();
        if (usSensor1.isRangeValid()) {
        	usSensor1.getRangeInches();	
        }
        
        usSensor2.startLiveWindowMode();
        usSensor2.updateTable();
        usSensor2.ping();
        if (usSensor2.isRangeValid()) {
        	usSensor2.getRangeInches();	
        }
        
        aiSensor1.startLiveWindowMode();
        aiSensor1.updateTable();
        aiSensor2.startLiveWindowMode();
        aiSensor2.updateTable();
    }
    
    /**
     * 
     * @return Distance in inches
     */
    public double getUSensor1() {
    	usSensor1.ping();
    	if (usSensor1.isRangeValid()) {
    		return usSensor1.getRangeInches();
    	}
    	else return -1;
    }
    
    /**
     * 
     * @return Distance in inches
     */
    public double getUSensor2() {
    	usSensor2.ping();
    	if (usSensor2.isRangeValid()) {
    		return usSensor2.getRangeInches();
    	}
    	else return -1;
    }
    
    /**
     * 
     * @return 0V - 5V; farther to closer
     */
    public double getAnalog1() {
    	return aiSensor1.getVoltage();
    }
    
    /**
     * 
     * @return 0V - 5V; farther to closer
     */
    public double getAnalog2() {
    	return aiSensor2.getVoltage();
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

