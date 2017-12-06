package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.GyroSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/*
 * Created by vmujoo on 11/28/2017.
 */

public class SensorsTest extends LinearOpMode{
    DistanceSensor distanceSensor;
    //I2c
    ColorSensor colorSensor;
    //I2c
    GyroSensor gyroSensor;
    //I2c
    @Override
    public void runOpMode() throws InterruptedException {
        distanceSensor = hardwareMap.get(DistanceSensor.class,"Distance");
        colorSensor = hardwareMap.get(ColorSensor.class,"color");
        gyroSensor = hardwareMap.get(GyroSensor.class, "gyro");
        gyroSensor.calibrate();
        waitForStart();
        gyroSensor.resetZAxisIntegrator();
        while(opModeIsActive()) {
            telemetry.addData("Distance", distanceSensor.getDistance(DistanceUnit.CM));
            telemetry.addData("Color", colorSensor.blue());
            //Clockwise equals 0++
            telemetry.addData("Gyro", gyroSensor.getHeading());
        }
        telemetry.update();
    }
}
