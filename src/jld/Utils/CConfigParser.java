package jld.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import jld.Exceptions.*;

public class CConfigParser {
	private File mConfigFile = new File("config.cfg");
	private Scanner mReader;
	private InetAddress mAddress = null;
	private int mPort = -1;
	
	public CConfigParser(){
		try {
			mReader = new Scanner(mConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println("Config file not found.");
			System.exit(0);
		}
		readConfig();
	}

	private void readConfig(){
		while(mReader.hasNext()){
			//System.out.println(mReader.next());
			String tag = mReader.next();
			if(tag.equalsIgnoreCase("IP")){
				mReader.next();
				setAddress(mReader.next());
			} else if(tag.equalsIgnoreCase("Port")){
				mReader.next();
				mPort = mReader.nextInt();
			}
		}
		if(mPort == -1 && mAddress == null){
			System.out.println("Konfigurationsdatei fehlerhaft, Dokumentation lesen / Helpdesk konsultieren.");
			System.exit(0);
		}
	}

	public final String getAddress() {
		return mAddress.toString();
	}

	private final void setAddress(String address) {
		try {
			mAddress = Inet4Address.getByName(address);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public final int getPort() {
		return mPort;
	}
	
	
}