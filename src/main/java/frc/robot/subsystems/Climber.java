package frc.robot.subsystems;

import java.util.TooManyListenersException;

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
import edu.wpi.first.wpilibj.DigitalInput;

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
        climberControl.set(climberSpeed);
    }

    public void climberArmRetract(double climberSpeed){
        climberControl.set(-climberSpeed);
    }

    public void climberStageOne(double climberSpeed){
        climberArmExtend(climberSpeed);
        climberArmRetract(climberSpeed); //need to add timing for everything
        System.out.println("STAGE ONE ENGAGED: \n");
    }

    public void climberStageTwo(double climberSpeed){
        climberPneumaticExtend();
        climberArmExtend(climberSpeed);
        climberArmRetract(climberSpeed);
        climberPneumaticRetract();
        System.out.println("STAGE TWO ENGAGED: \n");
    }

    public void climberStageThree(double climberSpeed,double accelMax){
        if(gyro.getAccelX()<accelMax){
            climberStageTwo(climberSpeed);
            System.out.println("STAGE Three ENGAGED: \n");
        }
    }

    public void fullClimb(double climberSpeed, double accelMax){
        climberStageOne(climberSpeed);
        climberStageTwo(climberSpeed);
        climberStageThree(climberSpeed, accelMax);
        System.out.println("CLIMB SENERIO 427 ENGAGED: \n");
    }

    // public boolean atMaxExtension() {
    //     return topLimitSwitch.get();
    // }

    public boolean atMaxExtension() {
        if (!topLimitSwitch.isPressed()) {
            if(climberExtended) {
                System.out.println("Fluffy pants");
                return climberEncoder.getPosition() == Constants.CLIMBER_REV_CYL_EXT;
            }
            return climberEncoder.getPosition() == Constants.CLIMBER_REV_CYL_RET;
        }
        return true;
    }

    public boolean isClimberRetracted() {
        return bottomLimitSwitch.isPressed();
    }

    public void getPressed() {
        System.out.println(bottomLimitSwitch.isPressed());
    }
}
