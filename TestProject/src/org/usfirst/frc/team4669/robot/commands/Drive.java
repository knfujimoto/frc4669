package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Move {

	protected double _dist;
	
    public Drive(double dist) {
    	super();
    	_dist = dist;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	super.initialize();
      	Robot.driveTrain.move(_dist);
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
     		SmartDashboard.putNumber("IMU_Yaw", Robot.imu.getYaw());
     	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	super.interrupted();
    }
}
