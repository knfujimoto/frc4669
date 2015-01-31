package org.usfirst.frc.team4669.robot.subsystems;



import org.usfirst.frc.team4669.robot.commands.TestCommandEye;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraEye extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CameraServer server;
	
	public CameraEye()
	{
		server = CameraServer.getInstance();
		server.setQuality(100);
		server.startAutomaticCapture();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TestCommandEye());
    }
}

