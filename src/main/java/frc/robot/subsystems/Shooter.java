package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
public class Shooter {
     CANSparkMax shooter;

     public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
     }

<<<<<<< Updated upstream
     public void shooterBall(double shooterSpeed) {
         shooter.set(shooterSpeed);
     }
=======
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
    }

    public void shootBall(double shooterMotorSpeed) {
        shooter.set(shooterMotorSpeed);
    }

    public void shootBallReverse(double shooterSpeed) {
        shooter.set(-shooterSpeed);
    }

    public double getShooterVelocity(double distance, int angle) {
         return Math.sqrt((distance * gravity)/Math.sin(angle))/(Math.PI*diameter);
    }
    
    public void setShooterVelocity(double distance, int angle) {
        setPoint = getShooterVelocity(distance, angle);
        shooterPIDController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
    }
>>>>>>> Stashed changes

}
