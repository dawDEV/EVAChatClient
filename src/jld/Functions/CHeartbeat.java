package jld.Functions;

import java.io.IOException;
import java.io.PrintWriter;

import jld.GUI.wndChat;
import jld.Utils.CUserErrorMessages;

public class CHeartbeat extends Thread {
	private boolean hbReceived = true;
	private boolean stopThread = false;
	
	public CHeartbeat(){
		this.start();
	}
	
	public void run(){
		try {
			while(!stopThread){
				if(!hbReceived){
					CUserErrorMessages.serverClosedConnection();
					System.exit(0);
				}
				hbReceived = false;
				sleep(6000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopThread(){
		stopThread = true;
	}
	
	public void beatReceived(){
		hbReceived = true;
	}
}
