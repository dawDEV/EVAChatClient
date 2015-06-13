package jld.Functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
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
			String msg = mParent.getMessage();
			if(msg.equals("")) return;
			BufferedWriter output = mParent.getOutput();
			final String HEADER = "0x0002";
			String send = HEADER + CUtils.parseLength(msg.length(), true) + msg;
			output.write(send.toCharArray(), 0, send.length());
			output.flush();
			mParent.clearMessagebox();
			if(!msg.startsWith("/")){
				mParent.newMessage(mParent.getUsername(), msg);
			}
		} catch(Exception e){
			CUserErrorMessages.sendingMessageFailed();
			e.printStackTrace();
		}
		
	}

}
