package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetYaw extends Command {
    public SetYaw() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {     
    	if (Robot.imu != null) {
        	Robot.imu.zeroYaw();
            SmartDashboard.putNumber(   "IMU_Yaw",              Robot.imu.getYaw());
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
