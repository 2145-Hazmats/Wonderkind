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
import frc.robot.subsystems.DrivetrainProfiledPID;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.ElevatorProfiledPID;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PlayAndLeave extends SequentialCommandGroup {
  public PlayAndLeave(Drivetrain m_Drivetrain, Elevator m_Elevator, Claw m_Claw, ElevatorProfiledPID m_ElevatorProfiledPID, DrivetrainProfiledPID m_DrivetrainProfiledPID) {
    addCommands(
      // Claw close
      m_Claw.clawShiftRightCommand(Constants.SpeedConstants.kClawShiftSpeed).withTimeout(1.00),
      // Elevator up
      Commands.runOnce(
        () -> {
          m_ElevatorProfiledPID.setGoal(57.50);
          m_ElevatorProfiledPID.enable();
        },
        m_Elevator),
      // Wait buffer
      new WaitCommand(1.5),
      // Claw open
      m_Claw.clawShiftLeftCommand(Constants.SpeedConstants.kClawShiftSpeed).withTimeout(1.00),
      // Elevator down
      Commands.runOnce(
        () -> {
          m_ElevatorProfiledPID.setGoal(1.00);
          m_ElevatorProfiledPID.enable();
        },
        m_Elevator),
      // Wait buffer
      new WaitCommand(1.5),
      // Back up past red line
      m_Drivetrain.driveForwardCommand(0.6).withTimeout(2.0),
      // Wait buffer
      new WaitCommand(10.5),
      // Fix?
      m_Drivetrain.driveForwardCommand(0.0).withTimeout(0.1)
      );
  }
}
