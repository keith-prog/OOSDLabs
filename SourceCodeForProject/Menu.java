package lab11;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Menu extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * Default Constructor
	 */
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 339);
		contentPane = new JPanel();
		contentPane.setBackground(Color.MAGENTA);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		/**
		 * Customer  Button
		 * Launch CustomerRegistration Screen
		 */
		JButton custBtn = new JButton("Customer");
		custBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerReg frame = new CustomerReg();
				frame.setVisible(true);
				frame.setTitle("CustomerReg");
			}
		});
		custBtn.setBounds(135, 113, 89, 38);
		contentPane.add(custBtn);
		/**
		 * Product Button
		 * Launch Product Screen
		 */
		JButton productBtn = new JButton("Product");
		productBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Product frame = new Product();
				frame.setVisible(true);
				frame.setTitle("Product");
			}
			
		});
		productBtn.setBounds(244, 113, 89, 38);
		contentPane.add(productBtn);
		/**
		 * Order Button
		 * Launch Order Screen
		 */
		JButton orderBtn = new JButton("Order");
		orderBtn.setBackground(Color.YELLOW);
		orderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Order frame = new Order();
				frame.setVisible(true);
				frame.setTitle("Orders");
			}
		});
		orderBtn.setBounds(135, 162, 89, 38);
		contentPane.add(orderBtn);
		/** 
		 * Invoice Button
		 * Launch Invoice Screen
		 */
		JButton invoiceBtn = new JButton("Invoice");
		invoiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Invoice1 frame = new Invoice1();
				frame.setVisible(true);
				frame.setTitle("Invoice");
			}
		});
		invoiceBtn.setBounds(244, 162, 89, 38);
		contentPane.add(invoiceBtn);
		
		JLabel lblNewLabel = new JLabel("Main Menu");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(160, 48, 162, 38);
		contentPane.add(lblNewLabel);
	}
}
