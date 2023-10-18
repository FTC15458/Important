package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
@Autonomous

public class GreenMachineLeftAuto extends LinearOpMode {
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor flDrive = null;
    private DcMotor frDrive = null;
    private DcMotor blDrive = null;
    private DcMotor brDrive = null;
    private DcMotor arm = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    private Servo rotateServo = null;
    
    @Override 
    public void runOpMode() 
    {
      
      flMotor = hardwareMap.get(DcMotor.class, "flMotor ");
      frMotor = hardwareMap.get(DcMotor.class, "frMotor");
      blMotor = hardwareMap.get(DcMotor.class, "blMotor");
      brMotor = hardwareMap.get(DcMotor.class, "brMotor");
      arm = hardwareMap.get(DcMotor.class, "arm");
      leftServo = hardwareMap.get(Servo.class, "leftServo");
      rightServo = hardwareMap.get(Servo.class,"rightServo");
      
      telemetry.addData("Status", "Waiting_For_Start" );
      telemetry.update();

      waitForStart();

      frMotor.setPower(1);
      flMotor.setPower(1);
      brMotor.setPower(1);
      blMotor.setPower(1);
      sleep(1000);
   }
  }

      
