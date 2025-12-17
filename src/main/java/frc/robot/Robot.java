// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  //private Command m_autonomousCommand;

  //private final RobotContainer m_robotContainer;

private TalonFX motor1 = new TalonFX(11);
private TalonFX motor2 = new TalonFX(12);
private TalonFX motor4 = new TalonFX(13);
private TalonFX motor5 = new TalonFX(5);
private SparkMax motor3 = new SparkMax(11, MotorType.kBrushless);
private SparkClosedLoopController motor3Controller = motor3.getClosedLoopController();
private XboxController controller = new XboxController(0);
private Orchestra orchestra = new Orchestra();

  public Robot() {
    //m_robotContainer = new RobotContainer();
  }

  @Override
  public void robotPeriodic() {
    // CommandScheduler.getInstance().run();
    orchestra.addInstrument(motor1);
    orchestra.addInstrument(motor2);
    orchestra.addInstrument(motor4);
    orchestra.addInstrument(motor5);
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.schedule();
    // }
  }

  @Override
  public void autonomousPeriodic() {
    motor1.set(0.5);
  }

  @Override
  public void autonomousExit() {
    motor1.set(0);
  }

  @Override
  public void teleopInit() {
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.cancel();
    // }
  }

  @Override
  public void teleopPeriodic() {
    motor1.set(controller.getLeftY());

    if (controller.getAButton()){
      motor3Controller.setReference(0, ControlType.kPosition);
     } else {
      motor3Controller.setReference(Math.PI, ControlType.kPosition);
    }
  
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    // CommandScheduler.getInstance().cancelAll();
    orchestra.loadMusic("Mario.chrp");
  }

  @Override
  public void testPeriodic() {
    orchestra.play();
  }

  @Override
  public void testExit() {}
}
