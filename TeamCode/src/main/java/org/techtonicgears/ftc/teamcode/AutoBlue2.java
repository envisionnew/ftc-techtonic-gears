package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;
import com.qualcomm.robotcore.hardware.*;


@Autonomous(name = "AutoBlue2")
public class AutoBlue2 extends LinearOpMode {
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    private ElapsedTime runtime = new ElapsedTime();
    java.lang.String teamColor = "blue";
    java.lang.String foundColor = null;
    ColorSensor colorSensor;

    @Override
    public void runOpMode() {
        //Initializing and performing init methods
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("color");

        //waiting for user to press start
        waitForStart();


        //moving off the balance beam with glyph secured in claw
        //glyphArm.clawClose();
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
            while (opModeIsActive() && runtime.seconds() < 0.5) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes the other jewel
                driveTrain.move(0.1, 0);
            }
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1) {
                jewelArm.setJewelArm(0);
            }


        } else {
            while (opModeIsActive() && runtime.seconds() < 0.5) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes off the same jewel it detects because colors dont match
                driveTrain.move(-0.1, 0);
            }
           //move to regain back to the same position
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 1) {
                jewelArm.setJewelArm(0);
                driveTrain.move(0.2, 0);
            }
            runtime.reset();
            while (opModeIsActive() && runtime.seconds() < 0.3) {
                jewelArm.setJewelArm(0);
                driveTrain.move(0, 0.15);
            }
        }
        /////////////
        //move to crypto
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.45)) {
            driveTrain.move(0.1, 0);
        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 2.4) {
            driveTrain.move(0.0, -0.1);
        }
        glyphArm.clawClose();
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1) {
            driveTrain.move(0.1, 0.0);
        }
        glyphArm.clawClose();
        //To stop the drive train


        //turn towards crypto box

        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 1) {
            driveTrain.move(0.0, 0.0);
        }
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {
            driveTrain.move(-0.1, 0.0);
        }

    }
}
