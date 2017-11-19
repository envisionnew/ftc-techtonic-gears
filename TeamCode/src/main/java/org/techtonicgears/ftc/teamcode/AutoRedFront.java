package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;
import com.qualcomm.robotcore.hardware.*;


@Autonomous(name = "AutoRedFront")
public class AutoRedFront extends LinearOpMode {
    //define the parts that will be moving
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    private ElapsedTime runtime = new ElapsedTime();
    //color of the team
    String teamColor = "red";
    String foundColor = null;
    ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        //Initializing and performing init methods


        //waiting for user to press start
        waitForStart();
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("color");



        //moving off the balance beam with glyph secured in claw
        glyphArm.clawOpen();
        glyphArm.moveUpOrDown(0.2);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Elapsed ", runtime.seconds());
            telemetry.update();
        }
        glyphArm.moveUpOrDown(0);

        //turn on color sensor light
        colorSensor.enableLed(true);
        //drop jewel arm so color sensor can detect
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2) {
            jewelArm.setJewelArm(0.65);
            //returns which color the jewel is
            if (colorSensor.red() > colorSensor.blue()) {
                foundColor = "red";
            } else if (colorSensor.blue() > colorSensor.red()) {
                foundColor = "blue";
            } else {

            }
        }
        runtime.reset();
        //checks if the color the jewel it detects is same as team color
        if (teamColor == foundColor) {
            while (opModeIsActive() && runtime.seconds() < 0.7) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes the other jewel
                driveTrain.move(0.1, 0);
            }
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1.5) {
                jewelArm.setJewelArm(0);
                driveTrain.move(-0.2, 0);
            }
            // if the color of the jewel is not the same as the color of the team
        } else {
            while (opModeIsActive() && runtime.seconds() < 0.7) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes off the same jewel it detects because colors dont match
                driveTrain.move(-0.1, 0);
            }
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1.1) {
                jewelArm.setJewelArm(0);
                driveTrain.move(-0.1, 0);
            }
        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.7) {
            driveTrain.move(0, -0.5);

        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1.75) {
            driveTrain.move(-0.1, -0.1);
        }
        glyphArm.clawClose();
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {
            driveTrain.move(0.7, 0);
        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {

        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.05) {
            driveTrain.move(-0.1, 0);
        }
    }
}