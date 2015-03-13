package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator extends Subsystem {
	public CANTalon elevator;
	public PIDParam pidP;
	public double minV = 5; // ticks per 1/100 sec
	public double maxV = 19.4; // ticks per 1/100 sec
	public double acc = 14.4;  //ticks per 1/100 sec per sec;
	public double div = 8;
	protected long startTime;
	protected int endPos;
	protected int idx;
	protected double v;
	protected boolean isPos;
	protected int state;

	protected double minVL = 0;
	protected double maxVL = 0;
	protected double accL = 0;
	public double intervalsPerSec = 50;
	protected double intervalsL = 0;
	protected double divL=0;
	protected double[] velocity = null;
	protected int[] position = null;
	protected int periodMs;
	protected int accTime;
	protected int accDist;
	protected int decDist;
	protected boolean last = false;
	protected int clearCount = 0;
	
	public Elevator(int id, boolean brakeMode) {
		super();
		elevator = new CANTalon(id);
		elevator.changeControlMode(CANTalon.ControlMode.Speed);
		elevator.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		elevator.enableBrakeMode(brakeMode);
		state = 0;
	}

	protected void calcProfile() {
		if (minV != minVL || maxV != maxVL || acc != accL || intervalsPerSec != intervalsL
				|| div != divL) {
			int periods = (int) Math.round((maxV - minV) / acc * intervalsPerSec);
			periodMs = (int) Math.round(1 / intervalsPerSec * 1000.0);
			velocity = new double[periods];
			position = new int[periods];
			double v = minV;
			double p = 0;
			int t = periodMs;
			double dv = acc / intervalsPerSec;
			for (int x = 0; x < periods; ++x) {
				velocity[x] = v;
				v += dv;
				t += periodMs;
				p = acc * t * t  / 20000 + minV * t / 10;
				position[x] = (int) Math.round(p /div);
			}
			minVL = minV;
			maxVL = maxV;
			accL = acc;
			intervalsL = intervalsPerSec;
			divL=div;
		}
	}
	
	public void execute(long time) {
		if (state == 1) { // wait for reset
			if (elevator.getEncPosition() == 0) {
				state = 2;
				startTime = time;
			} else {
				++clearCount;
				if (clearCount >10) {
					elevator.setPosition(0);
					clearCount = 0;
				}
			}
		}
		if (state == 2) { // accelerate
			long t = time - startTime;
			if (t < accTime) {
				int x = (int) ((t - (t % periodMs))/ periodMs);
				if (isPos) {
					v = velocity[x];
					elevator.set(v);
					SmartDashboard.putNumber("rightSet", v);
				} else {
					v = -velocity[x];
					elevator.set(v);
					SmartDashboard.putNumber("rightSet", v);
				}

			} else {
				int p = elevator.getEncPosition();
				SmartDashboard.putNumber("rightDiff", endPos-p);
				if (isPos) {
					if (p < decDist ) {
						if (last) {
							SmartDashboard.putNumber("rightSet", v*1.001);
						} else {
							SmartDashboard.putNumber("rightSet", v);
						}
					} else {
						if (p >= endPos) {
							elevator.set(0);
							SmartDashboard.putNumber("rightSet", 0);
						} else {
							int pd = endPos - p;
							while (position[idx] > pd && idx > 0) {
								--idx;
							}
							v = velocity[idx];
							elevator.set(v);
							SmartDashboard.putNumber("rightSet", v);
						}
					}
				} else {
					if (p > decDist ) {
						if (last) {
							SmartDashboard.putNumber("rightSet", v*1.001);
						} else {
							SmartDashboard.putNumber("rightSet", v);
						}
					} else {
						if (p <= endPos) {
							elevator.set(0);
							SmartDashboard.putNumber("rightSet", 0);
						} else {
							int pd = p - endPos;
							while (position[idx] > pd && idx > 0) {
								--idx;
							}
							v = -velocity[idx];
							elevator.set(v);
							SmartDashboard.putNumber("rightSet", v);
						}
					}
				}
				
			}
		}
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			elevator.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			elevator.setProfile(1);
		}
		elevator.setPosition(0);
		elevator.ClearIaccum();
		
		clearCount = 0;
		if (ticks == 0.0) {
			endPos =0;
			return;
		}
		state =1;

		isPos = endPos > 0;

		calcProfile();
		idx = position.length-1;

		v = 0;

		int d;
		if (ticks > 0) {
			d = (int) Math.round(ticks /2);
		} else {
			d = -(int) Math.round(ticks /2);
		}
		int x = position.length -1;
		while( position[x] > d && x > 0) {
			--x;
		}
		accTime = x * periodMs;
		if (x == 0) {
			decDist = 0;

		} else {
			int ad = position[x];
			if (isPos) {
				decDist = endPos - ad;
			} else {
				decDist = endPos + ad;
			}

		}
	}
	
	public boolean isFinished() {
		if (state == 1) {
			return false;
		}
		if (isPos) {
			if (elevator.getEncPosition() <= endPos) {
				return false;
			}
		} else {
			if (elevator.getEncPosition() >= endPos) {
				return false;
			}
		}

		return  true;
	}
	
	public void stop() {
		elevator.set(0);

		state = 0;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
