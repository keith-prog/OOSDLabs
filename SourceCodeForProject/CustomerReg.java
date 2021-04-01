package lab11;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.regex.*;


/**
 * CustomerRegistration Class
 * @author keithDavidson
 *
 */
public class CustomerReg  extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextField;
	private JTextField phoneTextField;
	private JTextField streetTextField;
	private JTextField cityTextField;
	private JTextField countyTextField;
	private JTextField emailTextField;
	private JPanel fieldJPanel1;
	private JTable table;

	
	String[][] input = new String[30][8];
	String[] columns= {"Customer ID","Name","Street","City","County","PhoneNumber","Email"};
	


	/**
	 * Default Constructor
	 */
	public CustomerReg()
	{
		try {
		/**
		 * Create CustomerTable
		 */
			table = new JTable(input,columns);
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
			String sql = "select * from customer";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			

				int i=0;
				while(result.next()) 
				{
					
				
					for(int j=0;j<7;j++) 
					{
					    input[i][j]=result.getString(j+1);
					}
					i=i+1;
				}
				
			}
		catch(Exception e)
			{
			e.printStackTrace();
			}
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.YELLOW);
		
		JLabel valiEmailLabel = new JLabel("");
		valiEmailLabel.setFont(new Font("Tahoma", Font.ITALIC, 11));
		valiEmailLabel.setForeground(Color.RED);
		valiEmailLabel.setBounds(366, 414, 135, 14);
		contentPane.add(valiEmailLabel);
		
		
		nameTextField = new JTextField();
		nameTextField.setColumns(10);
		nameTextField.setBounds(191, 154, 154, 28);
		contentPane.add(nameTextField);
		
		streetTextField = new JTextField();
		streetTextField.setColumns(10);
		streetTextField.setBounds(191, 204, 154, 28);
		contentPane.add(streetTextField);
		
		cityTextField = new JTextField();
		cityTextField.setColumns(10);
		cityTextField.setBounds(191, 255, 154, 28);
		contentPane.add(cityTextField);
		
		countyTextField = new JTextField();
		countyTextField.setColumns(10);
		countyTextField.setBounds(191, 305, 154, 28);
		contentPane.add(countyTextField);
		
		phoneTextField = new JTextField();
		phoneTextField.setColumns(10);
		phoneTextField.setBounds(191, 353, 154, 28);
		contentPane.add(phoneTextField);
		

		emailTextField = new JTextField();
		/**
		 * KeyListener with KeyAdapter
		 * For Email Validation
		 */
		emailTextField.addKeyListener(new KeyAdapter() {
			 @Override
			public void keyReleased(KeyEvent e) {
				 String PATTERN="^[a-zA-z0-9]{0,30}[@][a-zA-Z0-9]{0,10}[.][a-zA-Z]{0,5}$";
				 Pattern pat=Pattern.compile(PATTERN);
				 Matcher match=pat.matcher(emailTextField.getText());
				 if(!match.matches()) {
					 valiEmailLabel.setText("Please Enter Valid Email");
				 }else {
					 valiEmailLabel.setText(null);
				 }
			}
		});
		emailTextField.setColumns(10);
		emailTextField.setBounds(191, 405, 154, 28);
		contentPane.add(emailTextField);

				
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nameLabel.setBounds(39, 152, 123, 28);
		contentPane.add(nameLabel);
		
		JLabel streetLabel = new JLabel("Street");
		streetLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		streetLabel.setBounds(39, 202, 123, 28);
		contentPane.add(streetLabel);
		
		JLabel cityLabel = new JLabel("City");
		cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cityLabel.setBounds(39, 253, 123, 28);
		contentPane.add(cityLabel);
		
		JLabel countyLabel = new JLabel("County");
		countyLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		countyLabel.setBounds(39, 303, 123, 28);
		contentPane.add(countyLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		phoneLabel.setBounds(39, 351, 123, 28);
		contentPane.add(phoneLabel);
		
		JLabel emailLabel = new JLabel("Email");
		emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		emailLabel.setBounds(39, 405, 123, 28);
		contentPane.add(emailLabel);
		
		// Header
		JLabel headLabel = new JLabel("Sign In", SwingConstants.CENTER);
		headLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		fieldJPanel1 = new JPanel();
		fieldJPanel1.setLayout(new BorderLayout());
		fieldJPanel1.add(headLabel);
		contentPane.add(fieldJPanel1, BorderLayout.NORTH);
		

		/**
		 * Delete Button
		 */
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				int p = JOptionPane.showConfirmDialog(null,"Do you want to delete","Delete",JOptionPane.YES_NO_OPTION);
				if(p==0) {
					try 
					{
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
						
						
						String sql = "DELETE FROM  customer Where Name=?";
						PreparedStatement pstat = conn.prepareStatement(sql);
						
						pstat . setString (1,nameTextField.getText());					
						pstat.executeUpdate();
						JOptionPane.showMessageDialog(null,"Deleted");
						dispose();
					}
					catch(Exception e) 
					{
						JOptionPane.showMessageDialog(null,e);
					}
				}
				}
				
		});
		deleteButton.setBounds(757, 324, 89, 23);
		contentPane.add(deleteButton);
		/**
		 * Submit Button
		 */
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
					
					
					PreparedStatement pstat=conn.prepareStatement("insert into customer (Name,Street,City,County,Phone,Email) values(?,?,?,?,?,?)");
	                //Specifying the values of it's parameter
					
					
					pstat.setString(1,nameTextField.getText());;
					pstat.setString(2,streetTextField.getText());
					pstat.setString(3,cityTextField.getText());
					pstat.setString(4,countyTextField.getText());
					pstat.setString(5,phoneTextField.getText());
					pstat.setString(6,emailTextField.getText());
					
					pstat.executeUpdate();
					JOptionPane.showMessageDialog(null,"Entered");
					dispose();
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		
		submitButton.setBounds(500, 324, 85, 23);
		contentPane.add(submitButton);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(401, 140, 725, 143);
		contentPane.add(scrollPane);
	
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection conn;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String tClick = (table.getModel().getValueAt(row, 0).toString());
					String sql = "select * from customer where CustomerID='"+ tClick + " ' ";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet result = pstmt.executeQuery();
					if(result.next()) {
						String in1 = result.getString("Name");
						nameTextField.setText(in1);
						String in2 = result.getString("Street");
						streetTextField.setText(in2);
						String in3 = result.getString("City");
						cityTextField.setText(in3);
						String in4 = result.getString("County");
						countyTextField.setText(in4);
						String in5 = result.getString("Phone");
						phoneTextField.setText(in5);
						String in6 = result.getString("Email");
						emailTextField.setText(in6);
		                
					}
					
					
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		scrollPane.setViewportView(table);
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String value = table.getModel().getValueAt(row,0).toString();
					PreparedStatement pstat=conn.prepareStatement("UPDATE customer SET Name=?, Phone=?, Street=?, City=?, County=?, Email=?  where CustomerID="+value);
					pstat.setString(1,nameTextField.getText());
					pstat.setString(2,phoneTextField.getText());;
					pstat.setString(3,streetTextField.getText());
	                pstat.setString(4,cityTextField.getText());
	                pstat.setString(5,countyTextField.getText());
	                pstat.setString(6,emailTextField.getText());
	                
	                pstat.executeUpdate();
					JOptionPane.showMessageDialog(null,"Updated");
					dispose();

				}	
	catch(Exception e2 )
			{
			JOptionPane.showMessageDialog(null,e);
			}
				
			}
		});
		updateButton.setBounds(902, 324, 89, 23);
		contentPane.add(updateButton);
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
		            //Clearing Fields
					nameTextField.setText("");
					streetTextField.setText("");
					cityTextField.setText("");
					countyTextField.setText("");
					phoneTextField.setText("");
					emailTextField.setText("");
		        	
		        	 
						JOptionPane.showMessageDialog(null,"Reset");

				
				}
				catch(Exception e1) 
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		resetButton.setBounds(627, 324, 89, 23);
		contentPane.add(resetButton);
		
		JLabel HeadingLabel = new JLabel("Customer Registration");
		HeadingLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		HeadingLabel.setBounds(421, 27, 327, 43);
		contentPane.add(HeadingLabel);
		
		JLabel instructionLabel = new JLabel("To Access Customer Record Please Click in Table");
		instructionLabel.setBounds(421, 81, 327, 14);
		contentPane.add(instructionLabel);
		
		
	

	}
}
