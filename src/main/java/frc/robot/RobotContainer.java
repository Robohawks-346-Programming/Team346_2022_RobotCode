// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.robot.commands.ExampleCommand;
// import frc.robot.commands.Auto.FarLeft2Ball;
import frc.robot.commands.Auto.MoveOnly;
import frc.robot.commands.Auto.TestAuto;
import frc.robot.commands.Drivetrain.JoystickDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionProcessor;

import java.sql.Driver;

import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  public static final Drivetrain drivetrain = new Drivetrain();
  public static final Shooter shooter = new Shooter();
  public static final Climber climber = new Climber();
  public static final Intake intake = new Intake();
  public static final VisionProcessor visionprocessor = new VisionProcessor();
  public static final MoveOnly auto1 = new MoveOnly();
  // public static final FarLeft2Ball auto2 = new FarLeft2Ball();
  public static final TestAuto auto3 = new TestAuto();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    drivetrain.setDefaultCommand(new JoystickDrive());
    if (!OI.L2.get() || !OI.BUTTON_16.get()) {
      drivetrain.setDefaultCommand(new JoystickDrive());
    }
    OI.configureButtonBindings(drivetrain,shooter,climber,intake, visionprocessor);
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  // private void configureButtonBindings() {
  // }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return auto3;
  }
}
