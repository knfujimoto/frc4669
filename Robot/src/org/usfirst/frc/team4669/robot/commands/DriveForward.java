package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForward extends Command {

    private double dis;

	public DriveForward(double distance) {
        dis = distance;
    	requires(Robot.drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() { 
    	Robot.drive.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 Robot.drive.joystickDrive(-0.75, -0.75);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.drive.getLeft() >= dis/0.0087 && Robot.drive.getRight() <= -dis/0.0087);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
