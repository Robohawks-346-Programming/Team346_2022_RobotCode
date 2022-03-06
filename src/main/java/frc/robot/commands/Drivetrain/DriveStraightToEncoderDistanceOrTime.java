// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Drivetrain;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses an example subsystem. */
public class DriveStraightToEncoderDistanceOrTime extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */

  double targetDistance;
  double rotate = 0.0;
  double tSpeed;
  double m_startTime = -1;
  double m_timeout = -1;
  public DriveStraightToEncoderDistanceOrTime(double distance, double speed, double maxTime) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.drivetrain);
    tSpeed = speed;
    targetDistance = distance;
    this.withTimeout(maxTime);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.drivetrain.resetEncoder();
    RobotContainer.drivetrain.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.drivetrain.arcadeDrive(tSpeed, rotate);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    RobotContainer.drivetrain.arcadeDrive(0.0, 0.0);
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (targetDistance >= 0) 
      return (RobotContainer.drivetrain.getEncoderDistanceLeft() >= targetDistance) || isTimedOut();
    else
      return (RobotContainer.drivetrain.getEncoderDistanceLeft() <= targetDistance) || isTimedOut();
  }

  public synchronized final double timeSinceInitialized() {
    return m_startTime < 0 ? 0 : Timer.getFPGATimestamp() - m_startTime;
  }
  protected synchronized boolean isTimedOut() {
    return m_timeout != -1 && timeSinceInitialized() >= m_timeout;
  }
}