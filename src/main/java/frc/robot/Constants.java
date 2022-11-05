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

    //Motor IDs
    public static final int LEFT_PRIMARY_MOTOR_ID               = 14;
    public static final int LEFT_REPLICA_MOTOR_ID               = 13;
    public static final int RIGHT_PRIMARY_MOTOR_ID              = 10;
    public static final int RIGHT_REPLICA_MOTOR_ID              = 11;

    public static final int INTAKE_MOTOR_ID                     = 30;
    public static final int INTERNAL_MANIPULATOR_1_MOTOR__ID    = 31;
    public static final int INTERNAL_MANIPULATOR_2_MOTOR__ID    = 32;

    public static final int CLIMBER_1_MOTOR_ID                  = 40;
    public static final int CLIMBER_2_MOTOR_ID                  = 41;

    public static final int SHOOTER_MOTOR_ID                    = 20;
    public static final int SHOOTER_HOOD_MOTOR_ID               = 0;
    
    //PCM ID
    public static final int PCM_ID                              = 0;

    //Pnuematic Channels
    public static final int HIGH_GEAR_PNUEMATIC_ID              = 3;
    public static final int LOW_GEAR_PNUEMATIC_ID               = 2;

    public static final int INTAKE_IN_PNUEMATIC_ID              = 1;
    public static final int INTAKE_OUT_PNUEMATIC_ID             = 0;

    public static final int CLIMBER_IN_PNUEMATIC_ID             = 4;
    public static final int CLIMBER_OUT_PNUEMATIC_ID            = 5;

    //Intake motor speed
    public static final double INTAKE_MOTOR_SPEED               = 0.75;
    public static final double INTERNAL_MANIPULATOR_1_MOTOR_SPEED = -0.5;
    public static final double INTERNAL_MANIPULATOR_2_MOTOR_SPEED = 0.5;

    public static final double CLIMBER_EXTEND_MOTOR_SPEED       = -0.75; //0.5
    public static final double CLIMBER_RETRACT_MOTOR_SPEED      = -0.75; //-0.75

    public static final double SHOOTER_TARMAC_MOTOR_SPEED       = -0.75; //-0.75
    public static final double SHOOTER_LOW_GOAL_MOTOR_SPEED     = -0.375; 
    public static final double SHOOTER_MOTOR_SPEED              = -0.25;

    public static final double SHOOTER_PAD_MOTOR_SPEED          = -0.6; // original = 0.78, don't go above 0.88, don't go below 0.58? 
    public static final double SHOOTER_HOOD_MOTOR_SPEED         = 0.25;

    public static final double DRIVETRAIN_MAX_MOVE_SPEED        = 0.8;
    public static final double DRIVETRAIN_MAX_TURN_SPEED        = 0.75;

    //Limelight variables
    public static final double TARGET_HEIGHT                    = 8.667;
    public static final double SHOOTER_HEIGHT                   = 0;
    public static final double LIMELIGHT_HEIGHT                 = 2.333;
    public static final double LIMELIGHT_ANGLE                  = 25.0;
    public static final int VISION_LED_ON                       = 0;
    public static final int VISION_LED_OFF                      = 1;
    public static final double VISION_TURN                      = 0.5;
    public static final int VISION_MOVE                         = 0;
    public static final double X_THRESHOLD                      = 3; // 5
    public static final double Y_THRESHOLD                      = 0;


    //Sensor ports
    public static final int LASER_BREAK_PORT                    = 1;
    public static final int FORWARD_CLIMB_LIMIT_PORT            = 7;
    public static final int REVERSE_CLIMB_LIMIT_PORT            = 6;

    //Driver/Operator ports
    public static final int PS4_CONTROLLER_PORT                 = 0;
    public static final int BUTTON_BOARD_PORT                   = 1;
    // Motor revolutions
    public static final double CLIMBER_REV_CYL_EXT              = 0.7; //146.7
    public static final double CLIMBER_REV_CYL_RET              = 0.1; //133.2
    public static final double CLIMBER_REV_UP                   = 0.5;

    //PIDs
    public static final double DRIVE_P                          = 0;
    public static final double DRIVE_I                          = 0;
    public static final double DRIVE_D                          = 0;
    public static final double DRIVE_IZ                         = 0;
    public static final double DRIVE_FF                         = 0;

    public static final double SHOOTER_P                        = 0;
    public static final double SHOOTER_I                        = 0;
    public static final double SHOOTER_D                        = 0;
    public static final double SHOOTER_IZ                       = 0;
    public static final double SHOOTER_FF                       = 0.1;
    public static final double SHOOTER_MIN_OUTPUT               = 2000;
    public static final double SHOOTER_MAX_OUTPUT               = 4000;

    //Robot parameters
    public static final int DRIVETRAIN_WHEEL_DIAMETER           = 5; //5 inch wheels
    public static final double DRIVETRAIN_LOW_GEAR_RATIO        = 2.083; //24:50
    public static final double DRIVETRAIN_HIGH_GEAR_RATIO       = 1.467; //30:44
    public static final int SHOOTER_WHEEL_DIAMETER              = 4; // 4 inch wheel
}
