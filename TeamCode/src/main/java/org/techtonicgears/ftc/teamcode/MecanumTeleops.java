package org.techtonicgears.ftc.teamcode;

/**
 * Created by ritali on 12/19/17.
 */
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

//@Disabled
@TeleOp(name = "New TeleOp: Test ")
public class MecanumTeleops extends OpMode {
    //All RobotParts
    MecanumDriveTrain drive = new MecanumDriveTrain();


    @Override
    public void init() {
        //Init all RobotParts
        drive.init(hardwareMap);
        //Start telemetry message
        telemetry.addData("", "Press Start");
        telemetry.update();
    }
    @Override
    public void init_loop(){
    }
    @Override
    public void start(){
    }
    @Override
    public void loop() {

       drive.move(gamepad1.right_stick_y, gamepad1.right_stick_x,gamepad1.left_stick_x);

    }
    @Override
    public void stop() {
    }
}
