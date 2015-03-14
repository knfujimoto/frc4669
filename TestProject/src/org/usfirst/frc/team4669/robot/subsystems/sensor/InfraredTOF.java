package org.usfirst.frc.team4669.robot.subsystems.sensor;

import edu.wpi.first.wpilibj.I2C;

public class InfraredTOF extends I2C {
	public static final int TOFADDR = 0x29;
	public static final int  VL6180x_FAILURE_RESET  =-1;

	public static final int  VL6180X_IDENTIFICATION_MODEL_ID            =  0x0000;
	public static final int  VL6180X_IDENTIFICATION_MODEL_REV_MAJOR  =     0x0001; 
	public static final int  VL6180X_IDENTIFICATION_MODEL_REV_MINOR   =    0x0002;
	public static final int  VL6180X_IDENTIFICATION_MODULE_REV_MAJOR  =    0x0003;
	public static final int  VL6180X_IDENTIFICATION_MODULE_REV_MINOR  =    0x0004;
	public static final int  VL6180X_IDENTIFICATION_DATE         =         0x0006; //16bit value
	public static final int  VL6180X_IDENTIFICATION_TIME           =       0x0008; //16bit value

	public static final int  VL6180X_SYSTEM_MODE_GPIO0           =         0x0010;
	public static final int  VL6180X_SYSTEM_MODE_GPIO1             =       0x0011;
	public static final int  VL6180X_SYSTEM_HISTORY_CTRL            =      0x0012;
	public static final int  VL6180X_SYSTEM_INTERRUPT_CONFIG_GPIO   =      0x0014;
	public static final int  VL6180X_SYSTEM_INTERRUPT_CLEAR         =      0x0015;
	public static final int  VL6180X_SYSTEM_FRESH_OUT_OF_RESET   =         0x0016;
	public static final int  VL6180X_SYSTEM_GROUPED_PARAMETER_HOLD   =     0x0017;

	public static final int  VL6180X_SYSRANGE_START              =         0x0018;
	public static final int  VL6180X_SYSRANGE_THRESH_HIGH   =              0x0019;
	public static final int  VL6180X_SYSRANGE_THRESH_LOW     =             0x001A;
	public static final int  VL6180X_SYSRANGE_INTERMEASUREMENT_PERIOD  =   0x001B;
	public static final int  VL6180X_SYSRANGE_MAX_CONVERGENCE_TIME    =    0x001C;
	public static final int  VL6180X_SYSRANGE_CROSSTALK_COMPENSATION_RATE =0x001E;
	public static final int  VL6180X_SYSRANGE_CROSSTALK_VALID_HEIGHT  =    0x0021;
	public static final int  VL6180X_SYSRANGE_EARLY_CONVERGENCE_ESTIMATE = 0x0022;
	public static final int  VL6180X_SYSRANGE_PART_TO_PART_RANGE_OFFSET =  0x0024;
	public static final int  VL6180X_SYSRANGE_RANGE_IGNORE_VALID_HEIGHT =  0x0025;
	public static final int  VL6180X_SYSRANGE_RANGE_IGNORE_THRESHOLD    =  0x0026;
	public static final int  VL6180X_SYSRANGE_MAX_AMBIENT_LEVEL_MULT    =  0x002C;
	public static final int  VL6180X_SYSRANGE_RANGE_CHECK_ENABLES     =    0x002D;
	public static final int  VL6180X_SYSRANGE_VHV_RECALIBRATE          =   0x002E;
	public static final int  VL6180X_SYSRANGE_VHV_REPEAT_RATE        =     0x0031;

	public static final int  VL6180X_SYSALS_START            =             0x0038;
	public static final int  VL6180X_SYSALS_THRESH_HIGH     =              0x003A;
	public static final int  VL6180X_SYSALS_THRESH_LOW       =             0x003C;
	public static final int  VL6180X_SYSALS_INTERMEASUREMENT_PERIOD  =     0x003E;
	public static final int  VL6180X_SYSALS_ANALOGUE_GAIN        =         0x003F;
	public static final int  VL6180X_SYSALS_INTEGRATION_PERIOD     =       0x0040;

	public static final int  VL6180X_RESULT_RANGE_STATUS           =       0x004D;
	public static final int  VL6180X_RESULT_ALS_STATUS           =         0x004E;
	public static final int  VL6180X_RESULT_INTERRUPT_STATUS_GPIO   =      0x004F;
	public static final int  VL6180X_RESULT_ALS_VAL              =         0x0050;
	public static final int  VL6180X_RESULT_HISTORY_BUFFER     =           0x0052; 
	public static final int  VL6180X_RESULT_RANGE_VAL           =          0x0062;
	public static final int  VL6180X_RESULT_RANGE_RAW           =          0x0064;
	public static final int  VL6180X_RESULT_RANGE_RETURN_RATE    =         0x0066;
	public static final int  VL6180X_RESULT_RANGE_REFERENCE_RATE  =        0x0068;
	public static final int  VL6180X_RESULT_RANGE_RETURN_SIGNAL_COUNT =    0x006C;
	public static final int  VL6180X_RESULT_RANGE_REFERENCE_SIGNAL_COUNT = 0x0070;
	public static final int  VL6180X_RESULT_RANGE_RETURN_AMB_COUNT    =    0x0074;
	public static final int  VL6180X_RESULT_RANGE_REFERENCE_AMB_COUNT  =   0x0078;
	public static final int  VL6180X_RESULT_RANGE_RETURN_CONV_TIME    =    0x007C;
	public static final int  VL6180X_RESULT_RANGE_REFERENCE_CONV_TIME =    0x0080;

	public static final int  VL6180X_READOUT_AVERAGING_SAMPLE_PERIOD  =    0x010A;
	public static final int  VL6180X_FIRMWARE_BOOTUP              =        0x0119;
	public static final int  VL6180X_FIRMWARE_RESULT_SCALER    =           0x0120;
	public static final int  VL6180X_I2C_SLAVE_DEVICE_ADDRESS      =       0x0212;
	public static final int  VL6180X_INTERLEAVED_MODE_ENABLE      =        0x02A3;

	public InfraredTOF(Port port) {
		super(port, TOFADDR);
	}

	public boolean setup() {
		if(init() != 0){
		 	return false;
		}
		defautSettings();
		return true;
	}
	
	public int init() {
		int data = getRegister(VL6180X_SYSTEM_FRESH_OUT_OF_RESET);
		if (data != 1)
			return VL6180x_FAILURE_RESET;
		// Required by datasheet
		// http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
		setRegister(0x0207, 0x01);
		setRegister(0x0208, 0x01);
		setRegister(0x0096, 0x00);
		setRegister(0x0097, 0xfd);
		setRegister(0x00e3, 0x00);
		setRegister(0x00e4, 0x04);
		setRegister(0x00e5, 0x02);
		setRegister(0x00e6, 0x01);
		setRegister(0x00e7, 0x03);
		setRegister(0x00f5, 0x02);
		setRegister(0x00d9, 0x05);
		setRegister(0x00db, 0xce);
		setRegister(0x00dc, 0x03);
		setRegister(0x00dd, 0xf8);
		setRegister(0x009f, 0x00);
		setRegister(0x00a3, 0x3c);
		setRegister(0x00b7, 0x00);
		setRegister(0x00bb, 0x3c);
		setRegister(0x00b2, 0x09);
		setRegister(0x00ca, 0x09);
		setRegister(0x0198, 0x01);
		setRegister(0x01b0, 0x17);
		setRegister(0x01ad, 0x00);
		setRegister(0x00ff, 0x05);
		setRegister(0x0100, 0x05);
		setRegister(0x0199, 0x05);
		setRegister(0x01a6, 0x1b);
		setRegister(0x01ac, 0x3e);
		setRegister(0x01a7, 0x1f);
		setRegister(0x0030, 0x00);
		return 0;
	}

	public void defautSettings(){
		//Recommended settings from datasheet
		//http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
		//Enable Interrupts on Conversion Complete (any source)
		setRegister(VL6180X_SYSTEM_INTERRUPT_CONFIG_GPIO, (4 << 3)|(4) ); // Set GPIO1 high when sample complete
		setRegister(VL6180X_SYSTEM_MODE_GPIO1, 0x10); // Set GPIO1 high when sample complete
		setRegister(VL6180X_READOUT_AVERAGING_SAMPLE_PERIOD, 0x30); //Set Avg sample period
		setRegister(VL6180X_SYSALS_ANALOGUE_GAIN, 0x46); // Set the ALS gain
		setRegister(VL6180X_SYSRANGE_VHV_REPEAT_RATE, 0xFF); // Set auto calibration period (Max = 255)/(OFF = 0)
		setRegister(VL6180X_SYSALS_INTEGRATION_PERIOD, 0x63); // Set ALS integration time to 100ms
		setRegister(VL6180X_SYSRANGE_VHV_RECALIBRATE, 0x01); // perform a single temperature calibration
		//Optional settings from datasheet
		//http://www.st.com/st-web-ui/static/active/en/resource/technical/document/application_note/DM00122600.pdf
		setRegister(VL6180X_SYSRANGE_INTERMEASUREMENT_PERIOD, 0x09); // Set default ranging inter-measurement period to 100ms
		setRegister(VL6180X_SYSALS_INTERMEASUREMENT_PERIOD, 0x0A); // Set default ALS inter-measurement period to 100ms
		setRegister(VL6180X_SYSTEM_INTERRUPT_CONFIG_GPIO, 0x24); // Configures interrupt on New Sample Ready threshold event

		//Additional settings defaults from community
		setRegister(VL6180X_SYSRANGE_MAX_CONVERGENCE_TIME, 0x32);
		setRegister(VL6180X_SYSRANGE_RANGE_CHECK_ENABLES, 0x10 | 0x01);
		setRegister16(VL6180X_SYSRANGE_EARLY_CONVERGENCE_ESTIMATE, 0x7B );
		setRegister16(VL6180X_SYSALS_INTEGRATION_PERIOD, 0x64);
		setRegister(VL6180X_READOUT_AVERAGING_SAMPLE_PERIOD,0x30);
		setRegister(VL6180X_SYSALS_ANALOGUE_GAIN,0x40);
		setRegister(VL6180X_FIRMWARE_RESULT_SCALER,0x01);
	}

	public void startMeasure() {
		setRegister(VL6180X_SYSRANGE_START, 0x01); //Start Single shot mode
	}
	
	public int readMeasure() {
		return getRegister(VL6180X_RESULT_RANGE_VAL);
	}
	
	public int getDistance() {
		setRegister(VL6180X_SYSRANGE_START, 0x01); //Start Single shot mode
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return getRegister(VL6180X_RESULT_RANGE_VAL);
	}
	
	protected byte[] s2 = new byte[2];
	protected byte[] s3 = new byte[3];
	protected byte[] s4 = new byte[4];
	protected byte[] r1 = new byte[1];
	protected byte[] r2 = new byte[2];

	public void setRegister(int registerAddress16, int data8) {
		s3[0] = (byte) ((registerAddress16 >> 8) & 0xFF);
		s3[1] = (byte) (registerAddress16 & 0xFF);
		s3[2] = (byte) data8;
		transaction(s3, 3, null, 0);
	}

	public void setRegister16(int registerAddress16, int data16) {
		s4[0] = (byte) ((registerAddress16 >> 8) & 0xFF);
		s4[1] = (byte) (registerAddress16 & 0xFF);
		s4[2] = (byte) ((data16 >> 8) & 0xFF);
		s4[3] = (byte) (data16 & 0xFF);
		transaction(s4, 4, null, 0);
	}

	public int getRegister(int registerAddress16) {		
		s2[0] = (byte) ((registerAddress16 >> 8) & 0xFF);
		s2[1] = (byte) (registerAddress16 & 0xFF);
		transaction(s2, 2, r1, 1);
		int b = r1[0];
		if (b < 0) {
			b += 256;
		}
		return b;
	}

	public int getRegister16(int registerAddress16) {
		s2[0] = (byte) ((registerAddress16 >> 8) & 0xFF);
		s2[1] = (byte) (registerAddress16 & 0xFF);
		transaction(s2, 2, r2, 2);
		int b = r2[0];
		if (b < 0) {
			b += 256;
		}
		b = b * 256;
		b += r2[1];
		if (r2[1] < 0) {
			b += 256;
		} 
		return b;
	}
}
