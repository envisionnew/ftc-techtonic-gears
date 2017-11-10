package org.techtonicgears.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.hardware.*;

@Autonomous(name = "ColorTest")
public class ColorTest extends LinearOpMode{
    //defining the sensor
    ColorSensor colorSensor;

    @Override
    public void runOpMode(){
        colorSensor = hardwareMap.colorSensor.get("color");
        waitForStart();
        while(opModeIsActive()) {
            colorSensor.enableLed(true);
            //The value goes up to around 20, which is complete red/blue. We predict that 3 should detect the color properly.
            if (colorSensor.red() > 3){

            }else if (colorSensor.blue() > 3){

            }else{

            }
            //Return the value of the blue/red.
            telemetry.addData("red Value",colorSensor.red());
            telemetry.addData("blue Value",colorSensor.blue());
            telemetry.update();

        }
    }
}
