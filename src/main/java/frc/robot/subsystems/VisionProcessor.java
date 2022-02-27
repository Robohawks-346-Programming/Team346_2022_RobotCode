package frc.robot.subsystems;

import javax.xml.stream.events.StartDocument;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    private double statPoint = 0;

    private Timer timer = new Timer();

    // use these functions get & set values in the network table, respectively
    private double getNetworkTableEntry(String entry) {
        return limelightTable.getEntry(entry).getDouble(0);
    }
    private void setNetworkTableEntry(String entry, double value){
    limelightTable.getEntry(entry).setNumber(value);
    }
    public void setStartPoint(){
        statPoint = getNetworkTableEntry("tx");
    }
    public void testLimelight() {
        ledOff();
        timer.delay(1);
        ledOn();
        timer.delay(1);
        ledOff();
    }

    public void ledOn() 
    {
        setNetworkTableEntry("ledMode", Constants.VISION_LED_ON);
    }
    public void ledOff() 
    {
        setNetworkTableEntry("ledMode", Constants.VISION_LED_OFF);
    }

    public boolean seesTarget() {
        validTargets = getNetworkTableEntry("tv");
        return(validTargets>=1);
    }

    // pipeline control
    
    // boolean isCentered()



    // opt* double getAngleY() *** This is assuming the Y-axis is up and down

    public double getDistanceToTarget()
    {
        double limelightHeight = Constants.LIMELIGHT_HEIGHT;
        double targetHeight = Constants.LIME_TARGET_HEIGHT;
        double targetAngle = getNetworkTableEntry("ty") + 5; //Needs to add 5 to correct limelight error. Found through testing
        Double offsetAngle = 0.0; //Uses a constant if limelight is mounted on a tilt.
        SmartDashboard.putNumber("Angle Offset: ", offsetAngle);
        SmartDashboard.putNumber("Target Angle: ", targetAngle);
        return((targetHeight-limelightHeight)/(Math.tan(Math.toRadians(offsetAngle+targetAngle)))); 
    }

    public void pushData() {
        SmartDashboard.putNumber("num", (MathFunctions.CurvePath(statPoint, -Math.signum(statPoint)*getNetworkTableEntry("tx")+statPoint,Constants.VISION_TURN, .1, 10, 1.01)));
        SmartDashboard.putNumber("statpoint", statPoint);
        SmartDashboard.putNumber("TX", getNetworkTableEntry("tx"));
    } 
}