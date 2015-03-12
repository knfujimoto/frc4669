package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Move extends Command {
    public Move() {
    	requires(Robot.driveTrain);
    }

    public void drive(double distance) {
    	Robot.driveTrain.move(distance);
    }
    
    public void turn(double angle) {
    	Robot.driveTrain.turn(angle);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.pidP = PIDParam.DRIVE_RIGHT;
    	Robot.driveTrain.pidP1 = PIDParam.DRIVE_LEFT;
    	Robot.driveTrain.maxV= 800;
    	Robot.driveTrain.minV= 50;
    	Robot.driveTrain.acc= 600;
    	Robot.driveTrain.div= 8;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.execute();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
     	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.stop();
    }
}
