package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutonomousGear")
//@Disabled
public class AutonomousGear extends LinearOpMode{
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

        while (opModeIsActive() && (runtime.seconds() < 0.5)) {
            telemetry.addData("Elapsed ", runtime.seconds());
            telemetry.update();
        }
        glyphArm.moveUpOrDown(0);

        //move robot straight
        driveTrain.move(1.0, 0.0);
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 3.0)){
            telemetry.addData("Forward", runtime.seconds());
            telemetry.update();
        }
        driveTrain.move(0.0, 0.0);

        //turn towards crypto box
        glyphArm.clawOpen();


    }
}

