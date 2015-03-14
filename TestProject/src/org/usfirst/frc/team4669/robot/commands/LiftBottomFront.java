package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftBottomFront extends Command {
    public LiftBottomFront() {
    	requires(Robot.elevatorBottomFront);
    }

    public void drive(double distance) {
    	Robot.elevatorBottomFront.move();
    }
    

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevatorBottomFront.pidP = new PIDParam(.8, .003, 1.5, 300, 1.0, 48);
    	Robot.elevatorBottomFront.maxV= 200;
    	Robot.elevatorBottomFront.minV= 50;
    	Robot.elevatorBottomFront.acc= 200;
    	Robot.elevatorBottomFront.div= 8;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorBottomFront.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.elevatorBottomFront.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
     	Robot.elevatorBottomFront.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.elevatorBottomFront.stop();
    }
}
