package org.usfirst.frc.team4669.robot;

import org.usfirst.frc.team4669.robot.commands.DriveForward;
import org.usfirst.frc.team4669.robot.commands.FaceFlat;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

	Joystick leftStick = new Joystick(0);
	Joystick rightStick = new Joystick(1);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public OI() {
	
	// Make all the buttons
		Button  buttonL1  = new JoystickButton(leftStick, 1),
				buttonL2  = new JoystickButton(leftStick, 2),
				buttonL3  = new JoystickButton(leftStick, 3),
				buttonL4  = new JoystickButton(leftStick, 4),
				buttonL5  = new JoystickButton(leftStick, 5),
				buttonL6  = new JoystickButton(leftStick, 6),
				buttonL7  = new JoystickButton(leftStick, 7),
				buttonL8  = new JoystickButton(leftStick, 8),
				buttonL9  = new JoystickButton(leftStick, 9),
				buttonL10 = new JoystickButton(leftStick, 10),
				buttonL11 = new JoystickButton(leftStick, 11),
				buttonR1  = new JoystickButton(rightStick, 1),
				buttonR2  = new JoystickButton(rightStick, 2),
				buttonR3  = new JoystickButton(rightStick, 3),
				buttonR4  = new JoystickButton(rightStick, 4),
				buttonR5  = new JoystickButton(rightStick, 5),
				buttonR6  = new JoystickButton(rightStick, 6),
				buttonR7  = new JoystickButton(rightStick, 7),
				buttonR8  = new JoystickButton(rightStick, 8),
				buttonR9  = new JoystickButton(rightStick, 9),
				buttonR10 = new JoystickButton(rightStick, 10),
				buttonR11 = new JoystickButton(rightStick, 11);
	
	// Assign a command to button
		buttonR1.whenPressed(new DriveForward(10.0));
		buttonL1.whenPressed(new FaceFlat());
		
	}
	
	public Joystick getLeftJoystick() {
		return leftStick;
	}
	
	public Joystick getRightJoystick() {
		return rightStick;
	}
	
	public boolean buttonR3Pressed() {
		return rightStick.getRawButton(3);
	}
	
	public boolean buttonR2Pressed() {
		return rightStick.getRawButton(2);
	}
	
	public boolean buttonL3Pressed() {
		return leftStick.getRawButton(3);
	}
	
	public boolean buttonL2Pressed() {
		return leftStick.getRawButton(2);
	}
}

