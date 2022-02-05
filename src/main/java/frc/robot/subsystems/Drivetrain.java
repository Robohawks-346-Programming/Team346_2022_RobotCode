package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;

public class Drivetrain {
    CANSparkMax leftPrimary, leftReplica_1, leftReplica_2;
    CANSparkMax rightPrimary, rightReplica_1, rightReplica_2;
    MotorControllerGroup leftDrive, rightDrive;
    DifferentialDrive drive;

    public Drivetrain() {
        leftPrimary = new CANSparkMax(Constants.LEFT_PRIMARY_MOTOR_ID, MotorType.kBrushless);  
        leftReplica_1 = new CANSparkMax(Constants.LEFT_REPLICA_1_MOTOR_ID, MotorType.kBrushless);
        leftReplica_2 = new CANSparkMax(Constants.LEFT_REPLICA_2_MOTOR_ID, MotorType.kBrushless);
        rightPrimary = new CANSparkMax(Constants.RIGHT_PRIMARY_MOTOR_ID, MotorType.kBrushless);
        rightReplica_1 = new CANSparkMax(Constants.RIGHT_REPLICA_1_MOTOR_ID, MotorType.kBrushless);
        rightReplica_2 = new CANSparkMax(Constants.RIGHT_REPLICA_2_MOTOR_ID, MotorType.kBrushless);

        leftDrive = new MotorControllerGroup(leftPrimary, leftReplica_1, leftReplica_2);
        rightDrive = new MotorControllerGroup(rightPrimary, rightReplica_1, rightReplica_2);
        rightDrive.setInverted(true);

        drive = new DifferentialDrive(leftDrive, rightDrive);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        drive.arcadeDrive(moveSpeed, rotateSpeed);
    }
    
    public void curvatureDrive(double moveSpeed, double rotateSpeed, boolean buttonPressed) {
        drive.curvatureDrive(moveSpeed, rotateSpeed, buttonPressed);
    }
}
