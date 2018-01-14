package org.techtonicgears.ftc.teamcode;

/*/ Imports /*/

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;



public class DriveTrain {

    /*/ Define all the motors and variables that are being used in DriveTrain /*/

    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor  leftBack  = null;
    DcMotor  rightBack  = null;
    HardwareMap hwMap = null;

    public void init(HardwareMap Map) {
        hwMap = Map;

        /*/ Naming all of the motors /*/

        leftFront = hwMap.get(DcMotor.class, "front_left");
        rightFront = hwMap.get(DcMotor.class, "front_right");
        leftBack = hwMap.get(DcMotor.class, "rear_left");
        rightBack = hwMap.get(DcMotor.class, "rear_right");

        /*/ Setting the direction for all the motors /*/

        leftFront.setDirection(DcMotor.Direction.FORWARD);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        leftBack.setDirection(DcMotor.Direction.FORWARD);
        rightBack.setDirection(DcMotor.Direction.REVERSE);

        /*/ Power of motors upon initialize. /*/

        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

        /*/ No encoder being used on the robot, so this line should be placed. /*/

        leftBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBack.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFront.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        /*/ Zero Power Behavior set to BRAKE because REV has default set to FLOAT. Without this line the robot drifts. /*/

        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    /*/ The controls give reverse values, thus, we are subtracting powers in the left to turn right since it actually shows up as a negative power, and vice versa /*/

        public void move(double speed, double offset, double strafe){
        leftFront.setPower(speed-offset-strafe);
        rightFront.setPower(speed+offset+strafe);
        leftBack.setPower(speed-offset+strafe);
        rightBack.setPower(speed+offset-strafe);
    }


}