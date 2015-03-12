
package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick.AxisType;
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
	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		oi = new OI();
		SmartDashboard.putData(Scheduler.getInstance());
		SmartDashboard.putString("distance", "6");
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
		SmartDashboard.putString("minV", "50");
		SmartDashboard.putString("acc", "600");
		SmartDashboard.putString("div", "8");
		SmartDashboard.putNumber("Jx", OI.stick.getAxis(AxisType.kX));
		SmartDashboard.putNumber("Jy", OI.stick.getAxis(AxisType.kY));
		SmartDashboard.putNumber("Jth", OI.stick.getAxis(AxisType.kThrottle));
		SmartDashboard.putNumber("Jtw", OI.stick.getAxis(AxisType.kTwist));
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
