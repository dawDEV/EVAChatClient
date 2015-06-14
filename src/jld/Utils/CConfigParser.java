package jld.Utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CConfigParser {
	private File mConfigFile = new File("config.cfg");
	private Scanner mReader;
	private String mAddress = null;
	private int mPort = -1;
	
	public CConfigParser(){
		try {
			mReader = new Scanner(mConfigFile);
		} catch (FileNotFoundException e) {
			CUserErrorMessages.configFileNotFound();
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
				String address = mReader.next();
				if(checkAdressValidity(address)){
					setAddress(address);
				}	
			} else if(tag.equalsIgnoreCase("Port")){
				mReader.next();
				mPort = mReader.nextInt();
			}
		}
		if(mPort == -1 || mAddress == null){
			System.out.println("Konfigurationsdatei fehlerhaft, Dokumentation lesen / Helpdesk konsultieren.");
			System.exit(0);
		}
	}

	public final String getAddress() {
		return mAddress.toString();
	}

	private final void setAddress(String address) {
		mAddress = address;
	}

	public final int getPort() {
		return mPort;
	}
	
	private boolean checkAdressValidity(String address){
		return address.matches("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
		// Quelle: http://www.mkyong.com/regular-expressions/how-to-validate-ip-address-with-regular-expression/
	}
	
	
}