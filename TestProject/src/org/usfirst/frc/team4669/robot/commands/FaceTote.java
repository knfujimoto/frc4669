package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FaceTote extends Command {

    public FaceTote() {
        requires(Robot.driveTrain);
        requires(Robot.sensorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.sensorSubsystem.log();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.sensorSubsystem.log();
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