package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.Robot;
import org.usfirst.frc.team4669.robot.subsystems.sensor.InfraredTOF;

import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class I2CSensors extends Subsystem {
    public InfraredTOF tofRight;
    public InfraredTOF tofLeft;
    
    public I2CSensors() {
		super();
		tofRight = new InfraredTOF(Port.kOnboard);
		if (Robot.hasNavX) {
			tofLeft = new InfraredTOF(Port.kMXP);
		}
	}
    
    public boolean setup() {
    	try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	boolean r = tofRight.setup();
    	if (Robot.hasNavX) {
    		if (r) {
    			r =  tofLeft.setup();
    		}
    	}
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	return r;
    }
    
    protected int[] r2 = new int[2];
    
    public int[] getDistance() {
    	tofRight.startMeasure();
    	if (Robot.hasNavX) {
    		tofLeft.startMeasure();
    	}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	r2[0] = tofRight.readMeasure();
    	if (Robot.hasNavX) {
    		r2[1] = tofLeft.readMeasure();
    	} else {
    		r2[1] = -1;
    	}
    	return r2;
    }

	public void initDefaultCommand() {
    }
}

