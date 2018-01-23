package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Aanvi on 1/5/18.
 */


    @Autonomous(name = "AutoRedFrontLeft")
    @Disabled
    public class RedFrontLeft extends LinearOpMode {

        //creating all the parts of robot that will be needed
        DriveTrain driveTrain = new DriveTrain();
        JewelArm jewelArm = new JewelArm();
        GlyphArm glyphArm = new GlyphArm();
        ColorSensor colorSensor;
        ElapsedTime timer = new ElapsedTime();

        // varaibles needed for the color sensor
        String foundColor = null;
        String allianceColor = "red";

        @Override
        public void runOpMode() {

            //Initializing all parts
            driveTrain.init(hardwareMap);
            jewelArm.init(hardwareMap);
            glyphArm.init(hardwareMap);
            colorSensor = hardwareMap.colorSensor.get("color");

            //waiting for the start button to be pressed
            waitForStart();

            //closing the claw to secure glyph then moving it up
            glyphArm.clawClose();
            glyphArm.moveUpOrDown(0.3);

            //enabling the light on the color sensor
            colorSensor.enableLed(true);
            timer.reset();

            while (opModeIsActive() && timer.seconds() < 2) {
                //dropping the jewel arm down
                jewelArm.setJewelArm(0.7);

                //color sensor checks what color was found and then stores it in foundColor
                if (colorSensor.red() > colorSensor.blue())
                    foundColor = "red";
                else if (colorSensor.blue() > colorSensor.red())
                    foundColor = "blue";
            }

            timer.reset();

            while (opModeIsActive() && timer.seconds() < 0.5) {
                //robot makes a descision about which way to move depending on the color of the jewel
                if (foundColor == allianceColor) {
                    driveTrain.move(0, 0, 0.1);
                    jewelArm.setJewelArm(0);
                    driveTrain.move(0, 0, -0.1);
                } else {
                    driveTrain.move(0, 0, -0.1);
                    jewelArm.setJewelArm(0);
                    driveTrain.move(0, 0, 0.1);
                }
            }

            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.7) {
                //moves forward and off of balancing board
                driveTrain.move(0.1, 0, 0);
            }

            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.9) {
                //turns toward crypto box
                driveTrain.move(0, 0.5, 0);
            }


            //opens claw to drop glyph
            glyphArm.clawOpen();

            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.4) {
                //moves slightly back so glyph goes into column
                driveTrain.move(-0.1, 0, 0);

            }

        }
    }

