package jld.Utils;

public class CUtils {
	public static String parseLength(int length, boolean forMessage){
		assert(length <= 999);
		String returnValue = "";
		if(length < 10 && forMessage){
			returnValue = "00" + length;
		} else if(length < 10){
			returnValue = "0" + length;
		} else if(length < 100 && forMessage){
			returnValue = "0" + length;
		} else if(length < 100){
			returnValue = Integer.toString(length);
		}
		else returnValue = Integer.toString(length);
		return returnValue;
	}
	
	public static int parseLength(String length){
		return Integer.valueOf(length);
	}
}
