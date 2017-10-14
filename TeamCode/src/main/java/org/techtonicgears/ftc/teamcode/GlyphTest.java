package org.techtonicgears.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name= "GlyphTest: Ritali")
//@Disabled
public class GlyphTest extends OpMode
{
    GlyphArm glyphArm = new GlyphArm();
    double clawPos = 0.0d;

    public void init(){
        glyphArm.init(hardwareMap);
        telemetry.addData("Waiting: ", "For start");
        telemetry.update();
    }

    public void init_loop(){
    }

    public void start(){
        telemetry.addData("Started","");
        telemetry.update();
    }

    public void loop(){

       /* double speed = -gamepad2.right_stick_y;
        speed = Range.clip(speed, -0.5, 0.5);
        glyphArm.moveUpOrDown(speed);
*/
        if(gamepad2.left_bumper){
            clawPos += 0.01d;
            telemetry.addData("Left Bumper Pressed", clawPos);
            telemetry.update();
        }

        if(gamepad2.right_bumper){
            clawPos -= 0.01d;
            telemetry.addData("Right Bumper Pressed", clawPos);
            telemetry.update();
        }

           clawPos = Range.clip(clawPos, -0.5, 0.5);
           glyphArm.moveClaw(clawPos);
    }

    public void stop(){
    }
}
