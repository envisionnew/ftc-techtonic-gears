package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BlueBack extends LinearOpMode {
    GlyphArm glyphArm = new GlyphArm();
    DriveTrain driveTrain = new DriveTrain();
    JewelArm jewelArm = new JewelArm();
    ElapsedTime timer = new ElapsedTime();

    double left = 0.5;
    double middle = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        timer.reset();
        glyphArm.clawClose();

        waitForStart();

        while (opModeIsActive() && timer.seconds() < 0.5) {
            glyphArm.moveUpOrDown(0.3);
            jewelArm.setJewelArm(0.7);
        }
        timer.reset();
        if (jewelArm.colorSensor.blue() > jewelArm.colorSensor.red()) {
            move(0,0,0.5,2);
            jewelArm.setJewelArm(0);
            move(0,0,-0.5,4);
        } else {
            move(0,0,-0.5,2);
            jewelArm.setJewelArm(0);
        }
        move(0.5,0,0,2);
        while (opModeIsActive()&& jewelArm.gyroSensor.getHeading() > 180){
            driveTrain.move(0,-0.5,0);
        }
        move(0,0,0.5, left);
        move(0.5,0,0,2.5);

    }
    public void move(double speed, double offset, double strafe, double time){
        timer.reset();
        while (opModeIsActive() && timer.seconds() < time) {
            driveTrain.move(speed, offset, strafe);
        }
    }
}
