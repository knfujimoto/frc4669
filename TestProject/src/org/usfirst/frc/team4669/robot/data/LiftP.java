package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftP extends Subsystem {
	public CANTalon drive;
	public PIDParam pidP;
	public CANTalon drive1;
	public PIDParam pidP1;
	public double minV = 5; // ticks per 1/100 sec
	public double maxV = 19.4; // ticks per 1/100 sec
	public double acc = 14.4;  //ticks per 1/100 sec per sec;
	public double div = 8;
	protected long startTime;
	protected int endPos;
	protected int endPos1;
	protected int idx;
	protected int idx1;
	protected double v;
	protected double v1;
	protected boolean isPositive;
	protected boolean isPos1;
	protected int state;
	protected boolean isTurn =false;
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
	protected int decDist1;
	protected boolean last = false;
	protected int clearCount = 0;
	
	public LiftP(int id, boolean brakeMode, int id1, boolean brakeMode1) {
		super();
		drive = new CANTalon(id);
		drive.changeControlMode(CANTalon.ControlMode.Position);
		drive.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive.enableBrakeMode(brakeMode);
		drive1 = new CANTalon(id1);
		drive1.changeControlMode(CANTalon.ControlMode.Position);
		drive1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive1.enableBrakeMode(brakeMode);
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
			if (drive.getEncPosition() == 0 && drive1.getEncPosition() == 0) {
				state = 2;
				startTime = time;
			} else {
				++clearCount;
				if (clearCount >10) {
					drive.setPosition(0);
					drive1.setPosition(0);
					clearCount = 0;
				}
			}
		}
		if (state == 2) { // accelerate
			long t = time - startTime;
			if (t < accTime) {
				int x = (int) ((t - (t % periodMs))/ periodMs);
				if (isPositive) {
					v = position[x];
					drive.set(v);
					SmartDashboard.putNumber("rightSet", v);
				} else {
					v = -position[x];
					drive.set(v);
					SmartDashboard.putNumber("rightSet", v);
				}
				if (isPos1) {
					v1 = position[x];
					drive1.set(v1);
					SmartDashboard.putNumber("leftSet", v1);
				} else {
					v1 = -position[x];
					drive1.set(v1);
					SmartDashboard.putNumber("leftSet", v1);
				}
			} else {
				int p = drive.getEncPosition();
				SmartDashboard.putNumber("rightDiff", endPos-p);
				if (isPositive) {
					if (p < decDist ) {
						if (last) {
							SmartDashboard.putNumber("rightSet", v*1.001);
						} else {
							SmartDashboard.putNumber("rightSet", v);
						}
					} else {
						if (p >= endPos) {
							drive.set(0);
							SmartDashboard.putNumber("rightSet", 0);
						} else {
							int pd = endPos - p;
							while (position[idx] > pd && idx > 0) {
								--idx;
							}
							v = velocity[idx];
							drive.set(v);
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
							drive.set(0);
							SmartDashboard.putNumber("rightSet", 0);
						} else {
							int pd = p - endPos;
							while (position[idx] > pd && idx > 0) {
								--idx;
							}
							v = -velocity[idx];
							drive.set(v);
							SmartDashboard.putNumber("rightSet", v);
						}
					}
				}
				int p1 = drive1.getEncPosition();
				SmartDashboard.putNumber("leftDiff", endPos1-p1);
				if (isPos1) {
					if (p1 < decDist1 ) {
						if (last) {
							SmartDashboard.putNumber("leftSet", v1* 1.001);
						} else {
							SmartDashboard.putNumber("leftSet", v1);
						}
						last = ! last;
					} else {
						if (p1 >= endPos1) {
							drive1.set(0);
							SmartDashboard.putNumber("leftSet", 0);
						} else {
							int pd = endPos1 - p1;
							while (position[idx1] > pd && idx1 > 0) {
								--idx1;
							}
							v1 = velocity[idx1];
							drive1.set(v1);
							SmartDashboard.putNumber("leftSet", v1);
						}
					}
				} else {
					if (p1 > decDist1 ) {
						if (last) {
							SmartDashboard.putNumber("leftSet", v1* 1.001);
						} else {
							SmartDashboard.putNumber("leftSet", v1);
						}
						last = ! last;
					} else {
						if (p1 <= endPos1) {
							drive1.set(0);
							SmartDashboard.putNumber("leftSet", 0);
						} else {
							int pd = p1 - endPos1;
							while (position[idx1] > pd && idx1 > 0) {
								--idx1;
							}
							v1 = -velocity[idx1];
							drive1.set(v1);
							SmartDashboard.putNumber("leftSet", v1);
						}
					}
				}
			}
		}
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			drive.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive.setProfile(1);
		}
		drive.setPosition(0);
		drive.ClearIaccum();
		if (pidP1 != null) {
			drive1.setPID(pidP1.P, pidP1.I, pidP1.D, pidP1.F, pidP1.IZone, pidP1.Ramp, 1);
			drive1.setProfile(1);
		}
		drive1.setPosition(0);
		drive1.ClearIaccum();
		clearCount = 0;
		if (ticks == 0.0) {
			endPos =0;
			endPos1 =0;
			return;
		}
		state =1;
		endPos1 = (int) Math.round(ticks);
		if (isTurn) {
			endPos = endPos1;
		} else {
			endPos = -endPos1;
		}
		isPositive = endPos > 0;
		isPos1 = endPos1 > 0;
		calcProfile();
		idx = position.length-1;
		idx1 = idx;
		v = 0;
		v1 = v;
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
			decDist1 = 0;
		} else {
			int ad = position[x];
			if (isPositive) {
				decDist = endPos - ad;
			} else {
				decDist = endPos + ad;
			}
			if (isPos1) {
				decDist1 = endPos1 - ad;
			} else {
				decDist1 = endPos1 + ad;
			}
		}
	}
	
	public boolean isFinished() {
		if (state == 1) {
			return false;
		}
		if (isPositive) {
			if (drive.getEncPosition() <= endPos) {
				return false;
			}
		} else {
			if (drive.getEncPosition() >= endPos) {
				return false;
			}
		}
		if (isPos1) {
			if (drive1.getEncPosition() <= endPos1) {
				return false;
			}
		} else {
			if (drive1.getEncPosition() >= endPos1) {
				return false;
			}
		}
		return  true;
	}
	
	public void stop() {
		drive.set(0);
		drive1.set(0);
		state = 0;
	}
	
	@Override
	protected void initDefaultCommand() {
	}

}
