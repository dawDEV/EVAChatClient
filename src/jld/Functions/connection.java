package jld.Functions;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class connection{
	private static Socket mSocket;
	private static BufferedReader mInput;
	private static PrintWriter mOutput;
	 	
		
	public static void connectToServer(String mServAddress, int port){
		try{
			
			mSocket = new Socket(mServAddress, port);
			System.out.println("Connected to "+mServAddress+" on port "+port);
			
			mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
			mOutput = new PrintWriter(mSocket.getOutputStream());
				
			
		}catch(UnknownHostException e){
			e.printStackTrace();
			System.err.println("Unknown Host!");
		}catch (IOException e) {
			
			e.printStackTrace();
			System.err.println("Stream Error!");
		}
				
	}
	
	public static void sendMessage(String msg){
			mOutput.print(msg);
			mOutput.flush();
			System.out.println("client>" + msg);
		}
	
	public static void recvMessage(){
		while(true){
			String buffer = mInput.toString();
			String header = buffer.substring(1, 7);
			
			if (header.equals("0x0000")){
				//login rejected
			}else if(header.equals("0x0001")){
				//login accepted
			}else if(header.equals("0x0002")){
				//register rejected
			}else if(header.equals("0x0003")){
				//register successful
			}else if(header.equals("0x0004")){
				//message received
			}else{
				System.err.println("Input Error!");
			}
		}
	}
	
	public static void closeConnection(){
		//
		System.out.println("Connection Closed!");
	}
	
	public static void con_sondereingabe(String msg){
		//
		
	}
	
	public static void con_gruppe_aktualisieren(){
		//
	}
	
	public static void con_chat_aktualisieren(){
		//
	}
	
	public static void con_gruppe_wechseln(){
		//
	}
}
	
