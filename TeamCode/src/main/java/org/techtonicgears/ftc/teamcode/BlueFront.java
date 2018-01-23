package org.techtonicgears.ftc.teamcode;


import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;
import com.qualcomm.robotcore.util.ElapsedTime;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

@Autonomous(name = "BlueFront")
public class BlueFront extends LinearOpMode {
    //define the parts that will be moving
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    VuForia vuforia = new VuForia();

    private ElapsedTime runtime = new ElapsedTime();
    //color of the team
    java.lang.String teamColor = "blue";
    java.lang.String foundColor = null;
    ColorSensor colorSensor;
    //ModernRoboticsI2cRangeSensor rangeSensor;
    ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    IntegratingGyroscope gyro;

    @Override
    public void runOpMode() {
        double offset = 0.0;
        int heading = 0;
        int move = 0;
        double armPower = 0.4;

        //Initializing and performing init methods
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        vuforia.init(hardwareMap);

        colorSensor = hardwareMap.colorSensor.get("color");
        //rangeSensor = hardwareMap.get(ModernRoboticsI2cRangeSensor.class, "range");
        modernRoboticsI2cGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");
        gyro = (IntegratingGyroscope)modernRoboticsI2cGyro;


        // Start calibrating the gyro.
        // during the initialization phase at the start of each opMode.
        telemetry.log().add("Gyro Calibrating. Do Not Move!");
        modernRoboticsI2cGyro.calibrate();

        // Wait until the gyro calibration is complete
        runtime.reset();
        while (!isStopRequested() && modernRoboticsI2cGyro.isCalibrating())  {
            telemetry.addData("calibrating", "%s", Math.round(runtime.seconds())%2==0 ? "|.." : "..|");
            telemetry.update();
            sleep(50);
        }

        //waiting for user to press start
        waitForStart();

        //get VuMark
        RelicRecoveryVuMark vuMark = vuforia.getVuMark();

        //grab glyph and lift the glyph
        glyphArm.clawClose();
        glyphArm.moveUpOrDown(armPower);

        //turn on color sensor light
        colorSensor.enableLed(true);

        //drop jewel arm so color sensor can detect
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 0.5) {
            glyphArm.armUp(armPower);
            jewelArm.setJewelArm(0);
            //returns which color the jewel is
            if (colorSensor.red() > colorSensor.blue()) {
                foundColor = "red";
            } else if (colorSensor.blue() > colorSensor.red()) {
                foundColor = "blue";
            }
        }

        while (opModeIsActive() && runtime.seconds() < 1) {
            heading = modernRoboticsI2cGyro.getHeading();
            telemetry.addData("heading ", heading);
            telemetry.addData("vuMark ", vuMark);
            telemetry.addData("foundColor ", foundColor);
            telemetry.update();
        }

        //check if the color the jewel it detects is same as team color
        runtime.reset();
        if (teamColor == foundColor) {
            while (opModeIsActive() && runtime.seconds() < 0.1) {
                glyphArm.armUp(armPower);
                jewelArm.setJewelArm(0);
                //moves and pushes the other jewel
                driveTrain.move(0, 1, 0);
            }
            offset = -0.1;
            driveTrain.move(0, 0, 0);
            while (opModeIsActive() && runtime.seconds() < 0.4) {
                glyphArm.armUp(armPower);
                jewelArm.setJewelArm(1);
            }
            // if the color of the jewel is not the same as the color of the team
        } else if (foundColor != null){
            while (opModeIsActive() && runtime.seconds() < 0.1) {
                glyphArm.armUp(armPower);
                jewelArm.setJewelArm(0);
                //moves and pushes off the same jewel it detects because colors dont match
                driveTrain.move(0, -1, 0);
            }
            offset = 0.1;
            driveTrain.move(0, 0, 0);
            while (opModeIsActive() && runtime.seconds() < 0.4) {
                jewelArm.setJewelArm(1);
            }
        }

        //get down from the board
        runtime.reset();
        while (opModeIsActive() && runtime.seconds() < 4.5) {
            glyphArm.armUp(armPower);
            heading = modernRoboticsI2cGyro.getHeading();
            if (heading == 0) offset = 0.0;
            driveTrain.move(0.1, offset, 0);
            telemetry.addData("heading ", heading);
            telemetry.addData("offset", offset);
            telemetry.addData("vuMark ", vuMark);
            telemetry.addData("foundColor ", foundColor);
            telemetry.update();
        }
        driveTrain.move(0, 0, 0);

        //turn left 90 degrees
        runtime.reset();
        move = 0;
        offset = 0.0;
        while (opModeIsActive() && runtime.seconds() < 1.1) {
            glyphArm.armUp(armPower);
            heading = modernRoboticsI2cGyro.getHeading();
            if (heading >= 90 && !(heading >= 350) && move++ > 6) {
                break;
            }
            offset -= 0.1;
            driveTrain.move(0.1, offset, 0);
        }
        driveTrain.move(0.0, 0.0, 0);

        // head to crypto box
        runtime.reset();
        //double Gap = 255;
        //while (opModeIsActive() && Gap > 30) {
        //Gap = rangeSensor.getDistance(DistanceUnit.CM);
        while (opModeIsActive() && (runtime.seconds() < 9)) {
            glyphArm.armUp(armPower);
            heading = modernRoboticsI2cGyro.getHeading();
            offset = 0.0;
            if (heading > 90) {
                offset = 0.1;
            }
            if (heading < 90 ){
                offset = -0.1;
            }
            // strafe based on vuMark
            if (vuMark == RelicRecoveryVuMark.CENTER && (runtime.seconds() > 5) && (runtime.seconds() < 6.5) ) {
                driveTrain.move(0.1, 0, -0.3);
            } else
            if (vuMark == RelicRecoveryVuMark.LEFT && (runtime.seconds() > 6)  && (runtime.seconds() < 7.5) ) {
                driveTrain.move(0.1, 0, -0.5);
            } else {
                driveTrain.move(0.1, offset, 0);
            }
            telemetry.addData("heading ", heading);
            telemetry.addData("offset", offset);
            telemetry.addData("vuMark ", vuMark);
            telemetry.addData("foundColor ", foundColor);
            // telemetry.addData("Gap", Gap);
            telemetry.update();
        }

        driveTrain.move(0.0, 0.0, 0);

        // bring arm down
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 2)) {
            glyphArm.armDown(armPower);
        }

        //claw open and move back
        runtime.reset();
        glyphArm.clawOpen();
        while (opModeIsActive() && (runtime.seconds() < 2.5)) {
            driveTrain.move(-0.1, 0, 0);
        }
        driveTrain.move(0.0, 0.0, 0);

        // push the glyph into crypto box
        runtime.reset();
        glyphArm.clawClose();
        while (opModeIsActive() && (runtime.seconds() < 5)) {
            heading = modernRoboticsI2cGyro.getHeading();
            offset = 0.0;
            if (heading > 90) {
                offset = 0.1;
            }
            if (heading < 90 ){
                offset = -0.1;
            }
            driveTrain.move(0.1, offset, 0);
        }
        driveTrain.move(0.0, 0.0, 0);

        //move back
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 3)) {
            driveTrain.move(-0.1, 0, 0);
        }
        driveTrain.move(0.0, 0.0, 0);

        // The End!!
        while (opModeIsActive()) {
            telemetry.addData("heading ", heading);
            telemetry.addData("vuMark ", vuMark);
            telemetry.addData("foundColor ", foundColor);
            telemetry.update();
        }
    }
}
