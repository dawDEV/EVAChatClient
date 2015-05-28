package func;

import javax.swing.JOptionPane;

public class connection {

	public static void connection_aufbauen(){
		//
		JOptionPane.showConfirmDialog(null,"Verbindung zu Server aufgebaut", null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void connection_abbauen(){
		//
		JOptionPane.showConfirmDialog(null,"Verbindung zu Server abgebaut", null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void con_sondereingabe(String nachricht){
		//
		JOptionPane.showConfirmDialog(null,"Nachricht an Server " + nachricht,null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
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
	
