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

//Auto: shoot ball, turn right, back up, pick up ball, turn left(centered on hub), shoot ball


public class Auto2 extends CommandBase {
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Auto2(ExampleSubsystem subsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   
   Robot.shooter.shootBall(Constants.SHOOTER_MOTOR_SPEED);
   Robot.drivetrain.TurnToDegrees(0.5, 90);
   Robot.drivetrain.arcadeDrive(-Constants.DRIVETRAIN_MOTOR_SPEED,0);
   Robot.intake.intakeBallIn(Constants.INTAKE_MOTOR_SPEED, Constants.INTERNAL_MANIPULATOR_MOTOR_SPEED);
   Robot.drivetrain.TurnToDegrees(0.5, VisionProcessor.getRotate());
   Robot.shooter.shootBall(Constants.SHOOTER_MOTOR_SPEED);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
