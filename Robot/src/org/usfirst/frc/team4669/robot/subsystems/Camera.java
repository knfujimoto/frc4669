package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.CameraRun;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    Image frame;
    int session;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Camera() {
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        

        // the camera name (ex "cam0") can be found through the roborio web interface
        session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
	}
	
	public void start() {
		NIVision.IMAQdxStartAcquisition(session);
	}
	
	public void run() {
		 NIVision.IMAQdxGrab(session, frame, 1);
         
         
         CameraServer.getInstance().setImage(frame);
	}
	
	public void stop () {
		 NIVision.IMAQdxStopAcquisition(session);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new CameraRun());
    }
}

