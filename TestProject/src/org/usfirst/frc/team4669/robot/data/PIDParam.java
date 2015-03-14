package org.usfirst.frc.team4669.robot.data;

public class PIDParam {
	public double P;
	public double I;
	public double D;
	public int IZone;
	public double F;
	public double Ramp;
	
	public static PIDParam DRIVE_LEFT =  new PIDParam(.8, .003, 1.5, 300, 1.0, 48);
	public static PIDParam DRIVE_RIGHT =  new PIDParam(.8, .003, 1.5, 300, 1.0, 48);
	public static PIDParam LIFT =  new PIDParam(.8, .003, 1.5, 300, 0, 48);

	public PIDParam(double p, double i, double d, int iZone, double f,
			double ramp) {
		super();
		P = p;
		I = i;
		D = d;
		IZone = iZone;
		F = f;
		Ramp = ramp;
	}
	
	
}
