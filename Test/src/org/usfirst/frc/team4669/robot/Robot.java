
package org.usfirst.frc.team4669.robot;


import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the RobotDrive class.
 * The SampleRobot class is the base of a robot application that will automatically call your
 * Autonomous and OperatorControl methods at the right time as controlled by the switches on
 * the driver station or the field controls.
 *
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SampleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
 * don't. Unless you know what you are doing, complex code will be much more difficult under
 * this system. Use IterativeRobot or Command-Based instead if you're new.
 */
public class Robot extends SampleRobot {
    CANTalon motor;
    double kp, ki, kd, height;

    public Robot() {
        motor = new CANTalon(2);
        
        kp = 0.5;
        ki = 0.00000001;
        kd = 1.0;
        height = 0.0;
        
        motor.setPosition(0);
        motor.enableControl();
        motor.enableBrakeMode(true);
        motor.changeControlMode(CANTalon.ControlMode.Position);
        motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
        motor.setPID(kp, ki, kd);
        
        SmartDashboard.putNumber("Proportional", kp);
        SmartDashboard.putNumber("Integral", ki);
        SmartDashboard.putNumber("Derivative", kd);
        SmartDashboard.putNumber("Height", height);
        SmartDashboard.putNumber("EncoderPos", motor.get());
    }

    /**
     * 
     */
    public void autonomous() {
    	while (isAutonomous() && isEnabled()) {
    		
    	}
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        while (isOperatorControl() && isEnabled()) {
        	
        	kp = SmartDashboard.getNumber("Proportional", kp);
            kd = SmartDashboard.getNumber("Derivatvie", kd);
            height = SmartDashboard.getNumber("Height", height);
            
            motor.setPID(kp, ki, kd);
            	for (int i = (int) motor.get(); i < height-100; i+=10) {
            		motor.set(i);
            	}
            
            	for (int i = (int) motor.get(); i > height+100; i-=10) {
            		motor.set(i);
            	}
            
            
            SmartDashboard.putNumber("EncoderPos", motor.get());
        	
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
