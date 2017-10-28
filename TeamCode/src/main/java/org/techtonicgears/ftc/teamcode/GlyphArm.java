package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
import org.firstinspires.ftc.robotcore.external.Telemetry;


public class GlyphArm {
    private DcMotor verticalMotor;
    private Servo leftHand;
    private Servo rightHand;
    double leftOffset = 0.5;
    double rightOffset = 0.5;
    double clawOff = 0.2;
    HardwareMap hwMap = null;



public void init(HardwareMap Map) {
    hwMap = Map;
    verticalMotor = hwMap.get(DcMotor.class, "glyph_arm");
    leftHand = hwMap.get(Servo.class, "glyph_claw_l");
    rightHand = hwMap.get(Servo.class, "glyph_claw_r");

    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(0);
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);
}

public void getPosition(Telemetry telemetry){
    telemetry.addData("L: " + leftHand.getPosition(), "R: " + rightHand.getPosition());
    telemetry.update();

}

public void moveUpOrDown(double power){
    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(power);
}
public void clawClose(){
    leftHand.setPosition(Servo.MAX_POSITION - clawOff);
    rightHand.setPosition(Servo.MIN_POSITION + clawOff);
}

public void clawOpen(){
    leftHand.setPosition(Servo.MIN_POSITION + clawOff);
    rightHand.setPosition(Servo.MAX_POSITION - clawOff);

}
}