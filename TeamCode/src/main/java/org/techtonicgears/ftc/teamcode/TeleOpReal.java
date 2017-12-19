package org.techtonicgears.ftc.teamcode;

    /*/ Imports /*/

    import com.qualcomm.robotcore.eventloop.opmode.OpMode;
    import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
    import com.qualcomm.robotcore.util.Range;

    /*/ Named the TeleOp /*/

    @TeleOp(name = "TeleOp: Real")

        public class TeleOpReal extends OpMode{



            DriveTrain drive = new DriveTrain();
            GlyphArm glyphArm = new GlyphArm();
            RelicArm  arm = new RelicArm();
            JewelArm jewel = new JewelArm();

            /*/ Define the variables. /*/

            /*/ Used for the glyph arm movement. (Up and Down) /*/

            double linearSp = 0.0d;

            /*/ Used for the driving motion. (Forward Speed) /*/

            double speed = 0.0d;

            /*/ Used for the driving motion. (Turning) /*/

            double offset = 0.0d;

            /*/ This is the relic claw position . /*/

            double clawPos = 0.0d;

            /*/ Used for the relic claw movement. (Up and Down) /*/

            double arm1Pos = 0.0d;

            /*/ Used for the Relic Arm Extension /*/

            double slidePos = 0.0d;

            /*/ Height of glpyh arm. (This is so the glyph arm does not break.) /*/

            int height = 0;

            /*/ Used for driving (Forward / Reverse) /*/

            boolean mode = false;

            /*/ Mode of the arm (Relic or Glyph) /*/


            boolean armMode = false;

            /*/ Used to make sure that timer.reset() is used/happens only once. /*/
            boolean control = false;
            @Override
                public void init() {

                /*/ Inits all the robot parts /*/

                glyphArm.init(hardwareMap);
                drive.init(hardwareMap);
                arm.init(hardwareMap);
                jewel.init(hardwareMap);

                /*/ Starts the telemetry message. /*/

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

                    /*/ Setting the Jewel Arm /*/

        jewel.setJewelArm(0);

        /*/ Modes to make the Game Pad easier. /*/
        /*/ Reversing the driving to make the robot go in the reverse direction. (If going forward, now you would go reverse and vice versa.) /*/
        if(gamepad1.a){
            mode = false;
        }
        else if(gamepad1.y){
            mode = true;
        }

        /*/ If X is pressed, the Glyph Arm will be initialized. If B is pressed, the Relic Arm will be initalized. /*/

        if(gamepad2.x) {
            armMode = false;
        }
        else if(gamepad2.b){
            armMode = true;
        }

        //Drive Part
        //switch between negative and positive power
        if(mode == false) {
            speed = -gamepad1.right_stick_y;
        }else{
            speed = gamepad1.right_stick_y;
        }
        //clip speed to stop too fast power
        speed = Range.clip(speed, -0.5, 0.5);
        //divide offset by two to control turn
        offset = gamepad1.left_stick_x/2;

        drive.move(speed, offset);

        /*/ Glyph Arm Part /*/

        /*/ Used for moving the Glyph Arm One Glyph Length Higher. (To Place in Cryptobox) /*/

        if(armMode == false) {
            if (gamepad2.right_stick_y < 0 && control == false && height < 2) {
                linearSp = 1;
                control = true;
                glyphArm.time.reset();
                height++;
            }
            else if (gamepad2.right_stick_y > 0 && control == false && height > 0) {
                linearSp = -1;
                control = true;
                glyphArm.time.reset();
                height--;
            }

            /*/ Moving with a Minor Change for Precision. /*/

            if(gamepad2.left_stick_y < 0){
                linearSp = 0.3;
            }
            else if(gamepad2.left_stick_y > 0){
                linearSp = -0.3;
            }
            else if(glyphArm.time.seconds() > 0.4){
                control = false;
                linearSp = 0;
            }

            glyphArm.moveUpOrDown(linearSp);

            if (gamepad2.right_trigger > 0) {
                glyphArm.clawOpen();
            }
            else if (gamepad2.left_trigger > 0) {
                glyphArm.clawClose();
            }
        }

        /*/ Relic Arm Part /*/

        if(armMode == true) {
            /*/ Extending the relic arm out. /*/

            if (gamepad2.right_stick_y < 0) {
                slidePos = -1d;
            }
            else if (gamepad2.right_stick_y > 0) {
                slidePos = 1d;
            }
            else {
                slidePos = 0;
            }

            /*/ Part for the claw on the Relic Arm /*/

            if (gamepad2.right_trigger > 0) {
                clawPos += 0.01d;
            }

            if (gamepad2.left_trigger > 0) {
                clawPos -= 0.01d;

            }
            /*/ Part for lifting the Relic Arm so it can be extended over the wall. /*/

            if (arm1Pos > 1) {
                arm1Pos = 1;
            }
            else if (arm1Pos < -1) {
                arm1Pos = -1;
            }

            if (clawPos > 1) {
                clawPos = 1;
            }
            else if (clawPos < -1) {
                clawPos = -1;
            }

            if (gamepad2.a) {
                arm1Pos += 0.01d;
            }
            if (gamepad2.y) {
                arm1Pos -= 0.01d;
            }

        }

        /*/ Used for moving the Relic Arm. /*/

        arm.RelicExt(slidePos);
        arm.ClawMove(arm.relicClaw_ST+clawPos);
        arm.ArmMove(arm.relicArm1_ST+arm1Pos);

        /*/ Sending messages via Telemetry. /*/

        /*/glyphArm.getPosition(telemetry); /*/

        /*/ telemetry.addData("Power", speed); /*/

        telemetry.addData("time",glyphArm.time.seconds());
        telemetry.addData("Arm1Pos", arm1Pos);
        telemetry.update();

    }
    @Override
    public void stop() {
    }

}