package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveCheck;
import org.usfirst.frc.team4669.robot.commands.Trigger;
import org.usfirst.frc.team4669.robot.commands.Turn;
import org.usfirst.frc.team4669.robot.commands.TurnCheck;

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
	public static JoystickButton s1 = new JoystickButton(stick, 1);
	public static JoystickButton s3 = new JoystickButton(stick, 3);
	public static JoystickButton s4 = new JoystickButton(stick, 4);
	public static JoystickButton s5 = new JoystickButton(stick, 5);
	public static JoystickButton s6 = new JoystickButton(stick, 6);
	protected  Command cTrig = new Trigger();
	protected Command cTL45= new Turn(-45);
	protected Command cTR45= new Turn(45);
	protected Command cTL90= new Turn(-90);
	protected Command cTR90= new Turn(90);
	protected Command turn = new TurnCheck(); 
	protected Command forward = new DriveCheck();
	
	public OI() {
		SmartDashboard.putData("Move", forward);
		SmartDashboard.putData("Turn", turn);
		s1.whenReleased(cTrig);
		s3.whenReleased(cTL45);
		s4.whenReleased(cTR45);
		s5.whenReleased(cTL90);
		s6.whenReleased(cTR90);
	}

}

