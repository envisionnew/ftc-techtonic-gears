package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "AutoTest")
public class AutoTest extends LinearOpMode{
    ElapsedTime timer = new ElapsedTime();
    //defining the drive train, glyph arm, and jewel arm
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    //used for something else possibly: boolean moveWithArm = false;
    //used to tell the program which color team we are on and so it compares the jewel color to the team color
    java.lang.String teamColor = "red";
    //which color the jewel it sees is
    java.lang.String foundColor = null;
    //color sensor
    ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        //initializing
        drive.init(hardwareMap);
        glyphArm.init(hardwareMap);
        jewelArm.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("color");
        waitForStart();
        //turn on color sensor light
        colorSensor.enableLed(true);
        //drop jewel arm so color sensor can detect
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 2) {
            jewelArm.setJewelArm(0.45);
            //returns which color the jewel is
            if (colorSensor.red() > 3){
                foundColor = "red";
            }else if (colorSensor.blue() > 3){
                foundColor = "blue";
            }else{

            }
        }
        timer.reset();
        //checks if the color the jewel it detects is same as team color
        if(teamColor == foundColor){
            while (opModeIsActive() && timer.seconds() < 0.5) {
                jewelArm.setJewelArm(0.45);
                //moves and pushes the other jewel
                drive.move(0.1,0);
            }
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 1){
                jewelArm.setJewelArm(0);
            }

        }else{
            while (opModeIsActive() && timer.seconds() < 0.5) {
                jewelArm.setJewelArm(0.45);
                //moves and pushes off the same jewel it detects because colors dont match
                drive.move(-0.1,0);
            }
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 1){
                jewelArm.setJewelArm(0);
                drive.move(0.2,0);
            }
        }









    }
   /* public void moveSec(double sec, double power, double offset){
        while (opModeIsActive() && timer.seconds() < sec){

            drive.move(power,offset);
            if(moveWithArm == true) {
                glyphArm.clawOpen();
            }else {
                glyphArm.clawClose();
            }
        }
        timer.reset();
    }*/
}
