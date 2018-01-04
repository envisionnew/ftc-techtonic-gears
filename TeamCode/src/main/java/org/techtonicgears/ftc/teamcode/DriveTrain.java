package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class DriveTrain {
    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor  leftBack  = null;
    DcMotor  rightBack  = null;
    double speed;
    double offset;
    HardwareMap hwMap = null;

    public void init(HardwareMap Map) {
        hwMap = Map;
        leftFront = hwMap.get(DcMotor.class, "front_left");
        rightFront = hwMap.get(DcMotor.class, "front_right");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack = hwMap.get(DcMotor.class, "rear_left");
        rightBack = hwMap.get(DcMotor.class, "rear_right");
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //Zero power behavior set to brake because rev has default set to float without this line it drifts
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }
    //the controls give reverse values, thus, we are subtracting powers in the left to turn right
    //since it actually shows up as a negative power
    public void move(double speed, double offset, double strafe){
        leftFront.setPower(speed-offset-strafe);
        rightFront.setPower(speed+offset+strafe);
        leftBack.setPower(speed-offset+strafe);
        rightBack.setPower(speed+offset-strafe);
    }


}