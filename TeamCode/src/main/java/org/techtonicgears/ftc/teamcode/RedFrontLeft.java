package org.techtonicgears.ftc.teamcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

/**
 * Created by Aanvi on 1/5/18.
 */

@Autonomous(name = "AutoRedFront")
public class RedFrontLeft extends LinearOpMode {

    // robots drive train
    DriveTrain driveTrain = new DriveTrain();
    //jewel arm
    JewelArm jewelArm = new JewelArm();
    //glyph arm
    GlyphArm glyphArm = new GlyphArm();
    //color sensor
    ColorSensor colorSensor;
    //timer
    ElapsedTime timer = new ElapsedTime();
    //gyro sensor
    IntegratingGyroscope gyro;
    //vuforia
    VuForia vuForia = new VuForia();
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;

    //heading for gyro
    int heading;

    //offset variable
    double offset;

    //move variable
    int move = 0;

    //offset for different turning values
    double left = 0.8;
    double right = 0.5;
    double center = 0.6;

    //variables needed for the color sensor
    String foundColor = null;
    String allianceColor = "red";

    @Override
    public void runOpMode() {

        //Initializing all parts
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        glyphArm.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("color");
        vuForia.init(hardwareMap);
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope) modernRoboticsI2cGyro;

        //calibrating gyro
        modernRoboticsI2cGyro.calibrate();

        //waiting for the start button to be pressed
        waitForStart();

        //getting the vumark
        RelicRecoveryVuMark vuMark = vuForia.getVuMark();

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
            move = 0;
            offset = 0.0;
            while (opModeIsActive() && timer.seconds() < 1.1) {
                glyphArm.armUp(0.4);
                heading = modernRoboticsI2cGyro.getHeading();
                if (heading >= 90 && !(heading >= 350) && move++ > 6) {
                    break;
                }
                offset = 0.1;
                driveTrain.move(0.1, offset, 0);
            }
            driveTrain.move(0.0, 0.0, 0);


            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.9) {
                //turns toward crypto box based on vumark
                if (vuMark == RelicRecoveryVuMark.CENTER)
                    driveTrain.move(0, 0, center);

                if (vuMark == RelicRecoveryVuMark.LEFT)
                    driveTrain.move(0, 0, left);

                if (vuMark == RelicRecoveryVuMark.RIGHT)
                    driveTrain.move(0, 0, right);
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
}

