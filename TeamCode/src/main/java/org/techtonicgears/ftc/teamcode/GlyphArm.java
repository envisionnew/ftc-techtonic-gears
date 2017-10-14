package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;


public class GlyphArm {
    private DcMotor verticalMotor;
    private Servo leftHand;
    private Servo rightHand;
    double leftOffset = 0.5;
    double rightOffset = 0.5;
    HardwareMap hwMap = null;



public void init(HardwareMap Map) {
    hwMap = Map;
   // verticalMotor = hwMap.get(DcMotor.class, "Vertical_Motor");
    leftHand = hwMap.get(Servo.class, "glyph_claw_l");
    rightHand = hwMap.get(Servo.class, "glyph_claw_r");

    //verticalMotor.setDirection(DcMotor.Direction.FORWARD);
 //   verticalMotor.setPower(0);
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);
}

/*public void moveUpOrDown(double power){
    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(power);
}*/
public void clawClose(Double clawoffset){
    leftOffset += clawoffset;
    rightOffset -= clawoffset;
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);
}

public void moveClaw(Double clawoffset){
    leftOffset += clawoffset;
    rightOffset -= clawoffset;

    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);
}
public void clawOpen(Double clawoffset){
    leftOffset -= clawoffset;
    rightOffset += clawoffset;
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);

}
}