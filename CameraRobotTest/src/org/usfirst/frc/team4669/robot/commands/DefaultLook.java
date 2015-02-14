package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.subsystems.Camera;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DefaultLook extends Command {
	Camera cam = Robot.camera;
    public DefaultLook() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(cam);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	cam.getImage();
    	cam.setImage();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
