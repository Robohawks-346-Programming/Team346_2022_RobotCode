// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Drivetrain motor IDs
    public static final int LEFT_PRIMARY_MOTOR_ID               = 0;
    public static final int LEFT_REPLICA_1_MOTOR_ID             = 0;
    public static final int LEFT_REPLICA_2_MOTOR_ID             = 0;
    public static final int RIGHT_PRIMARY_MOTOR_ID              = 0;
    public static final int RIGHT_REPLICA_1_MOTOR_ID            = 0;
    public static final int RIGHT_REPLICA_2_MOTOR_ID            = 0;

    //Drivetrain pnuematic channels
    public static final int HIGH_GEAR_PNUEMATIC_ID              = 0;
    public static final int LOW_GEAR_PNUEMATIC_ID               = 0;

    //Intake motor ID
    public static final int INTAKE_MOTOR_ID                     = 0;

    //Intake motor speed
    public static final double INTAKE_MOTOR_SPEED               = 0;

    //Intake pneumatic channels
    public static final int INTAKE_IN_PNUEMATIC_ID              = 0;
    public static final int INTAKE_OUT_PNUEMATIC_ID             = 0;

    //Climber motor ID
    public static final int CLIMBER_MOTOR_ID                    = 0;

    //Climber pneumatic channels
    public static final int CLIMBER_PNEUMATIC_1_IN              = 0;
    public static final int CLIMBER_PNUEMATIC_1_OUT             = 0;
    public static final int CLIMBER_PNUEMATIC_2_IN              = 0; 
    public static final int CLIMBER_PNEUMATIC_2_OUT             = 0;

    //Climber motor speed
    public static final double CLIMBER_MOTOR_SPEED              = 0;

    //Shooter Motor ID
    public static final double climberSpeed                     =0.5;

    //Max Swing Acceleration for the Climber
    public static final double accelMax                         =0;

    //Shooter motor ID
    public static final int SHOOTER_MOTOR_ID                    = 0;

    //Shooter motor speed
    public static final double SHOOTER_MOTOR_SPEED              = 0;

    //Limelight variables
    public static final double TARGET_HEIGHT                    = 0;
    public static final double SHOOTER_HEIGHT                   = 0;
    public static final double LIMELIGHT_HEIGHT                 = 0;
    public static final int LIME_TARGET_HEIGHT                  = 0;
    public static final int VISION_LED_ON                       = 0;
    public static final int VISION_LED_OFF                      = 1;
    public static final int VISION_TURN                         = 0;

    //Sensor ports
    public static final int LASER_BREAK_PORT                    = 0;
    public static final int TOP_LIMIT_SWITCH_PORT               = 0;
    public static final int BOTTOM_LIMIT_SWITCH_PORT            = 0;
}
