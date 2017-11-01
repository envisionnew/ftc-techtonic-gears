package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by vmujoo on 9/21/2017.
 */
@TeleOp(name = "TeleOp: Old")
public class TeleOpOld extends OpMode{
    //All RobotParts
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    RelicArm  arm = new RelicArm();

    //Variables
    double linearSp = 0.0d;
    double speed = 0.0d;
    double offset = 0.0d;
    double clawPos = 0.0d;
    double arm1Pos = 0.0d;
    double slidePos = 0.0d;
    boolean mode = false;
    @Override
    public void init() {
        //Init all RobotParts
        glyphArm.init(hardwareMap);
        drive.init(hardwareMap);
        arm.init(hardwareMap);

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
        if(gamepad2.right_stick_y < 0){

            linearSp = 1;
        }else if(gamepad2.right_stick_y > 0){
            linearSp = -1;
        }else{
            linearSp = 0;
        }
        glyphArm.moveUpOrDown(linearSp);

        if(gamepad2.left_bumper){
            glyphArm.clawClose();
        }else if(gamepad2.right_bumper){
            glyphArm.clawOpen();
        }

        //Drive Part
        if(gamepad1.y){
            mode = false;
        }else if(gamepad1.a){
            mode = true;
        }
        if(mode == false) {
            speed = -gamepad1.right_stick_y;
        }else{
            speed = gamepad1.right_stick_y;
        }
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.left_stick_x/2;

        drive.move(speed, offset);
        //Relic Arm Part
        if(gamepad2.left_stick_y <0){

            slidePos = 1d;
        }else if(gamepad2.left_stick_y >0 ) {

            slidePos = -1d;
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

        //Sending messages
        //glyphArm.getPosition(telemetry);
        // telemetry.addData("Power",speed);
        // telemetry.addData("Offset",offset);
        telemetry.addData("UpTime", arm.Uptime);

        telemetry.update();


    }
    @Override
    public void stop() {
    }
}