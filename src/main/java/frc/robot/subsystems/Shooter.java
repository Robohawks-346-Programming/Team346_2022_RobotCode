package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
public class Shooter {
     CANSparkMax shooter;

     public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
     }

     public void shootBall(double shooterSpeed) {
         shooter.set(shooterSpeed);
     }

     public void shootBallReverse(double shooterSpeed) {
         shooter.set(-shooterSpeed);
     }

}
