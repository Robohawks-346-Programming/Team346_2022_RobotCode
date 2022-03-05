package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;
import frc.robot.Robot;

public class Drivetrain extends SubsystemBase {
    CANSparkMax leftPrimary, leftReplica ;
    CANSparkMax rightPrimary, rightReplica;
    RelativeEncoder leftEncoder, rightEncoder;
    MotorControllerGroup leftDrive, rightDrive;
    DifferentialDrive drive;
    DoubleSolenoid gearShifter;

    double revPerInch;
    double gearRatio;

    public Drivetrain() {
        leftPrimary = new CANSparkMax(Constants.LEFT_PRIMARY_MOTOR_ID, MotorType.kBrushless);  
        leftReplica = new CANSparkMax(Constants.LEFT_REPLICA_MOTOR_ID, MotorType.kBrushless);
        rightPrimary = new CANSparkMax(Constants.RIGHT_PRIMARY_MOTOR_ID, MotorType.kBrushless);
        rightReplica = new CANSparkMax(Constants.RIGHT_REPLICA_MOTOR_ID, MotorType.kBrushless);

        leftEncoder = leftPrimary.getEncoder();
        rightEncoder = rightPrimary.getEncoder(); 
            
        leftDrive = new MotorControllerGroup(leftPrimary, leftReplica);
        rightDrive = new MotorControllerGroup(rightPrimary, rightReplica);
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
        gearRatio = Constants.DRIVETRAIN_HIGH_GEAR_RATIO;
        revPerInch = (Math.PI*Constants.DRIVETRAIN_WHEEL_DIAMETER)/gearRatio;
    }

    public void shiftLowGear() {
        gearShifter.set(Value.kForward);
        gearRatio = Constants.DRIVETRAIN_LOW_GEAR_RATIO;
        revPerInch = (Math.PI*Constants.DRIVETRAIN_WHEEL_DIAMETER)/gearRatio;
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
        double turnPower = Constants.DRIVE_P * error;
        drive.arcadeDrive(power, turnPower);
    }

    public void driveStraightGyro(double power) {
        double error = -Robot.climber.gyro.getAngle();
        double turnPower = Constants.DRIVE_P * error;
        drive.arcadeDrive(power, turnPower);
    }

    public double getEncoderDistanceLeft() {
        return leftEncoder.getPosition() * revPerInch;
    }

    public double getEncoderDistanceRight() {
        return rightEncoder.getPosition() * revPerInch;
    }

    public void stop() {
        leftDrive.set(0.0);
        rightDrive.set(0.0);
    }

    public double getAngle() {
        return Robot.climber.gyro.getGyroAngleZ();
    }
}