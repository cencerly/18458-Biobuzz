package org.firstinspires.ftc.teamcode.LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.teamcode.TeleOp.DT;

@TeleOp
public class LimeLightDrive extends OpMode {
    DcMotor leftFront, leftBack, rightFront, rightBack;
    Limelight3A limelight;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.pipelineSwitch(1);

        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");

        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void start () {
        limelight.start();
    }

    @Override
    public void loop() {
        LLResult llResult = limelight.getLatestResult();

        double tx = llResult.getTx();

        double turnSpeed = -tx;
        double forward = 1;
        double stop = 0;

        if (llResult.isValid() && llResult.getTx() >= 15) {
            leftFront.setPower(turnSpeed);
            leftBack.setPower(turnSpeed);
            rightBack.setPower(turnSpeed);
            rightFront.setPower(turnSpeed);
        } else if (llResult.isValid() && llResult.getTx() <= -15) {
            leftFront.setPower(turnSpeed);
            leftBack.setPower(turnSpeed);
            rightBack.setPower(turnSpeed);
            rightFront.setPower(turnSpeed);
        } else {
            leftFront.setPower(stop);
            leftBack.setPower(stop);
            rightBack.setPower(stop);
            rightFront.setPower(stop);
        }

        if (llResult.isValid()) {
            leftFront.setPower(-forward);
            leftBack.setPower(-forward);
            rightBack.setPower(forward);
            rightFront.setPower(forward);
        } else {
            leftFront.setPower(stop);
            leftBack.setPower(stop);
            rightBack.setPower(stop);
            rightFront.setPower(stop);
        }
    }
}
