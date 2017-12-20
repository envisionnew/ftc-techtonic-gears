package org.techtonicgears.ftc.teamcode;

/*/ Imports /*/

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    /*/ Define the DC Motors /*/

    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor  leftBack  = null;
    DcMotor  rightBack  = null;


    public void init(HardwareMap map) {

        /*/ Configurations for the DC Motors /*/

        /*/  Names for the DC Motors /*/

        leftFront = map.get(DcMotor.class, "front_left");
        rightFront = map.get(DcMotor.class, "front_right");
        leftBack = map.get(DcMotor.class, "rear_left");
        rightBack = map.get(DcMotor.class, "rear_right");

        /*/ Allows robot to move Left and Right /*/

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        /*/ The power is set to 0 because the robot should not move when initialized. /*/

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        /*/ Used because we have no encoder on robot /*/

        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }

    public void move(double speed, double offset){

        /*/ Move Method /*/

        /*/ Helps turn the robot, both left and right /*/

        leftFront.setPower(speed-offset);
        leftBack.setPower(speed-offset);
        rightFront.setPower(speed+offset);
        rightBack.setPower(speed+offset);

    }
}