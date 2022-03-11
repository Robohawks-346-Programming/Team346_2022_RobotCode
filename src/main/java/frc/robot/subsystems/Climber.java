package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADIS16448_IMU;

public class Climber extends SubsystemBase{
    // Initialize Variables
    CANSparkMax climberControl;
    DoubleSolenoid climberSolenoid;
    ADIS16448_IMU gyro;
    SparkMaxLimitSwitch topLimitSwitch, bottomLimitSwitch;
    RelativeEncoder climberEncoder;
    boolean climberExtended = false;

    public Climber() {
        climberControl = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
        climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_OUT_PNUEMATIC_ID, Constants.CLIMBER_IN_PNUEMATIC_ID);

        topLimitSwitch = climberControl.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        bottomLimitSwitch = climberControl.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);

        topLimitSwitch.enableLimitSwitch(true);
        bottomLimitSwitch.enableLimitSwitch(true);

        gyro = new ADIS16448_IMU();
        gyro.reset();

        climberEncoder = climberControl.getEncoder();
    }

    public void climberPneumaticExtend() {
        climberSolenoid.set(Value.kForward);
        climberExtended = true;
    }
    
    public void climberPneumaticRetract() {
        climberSolenoid.set(Value.kReverse);
        climberExtended = false;
    }

    public void climberArmExtend(double climberSpeed){
        double output = climberSpeed;
        if(getBottomLimitPosition()) {
            output = Math.min(-output, 0);
        }
        else if(getForwardLimitPosition()) {
            output = Math.max(output, 0);
        }
        climberControl.set(output);
    }

    // public void climberArmRetract(double climberSpeed){
    //     climberControl.set(-climberSpeed);
    // }
    public void climberArmRetract(double climberSpeed){
        double output = climberSpeed;
        if(getBottomLimitPosition()) {
            output = Math.min(-output, 0);
        }
        else if(getForwardLimitPosition()) {
            output = Math.max(output, 0);
        }
        climberControl.set(-output);
    }
    
    // public boolean atMaxExtension() {
    //     return topLimitSwitch.get();
    // }

    public boolean atMaxExtension() {
        if (!topLimitSwitch.isPressed()) {
            if(climberExtended) {
                return climberEncoder.getPosition() == Constants.CLIMBER_REV_CYL_EXT;
            }
            return climberEncoder.getPosition() == Constants.CLIMBER_REV_CYL_RET;
        }
        return true;
    }

    public boolean isClimberRetracted() {
        return bottomLimitSwitch.isPressed();
    }

    public boolean getBottomLimitPosition() {
        return bottomLimitSwitch.isPressed();
    }

    public boolean getForwardLimitPosition() {
        return topLimitSwitch.isPressed();
    }
}
