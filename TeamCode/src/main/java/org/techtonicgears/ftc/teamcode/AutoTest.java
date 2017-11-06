package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;

/**
 * Created by vmujoo on 11/3/2017.
 */
@Autonomous(name = "AutoTest")
public class AutoTest extends LinearOpMode{
    ElapsedTime totalTime = new ElapsedTime();
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        totalTime.reset();
        while(opModeIsActive() && totalTime.seconds() < 2.2){
            drive.init(hardwareMap);
            glyphArm.init(hardwareMap);
            drive.timer.reset();
            drive.runwithArm = true;
            drive.moveSec(2,0.5,0);
            drive.runwithArm = false;
            drive.moveSec(0.2,0.2,0);
        }
    }
}
