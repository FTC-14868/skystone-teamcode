package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This file provides basic Telop driving for a Pushbot robot.
 * The code is structured as an Iterative OpMode
 *
 * This OpMode uses the common Pushbot hardware class to define the devices on the robot.
 * All device access is managed through the TTRobot class.
 *
 * This particular OpMode executes a basic Tank Drive Teleop for a PushBot
 * It raises and lowers the claw using the Gampad Y and A buttons respectively.
 * It also opens and closes the claws slowly using the left and right Bumper buttons.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="TTRobot: Teleop Tank", group="TTRobot")
//@Disabled
public class TTTankDriveOp extends OpMode {

    /* Declare OpMode members. */
    TTRobot robot;
    double Larm_Pos;
    double Rarm_Pos;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot = new TTRobot();
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Driver. HW info follows.");
        telemetry.addData("motorinfo", "leftDrive(%s/%s/%d), rightDrive(%s/%s/%d)",
                          robot.leftDrive.getManufacturer().toString(),
                            robot.leftDrive.getDeviceName(),
                            robot.leftDrive.getVersion(),
                            robot.rightDrive.getManufacturer().toString(),
                            robot.rightDrive.getDeviceName(),
                            robot.rightDrive.getVersion());
        /*
        telemetry.addData("colorSensor", "%s/%s/%d aRGB=%x.%x.%x.%x hue=%x",
                            robot.cSense.getManufacturer().toString(),
                            robot.cSense.getDeviceName(), robot.cSense.getVersion(),
                            robot.cSense.alpha(), robot.cSense.red(),
                            robot.cSense.green(), robot.cSense.blue(),
                            robot.cSense.argb());
        telemetry.addData("normColorSensor", "normalized aRBG=%08x",
                            robot.cSenseRGBA.getNormalizedColors().toColor());
        telemetry.addData("dSense", "OOR=%8.2e distance=%8.3e",
                            robot.dSense.distanceOutOfRange,
                            robot.dSense.getDistance(DistanceUnit.METER));
                            */
        telemetry.addData("Larmservo", "position=%3.2f", robot.Larm.getPosition());
        telemetry.addData("Rarmservo", "position=%3.2f", robot.Rarm.getPosition());

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
//        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double left;
        double right;
        // Run wheels in tank mode (note: The joystick goes negative when pushed forwards, so negate it)
        left = gamepad1.left_stick_y;
        right = gamepad1.right_stick_y;

        if (gamepad1.left_bumper || gamepad1.right_bumper) {
            left = left / 2;
            right = right / 2;
        }
        robot.leftDrive.setPower(left);
        robot.rightDrive.setPower(right);
        if (gamepad1.dpad_up && (Larm_Pos < Servo.MAX_POSITION)){
            Larm_Pos += .004;
        } else if (gamepad1.dpad_down && (Larm_Pos > Servo.MIN_POSITION)){
            Larm_Pos -= .004;
        }

        if (gamepad1.dpad_up && (Rarm_Pos < Servo.MAX_POSITION)){
            Rarm_Pos += .004;
        } else if (gamepad1.dpad_down && (Rarm_Pos > Servo.MIN_POSITION)){
            Rarm_Pos -= .004;
        }
        robot.Larm.setPosition(Larm_Pos);
        robot.Rarm.setPosition(Rarm_Pos);


        // Send telemetry message to signify robot running;
        //telemetry.addData("claw",  "Offset = %.2f", clawOffset);
        telemetry.addData("left",  "%.2f", left);
        telemetry.addData("right", "%.2f", right);
        telemetry.addData("Larmservo", "position=%4.3f, armpos=%4.3f", robot.Larm.getPosition(), Larm_Pos);
        telemetry.addData("Rarmservo", "position=%4.3f, armpos=%4.3f", robot.Rarm.getPosition(), Rarm_Pos);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}