package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.data.PIDParam;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftCheck extends Command {
	protected PIDParam lift =  new PIDParam(1, .003, 3, 300, 0, 48);
<<<<<<< HEAD
	protected double dist = 0;

    public LiftCheck() {
    	requires(Robot.lift);
    }
    
    public LiftCheck(double position) {
    	requires(Robot.lift);
    	dist = position;
=======

    public LiftCheck() {
    	requires(Robot.driveTrain);
>>>>>>> a05075499f31a565ee8e1202916579876edeca52
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lift.P =  Double.parseDouble(SmartDashboard.getString("p2"));
    	lift.I =  Double.parseDouble(SmartDashboard.getString("i2"));
    	lift.D =  Double.parseDouble(SmartDashboard.getString("d2"));
    	lift.IZone =  Integer.parseInt(SmartDashboard.getString("izone2"));
    	lift.F =  Double.parseDouble(SmartDashboard.getString("f2"));
    	lift.Ramp =  Double.parseDouble(SmartDashboard.getString("ramp2"));
    	Robot.lift.pidP = lift;
    	Robot.lift.maxV= (Double.parseDouble(SmartDashboard.getString("maxVL")));
    	Robot.lift.acc= (Double.parseDouble(SmartDashboard.getString("accL")));
    	Robot.lift.acc= (Double.parseDouble(SmartDashboard.getString("decL")));
<<<<<<< HEAD
    	if (dist == 0) dist = Double.parseDouble(SmartDashboard.getString("distance"));
=======
    	double dist = Double.parseDouble(SmartDashboard.getString("distance"));
>>>>>>> a05075499f31a565ee8e1202916579876edeca52
      	Robot.lift.moveTo(dist);
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
