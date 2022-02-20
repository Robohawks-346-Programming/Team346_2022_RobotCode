package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    // Initialize Variables
    CANSparkMax climber;
    DoubleSolenoid climberPneumatic1;
    DoubleSolenoid climberPneumatic2;
    

    public Climber() {
        climber = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
        climberPneumatic1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_PNUEMATIC_1_OUT, Constants.CLIMBER_PNEUMATIC_1_IN);
        climberPneumatic2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_PNEUMATIC_2_OUT, Constants.CLIMBER_PNUEMATIC_2_IN);
    }

    public void climberExtend(double climberSpeed) {
        climber.set(climberSpeed);
    }

    public void climberRetract(double climberSpeed) {
        climber.set(-climberSpeed);
    }

    public void climberPneumaticOff() {
        climberPneumatic1.set(Value.kOff);
        climberPneumatic2.set(Value.kOff);
    }

    public void climberPneumaticExtend() {
        climberPneumatic1.set(Value.kForward);
        climberPneumatic2.set(Value.kForward);
    }
    
    public void climberPneumaticRetract() {
        climberPneumatic1.set(Value.kReverse);
        climberPneumatic2.set(Value.kReverse);
    }
}
