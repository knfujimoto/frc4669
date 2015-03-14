package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.OI;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Trigger extends Move {

    public Trigger() {
    	super();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
		double tw =  OI.stick.getAxis(AxisType.kTwist);
		double y =  OI.stick.getAxis(AxisType.kY);
		SmartDashboard.putNumber("Jx", OI.stick.getAxis(AxisType.kX));
		SmartDashboard.putNumber("Jy", OI.stick.getAxis(AxisType.kY));
		SmartDashboard.putNumber("Jth", OI.stick.getAxis(AxisType.kThrottle));
		SmartDashboard.putNumber("Jtw", tw);
		double dist = Double.parseDouble(SmartDashboard.getString("distance"));
		drive(dist*-1*y);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	super.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return super.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    	super.end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    }
}
