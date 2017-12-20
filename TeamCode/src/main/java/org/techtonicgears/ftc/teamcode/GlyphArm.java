
package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class GlyphArm {
    private DcMotor verticalMotor;
    private Servo leftHand;
    private Servo rightHand;
    double leftOffset = 0.77;
    double rightOffset = 0.28;
    double clawOff = 0.4;

public void init(HardwareMap map) {
    verticalMotor = map.get(DcMotor.class, "glyph_arm");
    leftHand = map.get(Servo.class, "glyph_claw_l");
    rightHand = map.get(Servo.class, "glyph_claw_r");

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
    leftHand.setPosition(leftOffset - clawOff);
    rightHand.setPosition(rightOffset + clawOff);
}

public void clawOpen(){
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);

}
}