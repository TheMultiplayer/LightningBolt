package org.team5763;

import java.io.PrintStream;

public class ConsoleInterceptor extends PrintStream{
	boolean error;
	public ConsoleInterceptor(boolean error) {
		super(System.out);
		this.error=error;
	}
	public void print(String s){
		if(!error){
			Bolt.writeConsole(s);
		}else{
			Bolt.writeError(s);
		}
	}
}
