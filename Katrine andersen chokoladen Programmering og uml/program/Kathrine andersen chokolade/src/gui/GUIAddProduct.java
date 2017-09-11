package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import ctr.OrderCtr;
import ctr.ProductCtr;
import ctr.SaleLineItemCtr;
import model.Customer;
import model.Order;
import model.Product;
import model.SaleLineItem;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIAddProduct  extends JFrame {
	private JTable table;
	private JTextField txtSgVare;
	private JTable table_1;
	DefaultTableModel vareList = new DefaultTableModel();
	DefaultTableModel OrderList = new DefaultTableModel();
	private JTextField txtMngde;
	private JLabel lblMngde;
	private JButton btnAnuller;
	private JButton btnGem;
	private JButton btnFjern;
	private JTable table_2;
	private JButton btnSgVare;
	private Order order;
	private GUINewOrder guiNewOrder;
	private LinkedList<Product> productList;
	private double totalPrice;
	private GUIMain guiMain;
	private OrderCtr orderCtr;
	
	
	/**
	 * @wbp.parser.constructor
	 */
	public GUIAddProduct(Order o, GUINewOrder guiNewOrder) {
		order = o;
		this.guiNewOrder = guiNewOrder;
		SaleLineItemCtr saleLineItemCtr = new SaleLineItemCtr();
		ArrayList<SaleLineItem> saleLineItemList = new ArrayList<>();
		setTitle("Tilføj vare");
		setSize(1200, 663);
		getContentPane().setLayout(null);
		
		txtSgVare = new JTextField();
		txtSgVare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgVare.setText(""); 
			}
		});
		txtSgVare.setText("S\u00F8g vare");
		txtSgVare.setBounds(21, 28, 127, 30);
		getContentPane().add(txtSgVare);
		txtSgVare.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 68, 511, 480);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(vareList);
		
		vareList.addColumn("ID");
		vareList.addColumn("Navn");
		vareList.addColumn("Pris");
		vareList.addColumn("Lager antal");
		
		scrollPane.setViewportView(table_1);
		
		txtMngde = new JTextField();
		txtMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMngde.setText(""); 
			}
		});
		txtMngde.setText("M\u00E6ngde");
		txtMngde.setColumns(10);
		txtMngde.setBounds(541, 85, 89, 30);
		getContentPane().add(txtMngde);
		
		lblMngde = new JLabel("M\u00E6ngde");
		lblMngde.setBounds(541, 68, 46, 14);
		getContentPane().add(lblMngde);
		
		btnAnuller = new JButton("Anuller");
		btnAnuller.setBounds(10, 573, 89, 23);
		getContentPane().add(btnAnuller);
		
		btnGem = new JButton("Gem");
		btnGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIConfirm confirm = new GUIConfirm("Vare er tilføjet til ordren.");
				confirm.setModal(true);
				confirm.setVisible(true);
				if (confirm.getConfirmResult() == 1) {
					int saleLineItemIndex = 0;
					while(saleLineItemIndex < saleLineItemList.size()){
						SaleLineItem s = saleLineItemList.get(saleLineItemIndex);
						guiNewOrder.OrderList.addRow(new Object []{ s.getProduct().getId(), s.getProduct().getName(), s.getQuantity(), s.getProduct().getTotalQty(), s.getProduct().getPrice(), s.getPrice()});
						guiNewOrder.saleLineItem.add(s);
						totalPrice = totalPrice + s.getPrice();
						order.setTotalPrice(totalPrice);
						saleLineItemIndex++;
					}
				}
				dispose();
			}
		});
		btnGem.setBounds(1085, 573, 89, 23);
		getContentPane().add(btnGem);
		
		JButton btnTilfj = new JButton("Tilf\u00F8j >");
		btnTilfj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table_1.getSelectedRow();
				Product p = productList.get(selectedRow);
				int quantity = Integer.parseInt(txtMngde.getText());
				
				SaleLineItem sli;
				try {
					sli = saleLineItemCtr.createSaleLineItem(p, order, quantity);
					saleLineItemList.add(sli);
					OrderList.addRow(new Object [] {sli.getProduct().getId(), sli.getProduct().getName(), sli.getPrice(), sli.getQuantity()});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});
		btnTilfj.setBounds(541, 126, 89, 23);
		getContentPane().add(btnTilfj);
		
		btnFjern = new JButton("< Fjern");
		btnFjern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				saleLineItemList.remove(selectedRow);
				OrderList.removeRow(selectedRow);
			}
		});
		btnFjern.setBounds(541, 525, 89, 23);
		getContentPane().add(btnFjern);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(652, 68, 511, 480);
		getContentPane().add(scrollPane_1);
		
		table_2 = new JTable(OrderList);
		
		OrderList.addColumn("ID");
		OrderList.addColumn("Navn");
		OrderList.addColumn("Pris");
		OrderList.addColumn("Bestilt antal");
		
		scrollPane_1.setViewportView(table_2);
		
		btnSgVare = new JButton("S\u00F8g vare");
		btnSgVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCtr productCtr = new ProductCtr();
				try {
					productList = productCtr.findProductByName(txtSgVare.getText());
					
					vareList.setRowCount(0);
					
					int productListIndex = 0;
					while(productListIndex < productList.size()) {
						Product p = productList.get(productListIndex);
						vareList.addRow(new Object []{ p.getId(), p.getName(), p.getPrice(), p.getTotalQty()});
						productListIndex++;
						}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSgVare.setBounds(154, 32, 89, 23);
		getContentPane().add(btnSgVare);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Vare", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 1164, 549);
		getContentPane().add(table);
		
		getContentPane();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		
	}


	public GUIAddProduct(Order order, GUIMain guiMain) {
		//this.order = order;
		//this.guiMain = guiMain;
		SaleLineItemCtr saleLineItemCtr = new SaleLineItemCtr();
		ArrayList<SaleLineItem> saleLineItemList = new ArrayList<>();
		setTitle("Tilføj vare");
		setSize(1200, 663);
		getContentPane().setLayout(null);
		
		txtSgVare = new JTextField();
		txtSgVare.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgVare.setText(""); 
			}
		});
		txtSgVare.setText("S\u00F8g vare");
		txtSgVare.setBounds(21, 28, 127, 30);
		getContentPane().add(txtSgVare);
		txtSgVare.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 68, 511, 480);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(vareList);
		
		vareList.addColumn("ID");
		vareList.addColumn("Navn");
		vareList.addColumn("Pris");
		vareList.addColumn("Lager antal");
		
		scrollPane.setViewportView(table_1);
		
		txtMngde = new JTextField();
		txtMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMngde.setText(""); 
			}
		});
		txtMngde.setText("M\u00E6ngde");
		txtMngde.setColumns(10);
		txtMngde.setBounds(541, 85, 89, 30);
		getContentPane().add(txtMngde);
		
		lblMngde = new JLabel("M\u00E6ngde");
		lblMngde.setBounds(541, 68, 46, 14);
		getContentPane().add(lblMngde);
		
		btnAnuller = new JButton("Anuller");
		btnAnuller.setBounds(10, 573, 89, 23);
		getContentPane().add(btnAnuller);
		
		btnGem = new JButton("Gem");
		btnGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saleLineItemIndex = 0;
				while(saleLineItemIndex < saleLineItemList.size()) {
					SaleLineItem s = saleLineItemList.get(saleLineItemIndex);
					guiMain.oi.addRow(new Object []{ s.getProduct().getId(), s.getProduct().getName(), "dakla", s.getQuantity(), s.getProduct().getTotalQty(), s.getProduct().getPrice(), s.getPrice()});
					guiMain.productList.add(s);
					totalPrice = order.getTotalPrice() + s.getPrice();
					order.setTotalPrice(totalPrice);
					System.out.println("totalPrice: " + order.getTotalPrice());
					s.setOrderId(order.getId());
					try {
						saleLineItemCtr.addSaleLineItemToDB(s);
						System.out.println("Sli id: " + s.getId());
						OrderCtr orderCtr = new OrderCtr();
						orderCtr.updateOrderInDB(order, totalPrice, order.getTax(), order.getDate(), order.getStatus(), order.getDiscountPrice(), order.getCustomer(), order.getCompany(), order.getEmployee());
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					saleLineItemIndex++;
				}
				dispose();
			}
		});
		btnGem.setBounds(1085, 573, 89, 23);
		getContentPane().add(btnGem);
		
		JButton btnTilfj = new JButton("Tilf\u00F8j >");
		btnTilfj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table_1.getSelectedRow();
				Product p = productList.get(selectedRow);
				int quantity = Integer.parseInt(txtMngde.getText());
				
				SaleLineItem sli;
				try {
					sli = saleLineItemCtr.createSaleLineItem(p, order, quantity);
					saleLineItemList.add(sli);
					OrderList.addRow(new Object [] {sli.getProduct().getId(), sli.getProduct().getName(), sli.getPrice(), sli.getQuantity()});
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
			}
		});
		btnTilfj.setBounds(541, 126, 89, 23);
		getContentPane().add(btnTilfj);
		
		btnFjern = new JButton("< Fjern");
		btnFjern.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				saleLineItemList.remove(selectedRow);
				OrderList.removeRow(selectedRow);
			}
		});
		btnFjern.setBounds(541, 525, 89, 23);
		getContentPane().add(btnFjern);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(652, 68, 511, 480);
		getContentPane().add(scrollPane_1);
		
		table_2 = new JTable(OrderList);
		
		OrderList.addColumn("ID");
		OrderList.addColumn("Navn");
		OrderList.addColumn("Pris");
		OrderList.addColumn("Bestilt antal");
		
		scrollPane_1.setViewportView(table_2);
		
		btnSgVare = new JButton("S\u00F8g vare");
		btnSgVare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProductCtr productCtr = new ProductCtr();
				try {
					productList = productCtr.findProductByName(txtSgVare.getText());
					
					vareList.setRowCount(0);
					
					int productListIndex = 0;
					while(productListIndex < productList.size()) {
						Product p = productList.get(productListIndex);
						vareList.addRow(new Object []{ p.getId(), p.getName(), p.getPrice(), p.getTotalQty()});
						productListIndex++;
						}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnSgVare.setBounds(154, 32, 89, 23);
		getContentPane().add(btnSgVare);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Vare", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 1164, 549);
		getContentPane().add(table);
		
		getContentPane();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	
	/*
	 * This is the Refresh Recipe Secondary Table (VareList) Method
	 * For Each loop is refreshing the JTables by removing all rows and reconstructing
	 */
	
	public void refreshRecipeTableSecond() {
		for (int x = vareList.getRowCount()-1; x>= 0; x--) {
			vareList.removeRow(x);
			System.out.println("Row Remowed");
		}
			
	}
}
