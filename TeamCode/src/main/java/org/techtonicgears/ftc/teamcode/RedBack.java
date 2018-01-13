package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.vuforia.Vuforia;

@Autonomous(name = "RedBack")
public class RedBack extends LinearOpMode {
    //Declaring glyph arm
    GlyphArm glyphArm = new GlyphArm();
    //Declaring Drive train
    DriveTrain driveTrain = new DriveTrain();
    //Declaring Jewel Arm
    JewelArm jewelArm = new JewelArm();
    //Declaring Timer
    ElapsedTime timer = new ElapsedTime();
    /* Declaring the doubles that store the value that the robot has to move
    to reach both of the two columns */
    double left = 0.5;
    double middle = 1;
    double right = 0;
    String teamColor = "red";
    String foundColor = "";

    ModernRoboticsI2cGyro gyro;
    ColorSensor colorSensor;
    Vuforia vuforia = new Vuforia();

    @Override
    public void runOpMode() throws InterruptedException {
        //Initializing all RobotParts and giving them a hardwareMap input
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);

        colorSensor = hardwareMap.get(ColorSensor.class, "color");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");


        //Resetting the timer


        gyro.calibrate();
        while (!isStopRequested() && gyro.isCalibrating())  {
            telemetry.addData("Calibrating","");
            telemetry.update();
            sleep(50);
        }


        //Waiting for the start button to be pressed
        waitForStart();

        timer.reset();
        glyphArm.clawClose();

        while (opModeIsActive() && timer.seconds() < 0.4) {
            glyphArm.moveUpOrDown(0.3);
        }
        timer.reset();

        colorSensor.enableLed(true);

        while (opModeIsActive() && timer.seconds() < 2) {
            jewelArm.setJewelArm(0.65);
            //returns which color the jewel is
            if (colorSensor.red() > colorSensor.blue()) {
                foundColor = "red";
            } else if (colorSensor.blue() > colorSensor.red()) {
                foundColor = "blue";
            }
        }
        timer.reset();
        if (teamColor == foundColor) {
            telemetry.addData("Blue", "");
            telemetry.update();
            while (opModeIsActive() && timer.seconds() < 0.3) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes the other jewel
                driveTrain.move(0, 0.5, 0);
            }
            // if the color of the jewel is not the same as the color of the team

            timer.reset();
            while (opModeIsActive() && gyro.getHeading() < 359){
                driveTrain.move(0, -0.4, 0);
                jewelArm.setJewelArm(0);
            }


        } else if (foundColor != teamColor ){
            telemetry.addData("Red", "");
            telemetry.update();
            while (opModeIsActive() && timer.seconds() < 0.3) {
                jewelArm.setJewelArm(0.65);
                //moves and pushes off the same jewel it detects because colors dont match
                driveTrain.move(0, -0.5, 0);
            }
            while (opModeIsActive() && gyro.getHeading() > 1){
                driveTrain.move(0, 0.4,0);
            }
        }
        /*
        while (opModeIsActive() && timer.seconds() < 1){
            driveTrain.move(0.5, 0,0);
        }
        */

    }
}