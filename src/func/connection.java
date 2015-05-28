package func;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

public class connection{
		private Socket mSocket;
		private BufferedReader mInput;
		private PrintWriter mOutput;
	 	
		
		public void connectToServer(String mServAddress, int port){
			try{
				
				mSocket = new Socket(mServAddress, port);
				System.out.println("Connected to "+mServAddress+" on port "+port);
				
				mInput = new BufferedReader(new InputStreamReader(mSocket.getInputStream()));
				mOutput = new PrintWriter(mSocket.getOutputStream());
					
				
			}catch(UnknownHostException e){
				e.printStackTrace();
				System.err.println("Unknown host!");
			}catch (IOException e) {
				
				e.printStackTrace();
				System.err.println("Stream Error!");
			}
			
			
		}
		
		void sendMessage(String msg){
			mOutput.print(msg);
			mOutput.flush();
			System.out.println("client>" + msg);
		}

}
