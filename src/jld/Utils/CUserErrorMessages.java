package jld.Utils;

import javax.swing.JOptionPane;

public final class CUserErrorMessages {
	public static final void connectionFailed(){
		JOptionPane.showConfirmDialog(null,"Verbindung zum Server fehlgeschlagen.",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	public static final void serverClosedConnection(){
		JOptionPane.showConfirmDialog(null,"Der Server hat die Verbindung geschlossen.",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	public static final void loginFailed(){
		JOptionPane.showConfirmDialog(null,"Login fehlgeschlagen. Benutzername oder Passwort falsch!",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	public static final void passwordsDoNotMatch(){
		JOptionPane.showConfirmDialog(null,"Passwoerter stimmen nicht ueberein!",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static final void sendingMessageFailed(){
		JOptionPane.showConfirmDialog(null,"Ihre Nachricht konnte nicht gesendet werden.",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
}
