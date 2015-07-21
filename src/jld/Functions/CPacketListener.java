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
	
	// Store rest message if there's any
	private static String mRestMessage = "";
	
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
					String msg = String.valueOf(buffer);
					
					/* Sending and receiving beats */
					mInstance.mHBThread.beatReceived();
					mParent.getOutput().write("1");
					mParent.getOutput().flush();
					
					length = 0;
					if(mRestMessage.length() > 0){
						StringBuilder sb = new StringBuilder(mRestMessage);
						sb.append(msg);
						msg = sb.toString();
						mRestMessage = "";
					}
					while(length < msg.length() && (int)msg.charAt(length) != 0){
						length++;
					}
					
					msg = msg.substring(0, length);
					if(msg.equals("")) return;
					if(msg.startsWith("0x0004")){
						// Muessen behandelt werden
						int usernamelength = CUtils.parseLength(msg.substring(6, 8));
						String username = msg.substring(8, 8+usernamelength);
						int messageLength = Integer.valueOf(msg.substring(8+usernamelength, 8+usernamelength+3));
						String usermessage = msg.substring(8+usernamelength+3, 8+usernamelength+3+messageLength);
						
						// Rest
						mRestMessage = msg.substring(8+usernamelength+3+messageLength+1);
						
						if(username.equals("System")){
							Scanner sc = new Scanner(usermessage);
							if(usermessage.startsWith("joinChannel")){
								// Benutzer hat Channel betreten
								System.out.println(usermessage);
								sc.next();
								String user = sc.next();
								System.out.println(user);
								mParent.addToChannel(user);
								mParent.newMessage("", "User " + user + " joined your channel");
							} else if (usermessage.startsWith("leaveChannel")) {
								System.out.println(usermessage);
								// Benutzer hat Channel verlassen
								sc.next();
								String user = sc.next();
								System.out.println(user);
								mParent.removeFromChannel(user);
								mParent.newMessage("", "User " + user + " left your channel");
							} else if (usermessage.startsWith("newChannel")) {
								
								// Neuer Channelname
								sc.next();
								String channel = sc.next();
								mParent.clearUserlist();
								mParent.setChannelName(channel);
							} else if (usermessage.startsWith("inChannel")) {
								System.out.println(usermessage);
								//Userliste beim Betreten eines Channels fuellen
								sc.next();
								while(sc.hasNext()){
									String user = sc.next();
									mParent.addToChannel(user);
								}
							} else{
								// CASE: Andere System-Nachrichten
								mParent.newMessage(username, usermessage);
							}
							sc.close();
						} else{
							// CASE: Nachrichten
							mParent.newMessage(username, usermessage);
						}
						
					}
				
			} catch(Exception e){
				try {
					mParent.getOutput().write("1");
					mParent.getOutput().flush();
				} catch (IOException e1) {
				
				}
			}
		}
	}
}