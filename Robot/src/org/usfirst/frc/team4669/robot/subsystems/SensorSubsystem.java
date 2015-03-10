package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SensorSubsystem extends Subsystem {
	Ultrasonic usSensor1;
    Ultrasonic usSensor2;
    Ultrasonic usSensor3;
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
        usSensor3 = new Ultrasonic(4,5);
        aiSensor1 = new AnalogInput(0);
        aiSensor2 = new AnalogInput(1);
    }
    
    /**
     *
     */
    public void log() {
    	
    	
    	usSensor1.setEnabled(true);
    	usSensor1.setDistanceUnits(Ultrasonic.Unit.kInches);
    	usSensor1.startLiveWindowMode();
    	usSensor1.updateTable();
    	getUSensor1();
    	
        SmartDashboard.putNumber("UltraSonic1", getUSensor1());	
        
        usSensor2.setEnabled(true);
        //SmartDashboard.putNumber("UltraSonic2", getUSensor2());	
        
        usSensor3.setEnabled(true);
//        SmartDashboard.putNumber("UltraSonic3", getUSensor3());
        
       SmartDashboard.putNumber("Analog1", aiSensor1.getVoltage());
       SmartDashboard.putNumber("Analog2", getAnalog2());
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
    	else {
    		try {
				throw new Exception();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return -1;
    	}
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
    
    public double getUSensor3() {
    	usSensor3.ping();
    	if (usSensor3.isRangeValid()) {
    		return usSensor3.getRangeInches();
    	}
    	else return 5;
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

