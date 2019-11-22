package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name="TTRobot: Auto Drive By Encoder", group="TTRobot")
public class AutoV1 extends OpMode {
    /* Declare OpMode members. */
    enum OpState {START, Forward1, ARM_DOWN1, PULL_FOUNDATION, Left_Turn1, Reverse1, Forward2, PUSH_FOUNDATION, NAVIGATE, PARK;}
    double left_power, right_power, left_desired_power, right_desired_power;
    int left_position, right_position;
    TTRobot robot;
    double state_start_time, c_time;
    OpState state = OpState.START, nextstate = OpState.START;
    boolean firstpass = true;

    public void setstate(OpState newstate){
        nextstate = newstate;
        state_start_time = time;
        firstpass = true;
    }

    public int inch_to_enc_tick(double inch) {
        return (int) (inch*25.89);
    }
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

        setstate (OpState.START);
        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
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
        state = nextstate;
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        setstate (OpState.START);
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

        // next state is current state
        nextstate = state;

        if (state == OpState.START) {
            if (firstpass) firstpass = false;

            if ( time > state_start_time+1.0) {
                setstate (OpState.Forward1);
            }

        } else if (state == OpState.Forward1) {
            if (firstpass) {
                firstpass = false;
                robot.leftDrive.setDirection(DcMotor.Direction.REVERSE);
                robot.rightDrive.setDirection(DcMotor.Direction.FORWARD);
                robot.leftDrive.setTargetPosition(inch_to_enc_tick(30.5));
                robot.rightDrive.setTargetPosition(inch_to_enc_tick(30.5));
                robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_power = right_power = 1.0;
            }
            robot.leftDrive.setPower(left_power);
            robot.rightDrive.setPower(right_power);

            if ( !robot.leftDrive.isBusy() && !robot.rightDrive.isBusy()) {
                setstate(OpState.ARM_DOWN1);
            }
        } else if (state == OpState.ARM_DOWN1) {
            if (firstpass) {
                firstpass = false;
                robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.Larm.setPosition(Servo.MAX_POSITION);
                robot.Rarm.setPosition(Servo.MAX_POSITION);
            }
            if (time > state_start_time+1.0) {
                setstate(OpState.PULL_FOUNDATION);
            }
        } else if (state == OpState.PULL_FOUNDATION) {
            if (firstpass) {
                firstpass = false;

                robot.leftDrive.setDirection(DcMotor.Direction.FORWARD);
                robot.rightDrive.setDirection(DcMotor.Direction.REVERSE);
                robot.leftDrive.setTargetPosition(inch_to_enc_tick(20.0));
                robot.rightDrive.setTargetPosition(inch_to_enc_tick(20.0));
                robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                left_power = right_power = 0.5;
            }
            if (time>c_time+0.2) {
                c_time=time;
                left_power+=0.05;
                right_power+=0.05;
                robot.leftDrive.setPower(left_power);
                robot.rightDrive.setPower(right_power);
            }
            if ( !robot.leftDrive.isBusy() && !robot.rightDrive.isBusy()) {
                setstate(OpState.PARK);
            }
        } else if (state == OpState.PUSH_FOUNDATION) {
            if (firstpass) {
                firstpass = false;
            }
            if (time>c_time+0.75) {
                c_time=time;
                left_power+=0.2;
                right_power+=0.2;
                robot.leftDrive.setPower(left_power);
                robot.rightDrive.setPower(right_power);
            }
            if (!robot.leftDrive.isBusy() && !robot.rightDrive.isBusy()) {
                setstate(OpState.PARK);
            }
        } else if (state == OpState.PARK) {
            if (firstpass) {
                firstpass = false;
                robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }

        }

        // Send telemetry message to signify robot running;
        //telemetry.addData("claw",  "Offset = %.2f", clawOffset);
        telemetry.addData("left",  "%.2f", left_power);
        telemetry.addData("right", "%.2f", right_power);
        telemetry.addData("state", state.toString());

        // post loop

        // switch to next state if it's changed
        state = nextstate;
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
