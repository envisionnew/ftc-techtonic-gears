package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

/**
 * Created by vmujoo on 12/3/2017.
 */

public class AutoMove extends LinearOpMode{
    TechRobot robot = new TechRobot();
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        while(opModeIsActive() && robot.timer.seconds() <  1){
            robot.move(0.2, 0);
        }
    }
    public void linearDistance(double power, int distance){
        double rate = 2;
        double time = 1/power * (distance/rate);
        robot.timer.reset();

        while(opModeIsActive() && robot.timer.seconds() <  time){
            robot.move(power, 0);
        }
        robot.move(0,0);
    }
}
