package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by vmujoo on 9/21/2017.
 */
@TeleOp(name = "TeleOp: Drive")
public class DriveTest extends OpMode{
    DriveTrain drive = new DriveTrain();
    double speed = 0.0d;
    double offset = 0.0d;
    @Override
    public void init() {
        drive.init(hardwareMap);
        telemetry.addData("", "Press Start");
        telemetry.update();
        drive.offset = 0.0d;
        drive.speed = 0.0d;
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start() {

    }
    @Override
    public void loop() {

        speed = -gamepad1.right_stick_y;
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.left_stick_x/2;

        drive.move(speed, offset);

        telemetry.addData("Power",speed);
        telemetry.addData("Offset",offset);
    }
    @Override
    public void stop() {
    }
}