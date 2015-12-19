package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author TomasR
 */
public class XboxController {
    private DriverStation dStation;
    private int port=0,output=0;
    private Button[] buttons=new Button[10];
    private short lRumble=0, rRumble=0;
    
    public Axis LeftStick=new Axis(0,0), RightStick=new Axis(0,0);
    public triggers Triggers=new triggers(0,0);
    public ButtonRemap Buttons;
    public POV DPad = new POV();
    
    /**
     * Sets up the controller
     * @param port controller port
     */
    public XboxController(int port) {
    	dStation = DriverStation.getInstance();
    	this.port=port;
        for(int ii=0;ii<10;ii++){
        	buttons[ii]=new Button();
        }
        refresh();
        Buttons=new ButtonRemap();
    }
    
    public void vibrate(RumbleType type,float value){
        if (value < 0)
            value = 0;
        else if (value > 1)
            value = 1;
        if (type == RumbleType.left)
        	lRumble = (short)(value*65535);
        else
        	rRumble = (short)(value*65535);
        FRCNetworkCommunicationsLibrary.HALSetJoystickOutputs((byte)port, output, lRumble, rRumble);
    }

    public void refresh(){
    	for(int i=1;i<11;i++){
    		buttons[i-1].set(dStation.getStickButton(port, (byte)i));
    	}
        leftStick();
        rightStick();
        trigger();
        getDpad();
    }

    private void getDpad(){
    	DPad.set(dStation.getStickPOV(port, 0));
    }
    private void leftStick(){
    	LeftStick.X=dStation.getStickAxis(port, 0);
    	LeftStick.Y=dStation.getStickAxis(port, 1);
    }
    private void rightStick(){
    	RightStick.X=dStation.getStickAxis(port, 4);
    	RightStick.Y=dStation.getStickAxis(port, 5);
    }
    public void trigger(){
        Triggers.Left = dStation.getStickAxis(port, 2);
        Triggers.Right = dStation.getStickAxis(port, 3);
        Triggers.combine();
    }
    
    public class triggers{
    	public double Right;
    	public double Left;
    	public double Combined;
    	public triggers(double r, double l){
    		Right=r;
    		Left=l;
    		combine();
    	}
    	private void combine(){
    		Combined=Right-Left;
    	}
    }
    
    public class POV{
    	public boolean Up=false, Down=false, Left = false, Right=false;
    	public int degrees=-1;
    	private void set(int degree){
    		Up=(degree==315 || degree==0 || degree==45);
    		Down=(degree<=225 && degree>=135);
    		Left=(degree<=315 && degree>=225);
    		Right=(degree<=135 && degree>=45);
    		degrees=degree;
    	}
    }
    
    public class Axis{
        public double X,Y;
        public Axis(double x,double y){
            X=x;
            Y=y;
        }
    }
    
    public static class Button{
        public boolean current=false , last=false,changedDown=false,changedUp=false;
        private CustomButton button=new CustomButton(){
			@Override
			public boolean get() {
				return current ^ this.invert;
			}
        };
        /**
         * Runs your command automatically but delayed by one run
         * @param command your custom command
         * @param onHigh runs your command when the button is pressed
         * @param once runs you command only once
         */
        public void runCommand(Command command, boolean onHigh, boolean once){
        	button.invert=!onHigh;
        	if(once)button.whenActive(command);
        	else button.whileActive(command);
        }
        private void set(boolean current){
        	last=this.current;
        	this.current=current;
        	changedDown=!last && this.current;
        	changedUp=last && !this.current;
        }
    }
    
    public class ButtonRemap{
        public Button A =buttons[0];
        public Button B =buttons[1];
        public Button X =buttons[2];
        public Button Y =buttons[3];
        public Button LB =buttons[4];
        public Button RB =buttons[5];
        public Button Back =buttons[6];
        public Button Start =buttons[7];
        public Button LeftStick =buttons[8];
        public Button RightStick =buttons[9];
    	
    }
    private static abstract class CustomButton extends Trigger{
    	boolean invert=false;
    }
    public enum RumbleType{
    	left,right;
    }
}
