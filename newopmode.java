package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class newopmode extends OpMode {
    @Override
    public void  init() {
        telemetry.addData("hello wow","wow");

    }
    @Override
    public void  loop() {

    }
}
