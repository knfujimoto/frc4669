package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drive extends Command {
	protected PIDParam right =  new PIDParam(1, .003, 3, 300, 0, 48);
	protected PIDParam left =  new PIDParam(1, .003, 3, 300, 0, 48);

    public Drive() {
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	right.P =  Double.parseDouble(SmartDashboard.getString("p"));
    	right.I =  Double.parseDouble(SmartDashboard.getString("i"));
    	right.D =  Double.parseDouble(SmartDashboard.getString("d"));
    	right.IZone =  Integer.parseInt(SmartDashboard.getString("izone"));
    	right.F =  Double.parseDouble(SmartDashboard.getString("f"));
    	right.Ramp =  Double.parseDouble(SmartDashboard.getString("ramp"));
    	left.P =  Double.parseDouble(SmartDashboard.getString("p1"));
    	left.I =  Double.parseDouble(SmartDashboard.getString("i1"));
    	left.D =  Double.parseDouble(SmartDashboard.getString("d1"));
    	left.IZone =  Integer.parseInt(SmartDashboard.getString("izone1"));
    	left.F =  Double.parseDouble(SmartDashboard.getString("f1"));
    	left.Ramp =  Double.parseDouble(SmartDashboard.getString("ramp1"));
    	Robot.driveTrain.pidP = right;
    	Robot.driveTrain.pidP1 = left;
    	Robot.driveTrain.maxV= (Double.parseDouble(SmartDashboard.getString("maxV")));
    	Robot.driveTrain.minV= (Double.parseDouble(SmartDashboard.getString("minV")));
    	Robot.driveTrain.acc= (Double.parseDouble(SmartDashboard.getString("acc")));
    	double dist = Double.parseDouble(SmartDashboard.getString("distance"));
      	Robot.driveTrain.move(dist);
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
