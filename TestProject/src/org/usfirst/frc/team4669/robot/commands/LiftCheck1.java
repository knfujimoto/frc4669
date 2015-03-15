package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftCheck1 extends Command {
	protected PIDParam lift =  new PIDParam(1, .003, 3, 300, 0, 48);

    public LiftCheck1() {
    	requires(Robot.lift1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lift.P =  Double.parseDouble(SmartDashboard.getString("p2"));
    	lift.I =  Double.parseDouble(SmartDashboard.getString("i2"));
    	lift.D =  Double.parseDouble(SmartDashboard.getString("d2"));
    	lift.IZone =  Integer.parseInt(SmartDashboard.getString("izone2"));
    	lift.F =  Double.parseDouble(SmartDashboard.getString("f2"));
    	lift.Ramp =  Double.parseDouble(SmartDashboard.getString("ramp2"));
    	Robot.lift1.pidP = lift;
    	Robot.lift1.maxV= (Double.parseDouble(SmartDashboard.getString("maxVL")));
    	Robot.lift1.acc= (Double.parseDouble(SmartDashboard.getString("accL")));
    	Robot.lift1.dec= (Double.parseDouble(SmartDashboard.getString("decL")));
    	double dist = Double.parseDouble(SmartDashboard.getString("distance1"));
      	Robot.lift1.moveTo(dist);
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
