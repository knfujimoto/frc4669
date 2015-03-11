package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SingleDriveV extends Subsystem {
	public CANTalon drive;
	public PIDParam pidP;
	protected boolean active;
	protected double maxVFactor = 1440 / 100.0; // ticks per second div 1000
	protected long startTime;
	protected int endPos;
	protected boolean isMax = false;
	
	public SingleDriveV(int id, boolean brakeMode, boolean reverse) {
		super();
		drive = new CANTalon(id);
		drive.changeControlMode(CANTalon.ControlMode.Speed);
		drive.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive.enableBrakeMode(brakeMode);
		active = false;
	}

	public void setV(double v) {
		maxVFactor = v / 100.0;
	}
	
	public void execute(long time) {
		if (active) {
			int p = drive.getEncPosition();
			if (!isMax) {
				drive.set(maxVFactor);
				isMax = true;
			}
		}
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			drive.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive.setProfile(1);
		}
		drive.setPosition(0);
		startTime = System.currentTimeMillis();
		active = true;
		endPos = (int) Math.round(ticks);
	}
	
	public boolean isFinished() {
		return  drive.getEncPosition() > endPos;
	}
	
	public void stop() {
		drive.set(0);
		isMax = false;
		active = false;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
