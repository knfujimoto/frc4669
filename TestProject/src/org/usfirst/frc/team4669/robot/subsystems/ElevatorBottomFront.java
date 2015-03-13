package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.data.Elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ElevatorBottomFront extends Elevator{

	public ElevatorBottomFront() {
		super(3, false);
	}

	public void execute() {
		long time = System.currentTimeMillis();
		super.execute(time);

	}
	
	public void move() {

		super.move(3456);

	}

	
}

