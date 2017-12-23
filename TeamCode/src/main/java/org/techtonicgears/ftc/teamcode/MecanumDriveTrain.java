package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class MecanumDriveTrain {

    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor  leftBack  = null;
    DcMotor  rightBack  = null;


    public void init(HardwareMap map) {

        leftFront = map.get(DcMotor.class, "front_left");
        rightFront = map.get(DcMotor.class, "front_right");
        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack = map.get(DcMotor.class, "rear_left");
        rightBack = map.get(DcMotor.class, "rear_right");
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


    }
    //the controls give reverse values, thus, we are subtracting powers in the left to turn right
    //since it actually shows up as a negative power
    public void move(double speed, double offset, double strafe){

        leftFront.setPower(speed - offset - strafe);
        rightFront.setPower(speed + offset + strafe);
        leftBack.setPower(speed + offset + strafe);
        rightBack.setPower(speed - offset - strafe);
    }

    //strafing is on right joystick x axis along with the regular tank motion of forward and back(y axis)
    /*public void strafe(double power){
        leftBack.setPower(-power);
        leftFront.setPower(power);
        rightBack.setPower(power);
        rightFront.setPower(-power);
    }*/

}