package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
@TeleOp (name = "TeleOp")
public class teleOp extends LinearOpMode  {
    @Override
    public void runOpMode() throws InterruptedException {
        DT dt = new DT(this);
        Shooter shooter = new Shooter(this);
        Intake intake = new Intake();

        waitForStart();

        while (opModeIsActive()) {

            dt.teleop();
            intake.init(hardwareMap);
            shooter.teleOp();

            if (gamepad1.right_bumper) {
                shooter.runShooter();
            } else {
                shooter.stopShooter();
            }

            if (gamepad1.x)  {
                shooter.ReverseShooter();
            }

            if (gamepad1.a) {
                intake.intake();
            } else if (gamepad1.b) {
                intake.reverse();
            } else {
                intake.stop();
            }

            telemetry.addLine("=== TURRET ===");
            telemetry.addLine();
            telemetry.addLine("=== SHOOTER ===");
            telemetry.addLine();
            telemetry.addData("[dpad_up]", "Toggle Turret Tracking");
            telemetry.addData("[LB]", "Run Shooter");
            telemetry.addData("[X]", "Reverse Shooter");
            telemetry.update();
        }
    }
}
