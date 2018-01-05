package org.techtonicgears.ftc.teamcode;

/*/ Imports /*/

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class JewelArm {

    /*/ Defining the Jewel Arm /*/

    Servo jewelArm;
    HardwareMap hwMap;
    public void init(HardwareMap Map){
        hwMap = Map;

        /*/ Naming the Jewel Arm /*/

        jewelArm = hwMap.get(Servo.class, "jewel_arm");

        /*/ Setting the position of the Jewel Arm  upon initialize. */

        jewelArm.setPosition(0);
    }

    /*/ Method for setting the Jewel Arm to a certain position. Pos is a double which is used to set the position of the Jewel Arm. /*/

    public void setJewelArm(double Pos){
        jewelArm.setPosition(Pos);
    }
}
