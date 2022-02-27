package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Shooter extends SubsystemBase{
     CANSparkMax shooter, shooterHood;

     public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
        shooterHood = new CANSparkMax(Constants.SHOOTER_HOOD_MOTOR_ID, MotorType.kBrushless);
     }

     public void shootBall(double shooterSpeed) {
         shooter.set(shooterSpeed);
     }

     public void shootBallReverse(double shooterSpeed) {
         shooter.set(-shooterSpeed);
     }

}
