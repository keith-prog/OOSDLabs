package lab11;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
/**
 * Order Class
 * @author keithDavidson
 *
 */
public class Order extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField productNameField;
	private JComboBox comboBox;
	private JComboBox qtyComboBox;
	private JTextField priceTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Order frame = new Order();
					frame.setVisible(true);
					frame.setTitle("Orders");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * This is default constructor
	 */
	public Order() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 651, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/** 
		 * Table Variables and Loop
		 */
		
		String[][] input = new String[10][7];
		String[] columns= {"ProductID","ProductName","Quantity"," ParLevel","WholeSale Price","RetailPrice","Reordered"};
		table = new JTable(input,columns);
		
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
	
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 617, 73);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel headerLabel = new JLabel("Orders");
		headerLabel.setBounds(258, 33, 90, 29);
		headerLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel.add(headerLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 81, 617, 109);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel customerLabel = new JLabel("Cusotmer");
		customerLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		customerLabel.setBounds(124, 27, 78, 23);
		panel_1.add(customerLabel);
		
		/**
		 * ComboBox to Select Customer
		 * 
		 */
		
		 comboBox = new JComboBox();
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
					String sql = "select * from customer";
					
					
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery();
					
					while(rs.next()) 
					{
						// String name=rs.getString("Name");
						 comboBox.addItem(rs.getString(2));
						
					}
					

				}	
	catch(Exception e )
			{
			JOptionPane.showMessageDialog(null,e);
			}
				
				
		
		
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(231, 27, 131, 22);
		panel_1.add(comboBox);
		
		JLabel instructionLabel = new JLabel("Please Choose Customer Then Quantiy and Choose and Click Product Form Table");
		instructionLabel.setBounds(55, 84, 487, 14);
		panel_1.add(instructionLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 230, 498, 92);
		contentPane.add(scrollPane);
		/**
		 * MouseEvent with MouseAdapter
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
						productNameField.setText(in1);
					  double rPrice = Double.parseDouble(result.getString("RetailPrice"));
					 int quantity = Integer.parseInt(qtyComboBox.getSelectedItem().toString());
					 double total = rPrice * quantity;
					 priceTextField.setText(String.valueOf(total));
						
						

						
					}
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		scrollPane.setViewportView(table);
		/**
		 * AddItem To Order Button
		 */
		
		JButton addToOrder = new JButton("Add Item to Order");
		addToOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent argo) {
				try 
				{
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root", "" );	
					
					
					PreparedStatement pstat=conn.prepareStatement("insert into ordertable(Name,ProductName,Quantity,TotalPrice) VALUES(?,?,?,?)");
	                //Specifying the values of it's parameter
					
					
					String cName = comboBox.getSelectedItem().toString();
					pstat.setString(1, cName);
					String pName = productNameField.getText().toString();
					pstat.setString(2,pName);
					String qty=qtyComboBox.getSelectedItem().toString();
					pstat.setString(3,qty);
					String tPrice = priceTextField.getText().toString();
					pstat.setString(4, tPrice);
					
	                
	                pstat.executeUpdate();
					JOptionPane.showMessageDialog(null,"Item/Items Ordered");

					
				}	
	catch(Exception argo1 )
			{
			JOptionPane.showMessageDialog(null,argo1);
			}
			}
		});
		
		addToOrder.setBounds(263, 408, 132, 23);
		contentPane.add(addToOrder);
		
		productNameField = new JTextField();
		productNameField.setBounds(263, 338, 106, 20);
		contentPane.add(productNameField);
		productNameField.setColumns(10);
		
		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(227, 205, 49, 14);
		contentPane.add(quantityLabel);
		/**
		 * ComboBox to Select Quantity
		 * 
		 */
		
		String[] nums = {"1","2","3","4","5","6","7","8","9","10","25","50","100"};
		qtyComboBox = new JComboBox(nums);
		qtyComboBox.setBounds(340, 201, 55, 22);
		contentPane.add(qtyComboBox);
		
		JLabel priceLabel = new JLabel("Price");
		priceLabel.setBounds(261, 369, 49, 14);
		contentPane.add(priceLabel);
		
		priceTextField = new JTextField();
		priceTextField.setBounds(320, 369, 75, 23);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);
	}
}
