package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;

public class Intake {
    CANSparkMax intake;
    DoubleSolenoid intakeSolenoid;
    
    public Intake() {
        intake = new CANSparkMax(Constants.INTAKE_MOTOR_ID, MotorType.kBrushless);

        intakeSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.INTAKE_OUT_PNUEMATIC_ID, Constants.INTAKE_IN_PNUEMATIC_ID);
    }

    public void intakeBall(double intakeSpeed) {
        intake.set(intakeSpeed);
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
}

