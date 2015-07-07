package jld.Functions;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import jld.GUI.wndChat;
import jld.Utils.CUserErrorMessages;
import jld.Utils.CUtils;

public class CPacketListener extends Thread {
	private wndChat mParent;
	private static CPacketListener mInstance = null;
	private CHeartbeat mHBThread;
	
	private boolean stopThread = false;
	
	private CPacketListener() {
	}
	
	public static CPacketListener getInstance(wndChat parent){
		if(mInstance == null){
			mInstance = new CPacketListener();
			mInstance.mParent = parent;
			mInstance.mHBThread = new CHeartbeat();
			mInstance.start();
		}
		return mInstance;
	}
	
	@Override
	public void run(){
		while(!stopThread){
			try{
				BufferedReader input = mParent.getInput();
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
					final String msg = String.valueOf(buffer);
					/* Sending and receiving beats */
					mInstance.mHBThread.beatReceived();
					mParent.getOutput().write(1);
					mParent.getOutput().flush();
					
					Thread a = new Thread(){
						@Override
						public void run() {
							CPacketListener.handlePacket(msg, mParent);
						}
					};
					a.start();
				
			} catch(Exception e){
				try {
					mParent.getOutput().write(1);
					mParent.getOutput().flush();
				} catch (IOException e1) {
				
				}
			}
		}
	}
	
	public static void handlePacket(String msg, wndChat mParent){
		int length = 0;
		while((int)msg.charAt(length) != 0){
			length++;
		}
		msg = msg.substring(0, length);
		if(msg.equals("")) return;
		if(msg.startsWith("0x0004")){
			// Muessen behandelt werden
			int usernamelength = CUtils.parseLength(msg.substring(6, 8));
			String username = msg.substring(8, 8+usernamelength);
			String usermessage = msg.substring(8+usernamelength+3);
			if(username.equals("System")){
				Scanner sc = new Scanner(usermessage);
				if(usermessage.startsWith("joinChannel")){
					sc.next();
					String user = sc.next();
					mParent.addToChannel(user);
					mParent.newMessage("", "User " + user + " joined your channel");
				} else if (usermessage.startsWith("leaveChannel")) {
					sc.next();
					String user = sc.next();
					mParent.removeFromChannel(user);
					mParent.newMessage("", "User " + user + " left your channel");
				} else if (usermessage.startsWith("newChannel")) {
					sc.next();
					String channel = sc.next();
					mParent.clearUserlist();
					mParent.setChannelName(channel);
				} else if (usermessage.startsWith("inChannel")) {
					//Userliste beim Betreten eines Channels fuellen
					sc.next();
					
					while(sc.hasNext()){
						String user = sc.next();
						mParent.addToChannel(user);
					}
				} else{
					mParent.newMessage(username, usermessage);
				}
				sc.close();
			} else{
				// Koennen ausgegeben werden
				mParent.newMessage(username, usermessage);
			}
			
		}
	}
}