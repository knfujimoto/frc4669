package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.CameraRun;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Point;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    Image frame;
    int session;
    
//    Point start1 = new Point(0, 10);
//    Point end1 = new Point(120, 10);
//    Point start2 = new Point(300, 320);
//    Point end2 = new Point(380, 320);
//    Point start3 = new Point(400, 400);
//    Point end3 = new Point(450, 400);
//    Point start4 = new Point(435, 425);
//    Point end4 = new Point(470, 425);
    
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
         
//         NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, start1, end1, 255);
//         NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, start2, end2, 255);
//         NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, start3, end3, 255);
//         NIVision.imaqDrawLineOnImage(frame, frame, DrawMode.DRAW_VALUE, start4, end4, 255);
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

