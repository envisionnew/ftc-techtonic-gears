package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * Created by ritali on 1/3/18.
 */

@Autonomous
public class RedBackLeft extends LinearOpMode {
    //defining each part for the robot
    DriveTrain driveTrain = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewelArm = new JewelArm();
    ColorSensor colorSensor;
    //defining the elapsed time so we can move robot using seconds
    ElapsedTime elapsedTime = new ElapsedTime();

    @Override
    public void runOpMode() {

        //initialize methods
        glyphArm.init(hardwareMap);
        jewelArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        colorSensor = hardwareMap.colorSensor.get("colorsensor");
        //wait for the user to press start button
        waitForStart();

        //grab the glyph by closing the claw
        glyphArm.clawClose();

    /* move the arm up slightly so it doesn't drag the glyph across
        the ground and create friction */
        //timer is reset
        elapsedTime.reset();

        //for 0.5 secs, the opmode will have the arm move up at power 0.15
        while (opModeIsActive() && elapsedTime.seconds() < 0.5) {
            glyphArm.moveUpOrDown(0.15);
        }
        //stop glyph arm movement by setting power to 0
        glyphArm.moveUpOrDown(0);

        //detect color of the jewel and move based upon the input
        //turn on the color sensor
        colorSensor.enableLed(true);
        //drop the jewel arm
        elapsedTime.reset();
        //setting jewel arm position
        while (opModeIsActive() && elapsedTime.seconds() < 2) {
            jewelArm.setJewelArm(0.7);
        }

        /*the if statement detects which values the robot is looking at
        sensor finds out which jewel it is looking at */
        if(colorSensor.red() > colorSensor.blue()){
        //if the sensor detects the red jewel
            //robot needs to strafe to the right
            elapsedTime.reset();
            while (opModeIsActive() && elapsedTime.seconds() < 2.2) {
                driveTrain.move(0, 0, 0.5);
            }
            //lift up the jewel arm
            jewelArm.setJewelArm(0);
            //get back on balancing stone and regain lost ground
          /*(basically get back where you would have been if
             the jewels had been flipped */
            elapsedTime.reset();
            while (opModeIsActive() && elapsedTime.seconds() < 4.4) {
                driveTrain.move(0, 0, -0.5);
            }


        }
        //if the sensor sees the blue jewel
        else {
            // strafe to the left to knock off the other one
            elapsedTime.reset();
            while(opModeIsActive() && elapsedTime.seconds() < 2.2) {
                driveTrain.move(0, 0, -0.5);
            }

        }

          //move forward to avoid hitting the crypto box
            elapsedTime.reset();
          while (opModeIsActive() && elapsedTime.seconds() < 2){
              driveTrain.move(0.3, 0, 0);
          }

          //turn 180 degrees to face the crypto box
            elapsedTime.reset();
          while (opModeIsActive() && elapsedTime.seconds() < 3){
              driveTrain.move(0, 0.5, 0);
          }

          //strafe to position itself
            elapsedTime.reset();
          while (opModeIsActive() && elapsedTime.seconds() < 1) {
              driveTrain.move(0, 0, 0.1);
          }
          //Move forward to place the glyph
            elapsedTime.reset();
          while (opModeIsActive() && elapsedTime.seconds() < 2){
              driveTrain.move(0.7, 0, 0);
          }




    }
}
