
package org.usfirst.frc.team4669.robot;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;
import org.usfirst.frc.team4669.robot.subsystems.I2CSensors;
import org.usfirst.frc.team4669.robot.subsystems.TestLift;
import org.usfirst.frc.team4669.robot.subsystems.TestLift1;

import com.kauailabs.navx_mxp.AHRS;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static  DriveTrain driveTrain = new DriveTrain();
	public static TestLift lift = new TestLift();
	public static TestLift1 lift1 = new TestLift1();
	public static  I2CSensors sensors = new I2CSensors();
	public static OI oi;

    Command autonomousCommand;
    SerialPort serial_port;
    public static AHRS imu;                   // This class can only be used w/the navX MXP.
    boolean first_iteration;
    public static final boolean hasNavX = false;

	public Robot() {
		super();
		if (hasNavX) {
			try {
				serial_port = new SerialPort(57600, SerialPort.Port.kMXP);
				// You can add a second parameter to modify the
				// update rate (in hz) from. The minimum is 4.
				// The maximum (and the default) is 100 on a nav6, 60 on a navX MXP.
				// If you need to minimize CPU load, you can set it to a
				// lower value, as shown here, depending upon your needs.
				// The recommended maximum update rate is 50Hz
	
				// You can also use the IMUAdvanced class for advanced
				// features on a nav6 or a navX MXP.
	
				// You can also use the AHRS class for advanced features on
				// a navX MXP. This offers superior performance to the
				// IMU Advanced class, and also access to 9-axis headings
				// and magnetic disturbance detection. This class also offers
				// access to altitude/barometric pressure data from a
				// navX MXP Aero.
	
				byte update_rate_hz = 10;
	            imu = new AHRS(serial_port,update_rate_hz);
			} catch (Exception ex) {
				Logger.getLogger(Robot.class.getCanonicalName()).log(Level.SEVERE, ex.getMessage(), ex);
			}
		}
   }

	/**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	if (sensors.setup()) {
        	SmartDashboard.putString("sensorInit", "Ok");
    	} else {
        	SmartDashboard.putString("sensorInit", "Failed");
    	}
		oi = new OI();
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putString("distance", "6");
		SmartDashboard.putString("distance1", "6");
		SmartDashboard.putString("angle", "90");
		SmartDashboard.putString("p", ".8");
		SmartDashboard.putString("i", ".003");
		SmartDashboard.putString("d", "1.5");
		SmartDashboard.putString("izone", "300");
		SmartDashboard.putString("f", "1.0");
		SmartDashboard.putString("ramp", "48");
		SmartDashboard.putString("p1", ".8");
		SmartDashboard.putString("i1", ".003");
		SmartDashboard.putString("d1", "1.5");
		SmartDashboard.putString("izone1", "300");
		SmartDashboard.putString("f1", "1.0");
		SmartDashboard.putString("ramp1", "48");
		SmartDashboard.putString("maxV", "800");
		SmartDashboard.putString("acc", "600");
		SmartDashboard.putString("minV", "50");
		SmartDashboard.putString("div", "8");
		SmartDashboard.putString("maxVL", "30");
		SmartDashboard.putString("accL", "20");
		SmartDashboard.putString("decL", "20");
		SmartDashboard.putString("p2", ".2");
		SmartDashboard.putString("i2", ".0008");
		SmartDashboard.putString("d2", ".00001");
		SmartDashboard.putString("izone2", "1000");
		SmartDashboard.putString("f2", "0");
		SmartDashboard.putString("ramp2", "48");
    }

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
