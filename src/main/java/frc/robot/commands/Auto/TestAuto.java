// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Auto;

import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Intake.*;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.VisionProcessor.*;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

/** An example command that uses an example subsystem. */
public class TestAuto extends SequentialCommandGroup {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TestAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
    addCommands(
      new ParallelCommandGroup(
          new DeployIntake(),
          new SequentialCommandGroup(
            new DriveStraightToEncoderDistanceOrTime(-40.0, -0.5),
            new ParallelDeadlineGroup(
              new WaitCommand(2.5),
              //new TimeDrive(0.5,0.5),
              new CenterWithTarget(),
                new ParallelDeadlineGroup(
                  new WaitCommand(5),
                  new ShootBallTarmacAuto(),
                  new SequentialCommandGroup(
                    new WaitCommand(3),
                    new InternalManipulator2In()
              )
            )
          )
        ) 
      )
    );
  }



}
