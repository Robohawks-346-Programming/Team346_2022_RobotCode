// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.Auto;

// import frc.robot.commands.Drivetrain.DriveStraightToEncoderDistanceOrTime;
// import frc.robot.commands.Intake.IntakeBallIn;
// import frc.robot.commands.Intake.IntakeOut;
// import frc.robot.commands.Intake.IntakeStop;
// import frc.robot.commands.Intake.InternalManipulator1In;
// import frc.robot.commands.Shooter.ShootBallTarmac;
// import frc.robot.commands.VisionProcessor.CenterWithTarget;

// import java.lang.reflect.WildcardType;

// import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import edu.wpi.first.wpilibj2.command.WaitCommand;

// /** An example command that uses an example subsystem. */
// public class FarLeft2Ball extends SequentialCommandGroup {
//   @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
//   /**
//    * Creates a new ExampleCommand.
//    *
//    * @param subsystem The subsystem used by this command.
//    */
//   public FarLeft2Ball() {
//     // Use addRequirements() here to declare subsystem dependencies.
//     addCommands(
//       new ParallelCommandGroup(
//         new SequentialCommandGroup(
//           new IntakeOut(),
//           new IntakeBallIn()),
//         new SequentialCommandGroup(
//           new DriveStraightToEncoderDistanceOrTime(-43., -0.5), //Need to change distance
//           new IntakeStop(),
//           new CenterWithTarget(),
//           new ParallelCommandGroup(
//             new ShootBallTarmac(),
//             new SequentialCommandGroup(
//               new WaitCommand(1.5),
//               new InternalManipulator1In()),
//             new SequentialCommandGroup(
//               new WaitCommand(2.5),
//               new IntakeBallIn()
//             )
//           )
//         )
//       )
      
//     );
//   }



// }