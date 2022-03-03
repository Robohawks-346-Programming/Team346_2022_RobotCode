package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Climber.*;

public class OI {
    public static PS4Controller driverController = new PS4Controller(Constants.PS4_CONTROLLER_PORT);
    public static Joystick operatorControl = new Joystick(Constants.BUTTON_BOARD_PORT);
    // need to change variable names
   
    JoystickButton BUTTON_1 = new JoystickButton(operatorControl, 1),
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
    // Need to call commands w/ buttons

    public OI() {
        BUTTON_2.whileHeld(new ClimberExtend());
        BUTTON_1.whileHeld(new ClimberRetract());
        BUTTON_3.whenPressed(new ClimberPneumaticExtend());
        BUTTON_4.whenPressed(new ClimberPneumaticRetract());
    }
}
