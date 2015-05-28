package func;

import javax.swing.JOptionPane;

public class funktionen {

	public static boolean regestrieren(String name,String mail, char[] password, char[] password_w){
		if(name.equals("")){
			JOptionPane.showConfirmDialog(null,"Bitte Name eingeben",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
			return false;
		} else {
			if(password.equals("")){
				JOptionPane.showConfirmDialog(null,"Bitte Password eingeben",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				return false;
			}else{
				if(password.equals(password_w) == true){
					JOptionPane.showConfirmDialog(null,"Passwörter stimmen nicht überein",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
					return false;
				}else{
//					if(serverrückmeldung) == true){
						JOptionPane.showConfirmDialog(null, "\"" + name + "\"" + " wurde erfolgreich Registriert!", "", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
						return true;
//					} else {
//						JOptionPane.showConfirmDialog(null,"Fehler bei der Regestrierung",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
//						return false;
//					}
				}
			}
		}
	}		

	public static boolean login(String name, char[] password){
//		if(serverrückmeldung == true){
		return true;
//		} else {
//		JOptionPane.showConfirmDialog(null,"Fehler bei der Regestrierung",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
//		return false;		
//		}
	}

	public static void send(String nachricht){
		JOptionPane.showConfirmDialog(null, "\"" + nachricht + "\"   " + "Gesendet!", "", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
		
		//Send message with header
		connection.sendMessage("0x0000"+nachricht);
	}

	public static void gruppe_aktualisieren(){
		//
	}
	
	public static void nachrichten_aktualisieren(){
		//
	}
	
	
}
