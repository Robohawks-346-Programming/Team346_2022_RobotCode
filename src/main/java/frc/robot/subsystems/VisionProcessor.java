package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class VisionProcessor implements Subsystem{
    
    private double horizontalAngle;
    private double verticalAngle;
    private double validTargets;
    private double targetArea;
    private boolean leftToggle;

    private NetworkTableInstance limelighTableInstance = NetworkTableInstance.getDefault();
    private NetworkTable limelightTable = limelighTableInstance.getTable("limelight");

    private double shooterOffset = Constants.TARGET_HEIGHT-Constants.SHOOTER_HEIGHT;
    private double limelightOffset = Constants.TARGET_HEIGHT-Constants.LIMELIGHT_HEIGHT;
}
