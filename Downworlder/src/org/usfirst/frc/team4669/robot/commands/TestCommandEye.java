package org.usfirst.frc.team4669.robot.commands;

import org.usfirst.frc.team4669.robot.Robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TestCommandEye extends Command {
	int session;
	Image frame;

    public TestCommandEye() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.cameye);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Sets the image type to be used
    	frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
    	
    	session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
    	
    	//Say where to pull images
    	NIVision.IMAQdxConfigureGrab(session);
    	
    	//Start pulling images from camera
    	NIVision.IMAQdxStartAcquisition(session);
    	

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Defined the rectangle to manipulate
    	NIVision.Rect rect = new NIVision.Rect(10,10,100,100);
    	//Used to take the image
    	NIVision.IMAQdxGrab(session, frame, 1);
    	
    	//Draw a circle on the image with image dest, image source, rect to use, drawmode, shapemode, float pixelValue
    	NIVision.imaqDrawShapeOnImage(frame, frame, rect, NIVision.DrawMode.DRAW_VALUE, NIVision.ShapeMode.SHAPE_OVAL, 0.0f);
    	
    	//Set the image to the server
    	Robot.cameye.server.setImage(frame);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	NIVision.IMAQdxStopAcquisition(session);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
