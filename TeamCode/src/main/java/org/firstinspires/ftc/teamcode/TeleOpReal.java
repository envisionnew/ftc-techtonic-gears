package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by vmujoo on 9/21/2017.
 */
@TeleOp(name = "TeleOp: Drive")
public class TeleOpReal extends OpMode{
    //All RobotParts
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();

    //Variables
    double clawPos = 0.0d;
    double lienarSp = 0.0d;
    double speed = 0.0d;
    double offset = 0.0d;
    @Override
    public void init() {
        //Init all RobotParts
        glyphArm.init(hardwareMap);
        drive.init(hardwareMap);

        //Start telemetry message
        telemetry.addData("", "Press Start");
        telemetry.update();

        //Init all variables
        drive.offset = 0.0d;
        drive.speed = 0.0d;
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start() {

    }
    @Override
    public void loop() {
        //GlyphArm part
        lienarSp = -gamepad2.right_stick_y;
        lienarSp = Range.clip(lienarSp, -0.5, 0.5);
        glyphArm.moveUpOrDown(lienarSp);

        if(gamepad2.left_bumper){
            glyphArm.clawClose();
        }
        if(gamepad2.right_bumper){
            glyphArm.clawOpen();
        }

        //Drive Part
        speed = -gamepad1.right_stick_y;
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.left_stick_x/2;

        drive.move(speed, offset);

        //Sending messages
        glyphArm.getPosition(telemetry);
        telemetry.addData("Power",speed);
        telemetry.addData("Offset",offset);
    }
    @Override
    public void stop() {
    }
}