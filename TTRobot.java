package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is TTRobot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left  drive motor:        "left_drive"
 * Motor channel:  Right drive motor:        "right_drive"
 * Motor channel:  Manipulator drive motor:  "hang_arm
 * Servo channel:  Servo to control marker drop:  "marker_servo"
 *
 * See HardwarePushbot for another example
 */

public class TTRobot {
    /* Public OpMode members. */
    public DcMotor leftDrive   = null;
    public DcMotor rightDrive  = null;
    public Servo Larm = null;
    public Servo Rarm = null;
    /*
    public DcMotor hangArm     = null;
    public Servo markerServo   = null;
    public ColorSensor cSense = null;
    public DistanceSensor dSense = null;
    public NormalizedColorSensor cSenseRGBA = null;

    public static final double MID_SERVO       =  0.5 ;
    public static final double ARM_UP_POWER    =  0.45 ;
    public static final double ARM_DOWN_POWER  = -0.45 ;
    */

    /* local OpMode members. */
    HardwareMap hwMap           =  null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public TTRobot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive  = hwMap.get(DcMotor.class, "left_drive");
        rightDrive = hwMap.get(DcMotor.class, "right_drive");
        Larm = hwMap.get (Servo.class, "larm_servo");
        Rarm = hwMap.get (Servo.class, "rarm_servo");
        /*
        cSense    = hwMap.get(ColorSensor.class, "color_sensor");
        cSenseRGBA = hwMap.get(NormalizedColorSensor.class, "color_sensor");
        dSense    = hwMap.get(DistanceSensor.class, "color_sensor");
         */
        leftDrive.setDirection(DcMotor.Direction.FORWARD); // Set to FORWARD if using AndyMark motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE);// Set to REVERSE if using AndyMark motors

        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        //hangArm.setPower(0);
        Larm.setDirection(Servo.Direction.REVERSE);
        Larm.setPosition(Servo.MIN_POSITION);
        Larm.scaleRange(Servo.MIN_POSITION, Servo.MAX_POSITION*0.6);
        Rarm.setDirection(Servo.Direction.FORWARD);
        Rarm.setPosition(Servo.MIN_POSITION);
        Rarm.scaleRange(Servo.MIN_POSITION, Servo.MAX_POSITION*0.6);
        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        //hangArm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        //markerServo = hwMap.get(Servo.class, "marker_servo");
        //markerServo.setPosition(MID_SERVO);
    }
}
