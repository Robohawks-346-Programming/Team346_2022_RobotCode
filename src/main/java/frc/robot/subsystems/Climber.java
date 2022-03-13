package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
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
        climberControl.setIdleMode(IdleMode.kBrake);
        climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_OUT_PNUEMATIC_ID, Constants.CLIMBER_IN_PNUEMATIC_ID);

        //topLimitSwitch = climberControl.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);
        //bottomLimitSwitch = climberControl.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyClosed);

        //topLimitSwitch.enableLimitSwitch(true);
        //bottomLimitSwitch.enableLimitSwitch(true);

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
        //double output = climberSpeed;
        //if(getReverseLimitPosition()) {
        //    climberControl.set(-output);
        //}
        //else if(getForwardLimitPosition()) {
        //    climberControl.set(output);
        //}
        climberControl.set(climberSpeed);
    }

    // public void climberArmRetract(double climberSpeed){
    //     climberControl.set(-climberSpeed);
    // }\[]

    public void climberArmRetract(double climberSpeed){
        //double output = climberSpeed;
        //SmartDashboard.putNumber("Motor rev", climberEncoder.getPosition());
        //if(getReverseLimitPosition()) {
        //    climberControl.set(-output);
        //}
        //else if(getForwardLimitPosition()) {
        //    climberControl.set(output);
        //}
        climberControl.set(-climberSpeed);
    }
    
    // public boolean atMaxExtension() {
    //     return topLimitSwitch.get();
    // }

    public boolean atMaxExtension() {
        //if (!bottomLimitSwitch.isPressed()) {
            if(climberExtended) {
                return climberEncoder.getPosition() >= Constants.CLIMBER_REV_CYL_EXT;
            }
            else {
                return climberEncoder.getPosition() >= Constants.CLIMBER_REV_CYL_RET;
            }
        }
        //return true;
    //}

    // public boolean isClimberRetracted() {
    //     return topLimitSwitch.isPressed();
    // }

    // public boolean getReverseLimitPosition() {
    //     return topLimitSwitch.isPressed();
    // }

    // public boolean getForwardLimitPosition() {
    //     return bottomLimitSwitch.isPressed();
    // }

    public double getMotorRevolutions() {
        return climberEncoder.getPosition();
    }

    public void resetClimberEncoder() {
        climberEncoder.setPosition(0.0);
    }

    public void setClimberMotorRev() {
        while(climberEncoder.getPosition() <= Constants.CLIMBER_REV_UP) {
            climberControl.set(Constants.CLIMBER_MOTOR_SPEED);
        }
        climberControl.set(0.0);
    }
}
