package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveCheck;
import org.usfirst.frc.team4669.robot.commands.FaceTote;
import org.usfirst.frc.team4669.robot.commands.InchLeft;
import org.usfirst.frc.team4669.robot.commands.InchRight;
import org.usfirst.frc.team4669.robot.commands.LiftCheck;
import org.usfirst.frc.team4669.robot.commands.LiftCheck1;
import org.usfirst.frc.team4669.robot.commands.LowerTote;
import org.usfirst.frc.team4669.robot.commands.LowerTote1;
import org.usfirst.frc.team4669.robot.commands.PickUpRecycleBin;
import org.usfirst.frc.team4669.robot.commands.PickUpTote;
import org.usfirst.frc.team4669.robot.commands.PickUpTote1;
import org.usfirst.frc.team4669.robot.commands.SetYaw;
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
	public static Joystick stick = new Joystick(0);
	public static JoystickButton s1 = new JoystickButton(stick, 1);
	public static JoystickButton s2 = new JoystickButton(stick, 2);
	public static JoystickButton s3 = new JoystickButton(stick, 3);
	public static JoystickButton s4 = new JoystickButton(stick, 4);
	public static JoystickButton s5 = new JoystickButton(stick, 5);
	public static JoystickButton s6 = new JoystickButton(stick, 6);
	public static JoystickButton s7 = new JoystickButton(stick, 7);
	public static JoystickButton s10 = new JoystickButton(stick, 10);
	public static JoystickButton s11 = new JoystickButton(stick, 11);
	
	public static Joystick stickB = new Joystick(1);
	public static JoystickButton sB1 = new JoystickButton(stickB, 1);
	public static JoystickButton sB2 = new JoystickButton(stickB, 2);
	public static JoystickButton sB3 = new JoystickButton(stickB, 3);
	public static JoystickButton sB4 = new JoystickButton(stickB, 4);
	public static JoystickButton sB5 = new JoystickButton(stickB, 5);
	public static JoystickButton sB6 = new JoystickButton(stickB, 6);
	public static JoystickButton sB7 = new JoystickButton(stickB, 7);
	public static JoystickButton sB10 = new JoystickButton(stickB, 10);
	public static JoystickButton sB11 = new JoystickButton(stickB, 11);
	
//	protected  Command cTrig = new Trigger();
//	protected Command cTL45= new Turn(-45);
//	protected Command cTR45= new Turn(45);
//	protected Command cTL90= new Turn(-90);
//	protected Command cTR90= new Turn(90);
//	protected Command turn = new TurnCheck(); 
//	protected Command forward = new DriveCheck();
	protected Command zero = new SetYaw();
	protected Command lift = new LiftCheck(); 
	protected Command lift1 = new LiftCheck1();
	protected Command pickUpTote = new PickUpTote();
	protected Command pickUpTote1 = new PickUpTote1();
	protected Command lowerTote = new LowerTote();
	protected Command lowerTote1 = new LowerTote1();
	protected Command pickUpRecycleBin = new PickUpRecycleBin();
	
	protected Command inchLeft = new InchLeft();
	protected Command inchRight = new InchRight();
	protected Command turnL5 = new Turn(-5);
	protected Command turnR5 = new Turn(5);
	protected Command turnL10 = new Turn(-10);
	protected Command turnR10 = new Turn(10);
	
	protected Command faceTote = new FaceTote(); 

	public OI() {
//		SmartDashboard.putData("Move", forward);
		SmartDashboard.putData("Lift", lift);
		SmartDashboard.putData("Lift1", lift1);
//		SmartDashboard.putData("Turn", turn);
		SmartDashboard.putData("ZeroYaw", zero);
//		s1.whenReleased(cTrig);
		s2.whenReleased(lowerTote);
		s3.whenReleased(pickUpTote);
//		s4.whenReleased(cTL90);
//		s5.whenReleased(cTR90);
		s6.whenReleased(pickUpTote);
		s7.whenReleased(lowerTote);
//		s10.whenReleased(lowerTote);
		s11.whenReleased(pickUpRecycleBin);
		
//		sB4.whenReleased(inchLeft);
//		sB5.whenReleased(inchRight);
//		sB2.whenPressed(turnL5);
//		sB2.whileHeld(turnL10);
//		sB3.whenPressed(turnR5);
//		sB3.whileHeld(turnR10);
		
		sB1.whenReleased(faceTote);
		sB2.whenReleased(lowerTote1);
		sB3.whenReleased(pickUpTote1);
		
		
	}
	
	public Joystick getLeftJoystick() {
		return stick;
	}
	
	public Joystick getRightJoystick() {
		return stickB;
	}

}

