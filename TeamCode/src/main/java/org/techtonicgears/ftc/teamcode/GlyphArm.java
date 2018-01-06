package org.techtonicgears.ftc.teamcode;

/*/ Imports /*/

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class GlyphArm {

    /*/ Define the motors and variables that are being used in Glyph Arm /*/

    private DcMotor verticalMotor;
    private Servo leftHand;
    private Servo rightHand;
    double leftOffset = 0.77;
    double rightOffset = 0.28;
    double clawOff = 0.4;
    HardwareMap hwMap = null;

public void init(HardwareMap Map) {
    hwMap = Map;

    /*/ Naming all of the motors /*/

    verticalMotor = hwMap.get(DcMotor.class, "glyph_Arm");
    leftHand = hwMap.get(Servo.class, "glyph_claw_l");
    rightHand = hwMap.get(Servo.class, "glyph_claw_r");

    /*/ Setting direction, position, and/or the power of the motors /*/

    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(0);
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);
}


    /*/ Adding Telemetry Messages /*/

public void getPosition(Telemetry telemetry){
    telemetry.addData("L: " + leftHand.getPosition(), "R: " + rightHand.getPosition());
    telemetry.update();

}

    /*/ Creating the motion for moving GlyphArm up and down. /*/

public void moveUpOrDown(double power){
    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(power);
}

    /*/ Creating the motion for closing the GlyphArm's claws. /*/

public void clawClose(){
    leftHand.setPosition(leftOffset - clawOff);

    rightHand.setPosition(rightOffset + clawOff);
}

    /*/ Creating the motion for opening the GlyphArm's claws. /*/

public void clawOpen(){
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);

    }
}