package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "AutonomousODS")
@Disabled

public class AutonomousODS extends LinearOpMode {
        DriveTrain driveTrain = new DriveTrain();
        GlyphArm glyphArm = new GlyphArm();
        OpticalDistanceSensor ods;
        private ElapsedTime runtime = new ElapsedTime();

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
            ods = hardwareMap.get(OpticalDistanceSensor.class, "ods_sensor");

            //waiting for user to press start
            waitForStart();
            //moving off the balance beam with glyph secured in claw
            glyphArm.clawClose();
            glyphArm.moveUpOrDown(1);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 1.0)) {
                telemetry.addData("Elapsed ", runtime.seconds());
                telemetry.update();
            }
            glyphArm.moveUpOrDown(0);
            //testing ods
            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() < 30)){
                telemetry.addData("Status: ",    ods.status());
                telemetry.addData("Raw1: ",    ods.getRawLightDetected());
                telemetry.addData("Raw1 max: ",    ods.getRawLightDetectedMax());
                telemetry.addData("Normal1: ", ods.getLightDetected());
                telemetry.addData("Normal1 max: ", ods.getRawLightDetectedMax());

                telemetry.update();
            }
            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() < 10)){
                telemetry.addData("Status: ",    ods.status());
                telemetry.addData("Raw2: ",    ods.getRawLightDetected());
                telemetry.addData("Normal2: ", ods.getLightDetected());

                telemetry.update();
            }
            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() < 10)){
                telemetry.addData("Status: ",    ods.status());
                telemetry.addData("Raw3:",    ods.getRawLightDetected());
                telemetry.addData("Normal3:", ods.getLightDetected());

                telemetry.update();
            }

            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() < 10)){
                telemetry.addData("Status: ",    ods.status());
                telemetry.addData("Raw4:",    ods.getRawLightDetected());
                telemetry.addData("Normal4:", ods.getLightDetected());

                telemetry.update();
            }

            runtime.reset();
            while(opModeIsActive() && (runtime.seconds() < 10)){
                telemetry.addData("Status: ",    ods.status());
                telemetry.addData("Raw5:",    ods.getRawLightDetected());
                telemetry.addData("Normal5:", ods.getLightDetected());

                telemetry.update();
            }


            //move robot straight
           /* driveTrain.move(0.2, 0.0);
            runtime.reset();

            while (opModeIsActive() && (runtime.seconds() < 2.75)){

                telemetry.addData("Forward", runtime.seconds());
                telemetry.update();
            }

            //To stop the drive train
            driveTrain.move(0.0, 0.0);

            //turn towards crypto box
            glyphArm.clawOpen();
            */


        }
    }

