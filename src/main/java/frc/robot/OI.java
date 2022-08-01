

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.VisionProcessor.*;
import frc.robot.commands.Drivetrain.*;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionProcessor;

public class OI {
    public static PS4Controller driverControl = new PS4Controller(Constants.PS4_CONTROLLER_PORT);
    public static Joystick operatorControl = new Joystick(Constants.BUTTON_BOARD_PORT);
    // need to change variable names
   
    //Button board initialization
    public static JoystickButton BUTTON_1 = new JoystickButton(operatorControl, 1),
    BUTTON_2 = new JoystickButton(operatorControl, 2),
    BUTTON_3 = new JoystickButton(operatorControl, 3),
    BUTTON_4 = new JoystickButton(operatorControl, 4),
    BUTTON_5 = new JoystickButton(operatorControl, 5),
    BUTTON_6 = new JoystickButton(operatorControl, 6),
    BUTTON_7 = new JoystickButton(operatorControl, 7),
    BUTTON_8 = new JoystickButton(operatorControl, 8),
    BUTTON_9 = new JoystickButton(operatorControl, 9),
    BUTTON_10 = new JoystickButton(operatorControl, 10),
    BUTTON_11 = new JoystickButton(operatorControl, 11),
    BUTTON_12 = new JoystickButton(operatorControl, 12),
    BUTTON_13 = new JoystickButton(operatorControl, 13),
    BUTTON_14 = new JoystickButton(operatorControl, 14),
    BUTTON_15 = new JoystickButton(operatorControl, 15),
    BUTTON_16 = new JoystickButton(operatorControl, 16);

    //PS4 controller init
    public static JoystickButton L2 = new JoystickButton(driverControl, Button.kL2.value), 
    R2 = new JoystickButton(driverControl, Button.kR2.value);


    public OI() {
//:)
    }
    
    public static void configureButtonBindings(Drivetrain DRIVETRAIN, Shooter SHOOTER, Climber CLIMBER, Intake INTAKE, VisionProcessor VISIONPROCESSOR) {
        BUTTON_1.whileHeld(new ClimberRetractManual());
        BUTTON_2.whileHeld(new ClimberExtend()); // Needs to change to whenPressed, once done testing
        BUTTON_3.whenPressed(new ClimberPneumaticExtend());
        BUTTON_4.whileHeld(new InternalManipulator2Out());
        BUTTON_5.whileHeld(new ShootBallTarmac());
        BUTTON_6.whileHeld(new DeployIntake());
        BUTTON_7.whenPressed(new HighGear());

        BUTTON_9.whenPressed(new ClimberRetract());
        BUTTON_10.whenPressed(new ClimberPneumaticRetract());


        BUTTON_13.whileHeld(new InternalManipulator2In());
        BUTTON_14.whileHeld(new ShootBallPad());
        BUTTON_15.whileHeld(new InternalManipulator1In());
        BUTTON_16.whenPressed(new LowGear());

        L2.whenPressed(new CenterWithTarget());
        R2.whileHeld(new JoystickDrive());
    }
}
