package org.techtonicgears.ftc.teamcode;

/**
 * Created by Saurish on 1/26/18.
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
@Autonomous(name = "RedBackNew")
public class RedBackNew extends LinearOpMode {

    /*/ Declaring the Variables /*/
    GlyphArm glyphArm = new GlyphArm();
    DriveTrain driveTrain = new DriveTrain();
    JewelArm jewelArm = new JewelArm();
    ElapsedTime timer = new ElapsedTime();
    /* Declaring the doubles that store the value that the robot has to move to reach both of the two columns */
    VuForia vuForia = new VuForia();
    double left = 1.7;
    double middle = 1.43;
    double right = 1;
    String teamColor = "red";
    String foundColor = "";
    RelicRecoveryVuMark foundVuMark;
    double used = 0;
    ModernRoboticsI2cGyro gyro;
    ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException {
      /*/ Initializing all RobotParts and giving them a hardwareMap input /*/
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        vuForia.init(hardwareMap);
        colorSensor = hardwareMap.get(ColorSensor.class, "color");
        gyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "gyro");

      /*/ Resetting the timer /*/

        jewelArm.setJewelArm(1);
        gyro.calibrate();
        while (!isStopRequested() && gyro.isCalibrating()) {
            telemetry.addData("Calibrating", "");
            telemetry.update();
            sleep(50);
        }
        //Waiting for the start button to be pressed
        waitForStart();
        foundVuMark = vuForia.getVuMark();
        timer.reset();
        glyphArm.clawClose();
        while (opModeIsActive() && timer.seconds() < 1.3) {
            glyphArm.moveUpOrDown(-0.4);
        }
        timer.reset();
        colorSensor.enableLed(true);
        while (opModeIsActive() && timer.seconds() < 2) {
            glyphArm.moveUpOrDown(-0.15);
            jewelArm.setJewelArm(0);
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
            while (opModeIsActive() && timer.seconds() < 0.6) {
                jewelArm.setJewelArm(0);
                //moves and pushes the other jewel
                driveTrain.move(0, 0.25, 0);
            }
            jewelArm.setJewelArm(1);
            // if the color of the jewel is not the same as the color of the team
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.2) {
                driveTrain.move(0, -0.25, 0);
            }
            timer.reset();
            while (opModeIsActive() && gyro.getHeading() < 359) {
                driveTrain.move(0, -0.25, 0);
                jewelArm.setJewelArm(1);
            }
        } else if (foundColor != teamColor) {
            telemetry.addData("Red", "");
            telemetry.update();
            while (opModeIsActive() && timer.seconds() < 0.6) {
                jewelArm.setJewelArm(0);
                //moves and pushes off the same jewel it detects because colors dont match
                driveTrain.move(0, -0.25, 0);
            }
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 0.2) {
                driveTrain.move(0, 0.25, 0);
            }
            jewelArm.setJewelArm(1);
            while (opModeIsActive() && gyro.getHeading() > 1) {
                driveTrain.move(0, 0.25, 0);
            }
        }
        gyro.resetZAxisIntegrator();
        telemetry.addData("gyro", gyro.getHeading());
        telemetry.update();
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 0.3) {
            driveTrain.move(0, 0.25, 0);
        }
        while (opModeIsActive() && gyro.getHeading() > 273) {
            driveTrain.move(0.1, 0.25, 0);
        }


        pause(1);
      /*/ Look here. /*/

        if (foundVuMark.equals(RelicRecoveryVuMark.RIGHT)) {
            used = right;
        } else if (foundVuMark.equals(RelicRecoveryVuMark.LEFT)) {
            used = left;
        } else if (foundVuMark.equals(RelicRecoveryVuMark.CENTER)) {
            used = middle;
        } else {
            used = middle;
        }

        timer.reset();
        while (opModeIsActive() && timer.seconds() < used) {
            driveTrain.move(0.5, 0, 0);
        }

        pause(0.1);

        gyro.resetZAxisIntegrator();
        pause(1);
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1) {
            driveTrain.move(0, 0.25, 0);
        }
        while (opModeIsActive() && gyro.getHeading() > 270) {
            driveTrain.move(0, 0.25, 0);
        }


        pause(1);
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1.3) {
            driveTrain.move(0.25, 0, 0);

        }

        pause(1);
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 0.5) {
            glyphArm.clawOpen();
            driveTrain.move(-0.25, 0, 0);
        }


        pause(1);
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1) {
            glyphArm.moveUpOrDown(0);
            glyphArm.clawOpen();
            driveTrain.move(-0.25, 0, 0);
        }
        pause(1);

        timer.reset();
        while (opModeIsActive() && timer.seconds() < 0.5) {
            glyphArm.clawClose();
        }

        pause(1);

        timer.reset();
        while (opModeIsActive() && timer.seconds() < 2) {
            driveTrain.move(0.25, 0, 0);
        }

        pause(1);

        timer.reset();
        while (opModeIsActive() && timer.seconds() < 1) {
            driveTrain.move(-0.25, 0, 0);
        }
        pause(1);

    }

    public void pause(double seconds) {
        timer.reset();
        while (opModeIsActive() && timer.seconds() < seconds) {
            driveTrain.move(0, 0, 0);
            telemetry.addData("Wait", seconds);
            telemetry.update();
        }
    }
}
