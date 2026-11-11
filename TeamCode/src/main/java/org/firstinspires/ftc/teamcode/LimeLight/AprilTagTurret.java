package org.firstinspires.ftc.teamcode.LimeLight;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp
public class AprilTagTurret extends OpMode {

    Servo leftTurret, rightTurret;
    Limelight3A limelight;

    double startPos = 0.5;
    double turretPos = startPos;

    @Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        leftTurret = hardwareMap.get(Servo.class, "leftTurret");
        rightTurret = hardwareMap.get(Servo.class, "rightTurret");

        limelight.pipelineSwitch(1);
        Aim(startPos);
    }

    public void start () {
        limelight.start();
    }

    @Override
    public void loop() {
            LLResult llResult = limelight.getLatestResult();

            double deadBand = 1;
            double tx = llResult.getTx();
            double degree = (double) 1 /180;

        if (Math.abs(tx) > deadBand) {
            turretPos += tx * degree;
            turretPos = Range.clip(turretPos, 0.0, 1.0);
            Aim(turretPos);
            }
    }

    public void Aim(double position) {
        rightTurret.setPosition(position);
        leftTurret.setPosition(position);
    }
}