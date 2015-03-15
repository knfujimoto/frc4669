package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Turn extends Move {

	protected double _angle;
    public Turn(double angle) {
    	super();
    	_angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
    	turn(_angle);
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
     	if (Robot.imu != null) {
     		SmartDashboard.putNumber(   "IMU_Yaw",              Robot.imu.getYaw());
     	}
   }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    }
}
