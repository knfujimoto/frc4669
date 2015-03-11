package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SingleDrive extends Subsystem {
	public CANTalon drive;
	protected long startTime;
	protected long accTime;
	protected long decTime;
	protected long endTime;
	protected double accDist;
	protected boolean active;
	protected double acc = 1440; // ticks per second div 2000000
	protected double maxV = 1440; // ticks per second div 1000
	protected double accFactor = 1440 / 2000000.0; // ticks per second div 2000000
	protected double maxVFactor = 1440 / 1000.0; // ticks per second div 1000
	public double endPos;
	public PIDParam pidP;
	public double precision = 5;
	
	public SingleDrive(int id, boolean brakeMode, boolean reverse) {
		super();
		drive = new CANTalon(id);
		drive.changeControlMode(CANTalon.ControlMode.Position);
		drive.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive.enableBrakeMode(brakeMode);
		active = false;
	}

	public void execute(long time) {
		if (active) {
			if (time < accTime) {
				drivePos(accFactor * time * time);
			} else if (time > endTime) {
				drivePos(endPos);
				active = false;
			} else if (time >= decTime) {
				time = endTime - time;
				drivePos(endPos- (accFactor * time * time));
			} else {
				time -= accTime;
				drivePos(accDist+ time* maxVFactor);
			}
		}
	}
	
	public void drivePos(double d) {
		drive.set(d);
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			drive.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive.setProfile(1);
		}
		drive.setPosition(0);
		startTime = System.currentTimeMillis();
		double d;
		if (ticks < 0) {
			d = -ticks;
		} else {
			d = ticks;
		}
		double adDist = maxV * maxV / acc;
		if (d < adDist ) {
			long dt = Math.round(Math.sqrt(d  / acc)*1000.0);
			accTime = dt;
			decTime = accTime;
			endTime = decTime + dt;
		} else {
			long dt = Math.round(maxV / acc * 1000.0);
			accTime = dt;
			decTime = Math.round((d - adDist) / maxV * 1000.0) + accTime;
			endTime = dt + decTime;
		}
		if (ticks < 0) {
			 accFactor = -acc / 2000000.0;
			 maxVFactor = -maxV /1000.0;
			 accDist = -adDist / 2.0;
		} else {
			 accFactor = acc / 2000000.0;
			 maxVFactor = maxV /1000.0;
			 accDist = adDist / 2.0;
		}
		endPos = ticks;
		active = true;
		drive.ClearIaccum();
	}
	
	public boolean isFinished() {
		return  Math.abs(drive.getPosition() - endPos) < precision;
	}
	
	public void stop() {
		drive.set(drive.getPosition());
		active = false;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
