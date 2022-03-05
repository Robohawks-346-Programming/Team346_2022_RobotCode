// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import frc.robot.Robot;
import frc.robot.commands.Drivetrain.DriveStraightToEncoderDistanceOrTime;

import javax.swing.GroupLayout.SequentialGroup;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class MoveOnly extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MoveOnly() {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(new DriveStraightToEncoderDistanceOrTime(60.0, 0.5, 10.0));
  }


}
