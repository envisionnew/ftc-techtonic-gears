package org.firstinspires.ftc.teamcode.org.techtonicgrars.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


public class RelicArm {
    public DcMotor relicSlide   = null;
    public Servo relicClaw = null;
    public Servo relicArm1 = null;
    public Servo relicArm2 = null;
    public final double relicArm1_ST = 0.5;
    public final double relicArm2_ST = 0.5;
    public final double relicClaw_ST = 0.5;
    HardwareMap hwMap = null;

    private ElapsedTime period  = new ElapsedTime();

    public void init(HardwareMap Map) {
        hwMap = Map;
        relicSlide = hwMap.get(DcMotor.class, "Relic_Slide");
        relicClaw = hwMap.get(Servo.class, "Relic_Claw");
        relicArm1 = hwMap.get(Servo.class, "Relic_Arm_1");
        relicArm2 = hwMap.get(Servo.class, "Relic_Arm_2");
        relicSlide.setDirection(DcMotor.Direction.FORWARD);
        relicClaw.setPosition(relicClaw_ST);
        relicArm1.setPosition(relicArm1_ST);
        relicArm2.setPosition(relicArm2_ST);
        relicSlide.setPower(0);
        relicSlide.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}