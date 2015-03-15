package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lift extends Command {
	private double dist;
    public Lift() {
    	requires(Robot.lift);
    }
    
    public Lift(double distance) {
    	requires(Robot.lift);
    	dist = distance;
    }

    public void moveTo(double pos) {
    	Robot.lift.moveTo(pos);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.pidP = PIDParam.DRIVE_RIGHT;
    	Robot.lift.maxV= 100;
    	Robot.lift.acc= 100;
       	Robot.lift.dec= 100;
       	moveTo(dist);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
     	Robot.lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift.stop();
    }
}
