package org.usfirst.frc1591.Powerup.commands;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1591.Powerup.Robot;
import org.usfirst.frc1591.Powerup.subsystems.Lift;


public class timeDelay extends Command {

	private double m_time;
    
    public timeDelay(double time) {

    	m_time = time;
    
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	setTimeout(m_time);
    }


    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() { 
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	end();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	
    }
}