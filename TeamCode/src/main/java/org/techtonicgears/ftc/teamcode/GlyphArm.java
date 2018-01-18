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
    int armMove = 0;
    double armPower = 0.0;

public void init(HardwareMap Map) {
    hwMap = Map;

    /*/ Naming all of the motors /*/

    verticalMotor = hwMap.get(DcMotor.class, "glyph_arm");
    leftHand = hwMap.get(Servo.class, "glyph_claw_l");
    rightHand = hwMap.get(Servo.class, "glyph_claw_r");

    /*/ Setting direction, position, and/or the power of the motors /*/

    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(0);
    leftHand.setPosition(0.37);
    rightHand.setPosition(0.68);
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

public void armUp(double power){
    if ((++armMove % 5) == 0) {
        armPower = power;
    } else if (armPower > 0.1) {
        armPower -= 0.1;
    }
    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(armPower);
}

public void armDown (double power) {
    if (armPower != 0) armPower -= 0.025;
    verticalMotor.setDirection(DcMotor.Direction.FORWARD);
    verticalMotor.setPower(armPower);
}

    /*/ Creating the motion for closing the GlyphArm's claws. /*/

public void clawOpen(){
    leftHand.setPosition(leftOffset - clawOff);
    rightHand.setPosition(rightOffset + clawOff);
}

    /*/ Creating the motion for opening the GlyphArm's claws. /*/

public void clawClose(){
    leftHand.setPosition(leftOffset);
    rightHand.setPosition(rightOffset);

    }
}