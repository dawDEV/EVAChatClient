package jld.Functions;

import jld.CMain;
import jld.GUI.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import jld.Utils.CConfigParser;
import jld.Utils.CUtils;

public class loginListener implements ActionListener {
	wndLogin mParent;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Socket connection = CMain.getConnection();
			BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			final String LOGIN_HEADER = "0x0000";
			String username = mParent.getUsername();
			String pwd = mParent.getPassword();
			String send = LOGIN_HEADER + CUtils.parseLength(username.length(), false) + username + CUtils.parseLength(pwd.length(), false) + pwd;
			output.write(send.toCharArray(), 0, send.length());
			output.flush();
			
			char buffer[] = new char[256];
			int length = 0;
			length = input.read(buffer, 0, 256);
			
			/* length = -1 => Nutzer hat die Verbindung getrennt.
			 * length >= 1 => Nachrichten stehen an.
			 */
			if(length == -1){
				// Disconnect
				JOptionPane.showConfirmDialog(null,"Connection abgebrochen",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
				return;
			}
			String msg = String.valueOf(buffer);
			length = 0;
			while((int)msg.charAt(length) != 0){
				length++;
			}
			msg = msg.substring(0, length);
			
			if(msg.equals("0x0000")){
				// Login falsch
				JOptionPane.showConfirmDialog(null,"Anmeldung fehlgeschlagen",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
			else if(msg.equals("0x0001")){
				// Login richtig
				JOptionPane.showConfirmDialog(null, "Willkomen " + "\"" + username + "\"" + " !", "", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public loginListener(wndLogin parent){
		mParent = parent;
	}

}
