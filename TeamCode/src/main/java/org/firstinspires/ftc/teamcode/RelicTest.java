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
    RelicTest arm = new RelicTest();
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
        relicSlide = hwMap.get(DcMotor.class, "Relic Slide");
        relicClaw = hwMap.get(Servo.class, "Relic Claw");
        relicArm1 = hwMap.get(Servo.class, "Relic Arm 1");
        relicSlide.setDirection(DcMotor.Direction.FORWARD);
        relicClaw.setPosition(relicClaw_ST);
        relicArm1.setPosition(relicArm1_ST);
        relicSlide.setPower(0);
        relicSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

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