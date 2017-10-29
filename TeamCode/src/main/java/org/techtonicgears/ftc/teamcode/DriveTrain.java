package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by vmujoo on 10/8/2017.
 */
@Disabled
public class DriveTrain {
    DcMotor leftFront   = null;
    DcMotor rightFront   = null;
    DcMotor  leftBack  = null;
    DcMotor  rightBack  = null;
    double speed = 0.0d;
    double offset = 0.0d;

    HardwareMap hwMap = null;
    private ElapsedTime period  = new ElapsedTime();

    public DriveTrain(){

    }
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

    }
    public void move(double power, double dif){
        speed = power;
        offset = dif;
        leftFront.setPower((speed-offset)/2);
        rightFront.setPower((speed+offset)/2);
        leftBack.setPower((speed-offset)/2);
        rightBack.setPower((speed+offset)/2);
    }







}