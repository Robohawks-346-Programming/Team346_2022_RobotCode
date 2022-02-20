package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.DigitalInput;

public class Climber extends SubsystemBase{
    // Initialize Variables
    CANSparkMax climberControl;
    DoubleSolenoid climberPneumatic1;
    DoubleSolenoid climberPneumatic2;
    ADIS16448_IMU gyro;
    DigitalInput topLimitSwitch, bottomLimitSwitch;

    public Climber() {
        climberControl = new CANSparkMax(Constants.CLIMBER_MOTOR_ID, MotorType.kBrushless);
        climberPneumatic1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_PNUEMATIC_1_OUT, Constants.CLIMBER_PNEUMATIC_1_IN);
        climberPneumatic2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_PNEUMATIC_2_OUT, Constants.CLIMBER_PNUEMATIC_2_IN);

        topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH_PORT);
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

    public void climberArmExtend(double climberSpeed){
        climberControl.set(climberSpeed);
        
    }

    public void climberArmRetract(double climberSpeed){
        climberControl.set(-climberSpeed);
    }

    public void climberStageOne(double climberSpeed){
        climberArmExtend(climberSpeed);
        climberArmRetract(climberSpeed); //need to add timing for everything
    }

    public void climberStageTwo(double climberSpeed){
        climberPneumaticExtend();
        climberArmExtend(climberSpeed);
        climberArmRetract(climberSpeed);
        climberPneumaticRetract();
    }

    public void climberStageThree(double climberSpeed,double accelMax){
        if(gyro.getAccelX()<accelMax){
            climberStageTwo(climberSpeed);
        }
    }

    public void fullClimb(double climberSpeed, double accelMax){
        climberStageOne(climberSpeed);
        climberStageTwo(climberSpeed);
        climberStageThree(climberSpeed, accelMax);
    }

}
