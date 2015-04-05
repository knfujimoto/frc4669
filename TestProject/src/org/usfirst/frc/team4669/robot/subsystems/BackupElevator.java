package org.usfirst.frc.team4669.robot.subsystems;

import org.usfirst.frc.team4669.robot.commands.BackElevatorControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BackupElevator extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	CANTalon motor;
	
	public BackupElevator() {
		motor = new CANTalon(2);
		motor.enableControl();
    	motor.setPID(1.0, 0.0, 0.0);
    	motor.changeControlMode(CANTalon.ControlMode.PercentVbus);
	}
	
	public void joystick(Joystick stick) {
		double axis = stick.getY();
		motor.set(axis);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new BackElevatorControl());
    }
}

