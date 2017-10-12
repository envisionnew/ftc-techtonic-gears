package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "TeleOp: Drive")
public class DriveTest extends OpMode{
    DriveTrain drive = new DriveTrain();
    DcMotor rightFront;
    DcMotor leftFront;
    DcMotor rightBack;
    DcMotor leftBack;
    double speed = 0.0d;
    double offset = 0.0d;
    @Override
    public void init() {
        drive.init(hardwareMap);
        telemetry.addData("", "Press Start");
        telemetry.update();
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start() {

    }
    @Override
    public void loop() {
        leftFront = drive.leftFront;
        rightFront = drive.rightFront;
        leftBack = drive.leftBack;
        rightBack = drive.rightBack;

        speed = -gamepad1.right_stick_y;
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.left_stick_x/2;

        leftFront.setPower(speed-offset);
        rightFront.setPower(speed+offset);
        leftBack.setPower(speed-offset);
        rightBack.setPower(speed+offset);

        telemetry.addData("Power",speed);
        telemetry.addData("Offset",offset);
    }
    @Override
    public void stop() {
    }
}