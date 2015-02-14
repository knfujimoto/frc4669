package org.usfirst.frc.team4669.robot.subsystems;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class AxisCamera extends Subsystem {
    int session;
    Image frame;
    AxisCamera camera;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public AxisCamera(String string) {
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
    	camera = new AxisCamera(string);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

