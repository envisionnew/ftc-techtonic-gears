package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class JewelArm {
    //defining the jewel arm
    Servo jewelArm;
    HardwareMap hwMap;
    public void init(HardwareMap Map){
        hwMap = Map;
        jewelArm = hwMap.get(Servo.class, "jewel_arm");
        jewelArm.setPosition(0);
    }
    //method for setting jewel arm to a certain position, parameter is a double which is used to set the position
    public void setJewelArm(double Pos){
        jewelArm.setPosition(Pos);
    }
}
