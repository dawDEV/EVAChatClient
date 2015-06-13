package jld.GUI;

import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import jld.Functions.*;


public class wndChat {
	private static wndChat mInstance = null;
	private JFrame frmChat;
	private JTextField messageField;
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenu mnHilfe;
	
	private Socket mConnection;

	public Socket getConnection() {
		return mConnection;
	}

	public void setConnection(Socket mConnection) {
		this.mConnection = mConnection;
	}

	public static wndChat getInstance(final Socket connection) {
		
		if(mInstance == null){
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						mInstance = new wndChat();
						mInstance.setConnection(connection);
						mInstance.frmChat.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		return mInstance;
	}

	
	private wndChat() {
		initialize();
	}

	
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setBounds(100, 100, 439, 288);
		frmChat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChat.setResizable(false);
		frmChat.getContentPane().setLayout(null);
		
		// Setzt die Instanz auf null wenn das Fenster geschlossen wird. "Pseudo Destruktor"
		frmChat.addWindowListener(new WindowListener() {
			@Override
			public void windowClosed(WindowEvent e) {
				mInstance = null;
			}
			@Override
			public void windowActivated(WindowEvent e) {}

			@Override
			public void windowClosing(WindowEvent e) {}

			@Override
			public void windowDeactivated(WindowEvent e) {}

			@Override
			public void windowDeiconified(WindowEvent e) {}

			@Override
			public void windowIconified(WindowEvent e) {}

			@Override
			public void windowOpened(WindowEvent e) {}
		});

		
//List Nachrichtenliste		
		
	    //   DefaultListModel listenModell = new DefaultListModel();
	    //   JList messageList = new JList(listenModell);
		
		JList<String> messageList = new JList<String>();
		messageList.setEnabled(false);
		String testNachrichten[] = {"Hallo", "Hallo", "Wie geht es dir?", "Danke, Gut und dir?", "Mir auch,Danke"};
		messageList.setListData(testNachrichten);
		messageList.setBounds(10, 36, 315, 181);
		frmChat.getContentPane().add(messageList);
		
//List Gruppenliste		
				
		JList<String> groupList = new JList<String>();
		groupList.setEnabled(false);
		String testGruppe[] = {"Mueller", "Schmidt", "Schneider", "Fischer", "Weber", "Mayer", "Becker", "Schulz"};
		groupList.setListData(testGruppe);
		groupList.setBounds(335, 62, 89, 154);
		frmChat.getContentPane().add(groupList);

//Label Gruppenname					
				
		JLabel lblGroup = new JLabel("Gruppe");
		lblGroup.setBounds(335, 36, 46, 14);
		frmChat.getContentPane().add(lblGroup);

		
		
//Button
		
		JButton btnSendButton = new JButton("Senden");
		btnSendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Senden Knopf gedrückt
				funktionen.send(messageField.getText());
			}
		});

//new / set		
		
		btnSendButton.setBounds(335, 227, 89, 23);
		frmChat.getContentPane().add(btnSendButton);
		frmChat.getRootPane().setDefaultButton(btnSendButton);
		messageField = new JTextField();
		messageField.setBounds(10, 228, 315, 23);
		frmChat.getContentPane().add(messageField);
		messageField.setColumns(10);
	
//meuuBar
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 434, 25);
		frmChat.getContentPane().add(menuBar);
		
		mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);
		
		
		JMenuItem mntmAbmelden = new JMenuItem("Abmelden");
		mntmAbmelden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Abmelden Klick
				JOptionPane.showConfirmDialog(null, "Abgemeldet!", "", JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				try {
					mConnection.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				new wndLogin();
				frmChat.dispose();
			}
		});
		
		mnDatei.add(mntmAbmelden);
		
		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Beenden Klick
				System.exit(0);
			}
		});

		mnDatei.add(mntmBeenden);
		
		mnHilfe = new JMenu("Hilfe");
		menuBar.add(mnHilfe);
		
		JMenuItem mntmHilfe = new JMenuItem("Hilfe");
		mntmHilfe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hilfe Klick
				JOptionPane.showConfirmDialog(null, "Hilfe leider nicht gefunden", "", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
			}
		});
		mnHilfe.add(mntmHilfe);



		
		
		

	}
}
