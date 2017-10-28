package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;

@Disabled
public class GlyphArm {
    private DcMotor verticalMotor;
    private Servo leftHand;
    private Servo rightHand;
    double leftclaw_ST = 0.3;
    double rightclaw_ST = 0.8d;
    public int Uptime = 0;
    HardwareMap hwMap = null;




    public void init(HardwareMap Map) {
        hwMap = Map;
        verticalMotor = hwMap.get(DcMotor.class, "glyph_arm");
        leftHand = hwMap.get(Servo.class, "glyph_claw_l");
        rightHand = hwMap.get(Servo.class, "glyph_claw_r");

        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(0);
        leftHand.setPosition(leftclaw_ST);
        rightHand.setPosition(rightclaw_ST);
        Uptime = 0;
    }

    public void getPosition(Telemetry telemetry){
        telemetry.addData("L: " + leftHand.getPosition(), "R: " + rightHand.getPosition());
        telemetry.update();

    }

    public void moveL(double power){
        verticalMotor.setDirection(DcMotor.Direction.FORWARD);
        verticalMotor.setPower(power);
    }
    public void clawSet(double inc){
        leftHand.setPosition(leftclaw_ST+inc);
        rightHand.setPosition(rightclaw_ST-inc);
    }

}