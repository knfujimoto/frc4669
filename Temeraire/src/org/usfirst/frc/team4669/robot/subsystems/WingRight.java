package org.usfirst.frc.team4669.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class WingRight extends Subsystem {
	CANTalon joint;
	
	public WingRight() {
		joint = new CANTalon(3);
		joint.enableControl();
		joint.changeControlMode(CANTalon.ControlMode.Position);
    	joint.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
    	joint.setPID(0.1, 0.000, 0.0);
    	joint.enableBrakeMode(true);
    	joint.setF(1.0);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setPoint(double setPoint) {
    	joint.set(setPoint);
    }
    
    public void reset() {
    	joint.setPosition(0);
    	joint.set(0);
    }
    
    public double getPosition() {
    	return joint.getPosition();
    }
    
    public void log() {
    	SmartDashboard.putNumber("Right Wing Height", joint.getPosition());
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

