package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DcMotor;
@Autonomous

public class IHopeThisWorks extends LinearOpMode {
    private ElapsedTime eTime = new ElapsedTime();   
    private DcMotor flMotor = null;
    private DcMotor frMotor = null;
    private DcMotor brMotor = null;
    private DcMotor blMotor = null;
    private DcMotor arm = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    
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
      eTime.reset();
                
      flMotor.setDirection(DcMotorSimple.Direction.FORWARD);
      frMotor.setDirection(DcMotorSimple.Direction.FORWARD);
      blMotor.setDirection(DcMotor.Direction.FORWARD);
      brMotor.setDirection(DcMotor.Direction.REVERSE);

      while(opModeIsActive() && eTimeSeconds() < 1) {
        flMotor.setpower(1);
        frMotor.setpower(1);
        blMotor.setpower(1);
        brMotor.setpower(1);
      }

     eTime.reset();

    while(opModeIsActive() && eTimeSeconds() < 1) {
        flMotor.setpower(1);
        frMotor.setpower(-1);
        blMotor.setpower(1);
        brMotor.setpower(-1);
      }   

    eTime.reset();
        
     while (opModeIsActive() && eTimeSeconds() < .75) {
        flMotor.setpower(1);
        frMotor.setpower(1);
        blMotor.setpower(1);
        brMotor.setpower(1);
    }

    eTime.reset();
        
     while (opModeIsActive() && eTimeSeconds() < 1) {
        leftServo.setposition(-0.75);
        rightServo.setposition(0.75);
    }
        
    eTime.reset();

     while(opModeIsActive() && eTimeSeconds() < 0.5) {
         flMotor.setpower(1);
         frMotor.setpower(1);
         blMotor.setpower(1);
         brMotor.setpower(1);
    }
        //going to add some other code later, hopefully, we will be able to pick up some new pixels to help with scoring of points for our alliance
   }
  }

      
