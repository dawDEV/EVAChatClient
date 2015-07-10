package jld.Functions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;

import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

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
			msg.replace("�", "ae");
			msg.replace("�", "ue");
			msg.replace("�", "oe");
			msg.replace("�", "ss");
			final int MAX_MSG_LENGTH = 200; // Too ensure there's enough space for header infos and maybe future development
			int num = 1;
			while(msg.length() > MAX_MSG_LENGTH){
				// Cut message into smaller pieces
				String partMsg = msg.substring(0, MAX_MSG_LENGTH);
				msg = msg.substring(MAX_MSG_LENGTH);
				
				BufferedWriter output = mParent.getOutput();
				final String HEADER = "0x0002";
				String send = HEADER + CUtils.parseLength(partMsg.length(), true) + partMsg;
				output.write(send);
				output.flush();
				mParent.clearMessagebox();
				if(!msg.startsWith("/")){
					mParent.newMessage(mParent.getUsername(), partMsg);
				}
				System.out.println("Sending: " + num++);
			}
			BufferedWriter output = mParent.getOutput();
			final String HEADER = "0x0002";
			String send = HEADER + CUtils.parseLength(msg.length(), true) + msg;
			System.out.println("Sending: " + num++);
			output.write(send);
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
