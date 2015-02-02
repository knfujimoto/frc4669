package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.LookingOut;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class AxisCam extends Subsystem {
    int session;
    Image frame;
    AxisCamera cam;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public AxisCam()
    {
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	cam = new AxisCamera("10.1.91.100");
    }
    
    NIVision.Rect rect = new NIVision.Rect(10, 10, 100, 100);
    public void looking()
    {
    	
    	cam.getImage(frame);
        NIVision.imaqDrawShapeOnImage(frame, frame, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_OVAL, 0.0f);

        CameraServer.getInstance().setImage(frame);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LookingOut(10));
    }
}

