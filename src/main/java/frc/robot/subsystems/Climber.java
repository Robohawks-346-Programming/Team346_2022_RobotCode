package frc.robot.subsystems;


import com.revrobotics.AlternateEncoderType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder.Type;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.DigitalInput;

public class Climber extends SubsystemBase{
    // Initialize Variables
    CANSparkMax climberControl1, climberControl2;
    DoubleSolenoid climberSolenoid;
    ADIS16448_IMU gyro;
    DigitalInput forwardLimit, reverseLimit;
    SparkMaxAlternateEncoder.Type encoderType = SparkMaxAlternateEncoder.Type.kQuadrature;
    RelativeEncoder climberEncoder;
    boolean climberExtended = false;

    public Climber() {
        climberControl1 = new CANSparkMax(Constants.CLIMBER_1_MOTOR_ID, MotorType.kBrushed);
        climberControl2 = new CANSparkMax(Constants.CLIMBER_2_MOTOR_ID, MotorType.kBrushed);
        climberControl1.setIdleMode(IdleMode.kCoast);
        climberControl2.setIdleMode(IdleMode.kCoast);
        climberControl1.setInverted(true);
        climberSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.CLIMBER_OUT_PNUEMATIC_ID, Constants.CLIMBER_IN_PNUEMATIC_ID);

        forwardLimit = new DigitalInput(Constants.FORWARD_CLIMB_LIMIT_PORT);
        reverseLimit = new DigitalInput(Constants.REVERSE_CLIMB_LIMIT_PORT);
        
        gyro = new ADIS16448_IMU();
        gyro.reset();

        climberEncoder = climberControl1.getAlternateEncoder(Type.kQuadrature, 8192);
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
        climberControl1.set(climberSpeed);
        climberControl2.set(climberSpeed);
        SmartDashboard.putNumber("Climber rev", climberEncoder.getPosition());
    }

    public void climberArmRetract(double climberSpeed){
        climberControl1.set(-climberSpeed);
        climberControl2.set(-climberSpeed);
    }

    public boolean atMaxExtension() {
        if (!forwardLimit.get()) {
            if(!climberExtended) {
                return climberEncoder.getPosition() >= Constants.CLIMBER_REV_CYL_EXT;
            }
            else {
                return climberEncoder.getPosition() <= Constants.CLIMBER_REV_CYL_RET;
            }
        
        }
        return true;
    }

    public boolean isClimberRetracted() {
        return reverseLimit.get();
    }

    public boolean getReverseLimitPosition() {
        return reverseLimit.get();
    }

    public boolean getForwardLimitPosition() {
        return forwardLimit.get();
    }

    public double getMotorRevolutions() {
        return climberEncoder.getPosition();
    }

    public void resetClimberEncoder() {
        climberEncoder.setPosition(0.0);
    }

    public void setClimberMotorRev() {
        while(climberEncoder.getPosition() <= Constants.CLIMBER_REV_UP) {
            climberControl1.set(Constants.CLIMBER_MOTOR_SPEED);
            climberControl2.set(Constants.CLIMBER_MOTOR_SPEED);
        }
        climberControl1.set(0.0);
        climberControl2.set(0.0);
    }
}
