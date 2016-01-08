package org.usfirst.frc.team3571.robot.utilities;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary;
import edu.wpi.first.wpilibj.DriverStation;

/**
 *Handles Input from the Xbox Controller connected to the driver station.<br/>
 *Designed to have separate objects for the Buttons, Axis, and the Dpad so that anyone can make a local reference of either of them
 *@author TomasR
 */
public class XboxController {
    private DriverStation dStation;
    private int port=0, buttonState=0, buttonCount=0;
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
        buttonCount=dStation.getStickButtonCount(port);
    }
    /**
     * Sends values to the rumble motors in the controller
     * @param type either left or right
     * @param value (0 to 1) rumble intensity value
     */
    public void vibrate(RumbleType type,float value){
        if (value < 0)
            value = 0;
        else if (value > 1)
            value = 1;
        if (type == RumbleType.left)
        	lRumble = (short)(value*65535);
        else
        	rRumble = (short)(value*65535);
        FRCNetworkCommunicationsLibrary.HALSetJoystickOutputs((byte)port, 0, lRumble, rRumble);
    }
    /**
     * Returns the state of a specific button
     * @param i The button number starting with 1
     * @return True if the button is pressed else False
     * @throws Exception if the button does not exist
     */
    public boolean getRawButton(int i) throws Exception{
    	i-=1;
    	if (i >= 0 && i < buttonCount)return (buttonState & (1 << i))!=0;
    	throw new Exception(String.format("Button %d does not exist", i));
    }
    /**
     * Returns the state of a specific axis
     * @param i Axis number starting with 0
     * @return Returns a double between -1 and 1
     */
    public double getRawAxis(int i){
    	return dStation.getStickAxis(port, i);
    }
    /**
     * @return The number of buttons
     */
    public int getButtonCount(){
    	return buttonCount;
    }
    
    /**
     * Reacquires the values for all inputs
     */
    public void refresh(){
        getDpad();
    	getLeftStick();
    	getRightStick();
    	getTrigger();
    	getButtons();
    }
    private void getDpad(){
    	DPad.set(dStation.getStickPOV(port, 0));
    }
    private void getLeftStick(){
    	LeftStick.X = dStation.getStickAxis(port, 0);
    	LeftStick.Y = dStation.getStickAxis(port, 1);
    }
    private void getRightStick(){
    	RightStick.X = dStation.getStickAxis(port, 4);
    	RightStick.Y = dStation.getStickAxis(port, 5);
    }
    private void getTrigger(){
        Triggers.Left = dStation.getStickAxis(port, 2);
        Triggers.Right = dStation.getStickAxis(port, 3);
        Triggers.combine();
    }
    private void getButtons(){
    	buttonState = dStation.getStickButtons(port);
    	for(int i=0;i<10;i++){
    		buttons[i].set((buttonState & (1 << i)) !=0 );
    	}
    }
    
    public class triggers{
    	/**(0 to 1) value for the individual trigger**/
    	public double Right, Left;
    	/**(0 to 1) combined value equivalent to Right - Left**/
    	public double Combined;
    	public triggers(double r, double l){
    		Right=r;
    		Left=l;
    		combine();
    	}
    	private void combine(){
    		Combined = Right - Left;
    	}
    }
    
    public class POV{
    	public boolean Up=false, Down=false, Left = false, Right=false;
    	/**Returns -1 if the Direction Pad is not pressed else it returns a compass orientation starting with up being 0**/
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
            X = x;
            Y = y;
        }
    }
    public static class Button{
        public boolean current=false , last=false,changedDown=false,changedUp=false;
        private Trigger button;
        private boolean invertedCommand=false;
        /**
         * Runs your command automatically<br/>
         * Acts when pressed<br/>
         * Should only be called once when setting the command<br/>
         * Requires <u>Scheduler.getInstance().run()</u>
         * @param command your custom command
         * @param state A selection of when to run the command
         */
        public void runCommand(Command command,CommandState state){
        	if(button==null)button = new Trigger(){
    			@Override
    			public boolean get() {
    				return current ^ invertedCommand;
    			}
        	};
        	switch(state){
        	case once:
            	button.whenActive(command);
        		break;
        	case toggle:
        		button.toggleWhenActive(command);
        		break;
        	case constant:
        		button.whileActive(command);
        		break;
        	case cancel:
        		button.cancelWhenActive(command);
        		break;
        	}
        }
        /**
         * Runs your command automatically<br/>
         * Should only be called once when setting the command<br/>
         * Requires <u>Scheduler.getInstance().run()</u>
         * @param command your custom command
         * @param onHigh runs your command when the button is pressed
         * @param state A selection of when to run the command
         */
        public void runCommand(Command command, boolean onHigh,CommandState state){
        	invertedCommand=!onHigh;
        	runCommand(command,state);
        }
        private void set(boolean current){
        	last=this.current;
        	this.current=current;
        	changedDown=!last && this.current;
        	changedUp=last && !this.current;
        }
    }
    public class ButtonRemap{
        public Button A = buttons[0];
        public Button B = buttons[1];
        public Button X = buttons[2];
        public Button Y = buttons[3];
        /**Left Bumper**/
        public Button LB = buttons[4];
        /**Right Bumper**/
        public Button RB = buttons[5];
        public Button Back = buttons[6];
        public Button Start = buttons[7];
        public Button LeftStick = buttons[8];
        public Button RightStick = buttons[9];
    	
    }
    public enum CommandState{
    	/**Runs the command only once**/
    	once,
    	/**Works like once but cancels the command on the second press**/
    	toggle,
    	/**Runs the command on every call of <u>Scheduler.getInstance().run()</u>**/
    	constant,
    	/**Cancels the command**/
    	cancel;
    }
    public enum RumbleType{
    	left,right;
    }
}
