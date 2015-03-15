package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TimePos extends Subsystem {
	public CANTalon drive;
	public PIDParam pidP;
	public double maxV = 19.4; // ticks per 1/100 sec
	public double acc = 14.4;  //ticks per 1/100 sec per sec;
	public double dec = 14.4;  //ticks per 1/100 sec per sec;
	public int precision = 5;
	protected long startTime;
	protected boolean isPos;
	protected boolean last = false;
	protected int clearCount = 0;
	protected int endMax;
	protected int endMin;
	protected int accTime;
	protected int decTime;
	protected int endTime;
	protected double beginPos;
	protected double accPos;
	protected double endPos;
	protected double midV;
	
	
	public TimePos(int id, boolean brakeMode) {
		super();
		drive = new CANTalon(id);
		drive.changeControlMode(CANTalon.ControlMode.Position);
		drive.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive.enableBrakeMode(brakeMode);
		resetPos();
	}

	public void resetPos() {
		drive.setPosition(0);
		drive.set(0);
	}
	
	public void execute(long time) {
		long t = time - startTime;
		if (t < accTime) {
			if (isPos) {
				doPos(.5 * acc * t * t/ 10000.0+ beginPos);
			} else {
				doPos(-.5 * acc * t * t/ 10000.0+ beginPos);
			}
		} else if (t < decTime) {
			double mt = t - accTime;
			if (isPos) {
				doPos(accPos + midV * mt);
			} else {
				doPos(accPos - midV * mt);
			}
		} else if (t < endTime) {
			double et = endTime - t;
			if (isPos) {
				doPos(endPos - (.5 * dec * et * et/ 10000.0));
			} else {
				doPos(endPos + (.5 * dec * et * et/ 10000.0));
			}
		} else {
			doPos(endPos);
		}
	}
	
	protected void doPos(double p) {
		drive.set(p);
	}
	
	public void moveTo(double absPos) {
		if (pidP != null) {
			drive.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive.setProfile(1);
		}
		double curPos = drive.getEncPosition();
		drive.ClearIaccum();
		clearCount = 0;
		endPos = absPos;
		int t  = (int) Math.round(endPos); 
		endMax = t + precision;
		endMin = t - precision;
		midV = maxV /10;
		double dist = endPos -curPos;
		isPos = dist > 0;
		if (dist < 0) {
			dist = -dist;
		}
		double v = Math.sqrt((2 * dist) / (acc + acc * acc / dec) / 100) * acc;
		double a;
		double d;
		double m;
		if (v > maxV) {
			a = maxV / acc;
			d  = maxV / dec;
			m = (dist / 100 - .5 * acc * a * a - .5 * dec * d * d) / maxV;
		} else {
			a = v / acc;
			d = v / dec;
			m = 0;
		}
		beginPos= curPos;
		if (isPos) {
			accPos = .5 * acc * 100 * a * a + curPos;
		} else {
			accPos = -.5 * acc * 100 * a * a + curPos;
		}
		accTime = (int) Math.round(a * 1000);
		decTime = (int) Math.round((a+m) * 1000);
		endTime = (int) Math.round((a+m+d) * 1000);
		startTime = System.currentTimeMillis();
	}
	
	public boolean isFinished() {
		int pos = drive.getEncPosition();
		if (pos <= endMax && pos >= endMin ) {
			return true;
		}
		return  false;
	}
	
	public void stop() {
	//	drive.set(drive.getEncPosition());
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
