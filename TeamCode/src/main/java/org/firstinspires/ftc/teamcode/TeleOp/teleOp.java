package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Linkage;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter;
@TeleOp (name = "TeleOp")
public class teleOp extends LinearOpMode  {
    @Override
    public void runOpMode() throws InterruptedException {
        DT dt = new DT(this);
        Shooter shooter = new Shooter(this);
        Intake intake = new Intake();
        Linkage linkage = new Linkage();

        boolean LinkageState = true;

        waitForStart();

        while (opModeIsActive()) {

            dt.teleop();
            intake.init(hardwareMap);
            shooter.teleOp();
            linkage.init(hardwareMap);

            if (gamepad1.right_bumper) {
                shooter.runShooter();
            } else {
                shooter.stopShooter();
            }

            if (gamepad1.x)  {
                shooter.ReverseShooter();
            }

            if (gamepad1.x) {
                intake.intake();
            } else if (gamepad1.y) {
                intake.reverse();
            } else {
                intake.stop();
            }


            if (gamepad1.a) {
               LinkageState = !LinkageState;
            }

            if (LinkageState) {
                linkage.rest();
            } else if (!LinkageState) {
                linkage.down();
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
//