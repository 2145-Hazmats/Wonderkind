// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorProfiledPID;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class GrabAndGo extends SequentialCommandGroup {
  public GrabAndGo(Drivetrain m_Drivetrain, Elevator m_Elevator, Claw m_Claw, ElevatorProfiledPID m_ElevatorProfiledPID) {
    addCommands(
      // run feed() in Drivetrain periodic
      Commands.runOnce(
        () -> {
          Drivetrain.isArcadeDrive = false;
        },
        m_Drivetrain),
      // Remove driver input
      m_Drivetrain.driveForwardCommand(0).withTimeout(0.1),
      // Grab cone
      m_Claw.clawShiftRightCommand(Constants.SpeedConstants.kClawShiftSpeed).withTimeout(0.6),
      // Lift cone up
      Commands.runOnce(
        () -> {
          m_ElevatorProfiledPID.setGoal(42.00);
          m_ElevatorProfiledPID.enable();
        },
        m_Elevator),
      // Wait buffer
      new WaitCommand(0.5),
      // Drive back
      m_Drivetrain.driveForwardCommand(0.25).withTimeout(0.5),
      // Arm down
      Commands.runOnce(
        () -> {
          m_ElevatorProfiledPID.setGoal(1.0);
          m_ElevatorProfiledPID.enable();
        },
        m_Elevator),
      new WaitCommand(1.0),
      // stop running feed() in Drivetrain periodic. And disable elevator PID
      Commands.runOnce(
        () -> {
          m_ElevatorProfiledPID.disable();
          Drivetrain.isArcadeDrive = false;
        },
        m_Drivetrain)
      );
  }
}
