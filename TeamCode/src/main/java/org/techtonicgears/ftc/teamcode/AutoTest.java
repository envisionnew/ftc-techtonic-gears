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
    @Override
    public void runOpMode() throws InterruptedException{
        waitForStart();
        totalTime.reset();
        while(opModeIsActive() && totalTime.seconds() < 3){
            drive.init(hardwareMap);
            drive.timer.reset();
            drive.moveSec(1,1,0);
            drive.moveSec(2,0,1);
        }

    }
}
