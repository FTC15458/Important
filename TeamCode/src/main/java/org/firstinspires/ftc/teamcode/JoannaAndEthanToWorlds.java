package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//import com.qualcomm.robotcore.eventloop.opmode.IterativeOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.IterativeOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="GaryToWorlds", group="Iterative Opmode")
// @Disabled
public class JoannaAndEthanToWorlds extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive = null;
    private DcMotor frDrive = null;
    private DcMotor blDrive = null;
    private DcMotor brDrive = null;
    //private DcMotor flyWheel = null;
    private DcMotor arm = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    private Servo rotateServo = null;
    private ElapsedTime eTime = new ElapsedTime();

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // {ialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        flDrive = hardwareMap.get(DcMotor.class, "flDrive");
        frDrive = hardwareMap.get(DcMotor.class, "frDrive");
        blDrive = hardwareMap.get(DcMotor.class, "blDrive");
        brDrive = hardwareMap.get(DcMotor.class, "brDrive");
        arm = hardwareMap.get(DcMotor.class, "arm");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");
        rotateServo = hardwareMap.get(Servo.class, "rotateServo");

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        blDrive.setDirection(DcMotor.Direction.REVERSE);
        brDrive.setDirection(DcMotor.Direction.FORWARD);
        flDrive.setDirection(DcMotor.Direction.REVERSE);
        frDrive.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);

        blDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        brDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        flDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

    }


    // Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY

    @Override
    public void init_loop() {
    }


    // Code to run ONCE when the driver hits PLAY

    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;
        double servoPower;

        // Choose to drive using either Tank Mode, or POV Mode
        // Comment out the method that's not used.  The default below is POV.

        // POV Mode uses left stick to go forward, and right stick to turn.
        // - This uses basic math to combine motions and is easier to drive straight.
        double drive = -gamepad1.left_stick_x;
        double turn  =  gamepad1.left_stick_y;
        leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
        rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;


        // Tank Mode uses one stick to control each wheel.
        // - This requires no math, but it is hard to drive forward slowly and keep straight.
        // leftPower  = -gamepad1.left_stick_y ;
        // rightPower = -gamepad1.right_stick_y ;

        // Send calculated power to wheels

        //drives chasis
        if (gamepad1.right_stick_x > 0.6){
            flDrive.setPower(-1);
            frDrive.setPower(-1);
            blDrive.setPower(1);
            brDrive.setPower(1);
        }
        else if (gamepad1.right_stick_x < -0.6){
            flDrive.setPower(1);
            frDrive.setPower(1);
            blDrive.setPower(-1);
            brDrive.setPower(-1);
        }
        else{
            flDrive.setPower(leftPower);
            frDrive.setPower(rightPower);
            blDrive.setPower(leftPower);
            brDrive.setPower(rightPower);
        }

        //arm movement
        if (gamepad2.left_stick_y>0) { //down, weight will pull this down
            arm.setPower(0.20);
        } else if (gamepad2.left_stick_y<0) { //up
            arm.setPower(-0.35);
        } else if (gamepad2.triangle)
            arm.setPower(0);
        else {
            arm.setPower(-0.1);
        }

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);



        //servo moving
        telemetry.addData("Right Trigger", gamepad2.right_trigger); //check the darn servo wiring please and thank you
        if (gamepad2.right_trigger > 0){
            leftServo.setPosition(.2);//actually right servo, .15 is ideal
            rightServo.setPosition(0.45);
        }
        else if (gamepad2.left_trigger >0){
            leftServo.setPosition(0.07);//0.075 is ideal open
            rightServo.setPosition(0.65);//0.6 is ideal open
        }

        //rotateServo rotates
    /*   if (gamepad2.y && !lastY) {
           currentServoIndex++;
           rotateServo.setPosition(currentServoIndex % 3);
    }

        //only use when putting on claw
        //leftServo.setPosition(0);
        //rightServo.setPosition(1);
       // lastY = gamepad2.y;
    }
*/

    }
}

