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
import java.net.Socket;
import java.net.UnknownHostException;
import jld.Utils.CUtils;
import jld.Utils.CUserErrorMessages;

public class CLoginListener implements ActionListener {
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
				CUserErrorMessages.serverClosedConnection();
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
				CUserErrorMessages.loginFailed();
			}
			else if(msg.equals("0x0001")){
				// Login richtig
				//JOptionPane.showConfirmDialog(null, "Willkommen " + "\"" + username + "\"" + " !", "", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				wndChat.getInstance(connection);
				mParent.close();
			}
		} catch (UnknownHostException e1) {
			CUserErrorMessages.connectionFailed();
		} catch (IOException e1) {
			CUserErrorMessages.connectionFailed();
		}
	}
	
	public CLoginListener(wndLogin parent){
		mParent = parent;
	}

}
