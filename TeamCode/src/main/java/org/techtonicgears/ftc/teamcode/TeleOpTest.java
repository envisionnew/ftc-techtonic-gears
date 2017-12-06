package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "TeleOpTest")
public class TeleOpTest extends OpMode {
    TechRobot robot = new TechRobot();
    boolean mode = false;


    @Override
    public void init() {
        robot.init(hardwareMap);
        robot.timer.reset();
    }
    @Override
    public void init_loop(){

    }
    @Override
    public void start() {}

    @Override
    public void loop() {
        if(gamepad1.x){
            mode = false;
        }else if(gamepad1.b){
            mode = true;
        }

        if(mode == false) {

            if (gamepad1.right_stick_y != 0 || gamepad1.right_stick_x != 0) {
                robot.move(-(Math.abs(gamepad1.right_stick_y)* gamepad1.right_stick_y), Math.abs(gamepad1.right_stick_x)*gamepad1.right_stick_x);
            }else if(gamepad1.left_stick_x != 0){
                robot.turn(Math.abs(gamepad1.left_stick_x)*gamepad1.left_stick_x);
            }else{
                robot.move(0,0);
            }

        }else if(mode == true){
            if (gamepad1.right_stick_y != 0 || gamepad1.right_stick_x != 0) {
                robot.move(Math.abs(gamepad1.right_stick_y)* gamepad1.right_stick_y, Math.abs(gamepad1.right_stick_x)*gamepad1.right_stick_x);
            } else {
                robot.turn(Math.abs(gamepad1.left_stick_x)*gamepad1.left_stick_x);
            }
        }
        if (gamepad2.left_trigger > 0){
            robot.ClawOpen();
        }
        if (gamepad2.right_trigger > 0){
            robot.ClawClose();
        }
        /*if(gamepad2.right_stick_y < 0){
            robot.frontArm.setPower(-0.25);

        }else if(gamepad2.right_stick_y > 0) {
            robot.frontArm.setPower(0.15);
        }else{
            robot.frontArm.setPower(-0.05);
        }*/
    }
    @Override
    public void stop(){

    }
}
