package org.team5763;

import java.util.Scanner;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class NetworkTester {
	public NetworkTester() throws InterruptedException{
		NetworkTable table=Bolt.listener.table;
		Scanner scan=new Scanner(System.in);
		while(true){
			System.out.print(">");
			String seq=scan.next();
			if(seq.equalsIgnoreCase("exit")){
				scan.close();
				System.exit(0);
			}else if(seq.equalsIgnoreCase("rand")){
				for(int i=0;i<100;i++){
					table.putNumber("fY", 1-2*Math.random());
					table.putNumber("fX", 1-2*Math.random());
					Thread.sleep(50);
				}
			}else{
				System.out.print("Running custom command: ");
				table.putNumber("fX",0);
				table.putNumber("fY",0);
				for(int i=0;i<seq.length();i++){
					String cmd=seq.substring(i, i+1);
					System.out.print(cmd);
					if(cmd.equals("w")){
						table.putNumber("fY", 1);
					}else if(cmd.equals("a")){
						table.putNumber("fX", -1);
					}else if(cmd.equals("s")){
						table.putNumber("fY", -1);
					}else if(cmd.equals("d")){
						table.putNumber("fX", 1);
					}
				}
				System.out.print("\n");
			}
		}
	}
	public static void main(String[] args) throws InterruptedException {
		Bolt.main(null);
		System.out.println("Debug/Test mode\nCommands:\n\tRAND - Random joystick motion for 5s\n\tWASD - Move the joystick in one direction\n\tEXIT - Safely close");
		new NetworkTester();
	}
}
