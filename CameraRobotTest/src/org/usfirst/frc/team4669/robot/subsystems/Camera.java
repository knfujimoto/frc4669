package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.DefaultLook;
import org.usfirst.frc.team4669.robot.commands.DrawCircle;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.AxisCamera;

/**
 *
 */
public class Camera extends Subsystem {
    AxisCamera camera;
    Image frame;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public Camera (String ip) {
    	// Set the image type
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
    	// Set the camera to use
    	camera = new AxisCamera(ip);
    }
    
    public void getImage() {
    	camera.getImage(frame);
    }
    
    public void setImage() {
    	CameraServer.getInstance().setImage(frame);
    }

    public Image getFrame() {
    	return frame;
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DrawCircle());
    }
}

