package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//@Disabled
public class RelicArm {

    /*/ Define the motors and variables that are being used in Glyph Arm /*/

    public DcMotor relicSlide   = null;
    public Servo relicClaw = null;
    public Servo relicArm1 = null;
    public final double relicArm1_ST = 1;
    public final double relicClaw_ST = 0;
    HardwareMap hwMap = null;

    public void init(HardwareMap Map) {
        hwMap = Map;

        /*/ Naming the motors that are being used in RelicArm /*/

        relicSlide = hwMap.get(DcMotor.class, "Relic_Slide");
        relicClaw = hwMap.get(Servo.class, "Relic_Claw");
        relicArm1 = hwMap.get(Servo.class, "Relic_Arm_1");

        /*/ Setting Direction, Mode, And Power for the DC Motor, relicSlide. /*/

        relicSlide.setDirection(DcMotor.Direction.FORWARD);
        relicSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        relicSlide.setPower(0);

        /*/ Setting the position of the Servos, relicClaw and relicArm1. /*/

        relicClaw.setPosition(relicClaw_ST);
        relicArm1.setPosition(relicArm1_ST);


    }



    public void RelicExt(double power){

        relicSlide.setPower(power);
    }


    /*/ Method used to catch the Relic Arm via servo motor relicClaw. /*/
    public void ClawMove(double PosC){
        Range.clip(PosC,-1,1);
        relicClaw.setPosition(PosC);
    }

    /*/ Method used to move the claw outwards, via servo relicArm1. /*/

    public void ArmMove(double PosA){
        Range.clip(PosA,-1,1);
        relicArm1.setPosition(PosA);
    }
}