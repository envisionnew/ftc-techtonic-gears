package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class JewelArm {
    //defining the jewel arm
    Servo jewelArm;
    HardwareMap hwMap;
    ColorSensor colorSensor;
    GyroSensor gyroSensor;
    public void init(HardwareMap Map){
        hwMap = Map;
        jewelArm = hwMap.get(Servo.class, "jewel_arm");
        colorSensor = hwMap.get(ColorSensor.class, "color");
        gyroSensor = hwMap.get(GyroSensor.class, "gyrosensor");
        jewelArm.setPosition(0);
        gyroSensor.calibrate();
    }
    //method for setting jewel arm to a certain position, parameter is a double which is used to set the position
    public void setJewelArm(double Pos){
        jewelArm.setPosition(Pos);
    }
}
