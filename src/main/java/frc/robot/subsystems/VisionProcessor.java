package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;

public class VisionProcessor implements Subsystem{
    
    boolean isCentered = false;
    boolean led = false;
    boolean isAtY = false;
    boolean seesTarget;
    double tv;
    double xAngle;
    double yAngle;
    double rotate = 0.0;
    double move = 0.0;

    NetworkTableInstance limeLightInstance = NetworkTableInstance.getDefault();
    NetworkTable limeLightTable = limeLightInstance.getTable("/limelight");


    // use these functions get & set values in the network table, respectively
    public double getNTInfo(String tableInfo) {
        NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);
        return limeLightEntry.getDouble(0);
    }
    
    public void setNTInfo(String tableInfo, int setValue){
        NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);
        limeLightEntry.setNumber(setValue);
    }

    public void setHubPipeline() {
        setNTInfo("Hub", 0);
    }

    public void setBlueBallPipeline() {
        setNTInfo("BlueBall", 1);
    }

    public void setRedBallPipeline() {
        setNTInfo("RedBall", 2);
    }

    public void toggleLEDMode() {
        led = !led;
        if (led)
            setNTInfo("ledMode", Constants.VISION_LED_ON);
        else
            setNTInfo("ledMode", Constants.VISION_LED_OFF);
    }

    public boolean seesTarget() {
        tv = getNTInfo("tv");
        if(tv != 0.0)
            seesTarget = true;
        else
            seesTarget = false;
        return seesTarget;
    }

    public boolean isCentered() {
        xAngle = getNTInfo("tx");
        if (Math.abs(xAngle) <= Constants.X_THRESHOLD) 
            isCentered = true;
        else   
            isCentered = false;
        return isCentered;
    }

    public double getRotate() {
        rotate = 0.0;
        if (seesTarget()) 
            if(!isCentered())
                if (xAngle > 0)
                    rotate = Constants.VISION_TURN;
                else
                    rotate = -Constants.VISION_TURN;
        return rotate;
    }

    public boolean isAtY() {
        yAngle = getNTInfo("ty");
        if (Math.abs(yAngle)<= Constants.Y_THRESHOLD)
            isAtY = true;
        else
            isAtY = false;
        return isAtY;
    }

    public double getMove() {
        move = 0.0;
        if (seesTarget())
            if (!isAtY())
                if (yAngle > Constants.Y_THRESHOLD)
                    move = Constants.VISION_MOVE;
        return move;
    }
   
    // pipeline control
    // opt* double getAngleY() *** This is assuming the Y-axis is up and down

    public double getDistanceToTarget()
    {
        double limelightHeight = Constants.LIMELIGHT_HEIGHT;
        double targetHeight = Constants.LIME_TARGET_HEIGHT;
        double targetAngle = getNTInfo("ty") + 5; //Needs to add 5 to correct limelight error. Found through testing
        Double offsetAngle = 0.0; //Uses a constant if limelight is mounted on a tilt.
        SmartDashboard.putNumber("Angle Offset: ", offsetAngle);
        SmartDashboard.putNumber("Target Angle: ", targetAngle);
        return((targetHeight-limelightHeight)/(Math.tan(Math.toRadians(offsetAngle+targetAngle)))); 
    }

    public void pushData() {
        SmartDashboard.putBoolean("isCentered", isCentered());
        SmartDashboard.putNumber("X Displacement", xAngle);
        SmartDashboard.putBoolean("Has Target", seesTarget());
        SmartDashboard.putNumber("TV", tv);
    } 
}