// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import frc.robot.commands.Drivetrain.DriveStraightToEncoderDistanceOrTime;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** An example command that uses an example subsystem. */
public class MoveOnly extends CommandGroupBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public MoveOnly() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  @Override
  public void addCommands(Command... commands) {
    // TODO Auto-generated method stub
    new DriveStraightToEncoderDistanceOrTime(100.0, 0.5, 10.0);
    
  }


}
