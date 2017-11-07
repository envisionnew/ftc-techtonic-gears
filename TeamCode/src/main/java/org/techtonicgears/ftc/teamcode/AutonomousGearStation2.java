package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutonomousGearStation2")
public class AutonomousGearStation2 extends LinearOpMode{
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    private ElapsedTime runtime = new ElapsedTime();

    //Variables
    double linearSp = 0.0d;
    double speed = 0.0d;
    double offset = 0.0d;
    double clawPos = 0.0d;

    @Override
    public void runOpMode(){
        //Initializing and performing init methods
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);

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

        //move robot straight
        driveTrain.move(0.3, 0.0);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 1.0)){
            telemetry.addData("Forward", runtime.seconds());
            telemetry.update();
        }

        //To stop the drive train
        driveTrain.move(0.0, 0.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.5)){
            telemetry.addData("Stopped", runtime.seconds());
            telemetry.update();
        }

        //turning left to crypto box
        driveTrain.move(0.2, -0.5);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.0)){
            telemetry.addData("Turning", runtime.seconds());
            telemetry.update();
        }

        //turn towards crypto box
        glyphArm.clawOpen();
    }
}

