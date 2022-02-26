package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import frc.robot.Robot;

public class Drivetrain extends SubsystemBase {
    CANSparkMax leftPrimary, leftReplica_1, leftReplica_2;
    CANSparkMax rightPrimary, rightReplica_1, rightReplica_2;
    RelativeEncoder leftEncoder, rightEncoder;
    MotorControllerGroup leftDrive, rightDrive;
    DifferentialDrive drive;
    DoubleSolenoid gearShifter;
    public Drivetrain() {
        leftPrimary = new CANSparkMax(Constants.LEFT_PRIMARY_MOTOR_ID, MotorType.kBrushless);  
        leftReplica_1 = new CANSparkMax(Constants.LEFT_REPLICA_1_MOTOR_ID, MotorType.kBrushless);
        leftReplica_2 = new CANSparkMax(Constants.LEFT_REPLICA_2_MOTOR_ID, MotorType.kBrushless);
        rightPrimary = new CANSparkMax(Constants.RIGHT_PRIMARY_MOTOR_ID, MotorType.kBrushless);
        rightReplica_1 = new CANSparkMax(Constants.RIGHT_REPLICA_1_MOTOR_ID, MotorType.kBrushless);
        rightReplica_2 = new CANSparkMax(Constants.RIGHT_REPLICA_2_MOTOR_ID, MotorType.kBrushless);

        leftEncoder = leftPrimary.getEncoder();
        rightEncoder = rightPrimary.getEncoder(); 
            
        leftDrive = new MotorControllerGroup(leftPrimary, leftReplica_1, leftReplica_2);
        rightDrive = new MotorControllerGroup(rightPrimary, rightReplica_1, rightReplica_2);
        rightDrive.setInverted(true);

        drive = new DifferentialDrive(leftDrive, rightDrive);

        gearShifter = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.LOW_GEAR_PNUEMATIC_ID, Constants.HIGH_GEAR_PNUEMATIC_ID);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        drive.arcadeDrive(moveSpeed, rotateSpeed);
    }
    
    public void curvatureDrive(double moveSpeed, double rotateSpeed, boolean buttonPressed) {
        drive.curvatureDrive(moveSpeed, rotateSpeed, buttonPressed);
    }

    public void gearShifterOff() {
        gearShifter.set(Value.kOff);
    }

    public void shiftHighGear() {
        gearShifter.set(Value.kReverse);
    }

    public void shiftLowGear() {
        gearShifter.set(Value.kForward);
    }

    public double squareCurve(double axis) {
        if(axis<0) {
            return Math.pow(axis, 2);
        }
        else {
            return -Math.pow(axis, 2);
        }
    }

    public void resetEncoder() {
        leftEncoder.setPosition(0.0);
        rightEncoder.setPosition(0.0);
    }

    public void resetGyro() {
        Robot.climber.gyro.reset();
    }
    public void driveStraightEncoder(double power) {
        double error = leftEncoder.getPosition()-rightEncoder.getPosition();
        double turnPower = kP * error;
        drive.arcadeDrive(power, turnPower);
    }

    public void driveStraightGyro(double power) {
        double error = -Robot.climber.gyro.getAngle();
        double turnPower = kP * error;
        drive.arcadeDrive(power, turnPower);
    }
}
