package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.data.TimePos;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TestLift extends TimePos {
<<<<<<< HEAD
	public static double WHEEL_DIA = 1.75;
	public static double ENC_COUNT = 360;

	public TestLift() {
		super(2, false); // 4
=======
	public static double WHEEL_DIA = 4;
	public static double ENC_COUNT = 360;

	public TestLift() {
		super(4, false); // 4
>>>>>>> a05075499f31a565ee8e1202916579876edeca52
	}

	public void execute() {
		long time = System.currentTimeMillis();
		super.execute(time);
<<<<<<< HEAD
		SmartDashboard.putNumber("liftFrontPos", drive.getPosition());
		SmartDashboard.putNumber("liftFrontThrot", drive.getOutputVoltage());
		SmartDashboard.putNumber("liftFrontErr", drive.getClosedLoopError());
=======
		SmartDashboard.putNumber("rightPos", drive.getPosition());
		SmartDashboard.putNumber("rightThrot", drive.getOutputVoltage());
		SmartDashboard.putNumber("rightErr", drive.getClosedLoopError());
>>>>>>> a05075499f31a565ee8e1202916579876edeca52
	}
	
	public void moveTo(double inches) {
		double d = inches / WHEEL_DIA / Math.PI * 360*4;
		super.moveTo(d);
		SmartDashboard.putNumber("end", d);
	}
	
}

