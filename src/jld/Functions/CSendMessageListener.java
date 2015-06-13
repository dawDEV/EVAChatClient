package jld.Functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import jld.GUI.wndChat;
import jld.Utils.CUtils;
import jld.Utils.CUserErrorMessages;

public class CSendMessageListener implements ActionListener {
	wndChat mParent;
	
	public CSendMessageListener(wndChat parent) {
		mParent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		try{
			System.out.println("Message: " + mParent.getMessage());
			Socket connection = mParent.getConnection();
			BufferedWriter output = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
			final String HEADER = "0x0002";
			String msg = mParent.getMessage();
			
			String send = HEADER + CUtils.parseLength(msg.length(), true) + msg;
			output.write(send.toCharArray(), 0, send.length());
			output.flush();
		} catch(Exception e){
			CUserErrorMessages.sendingMessageFailed();
			e.printStackTrace();
		}
		
	}

}
