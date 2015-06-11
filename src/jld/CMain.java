package jld;
import jld.GUI.*;
import jld.Utils.CConfigParser;

import java.awt.EventQueue;
import java.net.Socket;

import javax.swing.JOptionPane;
public class CMain{
	static CConfigParser mCP;
	static Socket mConnection;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mCP = new CConfigParser();
					mConnection = new Socket("127.0.0.1", 1337);
					new wndLogin();
				} catch (Exception e) {
					JOptionPane.showConfirmDialog(null,"Verbindung zum Server fehlgeschlagen.",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
		});
	}
	
	public static CConfigParser getConfigParser(){
		return mCP;
	}
	
	public static Socket getConnection(){
		return mConnection;
	}
}
