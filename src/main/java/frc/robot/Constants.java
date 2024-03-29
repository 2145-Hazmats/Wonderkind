// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kCoDriverControllerPort = 1;
  }

  public final class CAN_ID_Constants {
    public static final int kLeftFrontMotorID = 2;
    public static final int kLeftBackMotorID = 4;
    public static final int kRightFrontMotorID = 3;
    public static final int kRightBackMotorID = 1;
    public static final int kElevatorMotorID = 5;
    public static final int kLeftClawMotorID = 6;
    public static final int kRightClawMotorID = 8;
  }

  public final class SpeedConstants {
    public static final double kUpElevatorSpeed = 0.3;
    public static final double kDownElevatorSpeed = 0.15;
    public static final double kClawOpenSpeed = 0.35;
    public static final double kClawCloseSpeed = 0.35;
    public static final double kClawShiftSpeed = 0.35;
  }

  public final class DrivetrainConstants {
    public static final double wheelDiameter = 0.1524; //in Meters
    public static final double gearboxRatio = 12.75;
    public static final double revToMeters = (1/gearboxRatio)*(Math.PI*wheelDiameter); //convert motor revolutions to distance in Meters
    public static final double RPMToMetersPerSec = revToMeters/60; //convert motor revolutions per minute to Meters per second
    public static final double TicksToRev = (1/4096);
    public static final double TicksToMeters =(revToMeters*TicksToRev);
  }
}
