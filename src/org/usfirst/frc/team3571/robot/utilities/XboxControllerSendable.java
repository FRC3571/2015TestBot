package org.usfirst.frc.team3571.robot.utilities;

import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * This class is <b>experimental</b> and <b>should not</b> be used for anything other than experimenting with LiveWindowSendable
 * @author Tomas
 */
public class XboxControllerSendable extends XboxController implements LiveWindowSendable {
	private ITable table;
	private ITableListener listener;
	private boolean buttons=true, LeftStickX = true, LeftStickY = true, RightStickX = true, RightStickY = true, dpad =true,leftTrigger=true, rightTrigger=true;
	public XboxControllerSendable(int port) {
		super(port);
	}
	public XboxControllerSendable(int port,double leftDeadZone, double rightDeadZone){
		super(port, leftDeadZone, rightDeadZone);
	}

	@Override
	public void initTable(ITable subtable) {
		table = subtable;
		updateTable();
	}

	@Override
	public ITable getTable() {
		return table;
	}

	@Override
	public String getSmartDashboardType() {
		return "Xbox Controller";//TODO Look into this name
	}

	@Override
	public void updateTable() {
		table.putNumber("LeftStick X", LeftStick.X);
		table.putNumber("LeftStick Y", LeftStick.Y);
		table.putNumber("RightStick X", RightStick.X);
		table.putNumber("RightStick Y", RightStick.Y);
		table.putBoolean("A", Buttons.A.current);
		table.putBoolean("B", Buttons.B.current);
		table.putBoolean("X", Buttons.X.current);
		table.putBoolean("Y", Buttons.Y.current);
		table.putBoolean("LB", Buttons.LB.current);
		table.putBoolean("RB", Buttons.RB.current);
		table.putBoolean("Back", Buttons.Back.current);
		table.putBoolean("Stat", Buttons.Start.current);
		table.putBoolean("LeftStick", Buttons.LeftStick.current);
		table.putBoolean("RightStick", Buttons.RightStick.current);
		table.putNumber("LeftTrigger", Triggers.Left);
		table.putNumber("RightTrigger", Triggers.Right);
		table.putNumber("DPad", DPad.degrees);


	}

	@Override
	public void startLiveWindowMode() {
		if(table != null){
			listener = new ITableListener(){
				@Override
				public void valueChanged(ITable source, String key, Object value, boolean isNew) {
					switch(key){
					case "LeftStick X":
						LeftStick.X = (double)value;
						LeftStickX=false;
						break;
					case "LeftStick Y":
						LeftStick.Y = (double)value;
						LeftStickY=false;
						break;
					case "RightStick X":
						RightStick.X = (double)value;
						RightStickX=false;
						break;
					case "RightStick Y":
						RightStick.Y = (double)value;
						RightStickY=false;
						break;
					case "LeftTrigger":
						Triggers.Left = (double)value;
						leftTrigger=false;
						break;
					case "RightTrigger":
						Triggers.Right = (double)value;
						rightTrigger=false;
						break;
					case "A":
						Buttons.A.set((boolean)value);
						buttons=false;
						break;
					case "B":
						Buttons.B.set((boolean)value);
						buttons=false;
						break;
					case "X":
						Buttons.X.set((boolean)value);
						buttons=false;
						break;
					case "Y":
						Buttons.Y.set((boolean)value);
						buttons=false;
						break;
					case "LB":
						Buttons.LB.set((boolean)value);
						buttons=false;
						break;
					case "RB":
						Buttons.RB.set((boolean)value);
						buttons=false;
						break;
					case "Back":
						Buttons.Back.set((boolean)value);
						buttons=false;
						break;
					case "Stat":
						Buttons.Start.set((boolean)value);
						buttons=false;
						break;
					case "LeftStick":
						Buttons.LeftStick.set((boolean)value);
						buttons=false;
						break;
					case "RightStick":
						Buttons.RightStick.set((boolean)value);
						buttons=false;
						break;
					case "DPad":
						DPad.set((int)value);
						dpad=false;
						break;
					}
				}
			};
			table.addSubTableListener(listener);
		}

	}

	@Override
	public void stopLiveWindowMode() {
		table.removeTableListener(listener);

	}
	
	public void refresh(){
		if(buttons)getButtons();
		if(dpad)getDpad();
		
		if(LeftStickX)LeftStick.X = dStation.getStickAxis(port, 0);
		if(LeftStickY)LeftStick.Y = dStation.getStickAxis(port, 1);
		
		if(leftTrigger)Triggers.Left = dStation.getStickAxis(port, 2);
		if(rightTrigger)Triggers.Right = dStation.getStickAxis(port, 3);
		if(leftTrigger || rightTrigger)Triggers.combine();
		
		if(RightStickX)LeftStick.X = dStation.getStickAxis(port, 4);
		if(RightStickY)LeftStick.Y = dStation.getStickAxis(port, 5);
	}

}
