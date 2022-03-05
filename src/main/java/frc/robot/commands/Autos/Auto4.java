// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autos;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.VisionProcessor;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */

//Shoot ball, back up, turn right, back up to ball, pick up ball, center with hub, shoot ball, turn right,
// back up till ball, pick up ball, center with hub, shoot ball
public class Auto4 extends CommandBase {
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Auto4(ExampleSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.shooter.shootBall(Constants.SHOOTER_MOTOR_SPEED);
    Robot.drivetrain.driveStraightEncoder(-0.8);
    Robot.drivetrain.TurnToDegrees(0.5, 90);
    Robot.drivetrain.driveStraightEncoder(-0.8);
    Robot.intake.intakeBallIn(Constants.INTAKE_MOTOR_SPEED, Constants.INTERNAL_MANIPULATOR_MOTOR_SPEED);
    Robot.drivetrain.TurnToDegrees(0.5, VisionProcessor.getRotate());
    Robot.shooter.shootBall(Constants.SHOOTER_MOTOR_SPEED);
    Robot.drivetrain.TurnToDegrees(0.5, 90);
    Robot.drivetrain.driveStraightEncoder(-0.8);
    Robot.intake.intakeBallIn(Constants.INTAKE_MOTOR_SPEED, Constants.INTERNAL_MANIPULATOR_MOTOR_SPEED);
    Robot.drivetrain.TurnToDegrees(0.5, VisionProcessor.getRotate());
    Robot.shooter.shootBall(Constants.SHOOTER_MOTOR_SPEED);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
