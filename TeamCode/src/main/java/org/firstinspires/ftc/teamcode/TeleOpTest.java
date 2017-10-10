package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftccommon.SoundPlayer;


/**
 * Created by vmujoo on 9/21/2017.
 */
@TeleOp(name = "TeleOp: Shuhul")
public class TeleOpTest extends OpMode{
    HardwarePushbot robot = new HardwarePushbot();
    DcMotor right;
    DcMotor left;
    double speed = 0.0d;
    double offset = 0.0d;
    double in = 0.0d;
    double armpower = 0.0d;
    @Override
    public void init() {
        robot.init(hardwareMap);
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
        right = hardwareMap.dcMotor.get("right_drive_B");
        left = hardwareMap.dcMotor.get("left_drive_B");
        //hardwareMap.dcMotor.get("left_drive") = robot.leftMotor;
        speed = -gamepad1.right_stick_y;
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.right_stick_x/2;
        //x -= y is ths same as x = x-y
        //SoundPlayer play = new SoundPlayer(1,1);
        //play.play(hardwareMap.appContext, R.raw.nxtstartupsound);

        if(gamepad1.left_bumper){
            in += 0.02d;
        }else if(gamepad1.right_bumper){
            in -= 0.02d;
        }

        if(gamepad1.right_trigger > 0) {
            armpower = gamepad1.right_trigger;
        }else if(gamepad1.left_trigger > 0){
            armpower = -gamepad1.left_trigger;
        }else{
            armpower = 0.0d;
        }
        in = Range.clip(in, -0.5, 0.5);
        robot.armMotor.setPower(armpower/8);
        robot.leftClaw.setPosition(robot.MID_SERVO+in);
        robot.rightClaw.setPosition(robot.MID_SERVO-in);
        
        if(offset > 0){
            left.setPower((speed+offset*2));
            right.setPower((speed-offset));
        }else if(offset < 0){
            left.setPower((speed+offset));
            right.setPower((speed-offset*2));
        }else{
            left.setPower((speed+offset));
            right.setPower((speed-offset));
        }
        telemetry.addData("Power",speed);
        telemetry.addData("Offset",offset);
    }
    @Override
    public void stop() {
    }
}