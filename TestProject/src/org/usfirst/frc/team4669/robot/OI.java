package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.Drive;
import org.usfirst.frc.team4669.robot.commands.Trigger;
import org.usfirst.frc.team4669.robot.commands.Turn;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public static 	Joystick stick = new Joystick(0);
	protected  Command cTrig = new Trigger();
	protected Command turn = new Turn(); 
	protected Command forward = new Drive();
	
	public OI() {
		SmartDashboard.putData("Move", forward);
		SmartDashboard.putData("Turn", turn);
		JoystickButton trig = new JoystickButton(stick, 1);
		trig.whenReleased(cTrig);
	}

}

