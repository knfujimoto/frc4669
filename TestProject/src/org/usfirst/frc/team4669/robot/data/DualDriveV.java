package org.usfirst.frc.team4669.robot.data;

import edu.wpi.first.wpilibj.CANTalon;

public class DualDriveV extends SingleDriveV {
	public CANTalon drive1;
	public PIDParam pidP1;
	protected boolean isMax1 = false;
	public boolean isTurn = false;

	public DualDriveV(int id, boolean brakeMode, boolean reverse, int id1, boolean brakeMode1, boolean reverse1) {
		super(id, brakeMode, reverse);
		drive1 = new CANTalon(id1);
		drive1.changeControlMode(CANTalon.ControlMode.Speed);
		drive1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		drive1.enableBrakeMode(brakeMode);
	}

	public void execute(long time) {
		if (active) {
			int p = drive1.getEncPosition();
			if (!isMax1) {
				drive1.set(maxVFactor);
				isMax1 = true;
			}
			super.execute(time);
		}
	}
	
	public void move(double ticks) {
		if (pidP != null) {
			drive1.setPID(pidP.P, pidP.I, pidP.D, pidP.F, pidP.IZone, pidP.Ramp, 1);
			drive1.setProfile(1);
		}
		drive1.setPosition(0);
		super.move(ticks);
	}
	
	public void stop() {
		drive1.set(0);
		isMax1 = false;
		super.stop();
	}
	public boolean isFinished() {
		return  drive1.getEncPosition() > endPos && super.isFinished();
	}

}
