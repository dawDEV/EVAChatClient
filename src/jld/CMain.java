package jld;
import jld.GUI.*;
import jld.Utils.CConfigParser;
import jld.Utils.ErrorMessages;

import java.awt.EventQueue;
import java.net.Socket;
public class CMain{
	static CConfigParser mCP;
	static Socket mConnection;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					new wndLogin();
				} catch (Exception e) {
					ErrorMessages.connectionFailed();
					System.exit(0);
				}
			}
		});
	}
	
	public static CConfigParser getConfigParser(){
		return mCP;
	}
	
	public static Socket getConnection(){
		if(mConnection == null || mConnection.isClosed()){
			try {
				mCP = new CConfigParser();
				mConnection = new Socket(mCP.getAddress(), mCP.getPort());
			} catch (Exception e) {
				ErrorMessages.connectionFailed();
			}
		}
		return mConnection;
	}
}
