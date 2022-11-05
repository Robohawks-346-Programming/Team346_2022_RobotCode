// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Intake;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DeployIntake extends CommandBase {
   
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DeployIntake() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.intake.intakeOut();
    RobotContainer.intake.intakeBallIn(Constants.INTAKE_MOTOR_SPEED);
    RobotContainer.intake.InternalManipulator1In(Constants.INTERNAL_MANIPULATOR_1_MOTOR_SPEED);
    SmartDashboard.putBoolean("Laser break", RobotContainer.intake.hasBall());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.intake.intakeIn();
    RobotContainer.intake.intakeBallIn(0.0);
    RobotContainer.intake.InternalManipulator1In(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;//RobotContainer.intake.hasBall();
  }
}
