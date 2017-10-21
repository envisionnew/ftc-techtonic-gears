package org.firstinspires.ftc.teamcode.org.techtonicgrars.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "TeleOp: Relic Arm")
@Disabled
public class RelicTest extends OpMode{
    RelicArm  arm = new RelicArm();
    DcMotor slide;
    Servo arm1;
    Servo claw;
    public DcMotor relicSlide = null;
    public Servo relicClaw = null;
    public Servo relicArm1 = null;
    public Servo relicArm2 = null;
    public final double relicArm1_ST = 1;
    public final double relicClaw_ST = 0;
    double linearM = 0.0d;
    double clawPos = 0.0d;
    double arm1Pos = 0.0d;
    double slidePos = 0.0d;
    HardwareMap hwMap = null;
    public void init(HardwareMap Map) {
        hwMap = Map;
    }

    public void init() {
        arm.init(hardwareMap);
        telemetry.addData("", "Press Start");
        telemetry.update();
    }
    @Override
    public void start() {
    }

    @Override
    public void loop() {

        slide = arm.relicSlide;
        arm1 = arm.relicArm1;
        claw = arm.relicClaw;

        if(gamepad2.y){
            slidePos += 0.01d;
        }

        if(gamepad2.a) {
            slidePos -= 0.01d;
        }

        if(gamepad2.right_trigger>0) {
            clawPos += 0.01d;
        }

        if(gamepad2.left_trigger>0) {
            clawPos -= 0.01d;

        }


        clawPos = Range.clip(clawPos, 0, 0);
        arm1Pos = Range.clip(arm1Pos, 0, 0);
        slidePos =  Range.clip(slidePos, 0, 0);

        slide.setPower(linearM);
        claw.setPosition(arm.relicClaw_ST+clawPos);
        arm1.setPosition(arm.relicArm1_ST+arm1Pos);
    }

    @Override
    public void stop() {
    }
}