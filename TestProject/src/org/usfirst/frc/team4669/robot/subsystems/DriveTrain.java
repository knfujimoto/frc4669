package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.data.DriveV;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends DriveV {
	public static double WHEEL_DIA = 4;
	public static double ENC_COUNT = 360;
	public static double WHEELBASE= 22;

	public DriveTrain() {
		super(4, false, 1, false);
	}

	public void execute() {
		long time = System.currentTimeMillis();
		super.execute(time);
		SmartDashboard.putNumber("rightPos", drive.getPosition());
		SmartDashboard.putNumber("leftPos", drive1.getPosition());
		SmartDashboard.putNumber("rightThrot", drive.getOutputVoltage());
		SmartDashboard.putNumber("leftThrot", drive1.getOutputVoltage());
		SmartDashboard.putNumber("rightErr", drive.getClosedLoopError());
		SmartDashboard.putNumber("leftErr", drive1.getClosedLoopError());
	}
	
	public void move(double inches) {
		double d = inches / WHEEL_DIA / Math.PI * 360*4;
		isTurn = false;
		super.move(d);
		SmartDashboard.putNumber("end", d);
	}
	
	public void turn(double angle) {
		isTurn = true;
		double d = WHEELBASE * Math.PI * angle / 360.0 / WHEEL_DIA / Math.PI * 360*4;
		super.move(d);
		SmartDashboard.putNumber("end", d);
	}
	
}

