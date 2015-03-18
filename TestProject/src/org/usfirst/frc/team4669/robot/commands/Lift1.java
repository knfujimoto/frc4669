package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Lift1 extends Command {
	
	private double dist= 0;
	
	public Lift1() {
    	requires(Robot.lift1);
    }
    
    public Lift1(double distance) {
    	requires(Robot.lift1);
    	dist = distance;
    }

    public void moveTo(double pos) {
    	Robot.lift1.moveTo(pos);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift1.pidP = PIDParam.DRIVE_RIGHT;
    	Robot.lift1.maxV= 100;
    	Robot.lift1.acc= 100;
       	Robot.lift1.dec= 100;
       	moveTo(dist);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift1.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.lift1.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
     	Robot.lift1.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.lift1.stop();
    }
}
