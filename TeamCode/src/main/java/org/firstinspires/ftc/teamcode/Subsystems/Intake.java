package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    public DcMotorEx intakeRight, intakeLeft;
    Gamepad gamepad;

    double IntakeOn = 1;
    public void init(HardwareMap hardWareMap) {
        intakeRight = hardWareMap.get(DcMotorEx.class, "intakeRight");
        intakeLeft = hardWareMap.get(DcMotorEx.class, "intakeLeft");

        intakeLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intakeRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        intakeRight.setDirection(DcMotorSimple.Direction.REVERSE);

        gamepad = new Gamepad();
    }

    public void intake() {
        intakeRight.setPower(IntakeOn);
        intakeLeft.setPower(IntakeOn);
    }
    public void reverse() {
        intakeRight.setPower(-1);
        intakeLeft.setPower(-1);
    }

    public void stop() {
        intakeRight.setPower(0);
        intakeLeft.setPower(0);
    }
}
