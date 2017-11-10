package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name = "TeleOp: Real")
public class TeleOpReal extends OpMode{
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
    int height = 0;
    boolean mode = false;
    boolean armMode = false;
    boolean control = false;
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
        glyphArm.time.reset();
    }
    @Override
    public void loop() {

        //Modes
        if(gamepad1.a){
            mode = false;
        }else if(gamepad1.y){
            mode = true;
        }

        if(gamepad2.x) {
            armMode = false;
        }else if(gamepad2.b){
            armMode = true;
        }

        //Drive Part
        if(mode == false) {
            speed = -gamepad1.right_stick_y;
        }else{
            speed = gamepad1.right_stick_y;
        }
        speed = Range.clip(speed, -0.5, 0.5);
        offset = gamepad1.left_stick_x/2;

        drive.move(speed, offset);

        //GlyphArm part

        if(armMode == false) {
            if (gamepad2.right_stick_y < 0 && control == false && height < 2) {
                linearSp = 1;
                control = true;
                glyphArm.time.reset();
                height++;
            } else if (gamepad2.right_stick_y > 0 && control == false && height > 0) {
                linearSp = -1;
                control = true;
                glyphArm.time.reset();
                height--;
            }

            if(gamepad2.left_stick_y < 0){
                linearSp = 0.3;
            }else if(gamepad2.left_stick_y > 0){
                linearSp = -0.3;
            }else if(glyphArm.time.seconds() > 0.4){
                control = false;
                linearSp = 0;
            }
            glyphArm.moveUpOrDown(linearSp);

            if (gamepad2.right_trigger > 0) {
                glyphArm.clawOpen();
            } else if (gamepad2.left_trigger > 0) {
                glyphArm.clawClose();
            }
        }

        //Relic Arm Part
        if(armMode == true) {
            if (gamepad2.right_stick_y < 0) {
                slidePos = -1d;
            } else if (gamepad2.right_stick_y > 0) {
                slidePos = 1d;
            } else {
                slidePos = 0;
            }

            if (gamepad2.right_trigger > 0) {
                clawPos += 0.01d;
            }

            if (gamepad2.left_trigger > 0) {
                clawPos -= 0.01d;

            }
            if (arm1Pos > 1) {
                arm1Pos = 1;
            } else if (arm1Pos < -1) {
                arm1Pos = -1;
            }

            if (clawPos > 1) {
                clawPos = 1;
            } else if (clawPos < -1) {
                clawPos = -1;
            }

            if (gamepad2.a) {
                arm1Pos += 0.01d;
            }
            if (gamepad2.y) {
                arm1Pos -= 0.01d;
            }
        }

        arm.RelicExt(slidePos);
        arm.ClawMove(arm.relicClaw_ST+clawPos);
        arm.ArmMove(arm.relicArm1_ST+arm1Pos);

        //Sending messages
        //glyphArm.getPosition(telemetry);
        // telemetry.addData("Power",speed);
        telemetry.addData("time",glyphArm.time.seconds());
        telemetry.addData("Arm1Pos", arm1Pos);
        telemetry.update();


    }
    @Override
    public void stop() {
    }

}