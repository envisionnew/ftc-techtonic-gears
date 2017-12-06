package org.techtonicgears.ftc.teamcode;
//This package is the package we use for all of our programs

//We have imported the DcMotor class, HardwareMap class, servo class, and the timer
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//Class Name
public class TechRobot {
    //Stating all needed motors
    DcMotor leftFront = null;
    DcMotor leftBack = null;
    DcMotor rightFront = null;
    DcMotor rightBack = null;
    Servo RightClaw = null;
    Servo LeftClaw = null;
    //#DcMotor frontArm = null;
    //Creating a timer object
    ElapsedTime timer = new ElapsedTime();

    //Initialization method: declares what the robot should do when the init button is pressed
    public void init(HardwareMap hwMap) {
        leftFront = hwMap.get(DcMotor.class, "left_front");
        leftBack = hwMap.get(DcMotor.class, "left_back");
        rightFront = hwMap.get(DcMotor.class, "right_front");
        rightBack = hwMap.get(DcMotor.class, "right_back");
        LeftClaw = hwMap.get(Servo.class, "front_left");
        RightClaw = hwMap.get(Servo.class, "front_right");

        //#frontArm = hwMap.get(DcMotor.class, "front_up");
        //Sets the initial direction of the dc motors
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
       //#.setDirection(DcMotor.Direction.FORWARD);

        //Sets the initial servo direction
        LeftClaw.setPosition(0);
        RightClaw.setPosition(1);

        //
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //frontArm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void move(double forward, double turn) {
        leftFront.setPower((forward+turn)/2);
        rightFront.setPower((forward-turn)/2);
        leftBack.setPower((-forward+turn)/2);
        rightBack.setPower((-forward-turn)/2);
    }
    public void turn(double power){
        leftFront.setPower(power/2);
        rightFront.setPower(-power/2);
        leftBack.setPower(-power/2);
        rightBack.setPower(power/2);

    }
    public void ClawClose(){
        LeftClaw.setPosition(1);
        RightClaw.setPosition(0);
    }
    public void ClawOpen(){
        LeftClaw.setPosition(0);
        RightClaw.setPosition(1);
    }
}
