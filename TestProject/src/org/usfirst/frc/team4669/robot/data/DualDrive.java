package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;

public class DualDrive extends SingleDrive {
	public CANTalon drive1;
	public PIDParam pidP1;
	public boolean isTurn = false;
	public double endPos1;

	public DualDrive(int id, boolean brakeMode, boolean reverse, int id1, boolean brakeMode1, boolean reverse1) {
		super(id, brakeMode, reverse);
		drive1 = new CANTalon(id1);
		drive1.changeControlMode(CANTalon.ControlMode.Position);
		drive1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive1.enableBrakeMode(brakeMode);
	}

	public void drivePos(double d) {
		super.drivePos(d);
		if (isTurn) {
			drive1.set(d);
		} else {
			drive1.set(-d);
		}
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			drive1.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive1.setProfile(1);
		}
		drive1.setPosition(0);
		super.move(ticks);
		if (isTurn) {
			endPos1 = ticks;
		} else  {
			endPos1 = -ticks;
		}
		drive1.ClearIaccum();
	}
	
	public void stop() {
		drive1.set(drive1.getPosition());
		super.stop();
	}
	public boolean isFinished() {
		return  Math.abs(drive1.getPosition() - endPos1) < precision && super.isFinished();
	}

}
