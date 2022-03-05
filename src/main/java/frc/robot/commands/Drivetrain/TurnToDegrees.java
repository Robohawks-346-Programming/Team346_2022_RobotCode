package frc.robot.commands.Drivetrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
/**
 *
 */
public class TurnToDegrees extends CommandBase {
	
	private double goalDegrees;
    public ADIS16448_IMU gyro;
	/**
	 * Constructor
	 * @param rotationSpeed
	 * @param goalDegrees
	 */
    public TurnToDegrees(double rotationSpeed, double goalDegrees) {
        // Use requires() here to declare subsystem dependencies
        
        this.goalDegrees = goalDegrees;
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	Robot.drivetrain.resetGyro();;
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	if (goalDegrees >= 0) {
    		Robot.drivetrain.arcadeDrive(Constants.DRIVETRAIN_MOTOR_SPEED,  0.5 );
    	} else {
    		Robot.drivetrain.arcadeDrive(Constants.DRIVETRAIN_MOTOR_SPEED, -0.5 );
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if (goalDegrees >= 0) {
            return (gyro.getAngle() >= goalDegrees);
    	} else {
    		return (gyro.getAngle() <= goalDegrees);
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.arcadeDrive(0, 0);;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}