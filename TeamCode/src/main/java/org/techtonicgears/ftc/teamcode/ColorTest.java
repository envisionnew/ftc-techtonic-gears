package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

/**
 * Created by vmujoo on 11/5/2017.
 */

public class ColorTest extends LinearOpMode{
    ColorSensor colorSensor;

    @Override
    public void runOpMode() throws InterruptedException{
        colorSensor = hardwareMap.colorSensor.get("color");
        waitForStart();
        while(opModeIsActive()) {
            colorSensor.enableLed(true);
            if (colorSensor.red() > 10){

            }else if (colorSensor.blue() > 10){

            }else{

            }
            telemetry.addData("red Value",colorSensor.red());
            telemetry.addData("blue Value",colorSensor.blue());

        }
    }
}
