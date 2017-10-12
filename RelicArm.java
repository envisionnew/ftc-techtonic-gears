package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;



@TeleOp(name = "TeleOp: Relic")
@Disabled
public class RelicTest extends OpMode{
    RelicArm arm = new RelicArm();
    DcMotor slide;
    Servo arm1;
    Servo arm2;
    Servo claw;
    double linearM = 0.0d;
    double clawPos = 0.0d;
    double arm1Pos = 0.0d;
    double arm2Pos = 0.0d;
    @Override
    public void init() {
        arm.init(hardwareMap);
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

        slide = arm.relicSlide;
        arm1 = arm.relicArm1;
        arm2 = arm.relicArm2;
        claw = arm.relicClaw;

        linearM = gamepad2.left_stick_y/4;
        if(gamepad2.right_trigger > 0){
            clawPos += 0.01d;
        }else if(gamepad2.left_trigger > 0){
            clawPos -= 0.01d;
        }
        if(gamepad2.y){
            arm1Pos += 0.02d;
        }else if(gamepad2.a){
            arm1Pos -= 0.02d;
        }
        if(gamepad2.x){
            arm1Pos += 0.02d;
        }else if(gamepad2.b){
            arm1Pos -= 0.02d;
        }
        arm2Pos = Range.clip(arm2Pos, -0.5, 0.5);
        clawPos = Range.clip(clawPos, -0.5, 0.5);
        arm1Pos = Range.clip(arm1Pos, -0.5, 0.5);

        slide.setPower(linearM);
        claw.setPosition(arm.relicClaw_ST+clawPos);
        arm1.setPosition(arm.relicArm1_ST+arm1Pos);
        arm2.setPosition(arm.relicArm2_ST+arm2Pos);

    }
    @Override
    public void stop() {
    }
}