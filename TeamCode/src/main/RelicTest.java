package org.techtonicgears.ftc.teamcode;

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
    double clawPos = 0.0d;
    double arm1Pos = 0.0d;
    double slidePos = 0.0d;

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

        if(gamepad2.y){
            slidePos = 0.5d;
        }else if(gamepad2.a) {
            slidePos = -0.5d;
        }else{
            slidePos = 0;
        }

        if(gamepad2.right_trigger>0) {
            clawPos += 0.01d;
        }

        if(gamepad2.left_trigger>0) {
            clawPos -= 0.01d;

        }
        if(gamepad2.x) {
            arm1Pos += 0.01d;
        }
        if(gamepad2.b) {
            arm1Pos -= 0.01d;
        }

        arm.RelicExt(slidePos);
        arm.ClawMove(arm.relicClaw_ST+clawPos);
        arm.ArmMove(arm.relicArm1_ST+arm1Pos);
    }

    @Override
    public void stop() {
    }
}