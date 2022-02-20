package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
    CANSparkMax intake;
    DoubleSolenoid intakeSolenoid;
    DigitalInput laserBreak;
    
    public Intake() {
        intake = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);

        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_OUT_PNUEMATIC_ID, Constants.INTAKE_IN_PNUEMATIC_ID);

        laserBreak = new DigitalInput(Constants.LASER_BREAK_PORT);
    }

    public void intakeBallIn(double intakeSpeed) {
        intake.set(intakeSpeed);
    }

    public void intakeBallOut(double intakeSpeed) {
        intake.set(-intakeSpeed);
    }

    public void intakeOff() {
        intakeSolenoid.set(Value.kOff);
    }
    public void intakeOut() {
        intakeSolenoid.set(Value.kForward);
    }

    public void intakeIn() {
        intakeSolenoid.set(Value.kReverse);
    }

    public boolean returnBreak() {
        return laserBreak.get();
    }

    public void pushData() {
        SmartDashboard.putBoolean("Laser break unobstructed: ", laserBreak.get());
    }
}

