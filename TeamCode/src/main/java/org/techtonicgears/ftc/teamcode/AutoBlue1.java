package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutonomousReal")
public class AutoBlue1 extends LinearOpMode {
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    private ElapsedTime runtime = new ElapsedTime();
    java.lang.String teamColor = "blue";
    java.lang.String foundColor = null;
    ColorSensor colorSensor;

    //Variables
    double linearSp = 0.0d;
    double speed = 0.0d;
    double offset = 0.0d;
    double clawPos = 0.0d;

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
        glyphArm.moveUpOrDown(0.3);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)) {
            telemetry.addData("Elapsed ", runtime.seconds());
            telemetry.update();
        }
        glyphArm.moveUpOrDown(0);

        //turn on color sensor light
        colorSensor.enableLed(true);
        //drop jewel arm so color sensor can detect
        timer.reset();
        while (opModeIsActive() && timer.seconds() < 2) {
            jewelArm.setJewelArm(0.45);
            //returns which color the jewel is
            if (colorSensor.red() > 3) {
                foundColor = "red";
            } else if (colorSensor.blue() > 3) {
                foundColor = "blue";
            } else {

            }
        }
        timer.reset();
        //checks if the color the jewel it detects is same as team color
        if (teamColor == foundColor) {
            while (opModeIsActive() && timer.seconds() < 0.5) {
                jewelArm.setJewelArm(0.45);
                //moves and pushes the other jewel
                drive.move(0.1, 0);
            }
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 1) {
                jewelArm.setJewelArm(0);
            }

        } else {
            while (opModeIsActive() && timer.seconds() < 0.5) {
                jewelArm.setJewelArm(0.45);
                //moves and pushes off the same jewel it detects because colors dont match
                drive.move(-0.1, 0);
            }
            timer.reset();
            while (opModeIsActive() && timer.seconds() < 1) {
                jewelArm.setJewelArm(0);
                drive.move(0.2, 0);
            }

            //starting crypto box movement

            //move robot straight
            driveTrain.move(0.2, 0.1);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.9)) {
                telemetry.addData("Forward", runtime.seconds());
                telemetry.update();
            }

            //To stop the drive train
            driveTrain.move(0.0, 0.0);

            //turn towards crypto box
            glyphArm.clawOpen();
        }
    }
}
