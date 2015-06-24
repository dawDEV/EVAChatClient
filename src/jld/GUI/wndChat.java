package jld.GUI;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import jld.Functions.*;
import jld.Utils.CUserErrorMessages;


public class wndChat {
	private JFrame frmChat;
	private JTextField txtMessage;
	private JMenuBar menuBar;
	private JMenu mnDatei;
	private JMenu mnHilfe;
	private JList<String> listMessage;
	private JScrollPane listScrollPane;
	private JList<String> mUserlist;
	private JLabel lblChannelname;
	private LinkedList<String> mUsersInChannel = new LinkedList<String>();
	private LinkedList<String> mChatMessages = new LinkedList<String>();
	private String mUsername;
	
	private Socket mConnection;
	private BufferedReader mInput;
	private BufferedWriter mOutput;

	public Socket getConnection() {
		return mConnection;
	}
	public BufferedReader getInput() {
		return mInput;
	}

	public BufferedWriter getOutput() {
		return mOutput;
	}
	
	public String getUsername(){
		return mUsername;
	}

	
	public wndChat(Socket connection, BufferedReader in, BufferedWriter out, String username) {
		mConnection = connection;
		mInput = in;
		mOutput = out;
		mUsername = username;
		initialize();
		frmChat.setVisible(true);
		CPacketListener.getInstance(this);
	}

	
	private void initialize() {
		frmChat = new JFrame();
		frmChat.setTitle("Chat");
		frmChat.setBounds(100, 100, 439, 288);
		frmChat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmChat.setResizable(false);
		frmChat.getContentPane().setLayout(null);
		
		frmChat.addWindowListener(new WindowListener() {
			public void windowOpened(WindowEvent e) {
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// Um auch den lesenden PacketListener zu schlie�en
				System.exit(0);
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
		
//List Nachrichtenliste		
		listMessage = new JList<String>();
		
		listMessage.setEnabled(false);
		listMessage.setBounds(10, 36, 315, 181);
		listScrollPane = new JScrollPane(listMessage, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		listScrollPane.setBounds(10, 36, 315, 181);
		
		frmChat.getContentPane().add(listScrollPane);
		
//List Gruppenliste		
				
		mUserlist = new JList<String>();
		mUserlist.setEnabled(false);
		mUserlist.setBounds(335, 62, 89, 154);
		frmChat.getContentPane().add(mUserlist);

//Label Gruppenname					
				
		lblChannelname = new JLabel("Gruppe");
		lblChannelname.setBounds(335, 36, 100, 14);
		frmChat.getContentPane().add(lblChannelname);

		
		
//Button
		
		JButton btnSendButton = new JButton("Senden");
		btnSendButton.addActionListener(new CSendMessageListener(this));

//new / set		
		
		btnSendButton.setBounds(335, 227, 89, 23);
		frmChat.getContentPane().add(btnSendButton);
		frmChat.getRootPane().setDefaultButton(btnSendButton);
		txtMessage = new JTextField();
		txtMessage.setBounds(10, 228, 315, 23);
		frmChat.getContentPane().add(txtMessage);
		txtMessage.setColumns(10);
	
//menuBar
		
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
				try {
					mOutput.write("0x0002005/help");
					mOutput.flush();
				} catch (IOException e1) {
					CUserErrorMessages.askingForHelpFailed();
				}
			}
		});
		mnHilfe.add(mntmHilfe);
	}
	
	public String getMessage(){
		return txtMessage.getText();
	}
	
	public void newMessage(String username, String msg){
		mChatMessages.add(username + ": " + msg);
		String[] messages = new String[mChatMessages.size()];
		for(int i = 0; i < messages.length; i++){
			messages[i] = mChatMessages.get(i);
		}
		listMessage.setListData(messages);
		listScrollPane.getVerticalScrollBar().setValue(listScrollPane.getVerticalScrollBar().getMaximum());
		
	}
	
	public void clearMessagebox(){
		txtMessage.setText("");
	}
	
	public void addToChannel(String username){
		mUsersInChannel.add(username);
		Collections.sort(mUsersInChannel);
		String[] users = new String[mUsersInChannel.size()];
		for(int i = 0; i < users.length; i++){
			users[i] = mUsersInChannel.get(i);
		}
		mUserlist.setListData(users);
	}
	
	public void removeFromChannel(String username){
		mUsersInChannel.remove(username);
		String[] users = new String[mUsersInChannel.size()];
		for(int i = 0; i < users.length; i++){
			users[i] = mUsersInChannel.get(i);
		}
		mUserlist.setListData(users);
	}
	
	public void clearUserlist(){
		mUsersInChannel = new LinkedList<String>();
		mUsersInChannel.add(mUsername);
		String[] users = {mUsername};
		mUserlist.setListData(users);
	}
	
	public void setChannelName(String name){
		lblChannelname.setText(name);
	}
}
