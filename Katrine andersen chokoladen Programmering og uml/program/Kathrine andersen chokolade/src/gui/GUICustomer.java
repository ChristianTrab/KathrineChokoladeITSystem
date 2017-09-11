package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import ctr.CustomerCtr;
import model.Customer;
import model.Product;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUICustomer extends JFrame {
	private JTable table;
	private JTextField txtIdNavnFirma;
	private JTable table_1;
	private CustomerCtr customerCtr = new CustomerCtr();
	private DefaultTableModel cusList = new DefaultTableModel();
	private LinkedList<Customer> customerList = new LinkedList<>();
	LinkedList<Product> productList;
	private int customerId, selectedCustomerRow;
	
	public GUICustomer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1920, 1045);
		getContentPane().setLayout(null);
		
		txtIdNavnFirma = new JTextField();
		txtIdNavnFirma.setText("Id, Navn, Firma, Tlf nr");
		txtIdNavnFirma.setBounds(26, 31, 140, 28);
		getContentPane().add(txtIdNavnFirma);
		txtIdNavnFirma.setColumns(10);
		txtIdNavnFirma.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIdNavnFirma.setText("");
			}
		});
		
		JButton btnSgKunde = new JButton("S\u00F8g kunde");
		btnSgKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshProductTable();
				try {
					searchCustomer(txtIdNavnFirma.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSgKunde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSgKunde.setBounds(176, 34, 101, 23);
		getContentPane().add(btnSgKunde);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(26, 70, 1853, 835);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(cusList);
		table_1.setDefaultEditor(Object.class, null);				// Makes the cell non-editable
		
		cusList.addColumn("ID");
		cusList.addColumn("Navn");
		cusList.addColumn("Firma");
		cusList.addColumn("Type");
		cusList.addColumn("Tlf nr");
		cusList.addColumn("E-mail");
		cusList.addColumn("CVR");
		cusList.addColumn("Betalings info");
		cusList.addColumn("Leveringsadresse");
		
		scrollPane.setViewportView(table_1);
		
		JButton btnSletKunde = new JButton("Slet kunde");
		btnSletKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette den valgte kunde?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						deleteCustomer();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			

		});
		btnSletKunde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletKunde.setBounds(1713, 916, 166, 37);
		btnSletKunde.setBackground(Color.RED);
		getContentPane().add(btnSletKunde);
		
		JButton btnTilfjKunde = new JButton("Tilf\u00F8j kunde");
		btnTilfjKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUICustomerNew guiCustomerNew = new GUICustomerNew();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnTilfjKunde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTilfjKunde.setBounds(26, 916, 166, 37);
		getContentPane().add(btnTilfjKunde);
		
		JButton btnRedigereKunde = new JButton("Redigere kunde");
		btnRedigereKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnRedigereKunde.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereKunde.setBounds(205, 916, 166, 37);
		getContentPane().add(btnRedigereKunde);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBorder(new TitledBorder(null, "Kunde Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBounds(10, 11, 1884, 959);
		getContentPane().add(table);
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setVisible(true);
	}
	
	public void searchCustomer(String search) throws SQLException {
		customerList = customerCtr.findCustomerByString(search);
		
		int customerListIndex = 0;
		while (customerListIndex < customerList.size()) {
			Customer customer = customerList.get(customerListIndex);
			cusList.addRow(new Object [] {customer.getId(), customer.getfName() + " " + customer.getlName(), customer.getCompanyName(), customer.getType(), customer.getPhone(), customer.getEmail(), customer.getCvr(), customer.getPayment(), customer.getAddress() + " - " + customer.getCity() + ", " + customer.getZipCode()});
			customerListIndex++;
		}
	}
		
	
	public void deleteCustomer() throws SQLException {
		int selectedCustomerRow = table_1.getSelectedRow();
		Customer c = customerList.get(selectedCustomerRow);
		cusList.removeRow(selectedCustomerRow);
		customerCtr.deleteCustomer(c.getId());
		
	}
	
	
	public void refreshProductTable() { 		// Clears the Customer List Jtable
		for (int x = cusList.getRowCount()-1; x >= 0; x--) {
			cusList.removeRow(x);
			System.out.println("Row removed");
		}
	}
	
	public static void kunde(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		// TODO Auto-generated method stub
		GUICustomer gui = new GUICustomer();
	}
}