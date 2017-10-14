package org.firstinspires.ftc.teamcode;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@TeleOp(name= "TeleOp")
//@Disabled
public class TestTOP extends OpMode{

    //creating a robot
    HardwarePushbot robot = new HardwarePushbot();
    //initializing things
    DcMotor left;
    DcMotor right;
    double wheelOffset = 0.0;
    double speed = 0.0;
    double clawOffset = 0.0;
    double armSpeed = 0.0;

    @Override
    public void init() {

        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Waiting:", "For start");

    }

    @Override
    public void init_loop(){

    }
    @Override
    public void start(){
    }
    public void loop() {
    right = hardwareMap.dcMotor.get("right_drive");
    left = hardwareMap.dcMotor.get("left_drive");

     speed = -gamepad1.right_stick_y;
     speed = Range.clip(speed, -0.5, 0.5);
     wheelOffset = gamepad1.right_stick_x/2;

       /* left = gamepad1.right_stick_y;
        right = -gamepad1.right_stick_y;
        left = gamepad1.right_stick_x;
        right = -gamepad1.right_stick_x;
        robot.leftMotor.setPower(left);
        robot.rightMotor.setPower(right); */

     if(wheelOffset > 0) {
         left.setPower((speed + wheelOffset * 2) / 2);
         right.setPower((speed - wheelOffset) / 2);
     }else if(wheelOffset < 0) {
         left.setPower((speed + wheelOffset) / 2);
         right.setPower((speed - wheelOffset * 2) / 2);
     }else{
         left.setPower((speed+wheelOffset)/2);
         right.setPower((speed-wheelOffset)/2);
     }
//claw
     if(gamepad1.dpad_up) {
         clawOffset += 0.01;
     }else if(gamepad1.dpad_down)   {
         clawOffset -= 0.01;
     }
//arm
   /*  if(-gamepad1.left_stick_y > 0){
         armSpeed = -gamepad1.left_stick_y;
     }else if(gamepad1.left_stick_y > 0){
         armSpeed = gamepad1.left_stick_y;*/
   if(gamepad1.left_stick_y > 0 ) {
       robot.armMotor.setPower(robot.ARM_DOWN_POWER);
   }else if(gamepad1.left_stick_y < 0){
       robot.armMotor.setPower(robot.ARM_UP_POWER);

     }else{
         armSpeed = 0.0;
     }
     clawOffset = Range.clip(clawOffset, -0.5, 0.5);
        robot.armMotor.setPower(armSpeed/8);
        robot.leftClaw.setPosition(robot.MID_SERVO - clawOffset);
        robot.rightClaw.setPosition(robot.MID_SERVO + clawOffset);

     telemetry.addData("Power: ", speed);
     telemetry.addData("Wheel Offset: ", wheelOffset);
     telemetry.addData("Claw Offset: ", clawOffset);
    }
    @Override
    public void stop(){

    }

    }