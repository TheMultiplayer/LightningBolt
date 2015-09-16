package org.team5763;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

public class NetworkListener implements ITableListener {
	NetworkTable table;
	double x,y; 
	boolean readyState;
	
	public NetworkListener(){
		try{
			NetworkTable.setClientMode();
			NetworkTable.setTeam(Bolt.manager.getTeamNumber());
			System.out.println("[network]  Set team number to "+Bolt.manager.getTeamNumber());
		}catch (Exception e){
			System.err.println("Connection failed.  Reverting to localhost.");
			NetworkTable.setIPAddress("127.0.0.1");
		};
		System.out.println("[network]  Getting table...");
		table=NetworkTable.getTable("FFS");
		System.out.println("[network]  Done!");
		table.putNumber("fX",0);
		table.putNumber("fY",0);
		table.addTableListener(this);
	}
	@Override
	public void valueChanged(ITable arg0, String arg1, Object arg2, boolean arg3) {
		if(!arg3){
			if(arg1.equals("fX")){
				x=(Double)arg2;
			}else if(arg1.equals("fY")){
				y=(Double)arg2;
			}
			Bolt.joystick.drive(x, y);
		}		
	}
}
