package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Linkage {
    private static final double REST_POSE = .628;
    private static final double GRAB_POSE = .91;
    public Servo servo1, servo2;
    public HardwareMap hardwareMap;
    public IMU imu;


    public void init(HardwareMap hardwareMap) {
        this.servo1 = (Servo) hardwareMap.get("servoLeft");
        this.servo2 = (Servo) hardwareMap.get("servoRight");

        servo1.setDirection(Servo.Direction.FORWARD);
        servo2.setDirection(Servo.Direction.REVERSE);
    }
    public void rest() {
        servo1.setPosition(REST_POSE);
        servo2.setPosition(REST_POSE);
    }

    public void down() {
        servo1.setPosition(GRAB_POSE);
        servo2.setPosition(GRAB_POSE);
        }
}

