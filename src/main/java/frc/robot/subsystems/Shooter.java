package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
public class Shooter extends SubsystemBase{
    CANSparkMax shooter, shooterHood;
    double l; //Distance, ft
    double g = 32.2; // acceleration of gravity, ft/s^2
    double d = 0.333; // diameter of shooter wheel : 4 in, ft
    double v; //Calculated velocity, ft/s
    double R; //Calculated velocity converted, rpm
    double angle; //Initial angle ball leaves shooter, could be constant or variable, in degrees

    public Shooter() {
        shooter = new CANSparkMax(Constants.SHOOTER_MOTOR_ID, MotorType.kBrushless);
        shooterHood = new CANSparkMax(Constants.SHOOTER_HOOD_MOTOR_ID, MotorType.kBrushless);

        R = (Math.sqrt((l*g)/Math.sin(angle)))/(Math.PI*d);
    }

    public void shootBall(double shooterSpeed) {
        shooter.set(shooterSpeed);
    }

    public void shootBallReverse(double shooterSpeed) {
        shooter.set(-shooterSpeed);
    }

}
