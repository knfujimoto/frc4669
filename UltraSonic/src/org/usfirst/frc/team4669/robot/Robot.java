
package org.usfirst.frc.team4669.robot;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
    RobotDrive myRobot;
    Joystick stick;
    Ultrasonic usSensor1;
    Ultrasonic usSensor2;
    AnalogInput aiSensor1;
    AnalogInput aiSensor2;
    double distance;

    public Robot() {
        myRobot = new RobotDrive(0, 1);
        myRobot.setExpiration(0.1);
        stick = new Joystick(0);
        usSensor1 = new Ultrasonic(0,1);
        usSensor2 = new Ultrasonic(2,3);
        aiSensor1 = new AnalogInput(0);
        aiSensor2 = new AnalogInput(1);
    }

    /**
     * Drive left & right motors for 2 seconds then stop
     */
    public void autonomous() {
        myRobot.setSafetyEnabled(false);
        myRobot.drive(-0.5, 0.0);	// drive forwards half speed
        Timer.delay(2.0);		//    for 2 seconds
        myRobot.drive(0.0, 0.0);	// stop robot
    }

    /**
     * Runs the motors with arcade steering.
     */
    public void operatorControl() {
        myRobot.setSafetyEnabled(true);
        while (isOperatorControl() && isEnabled()) {
            myRobot.arcadeDrive(stick); // drive with arcade style (use right stick)
            LiveWindow.setEnabled(true);
            
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
            
            Timer.delay(0.005);		// wait for a motor update time
        }
    }

    /**
     * Runs during test mode
     */
    public void test() {
    }
}
