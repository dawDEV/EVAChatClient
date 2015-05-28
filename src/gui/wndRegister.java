package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.EventQueue;

import func.funktionen;

public class wndRegister {

	private JFrame frmRegister;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_W;
	private JLabel lblNewLabel_3;
	private JTextField MailField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					wndRegister window = new wndRegister();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public wndRegister() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRegister = new JFrame();
		frmRegister.setTitle("Register");
		frmRegister.setBounds(100, 100, 375, 225);
		frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegister.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Benutzername :");
		lblNewLabel.setBounds(10, 11, 100, 25);
		frmRegister.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(10, 119, 100, 25);
		frmRegister.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password :");
		lblNewLabel_2.setBounds(10, 83, 100, 25);
		frmRegister.getContentPane().add(lblNewLabel_2);
		
		nameField = new JTextField();
		nameField.setBounds(120, 11, 227, 25);
		frmRegister.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 83, 227, 25);
		frmRegister.getContentPane().add(passwordField);
		
		passwordField_W = new JPasswordField();
		passwordField_W.setBounds(120, 119, 227, 25);
		frmRegister.getContentPane().add(passwordField_W);
		
		lblNewLabel_3 = new JLabel("EMailadresse :");
		lblNewLabel_3.setBounds(10, 47, 100, 25);
		frmRegister.getContentPane().add(lblNewLabel_3);
		
		MailField = new JTextField();
		MailField.setBounds(120, 47, 227, 25);
		frmRegister.getContentPane().add(MailField);
		MailField.setColumns(10);
		
		JButton btnExitButton = new JButton("Beenden");
		btnExitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//ExitButton klick
				System.exit(0);
			}
		});
		btnExitButton.setBounds(238, 155, 110, 25);
		frmRegister.getContentPane().add(btnExitButton);
		
		JButton btnRegisterButton = new JButton("Registrieren");
		btnRegisterButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//RegisterButton klick
				if(
				funktionen.regestrieren(nameField.getText(), MailField.getText(), passwordField.getPassword(), passwordField_W.getPassword())
				){
				wndLogin.main(null);
				frmRegister.dispose();
				} else {
					JOptionPane.showConfirmDialog(null,"Fehler bei der Regestrierung",null, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE);
				}
		

			}
		});
		btnRegisterButton.setBounds(120, 155, 110, 25);
		frmRegister.getRootPane().setDefaultButton(btnRegisterButton);
		frmRegister.getContentPane().add(btnRegisterButton);
	}
}
