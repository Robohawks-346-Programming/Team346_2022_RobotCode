package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase{
    CANSparkMax shooter, shooterHood;
    SparkMaxPIDController shooterPIDController;
    RelativeEncoder shooterEncoder;
    
    double l; //Distance, ft
    double g = 32.2; // acceleration of gravity, ft/s^2
    double d = 0.333; // diameter of shooter wheel : 4 in, ft
    double v; //Calculated velocity, ft/s
    double R; //Calculated velocity converted, rpm
    double angle; //Initial angle ball leaves shooter, could be constant or variable, in degrees
  
    double kP,kI,kD,kIZ,kFF,kMinOut,kMaxOut;
    double P,I,D,IZ,FF,MinOut,MaxOut;
    double setPoint;
    public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
        shooterHood = new CANSparkMax(Constants.SHOOTER_HOOD_MOTOR_ID, MotorType.kBrushless);

        kP = Constants.SHOOTER_P;
        kI = Constants.SHOOTER_I;
        kD = Constants.SHOOTER_D;
        kIZ = Constants.SHOOTER_IZ;
        kFF = Constants.SHOOTER_FF;
        kMinOut = Constants.SHOOTER_MIN_OUTPUT;
        kMaxOut = Constants.SHOOTER_MAX_OUTPUT;

        R = (Math.sqrt((l*g)/Math.sin(angle)))/(Math.PI*d);

        shooterPIDController = shooter.getPIDController();
        shooterEncoder = shooter.getEncoder();

        shooterPIDController.setP(kP);
        shooterPIDController.setI(kI);
        shooterPIDController.setD(kD);
        shooterPIDController.setIZone(kIZ);
        shooterPIDController.setFF(kFF);
        shooterPIDController.setOutputRange(kMinOut, kMaxOut);

        SmartDashboard.putNumber("Shooter P", kP);
        SmartDashboard.putNumber("Shooter I", kI);
        SmartDashboard.putNumber("Shooter D", kD);
        SmartDashboard.putNumber("Shooter IZ", kIZ);
        SmartDashboard.putNumber("Shooter FF", kFF);
        SmartDashboard.putNumber("Shooter Min Output", kMinOut);
        SmartDashboard.putNumber("Shooter Max Output", kMaxOut);

        P = SmartDashboard.getNumber("Shooter P", 0);
        I = SmartDashboard.getNumber("Shooter I", 0);
        D = SmartDashboard.getNumber("Shooter D", 0);
        IZ = SmartDashboard.getNumber("Shooter IZ", 0);
        FF = SmartDashboard.getNumber("Shooter FF", 0);
        MinOut = SmartDashboard.getNumber("Shooter Min Output", 0);
        MaxOut = SmartDashboard.getNumber("Shooter Max Output", 0);

        if((P != kP)) { shooterPIDController.setP(P); kP = P; }
        if((I != kI)) { shooterPIDController.setI(I); kI = I; }
        if((D != kD)) { shooterPIDController.setD(D); kD = D; }
        if((IZ != kIZ)) { shooterPIDController.setIZone(IZ); kIZ = IZ; }
        if((FF != kFF)) { shooterPIDController.setFF(FF); kFF = FF; }
        if((MaxOut != kMaxOut) || (MinOut != kMinOut)) { 
          shooterPIDController.setOutputRange(MinOut, MaxOut); 
          kMinOut = MinOut; kMaxOut = MaxOut;
        }

        setPoint = R;
        shooterPIDController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
    }

    public void shootBall(double shooterSpeed) {
        shooter.set(shooterSpeed);
    }

    public void shootBallReverse(double shooterSpeed) {
        shooter.set(-shooterSpeed);
    }

}
