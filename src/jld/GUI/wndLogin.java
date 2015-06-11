package jld.GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import jld.Functions.*;


public class wndLogin {

	
	private JFrame frmLogin;
	private JTextField nameField;
	private JPasswordField passwordField;
	
	public wndLogin() {
		initialize();
	}

	
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 350, 150);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setResizable(false);
		frmLogin.getContentPane().setLayout(null);

		//Textfelder		
		
		nameField = new JTextField();
		nameField.setBounds(118, 11, 200, 25);
		frmLogin.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(118, 47, 200, 25);
		frmLogin.getContentPane().add(passwordField);

		//Login Button
		
		JButton btnLoginButton = new JButton("Login");
		btnLoginButton.addActionListener(new loginListener(this));
				
		btnLoginButton.setBounds(10, 83, 100, 25);
		frmLogin.getContentPane().add(btnLoginButton);
		frmLogin.getRootPane().setDefaultButton(btnLoginButton);

		//Register Button
		
		JButton btnRegisterButton = new JButton("Register");
		btnRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Register Klick
				wndRegister.main(null);
				frmLogin.dispose();
			}
		});
		btnRegisterButton.setBounds(118, 83, 100, 25);
		frmLogin.getContentPane().add(btnRegisterButton);

		//Exit Button
		
		JButton btnExitButton = new JButton("Beenden");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Beenden Klick
				System.exit(0);
			}
		});
		btnExitButton.setBounds(224, 83, 100, 25);
		frmLogin.getContentPane().add(btnExitButton);
		
		//Label
		
		JLabel lblBenutzername = new JLabel("Benutzername:");
		lblBenutzername.setBounds(10, 11, 100, 25);
		frmLogin.getContentPane().add(lblBenutzername);
		
		JLabel lblPassword = new JLabel("Passwort:");
		lblPassword.setBounds(10, 47, 100, 25);
		frmLogin.getContentPane().add(lblPassword);
		
		// GUI Sichtbar machen
		frmLogin.setVisible(true);
	}
	
	public String getUsername(){
		return nameField.getText();
	}
	
	public String getPassword(){
		char[] pwd = passwordField.getPassword();
		return String.valueOf(pwd);
	}
}
