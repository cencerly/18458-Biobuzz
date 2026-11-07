package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class Shooter {

    public static double kP = 0.00086324, kI = 0.00, kD = 3.50004002;
    private final PIDController velController;
    public DcMotorEx shooter, shooter2;
    public double targetRPM = 3000;
    public double ticksPerSecond;
    public Gamepad Driver1;

    Telemetry telemetry;

    private static final double TICKS_PER_REV = 28;

    public Shooter(OpMode opMode) {
        HardwareMap hardwareMap = opMode.hardwareMap;
        Driver1 = opMode.gamepad1;
        telemetry = opMode.telemetry;
        shooter = hardwareMap.get(DcMotorEx.class, "shooter");
        shooter2 = hardwareMap.get(DcMotorEx.class, "shooter2");

        shooter2.setDirection(DcMotorSimple.Direction.REVERSE);

        shooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        velController = new PIDController(kP, kI, kD);

    }
    public double currentRPM = (ticksPerSecond / TICKS_PER_REV) * 60.0;
    public void teleOp() {
        ticksPerSecond = shooter.getVelocity();
        currentRPM = (ticksPerSecond / TICKS_PER_REV) * 60.0;

    }

    public void runShooter() {
        shooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        shooter2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
        ticksPerSecond = shooter.getVelocity();
        currentRPM = (ticksPerSecond / TICKS_PER_REV) * 60.0;

        velController.setPID(kP, kI, kD);

        double pid = velController.calculate(currentRPM, targetRPM);
        shooter.setPower(pid);
        shooter2.setPower(pid);


        telemetry.addData("CloseCurrent RPM", currentRPM);
        telemetry.addData("CloseTarget RPM", targetRPM);
        telemetry.addData("Power", pid);
    }

    public void ReverseShooter() {
        shooter.setPower(-1);
    }
    public void stopShooter() {
        shooter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shooter2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        shooter.setPower(0);
        shooter2.setPower(0);
    }
}