// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.operatorinterface.OperatorInterface;

public class RobotContainer {
  private final DeceiverRobotState robotState;

  // Subsystems

  // Operator Interface
  private final OperatorInterface operatorInterface;

  /**
   * Constructor
   *
   * @param state RobotState object to track the state of the robot
   */
  public RobotContainer(DeceiverRobotState state) {
    robotState = state;

    // Lower brownout voltage
    RobotController.setBrownoutVoltage(6.0);

    // Create Operator Interface
    // TODO: Sim operator interface
    operatorInterface = new OperatorInterface(robotState);

    // Create subsystem hardware
    switch (RobotConstants.currentMode) {
      case COMMISIONING:
        // Fall Through
      case REAL:
        // Real Robot, use real hardware interfaces
        break;
      case SIM:
        // Simulated robot use simulation hardware interfaces
        break;
      case REPLAY:
        // Replayed Robot, don't use hardware
        break;
    }

    configureAutoModes();
    configureBindings();
  }

  private void configureAutoModes() {}

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return operatorInterface.getAutoCommand();
  }
}
