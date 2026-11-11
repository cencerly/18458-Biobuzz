package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Turret {

    double forward = 1;
    double right = .5 ;
    double left = 0;

    Servo rightTurret, leftTurret;

    public void init(HardwareMap hardwareMap) {
        rightTurret = hardwareMap.get(Servo.class, "rightTurret");
        leftTurret = hardwareMap.get(Servo.class, "leftTurret");
    }

    public void FaceForward() {
        rightTurret.setPosition(forward);
        leftTurret.setPosition(forward);
    }

    public void FaceLeft() {
        rightTurret.setPosition(left);
        leftTurret.setPosition(left);
    }

    public void FaceRight() {
        rightTurret.setPosition(right);
        leftTurret.setPosition(right);
    }
}
