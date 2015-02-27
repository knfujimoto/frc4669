package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveElevator extends Command {

    public MoveElevator() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
	protected void execute() {
    	if (Robot.oi.buttonR3Pressed()) {
    		Robot.lift.setTop(Robot.lift.getTop() + 3456.0);
    	}
    	if (Robot.oi.buttonR2Pressed()) {
    		Robot.lift.setTop(Robot.lift.getTop() - 1728.0);
    	}
    	if (Robot.oi.buttonL3Pressed()) {
    		Robot.lift.setBottom(Robot.lift.getBottom() + 1728.0);
    	}
    	if (Robot.oi.buttonL2Pressed()) {
    		Robot.lift.setBottom(Robot.lift.getBottom() - 1728.0);
    	}
    	Robot.lift.log();
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
