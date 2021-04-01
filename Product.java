package lab11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.JTable;
import javax.swing.JScrollPane;

 /**
  * Product Class
  * @author keithDavidson
  *
  */
public class Product extends JFrame
{

	private JPanel contentPane;
	private JTextField ProductNameTextField;
	private JTextField quantityTextField;
	private JTextField parLevelTextField;
	private JTextField wholeSalePriceTextField;
	private JTextField retailPriceTextField;
	private JRadioButton reorderedJRadioButton;
	private JRadioButton notReorderedJRadioButton;
	private ButtonGroup radioGroup;
	
	private JTable table;

	
	String[][] input = new String[10][7];
	String[] column= {"ProductID","ProductName","Quantity"," ParLevel","WholeSale Price","RetailPrice","Reordered"};
	

/**
 * This is default constructor
 * 
 */
	
	public Product()
	{
		/**
		 * Create ProductTable
		 */
		try {
		
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
			String sql = "select * from product";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet result = pstmt.executeQuery();
			

				int i=0;
				while(result.next()) 
				{
					
				
					for(int j=0;j<7;j++) 
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
	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.YELLOW);
		
		
		
		ProductNameTextField = new JTextField();
		ProductNameTextField.setBounds(191, 100, 154, 28);
		contentPane.add(ProductNameTextField);
		ProductNameTextField.setColumns(10);
		
		quantityTextField = new JTextField();
		quantityTextField.setColumns(10);
		quantityTextField.setBounds(191, 154, 154, 28);
		contentPane.add(quantityTextField);
		
		parLevelTextField = new JTextField();
		parLevelTextField.setColumns(10);
		parLevelTextField.setBounds(191, 204, 154, 28);
		contentPane.add(parLevelTextField);
		
		wholeSalePriceTextField = new JTextField();
		wholeSalePriceTextField.setColumns(10);
		wholeSalePriceTextField.setBounds(191, 255, 154, 28);
		contentPane.add(wholeSalePriceTextField);
		
		retailPriceTextField = new JTextField();
		retailPriceTextField.setColumns(10);
		retailPriceTextField.setBounds(191, 305, 154, 28);
		contentPane.add(retailPriceTextField);
		
		
		
		JLabel ProductNameLabel = new JLabel("Product Name");
		ProductNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ProductNameLabel.setBounds(39, 98, 123, 28);
		contentPane.add(ProductNameLabel);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		quantityLabel.setBounds(39, 152, 123, 28);
		contentPane.add(quantityLabel);
		
		JLabel parLevelLabel = new JLabel("Par Level");
		parLevelLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		parLevelLabel.setBounds(39, 202, 123, 28);
		contentPane.add(parLevelLabel);
		
		JLabel wholeSalePriceLabel = new JLabel("WholeSale Price");
		wholeSalePriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		wholeSalePriceLabel.setBounds(39, 253, 123, 28);
		contentPane.add(wholeSalePriceLabel);
		
		JLabel retailPriceLabel = new JLabel("Retail Price");
		retailPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		retailPriceLabel.setBounds(39, 303, 123, 28);
		contentPane.add(retailPriceLabel);
		
		JRadioButton notReorderedJRadioButton = new JRadioButton("Not Reordered", true);
		notReorderedJRadioButton.setBounds(91, 357, 109, 23);
	    //setLayer(notReorderedJRadioButton,3);
	    getContentPane().add(notReorderedJRadioButton);

	    JRadioButton reorderedJRadioButton = new JRadioButton("Reordered", false);
	    reorderedJRadioButton.setBounds(236, 357, 109, 23);
	   // setLayer(reorderedJRadioButton,3);
	    getContentPane().add(reorderedJRadioButton);
		
		
		ButtonGroup group= new ButtonGroup();
		group.add(reorderedJRadioButton);
		group.add(notReorderedJRadioButton);

		
		
			
	

		/**
		 * Reset Button
		 */
				
		
		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
		            //Clearing Fields
		        	ProductNameTextField.setText("");
		        	quantityTextField.setText("");
		        	parLevelTextField.setText("");
		        	wholeSalePriceTextField.setText("");
		        	retailPriceTextField.setText("");
		        	
		        	 
						JOptionPane.showMessageDialog(null,"Reset");

				
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		/**
		 * Submit Button
		 */
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
					
					
					PreparedStatement pstat=conn.prepareStatement("insert into product (ProductName,Quantity,ParLevel,WholeSalePrice,RetailPrice,Reordered) values(?,?,?,?,?,?) ");
	                //Specifying the values of it's parameter
					pstat.setString(1,ProductNameTextField.getText());
					pstat.setString(2,quantityTextField.getText());;
					pstat.setString(3,parLevelTextField.getText());
	                pstat.setString(4,wholeSalePriceTextField.getText());
	                pstat.setString(5,retailPriceTextField.getText());
	                String radioText = "";
	                if(notReorderedJRadioButton.isSelected()) {
	                	radioText = notReorderedJRadioButton.getText();
	                }
	                if(reorderedJRadioButton.isSelected()) {
	                	radioText = reorderedJRadioButton.getText();
	                }
	                pstat.setString(6,radioText);
	                
	                pstat.executeUpdate();
					JOptionPane.showMessageDialog(null,"Entered");
					dispose();

				}	
	catch(Exception e )
			{
			JOptionPane.showMessageDialog(null,e);
			}
	}
});
		
		resetButton.setBounds(640, 324, 89, 23);
		contentPane.add(resetButton);
		
		
		submitButton.setBounds(500, 324, 89, 23);
		contentPane.add(submitButton);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(490, 123, 504, 143);
		contentPane.add(scrollPane);
		table = new JTable(input,column);
		scrollPane.setViewportView(table);
		/**
		 * Update Button
		 */
		
		JButton updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String value = table.getModel().getValueAt(row,0).toString();
					PreparedStatement pstat=conn.prepareStatement("UPDATE product SET ProductName=?, Quantity=?, ParLevel=?, WholesalePrice=?, RetailPrice=?, Reordered=?  where ProductID="+value);
					pstat.setString(1,ProductNameTextField.getText());
					pstat.setString(2,quantityTextField.getText());;
					pstat.setString(3,parLevelTextField.getText());
	                pstat.setString(4,wholeSalePriceTextField.getText());
	                pstat.setString(5,retailPriceTextField.getText());
	                String radioText = "";
	                if(notReorderedJRadioButton.isSelected()) {
	                	radioText = notReorderedJRadioButton.getText();
	                }
	                if(reorderedJRadioButton.isSelected()) {
	                	radioText = reorderedJRadioButton.getText();
	                }
	                pstat.setString(6,radioText);
	                
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
		updateButton.setBounds(891, 324, 89, 23);
		contentPane.add(updateButton);
		/**
		 * Delete  Button
		 */
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int p = JOptionPane.showConfirmDialog(null,"Do you want to delete","Delete",JOptionPane.YES_NO_OPTION);
				if(p==0) {
					try 
					{
						Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
						
						
						String sql = "DELETE FROM  product Where ProductName=?";
						PreparedStatement pstat = conn.prepareStatement(sql);
						
						pstat . setString (1,ProductNameTextField.getText());					
						pstat.executeUpdate();
						JOptionPane.showMessageDialog(null,"Deleted");
						dispose();
					
					}
					catch(Exception e1) 
					{
						JOptionPane.showMessageDialog(null,e);
					}
				}
				}
				
		});
		deleteButton.setBounds(766, 324, 89, 23);
		contentPane.add(deleteButton);
		
		JLabel HeadingLabel = new JLabel("Product Registration");
		HeadingLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		HeadingLabel.setBounds(465, 17, 325, 37);
		contentPane.add(HeadingLabel);
		
		JLabel lblNewLabel = new JLabel("To Access Product Records Please Click in Table");
		lblNewLabel.setBounds(449, 65, 341, 14);
		contentPane.add(lblNewLabel);
		/**
		 * Mouse Event with MouseAdapter
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection conn;
					conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );
					int row = table.getSelectedRow();
					String tClick = (table.getModel().getValueAt(row, 0).toString());
					String sql = "select * from product where ProductID='"+ tClick + " ' ";
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet result = pstmt.executeQuery();
					if(result.next()) {
						String in1 = result.getString("ProductName");
						ProductNameTextField.setText(in1);
						String in2 = result.getString("Quantity");
						quantityTextField.setText(in2);
						String in3 = result.getString("ParLevel");
						parLevelTextField.setText(in3);
						String in4 = result.getString("WholeSalePrice");
						wholeSalePriceTextField.setText(in4);
						String in5 = result.getString("RetailPrice");
						retailPriceTextField.setText(in5);
						
		                
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		

	}
}
