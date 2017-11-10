package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.util.*;

/**
 * Created by vmujoo on 11/3/2017.
 */
@Autonomous(name = "AutoTest")
public class AutoTest extends LinearOpMode{
    ElapsedTime timer = new ElapsedTime();
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    boolean moveWithArm = false;
    boolean turn = true;

    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        drive.init(hardwareMap);
        glyphArm.init(hardwareMap);
        timer.reset();
        if(turn == false) {
            moveWithArm = true;
            moveSec(1, 0.5, 0.01);
            moveWithArm = false;
            moveSec(1.5, 0.2, 0);
        }else{
            moveWithArm = true;
            moveSec(0.7, 0.5, 0);
            moveSec(0.8, 0, 1);
            moveWithArm = false;
            moveSec(0.8, 0.2, 0);
        }
    }
    public void moveSec(double sec, double power, double offset){
        while (opModeIsActive() && timer.seconds() < sec){

            drive.move(power,offset);
            if(moveWithArm == true) {
                glyphArm.clawOpen();
            }else {
                glyphArm.clawClose();
            }
        }
        timer.reset();
    }
}
