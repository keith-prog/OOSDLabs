package lab11;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.List;
import java.awt.Color;
/**
 * Invoice Class
 * @author keithDavidson
 *
 */
public class Invoice1 extends JFrame {

	private JPanel contentPane;
	
	private JTable table;
	private JTextField nameTextField;
	private JTextField totalPriceField;
	
	static JTable table1;
	
	
	String driverName = "com.mysql.cj.jdbc.Driver";
	final String DATABASE_URL = "jdbc:mysql://localhost/mydatabase";
	String userName = "root";
	String password = "";
	String[] columnNames = {"CustName","ProductName","Quantity","TotalPrice"};



	
	
	

	
	

	/**
	 * Create the frame.
	 * Default Constructor
	 */
	public Invoice1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		String[][] input = new String[30][4];
		String[] column= {"Name","ProductName","Quantity","TotalPrice"};
try {
		/**
		 * Invoice Table
		 */
	        table = new JTable(input,column);
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
			String sql = "select * from ordertable";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			

				int i=0;
				while(result.next()) 
				{
					
				
					for(int j=0;j<4;j++) 
					{
					    input[i][j]=result.getString(j+1);
					}
					i++;
				}
				
			}
		catch(Exception e)
			{
			e.printStackTrace();
			}
		 
		 
		
		
		
		
		JLabel headingLabel = new JLabel("Invoice");
		headingLabel.setBounds(270, 86, 132, 30);
		headingLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(headingLabel);
		
		/**
		 * MouseListener with MouseAdapter
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection conn;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String tClick = (table.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT Name FROM ordertable  WHERE Name='" + tClick + " ' ";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet result = pstmt.executeQuery();
					if(result.next()) {
						String in1 = result.getString("Name");
						nameTextField.setText(in1);
				
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 213, 498, 92);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		/**
		 * Invoice Details Button
		 */
		JButton invoiceDetailBtn = new JButton("Invoice Details");
		invoiceDetailBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String tClick = (table.getModel().getValueAt(row, 0).toString());
					String sql = "SELECT SUM(TotalPrice) FROM ordertable WHERE Name='" + tClick + " ' ";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet result = pstmt.executeQuery();
					if(result.next()) {
							String in2 = result.getString(1);
						totalPriceField.setText(in2);
					
						JOptionPane.showMessageDialog(null,"Total Price of  "  + tClick +" Bill is " + in2, "Invoice Details", JOptionPane.PLAIN_MESSAGE);
						
						
					}
					
					 dispose();

					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		invoiceDetailBtn.setBounds(251, 351, 126, 23);
		contentPane.add(invoiceDetailBtn);
		
		JLabel custNameLabel = new JLabel("Customer Name");
		custNameLabel.setBounds(38, 423, 106, 14);
		contentPane.add(custNameLabel);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(154, 420, 118, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel totalPriceLabel = new JLabel("Total Price");
		totalPriceLabel.setBounds(301, 426, 76, 14);
		contentPane.add(totalPriceLabel);
		
		totalPriceField = new JTextField();
		totalPriceField.setColumns(10);
		totalPriceField.setBounds(387, 426, 118, 20);
		contentPane.add(totalPriceField);
		
		JLabel directionsLabel = new JLabel("Please Choose Customer from Table and Press Invoice Details Button to Receive Total Price Owed");
		directionsLabel.setBounds(80, 162, 583, 14);
		contentPane.add(directionsLabel);
		
		
	}
}
