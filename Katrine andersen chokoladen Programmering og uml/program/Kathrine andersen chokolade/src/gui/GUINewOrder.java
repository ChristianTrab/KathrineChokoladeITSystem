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
import ctr.OrderCtr;
import ctr.SaleLineItemCtr;
import model.Company;
import model.Customer;
import model.Employee;
import model.Order;
import model.SaleLineItem;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import ctr.CustomerCtr;

public class GUINewOrder extends JFrame {
	private GUINewOrder guiNewOrder;
	private JTextField txtSgKunde;
	private JTable table_1;
	DefaultTableModel KundeList = new DefaultTableModel();
	DefaultTableModel ValgtKundeList = new DefaultTableModel();
	DefaultTableModel OrderList = new DefaultTableModel();
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable table_2;
	private JScrollPane scrollPane_1;
	private JTable table;
	private JTable table_3;
	private JTable table_4;
	private JScrollPane scrollPane_2;
	private JButton btnTilfjVare;
	private JButton btnFjernVare;
	private JButton btnRedigereVare;
	private JButton btnAnnuller;
	private JButton btnNewButton_2;
	public CustomerCtr customerCtr;
	private LinkedList<Customer> list;
	LinkedList<SaleLineItem> saleLineItem = new LinkedList<>();
	private SaleLineItemCtr saleLineItemCtr;
	private JTextField textField;
	private JTable table_6;
	
	public GUINewOrder() {
		OrderCtr orderCtr = new OrderCtr();
		saleLineItemCtr = new SaleLineItemCtr();
		guiNewOrder = this;
			Order order;
			try {
				order = orderCtr.createEmptyOrder();
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1000, 865);
		getContentPane().setLayout(null);
		
		txtSgKunde = new JTextField();
		txtSgKunde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgKunde.setText(""); 
			}
		});
		txtSgKunde.setBounds(24, 30, 119, 27);
		txtSgKunde.setText("S\u00F8g kunde");
		getContentPane().add(txtSgKunde);
		txtSgKunde.setColumns(10);
		
		
		JButton btnSgKunde = new JButton("S\u00F8g kunde");
		btnSgKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					customerCtr = new CustomerCtr();
					list = customerCtr.findCustomerByString(txtSgKunde.getText());
					
					KundeList.setRowCount(0);
					
					
					int customerListIndex = 0;
					while(customerListIndex < list.size()) {
						Customer c = list.get(customerListIndex);
						KundeList.addRow(new Object[] { c.getId(), c.getCompanyName(), c.getfName() + " " + c.getlName(), c.getCvr() });
						customerListIndex++;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnSgKunde.setBounds(155, 31, 97, 25);
		getContentPane().add(btnSgKunde);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 68, 900, 206);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(KundeList);
		
		KundeList.addColumn("ID");
		KundeList.addColumn("Firma Navn");
		KundeList.addColumn("Navn");
		KundeList.addColumn("CVR");
		
		scrollPane.setViewportView(table_1);
		
		btnNewButton = new JButton("Ny kunde");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GUICustomerNew guiCustomerNew = new GUICustomerNew();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(262, 31, 89, 25);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Tilf\u00F8j til order");
		btnNewButton_1.setBounds(35, 295, 141, 45);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ValgtKundeList.setRowCount(0);
				
				
				int selectedRow = table_1.getSelectedRow();
				Customer c = list.get(selectedRow);
				ValgtKundeList.addRow(new Object[] { c.getId(), c.getCompanyName(), c.getfName() + " " + c.getlName(), c.getCvr() });
				order.setCustomer(c);
			}
		});
		getContentPane().add(btnNewButton_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(194, 305, 740, 43);
		getContentPane().add(scrollPane_1);
		
		table_2 = new JTable(ValgtKundeList);
		
		ValgtKundeList.addColumn("ID");
		ValgtKundeList.addColumn("Firma Navn");
		ValgtKundeList.addColumn("Navn");
		ValgtKundeList.addColumn("CVR");
		
		scrollPane_1.setViewportView(table_2);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 467, 789, 294);
		getContentPane().add(scrollPane_2);
		
		table_4 = new JTable(OrderList);
		
		OrderList.addColumn("ID");
		OrderList.addColumn("Produkt navn");
//		OrderList.addColumn("Emballage navn");
		OrderList.addColumn("Bestilt antal");
		OrderList.addColumn("Lager antal");
		OrderList.addColumn("Stk. pris");
		OrderList.addColumn("Samlet pris");
		
		scrollPane_2.setViewportView(table_4);
		
		btnTilfjVare = new JButton("Tilf\u00F8j vare");
		btnTilfjVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIAddProduct guiAddProduct = new GUIAddProduct(order, guiNewOrder);
			}
		});
		btnTilfjVare.setBounds(823, 482, 125, 37);
		getContentPane().add(btnTilfjVare);
		
		btnFjernVare = new JButton("Fjern vare");
		btnFjernVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_4.getSelectedRow();
				OrderList.removeRow(selectedRow);
				//Delete SaleLineItem from DB
				SaleLineItemCtr saleLineItemCtr = new SaleLineItemCtr();
				try {
					saleLineItemCtr.deleteSaleLineItem(order, saleLineItem.get(selectedRow));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnFjernVare.setBounds(823, 724, 125, 37);
		getContentPane().add(btnFjernVare);
		
		btnRedigereVare = new JButton("Redigere antal");
		btnRedigereVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRedigereVare.setBounds(823, 530, 125, 37);
		getContentPane().add(btnRedigereVare);
		
		table_3 = new JTable();
		table_3.setBorder(new TitledBorder(null, "Order liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_3.setBackground(UIManager.getColor("Button.background"));
		table_3.setBounds(10, 438, 964, 336);
		getContentPane().add(table_3);
		
		btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					orderCtr.deleteOrder(order);
					dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAnnuller.setBounds(10, 792, 89, 23);
		getContentPane().add(btnAnnuller);
		
		btnNewButton_2 = new JButton("Gem");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.setDate(textField.getText());
				Employee employee = new Employee();
				Company company = new Company();
				order.setTax(order.getTotalPrice()*0.25);
				try {
					GUIConfirm confirm = new GUIConfirm("Ordren er oprettet og gemt i systemet.");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						orderCtr.addOrderToDB(order, order.getTotalPrice(), order.getTax(), order.getDate(), 1, 0, order.getCustomer(), company, employee);
						int saleLineItemIndex = 0;
						while(saleLineItemIndex < saleLineItem.size()) {
							SaleLineItem s = saleLineItem.get(saleLineItemIndex);
							s.setOrderId(order.getId());
							saleLineItemCtr.addSaleLineItemToDB(s);
							saleLineItemIndex++;
						}
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_2.setBounds(885, 792, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		getContentPane();
		
		textField = new JTextField();
		textField.setText("01-01-1900");
		textField.setBounds(34, 376, 149, 37);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblLeveringsdato = new JLabel("Leveringsdato");
		lblLeveringsdato.setBounds(194, 387, 86, 14);
		getContentPane().add(lblLeveringsdato);
		
		table_6 = new JTable();
		table_6.setBorder(new TitledBorder(null, "Valgte kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_6.setBackground(UIManager.getColor("Button.background"));
		table_6.setBounds(183, 285, 765, 73);
		getContentPane().add(table_6);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Tilf\u00F8j kunde til ordre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 964, 416);
		getContentPane().add(table);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
}
