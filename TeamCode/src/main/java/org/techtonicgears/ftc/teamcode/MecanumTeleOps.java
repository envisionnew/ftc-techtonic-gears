package org.techtonicgears.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


//@Disabled
@TeleOp(name = "New TeleOp: Test ")
public class MecanumTeleOps extends OpMode {
    //All RobotParts
    MecanumDriveTrain drive = new MecanumDriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    RelicArm  relicArm = new RelicArm();
    JewelArm jewel = new JewelArm();

    //Variables
    double linearSp = 0.0d; //for glyph arm up/down movement
    double speed = 0.0d; //for drive forward speed
    double offset = 0.0d; //for drive turning
    double strafe = 0.0d;
    double clawPos = 0.0d; //relic claw position
    double arm1Pos = 0.0d; //the relic arm up/down pos
    double slidePos = 0.0d; //relic arm extend movement
    int height = 0; //height of glyph arm to stop at right height
    boolean armMode = false; //the mode of arm, relic or glyph
    boolean control = false; //to make sure timer.reset() only happens once
    @Override
    public void init() {
        //Init all RobotParts
        glyphArm.init(hardwareMap);
        drive.init(hardwareMap);
        relicArm.init(hardwareMap);
        jewel.init(hardwareMap);

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
        //Jewel set arm up
        jewel.setJewelArm(0);

        //Modes to make gamepad control easier
        //x is for glyph controls, b is for relic controls
        if(gamepad2.x) {
            armMode = false;
        }else if(gamepad2.b){
            armMode = true;
        }

        //Drive Part
        speed = -gamepad1.right_stick_y;
        //clip speed to stop too fast power
        speed = Range.clip(speed, -0.5, 0.5);

        strafe = -gamepad1.right_stick_x;
        strafe = Range.clip(strafe, -0.5, 0.5);

        //divide offset by two to control turn
        offset = gamepad1.left_stick_x/2;


        drive.move(speed, offset, strafe);
        leftfront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //GlyphArm part
        // for moving up and down by about a glyph length
        if(armMode == false) {
            if (gamepad2.right_stick_y < 0 && control == false && height < 2) {
                linearSp = 0.1;
                telemetry.addData("Speed", linearSp);
                telemetry.update();
                control = true;
                glyphArm.time.reset();
                height++;
            } else if (gamepad2.right_stick_y > 0 && control == false && height > 0) {
                linearSp = -0.1;
                telemetry.addData("Speed", linearSp);
                telemetry.update();
                control = true;
                glyphArm.time.reset();
                height--;
            }

            //moving with minor change for precision
            if(gamepad2.left_stick_y < 0){
                linearSp = 0.3;
            }else if(gamepad2.left_stick_y > 0){
                linearSp = -0.3;
            }else if(glyphArm.time.seconds() > 0.4){
                control = false;
                linearSp = 0;
            }
            glyphArm.moveUpOrDown(linearSp);
            // when the triggers are pressed, the claw opens/closes
            if (gamepad2.right_trigger > 0) {
                glyphDC.clawOpen();
                telemetry.addLine("Claw Open");
                telemetry.update();
            } else if (gamepad2.left_trigger > 0) {
                glyphArm.clawClose();
                telemetry.addLine("Claw Close");
                telemetry.update();
            }
        }

        /*/Relic Arm Part
        if(armMode == true) {
            //extending part of the relic arm
            if (gamepad2.right_stick_y < 0) {
                slidePos = -1d;
            } else if (gamepad2.right_stick_y > 0) {
                slidePos = 1d;
            } else {
                slidePos = 0;
            }
            //claw for grabbing the relic

            if (gamepad2.right_trigger > 0) {
                clawPos += 0.01d;
            }

            if (gamepad2.left_trigger > 0) {
                clawPos -= 0.01d;
            }
            //lifting up the relic after picking it up to clear the wall
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
        //moving relic
        relicArm.RelicExt(slidePos);
        relicArm.ClawMove(relicArm.relicClaw_ST+clawPos);
        relicArm.ArmMove(relicArm.relicArm1_ST+arm1Pos);
/*/
        //Sending messages
        //glyphArm.getPosition(telemetry);
        // telemetry.addData("Power",speed);
        telemetry.addData("Time: ",glyphArm.time.seconds());
        telemetry.addData("Arm1Pos", arm1Pos);
        telemetry.update();


    }
    @Override
    public void stop() {
    }
}