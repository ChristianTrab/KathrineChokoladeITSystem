package gui;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.*;

import ctr.ProductCtr;
import ctr.SaleLineItemCtr;
import model.Order;
import model.SaleLineItem;
import java.awt.BorderLayout;

public class GUIShowReceipt extends JFrame{
	JTextArea txtrHej = new JTextArea();
	
	public GUIShowReceipt(Order order, LinkedList<SaleLineItem> productList, SaleLineItemCtr sLICtr) throws SQLException {
		setTitle("Faktura");
		setSize(549, 463);
		getContentPane().setLayout(null);

		txtrHej.setLocation(0, 0);
		txtrHej.setSize(531, 416);
		txtrHej.setText("");
		getContentPane().add(txtrHej, BorderLayout.NORTH);
		txtrHej.setEditable(false);
	
		
		txtrHej.append("Kunde ID: " + order.getCustomer().getId() + "\n" + "Kunde navn: " + order.getCustomer().getfName() + " " + order.getCustomer().getlName() + "\n" + "Kunde type: " + order.getCustomer().getType() + "\n" + "Kunde firma: " + order.getCustomer().getCompanyName() + "\n" + "Kunde CVR: " + order.getCustomer().getCvr() + "\n" + "Leveringsadresse: " + order.getCustomer().getAddress() + "\n" + "By & Postnr. " + order.getCustomer().getCity() + " - " + order.getCustomer().getZipCode() + "\n" + "Telefon nummer: " + order.getCustomer().getPhone() + "\n" + "Email: " + order.getCustomer().getEmail() + "\n" + "Beløb: " + order.getTotalPrice() + "\n" + "Moms (25%): " + order.getTax() + "\n" + "Ordre Rabat: " + order.getDiscountPrice());
		txtrHej.append("\n" + "-----------------------------------------");
		txtrHej.append("\n" + "Produkt Id | Navn            | Antal    | Pris");
		printProducts(order, productList);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void printProducts(Order order, LinkedList<SaleLineItem> productList) throws SQLException{
		
		for(SaleLineItem sli : productList){
			System.out.println(sli.getProduct().getTotalQty());
			txtrHej.append("\n" + sli.getProduct().getId() + " " + sli.getProduct().getName() + " " + sli.getQuantity() + "  " + sli.getPrice());
		}
	
		
	}
}
