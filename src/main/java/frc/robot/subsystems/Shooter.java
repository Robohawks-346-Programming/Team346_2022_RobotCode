package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Shooter extends SubsystemBase{
    CANSparkMax shooter;
    SparkMaxPIDController shooterPIDController;
    RelativeEncoder shooterEncoder;
    
    double gravity = 32.2; // acceleration of gravity, ft/s^2
    double diameter = Constants.SHOOTER_WHEEL_DIAMETER/12; // diameter of shooter wheel : conversion from inches to feet
    double angle; //Initial angle ball leaves shooter, could be constant or variable, in degrees
  
    double kP,kI,kD,kIZ,kFF,kMinOut,kMaxOut;
    double P,I,D,IZ,FF,MinOut,MaxOut;
    double setPoint;
    public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);

        kP = Constants.SHOOTER_P;
        kI = Constants.SHOOTER_I;
        kD = Constants.SHOOTER_D;
        kIZ = Constants.SHOOTER_IZ;
        kFF = Constants.SHOOTER_FF;
        kMinOut = Constants.SHOOTER_MIN_OUTPUT;
        kMaxOut = Constants.SHOOTER_MAX_OUTPUT;

        shooterPIDController = shooter.getPIDController();
        shooterEncoder = shooter.getEncoder();

        shooterPIDController.setP(kP);
        shooterPIDController.setI(kI);
        shooterPIDController.setD(kD);
        shooterPIDController.setIZone(kIZ);
        shooterPIDController.setFF(kFF);
        shooterPIDController.setOutputRange(kMinOut, kMaxOut);

    }

    public void shootBall(double shooterSpeed) {
        shooter.set(shooterSpeed);
        SmartDashboard.putNumber("Shooter RPM", shooterEncoder.getVelocity());
    }

    public void shootBallReverse(double shooterSpeed) {
        shooter.set(-shooterSpeed);
    }

    public void setShooterVelocity(double setPoint) {
        boolean shooterVelocity;
        SmartDashboard.putNumber("Shooter Setpoint", setPoint);
        shooterPIDController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
        SmartDashboard.putNumber("Shooter RPM", shooterEncoder.getVelocity());
        if(setPoint+50 <= shooterEncoder.getVelocity() && setPoint-50 >= shooterEncoder.getVelocity()) { //Constant may change 
            shooterVelocity = true;
        }
        else {
            shooterVelocity = false;
        }
        SmartDashboard.putBoolean("Shooter is Good", shooterVelocity); //Tells when ready to shoot
    }

}
