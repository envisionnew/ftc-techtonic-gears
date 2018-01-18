package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

public class BlueBack extends LinearOpMode {
    //Declaring glyph arm
    GlyphArm glyphArm = new GlyphArm();
    //Declaring Drive train
    DriveTrain driveTrain = new DriveTrain();
    //Declaring Jewel Arm
    JewelArm jewelArm = new JewelArm();
    //Declaring Timer
    ElapsedTime timer = new ElapsedTime();
    /*PARTS THAT ARE NOT ON ANY OF THE INDIVIDUAL CLASSES HAVE TO BE SEPERATELY
      DEFINED IN THE PROGRAM, INCLUDING GYRO*/
    GyroSensor gyroSensor;
    /* Declaring the doubles that store the value that the robot has to move
    to reach both of the two columns */
    double left = 0.5;
    double middle = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        //Initializing all RobotParts and giving them a hardwareMap input
        glyphArm.init(hardwareMap);
        driveTrain.init(hardwareMap);
        jewelArm.init(hardwareMap);
        gyroSensor = hardwareMap.get(GyroSensor.class, "gyro");
        //Resetting the timer
        timer.reset();
        //Opening the glyph arm claw
        glyphArm.clawOpen();

        //Waiting for the start button to be pressed
        waitForStart();

        //While loop saying in less then .5 seconds, move the glyph arm up and Jewel arm down
        while (opModeIsActive() && timer.seconds() < .5) {
            //Moves the glyph arm up at 0.3 power
            glyphArm.moveUpOrDown(0.3);
            //Moves the jewel arm down at 0.7 power
            jewelArm.setJewelArm(0.7);
        }
        //if statement for detecting jewel color
        if (jewelArm.colorSensor.blue() > jewelArm.colorSensor.red()) {
            //Move statement saying if jewel is blue, move sideways to the right
            move(0, 0, 0.5, 2);
            //then, bring the jewel arm back up
            jewelArm.setJewelArm(0);
            //Move over the balancing board
            move(0, 0, -0.5, 4);
            //Else saying what to do if red
        } else {
            //Move opposite direction sideways
            move(0, 0, -0.5, 2);
            //Set the jewel arm position back to zero, moving it up
            jewelArm.setJewelArm(0);
        }
        //Move the robot forward so that it can turn without hitting the crypto box
        move(0.5, 0, 0, 2);
        //while loop saying turn until gyro sensor turned 180
        while (opModeIsActive()&& gyroSensor.getHeading() > 180){
            driveTrain.move(0,-0.5,0);
        }
        //Moving sideways to the right depending on which column the robot has to go to
        move(0, 0, 0.5, left);
        //Moving forward to get to the crypto box
        move(0.5, 0, 0, 2.5);

    }
    //Method for moving  so that the while OpModIsActive loop is not repeated so many times
    //Accepts speed, offset, strafe just like driveTrain.move , but also accepts a time
    public void move(double speed, double offset, double strafe, double time) {
        //Resetting the timer
        timer.reset();
        //while loop to move the robot
        while (opModeIsActive() && timer.seconds() < time) {
            driveTrain.move(speed, offset, strafe);
        }
    }
}
