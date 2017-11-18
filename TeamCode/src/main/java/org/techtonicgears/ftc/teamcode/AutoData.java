package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name = "AutoData")
public class AutoData extends OpMode{
    //All RobotParts
    DriveTrain drive = new DriveTrain();
    GlyphArm glyphArm = new GlyphArm();
    JewelArm jewel = new JewelArm();
    ElapsedTime timer = new ElapsedTime();

    //Variables
    int counter = 0;
    boolean control = false;
    double[] data = new double[5];
    @Override
    public void init() {
        //Init all RobotParts
        glyphArm.init(hardwareMap);
        drive.init(hardwareMap);
        jewel.init(hardwareMap);

        //Start telemetry message
        telemetry.addData("", "Press Start");
        telemetry.update();
    }

    @Override
    public void init_loop(){
    }
    @Override
    public void start() {
        glyphArm.clawClose();
    }
    @Override
    public void loop() {
        if(counter == 0) {
            recordMove(-0.1, 0, 0);
        }else if(counter == 1) {
            recordMove(0, -0.1, 1);
        }else if(counter == 2) {
            recordMove(-0.1, -0.1, 2);
        }else if(counter == 3){
            recordMove(0.1, 0, 3);
        }
        printData();

    }
    @Override
    public void stop() {
    }
    public void recordMove(double power, double dif, int pos){
            if (gamepad1.a && control == false) {
                timer.reset();
                drive.move(power, dif);
                control = true;
            } else if (gamepad1.y && control == true) {
                drive.move(0, 0);
                data[pos] = timer.seconds();
                control = false;
                counter++;
            }
    }
    public void printData(){
        telemetry.addData("1st forward",data[0]);
        telemetry.addData("1st turn",data[1]);
        telemetry.addData("2nd forward",data[2]);
        telemetry.addData("2nd turn",data[3]);
        telemetry.addData("3rd forward",data[4]);
        telemetry.update();
    }
    public void moveSec(double sec, double power, double offset){
        while (timer.seconds() < sec){
            drive.move(power,offset);
        }
        timer.reset();
        drive.move(0,0);
    }

}