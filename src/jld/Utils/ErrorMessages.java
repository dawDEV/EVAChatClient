package jld.Utils;

import javax.swing.JOptionPane;

public class ErrorMessages {
	public static void connectionFailed(){
		JOptionPane.showConfirmDialog(null,"Verbindung zum Server fehlgeschlagen.",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
}
