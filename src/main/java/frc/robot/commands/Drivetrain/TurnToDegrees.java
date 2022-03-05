// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class TurnToDegrees extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  double rotationSpeed;
  double goalDegrees;
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnToDegrees(double rotationSpeed, double goalDegrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);

    this.rotationSpeed = rotationSpeed;
    this.goalDegrees = goalDegrees;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(goalDegrees >= 0) {
      Robot.drivetrain.arcadeDrive(0.0, rotationSpeed);
    }
    else {
      Robot.drivetrain.arcadeDrive(0.0, -rotationSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(goalDegrees >= 0) {
      return (Robot.drivetrain.getAngle() >= goalDegrees);
    }
    else {
      return (Robot.drivetrain.getAngle() <= goalDegrees);
    }
  }
}
