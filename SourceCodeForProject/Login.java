package lab11;

import java.awt.BorderLayout;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



/**
 * Login Class
 * @author keithDavidson
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField UserTextField;
	private JPasswordField passwordField;
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 365, 562);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel userNameLabel = new JLabel("Username");
		userNameLabel.setLabelFor(userNameLabel);
		userNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		userNameLabel.setBounds(44, 145, 239, 49);
		contentPane.add(userNameLabel);
		
		UserTextField = new JTextField();
		UserTextField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		UserTextField.setBounds(43, 205, 272, 43);
		contentPane.add(UserTextField);
		UserTextField.setColumns(10);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordLabel.setBounds(44, 270, 239, 43);
		contentPane.add(passwordLabel);
		
		passwordField = new JPasswordField();  
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(44, 324, 272, 49);
		contentPane.add(passwordField);
		
		JButton btnSubmitButton = new JButton("Submit");
		
		
		/**
		 * Submit Button
		 */
		btnSubmitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent args) {
				
				final String DATABASE_URL = "jdbc:mysql://localhost/mydatabase";
				
				
				String sql = "select * from tblogin where username = ? and password = ?";
				
				try {
					//Class.forName("com.mysql.jdc.Driver");
					Connection conn = DriverManager.getConnection(DATABASE_URL, "root", "");
					PreparedStatement pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, UserTextField.getText());
					pstmt.setString(2, new String(passwordField.getPassword()));
					
					ResultSet rs = pstmt.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(null,"welcome " +UserTextField.getText(), "Successful Login", JOptionPane.PLAIN_MESSAGE);
						
						Menu frame = new Menu();
						frame.setVisible(true);
						frame.setTitle("Menu");
						dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Invaild Username/Password ", "Unsuccessful Login", JOptionPane.ERROR_MESSAGE);
					}
					conn.close();
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
				
					
			}
		});
		btnSubmitButton.setBounds(110, 413, 150, 43);
		contentPane.add(btnSubmitButton);
		
		JLabel lblNewLabel = new JLabel("Login Page");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setBounds(75, 74, 239, 49);
		contentPane.add(lblNewLabel);
	}
	
}
