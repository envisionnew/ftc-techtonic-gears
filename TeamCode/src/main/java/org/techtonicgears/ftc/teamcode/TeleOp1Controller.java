package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by vmujoo on 9/21/2017.
 */
@TeleOp(name = "TeleOp: 1 Controller")
@Disabled
public class TeleOp1Controller extends OpMode{
    //All RobotParts
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
//    RelicArm  arm = new RelicArm();

    //Variables
    //Wheels
    double drSpeed = 0.0d;
    double wheelOffset = 0.0d;
    //Glyph arm
    double clawPos = 0.0d;
    double linearSp = 0.0d;
    //Relic Arm
    double arm1Pos = 0.0d;
    double slidePos = 0.0d;
    @Override
    public void init() {
        //Init all RobotParts
        glyphArm.init(hardwareMap);
        drive.init(hardwareMap);
//        arm.init(hardwareMap);

        //Start telemetry message
        telemetry.addData("", "Press Start");
        telemetry.update();
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
        if(gamepad1.y){
            linearSp = 0.5;
            glyphArm.moveUpOrDown(linearSp);
        }else if(gamepad1.a ){
            linearSp = -0.5;
            glyphArm.moveUpOrDown(linearSp);
        }else{
            linearSp = 0;
        }

        if(gamepad1.left_bumper){
            glyphArm.clawClose();
        }else if(gamepad1.right_bumper){
            glyphArm.clawOpen();
        }

        //Drive Part
        drSpeed = -gamepad1.right_stick_y;
        drSpeed = Range.clip(drSpeed, -0.5, 0.5);
        wheelOffset = gamepad1.left_stick_x/2;

        drive.move(drSpeed, wheelOffset);

       /* if(gamepad2.y){
            slidePos = 0.5d;
        }else if(gamepad2.a) {
            slidePos = -0.5d;
        }else{
            slidePos = 0;
        }

        if(gamepad2.right_trigger>0) {
            clawPos += 0.01d;
        }

        if(gamepad2.left_trigger>0) {
            clawPos -= 0.01d;

        }
        if(gamepad2.x) {
            arm1Pos += 0.01d;
        }
        if(gamepad2.b) {
            arm1Pos -= 0.01d;
        }

        arm.RelicExt(slidePos);
        arm.ClawMove(arm.relicClaw_ST+clawPos);
        arm.ArmMove(arm.relicArm1_ST+arm1Pos);
        */

        //Sending messages
        //glyphArm.getPosition(telemetry);
       // telemetry.addData("Power",speed);
       // telemetry.addData("Offset",offset);

        
    }
    @Override
    public void stop() {
    }
}