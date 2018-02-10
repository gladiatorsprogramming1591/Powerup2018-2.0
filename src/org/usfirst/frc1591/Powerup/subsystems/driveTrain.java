// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1591.Powerup.subsystems;

import org.usfirst.frc1591.Powerup.RobotMap;
import org.usfirst.frc1591.Powerup.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.RobotDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class driveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX leftTalon = RobotMap.driveTrainleftTalon;
    private final WPI_TalonSRX rightTalon = RobotMap.driveTrainrightTalon;
    private final RobotDrive robotDrive = RobotMap.driveTrainrobotDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    //variables
    private double powerY;
    private double rampSpeed;
    private long lastTimeDriveTrainUpdated;
    private int numIter;
    
    public driveTrain() {
    	powerY = 0;
    	rampSpeed = 1;
    	lastTimeDriveTrainUpdated = System.currentTimeMillis();
    	numIter = 0;
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new driveManual());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	SmartDashboard.putNumber("PowerY", powerY);
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    //Setting ramp speed
    public void setRampSpeed(double rs) {
    	rampSpeed = rs;
    }
    
    public void drive (double x, double y) {
    	//Ramp based on time
    	long currentTimeMS = System.currentTimeMillis();
    	double deltaTimeSec = (double)(currentTimeMS - lastTimeDriveTrainUpdated) / 1000.0;
    	double ramp = rampSpeed * deltaTimeSec;
        //To create a dead zone in the middle of the joy stick. 
    	if (x < .03 && x > -.03) {
    		x = 0;
    	}
    	if (y < .03 && y > -.03) {
    		y = 0;
    	}
    	
    	//Make the wheel speed up/slow down. Needed on y axis not x.
    	if (powerY < y) {
	    	powerY += ramp;
    	}
    	if (powerY > y) {
    		powerY -= ramp;
    	}
    		
    	
    	robotDrive.arcadeDrive(x, powerY);
    	lastTimeDriveTrainUpdated = currentTimeMS;
    	
    	SmartDashboard.putNumber("Ramp", ramp);
    	SmartDashboard.putNumber("powerY", powerY);
    	
    	if ((numIter++ % 20) == 0) {
    		System.out.println("Ramp " + ramp);
    		System.out.println("powerY" + powerY);
    		System.out.println("deltaTime" + deltaTimeSec);
    	
    	
    	}
    	
    }
    
//    public void driveLowGear(double x, double y) {
//    	robotDrive.arcadeDrive(x / 3, y / 3);
//    }
}
