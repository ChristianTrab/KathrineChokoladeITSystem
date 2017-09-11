package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIOrderNew extends JFrame {
	private JTextField txtSgKunde;
	private JTable table_1;
	DefaultTableModel KundeList = new DefaultTableModel();
	DefaultTableModel ValgtKundeList = new DefaultTableModel();
	DefaultTableModel OrderList = new DefaultTableModel();
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTable table_2;
	private JScrollPane scrollPane_1;
	private JLabel lblSetStatus;
	private JTable table;
	private JTable table_3;
	private JTable table_4;
	private JScrollPane scrollPane_2;
	private JButton btnTilfjVare;
	private JButton btnFjernVare;
	private JButton btnRedigereVare;
	private JButton btnAnnuller;
	private JButton btnNewButton_2;
	private JButton btnSgKunde;
	public GUIOrderNew() {
		
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1000, 865);
		getContentPane().setLayout(null);
		
		txtSgKunde = new JTextField();
		txtSgKunde.setBounds(24, 30, 119, 27);
		txtSgKunde.setText("S\u00F8g kunde");
		getContentPane().add(txtSgKunde);
		txtSgKunde.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 67, 535, 236);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(KundeList);
		
		KundeList.addColumn("ID");
		KundeList.addColumn("Firma Navn");
		KundeList.addColumn("Navn");
		KundeList.addColumn("CVR");
		
		scrollPane.setViewportView(table_1);
		
		btnNewButton = new JButton("Ny kunde");
		btnNewButton.setBounds(568, 67, 125, 37);
		getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("Tilf\u00F8j til order");
		btnNewButton_1.setBounds(24, 314, 149, 37);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		getContentPane().add(btnNewButton_1);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 362, 535, 60);
		getContentPane().add(scrollPane_1);
		
		table_2 = new JTable(ValgtKundeList);
		
		ValgtKundeList.addColumn("ID");
		ValgtKundeList.addColumn("Firma Navn");
		ValgtKundeList.addColumn("Navn");
		ValgtKundeList.addColumn("CVR");
		
		scrollPane_1.setViewportView(table_2);
		
		lblSetStatus = new JLabel("Set Status ?");
		lblSetStatus.setBounds(569, 363, 94, 14);
		getContentPane().add(lblSetStatus);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(24, 482, 789, 279);
		getContentPane().add(scrollPane_2);
		
		table_4 = new JTable(OrderList);
		
		OrderList.addColumn("ID");
		OrderList.addColumn("Produkt navn");
		OrderList.addColumn("Emballage navn");
		OrderList.addColumn("Bestilt antal");
		OrderList.addColumn("Lager antal");
		OrderList.addColumn("Stk. pris");
		OrderList.addColumn("Samlet pris");
		
		scrollPane_2.setViewportView(table_4);
		
		btnTilfjVare = new JButton("Tilf\u00F8j vare");
		btnTilfjVare.setBounds(823, 482, 125, 37);
		getContentPane().add(btnTilfjVare);
		
		btnFjernVare = new JButton("Fjern vare");
		btnFjernVare.setBounds(823, 724, 125, 37);
		getContentPane().add(btnFjernVare);
		
		btnRedigereVare = new JButton("Redigere vare");
		btnRedigereVare.setBounds(823, 530, 125, 37);
		getContentPane().add(btnRedigereVare);
		
		table_3 = new JTable();
		table_3.setBorder(new TitledBorder(null, "Order liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_3.setBackground(UIManager.getColor("Button.background"));
		table_3.setBounds(10, 455, 964, 319);
		getContentPane().add(table_3);
		
		btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(10, 792, 89, 23);
		getContentPane().add(btnAnnuller);
		
		btnNewButton_2 = new JButton("Gem");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(885, 792, 89, 23);
		getContentPane().add(btnNewButton_2);
		
		btnSgKunde = new JButton("S\u00F8g kunde");
		btnSgKunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findCustomer(txtSgKunde.getText());
			}
		});
		btnSgKunde.setBounds(153, 32, 89, 23);
		getContentPane().add(btnSgKunde);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Tilf\u00F8j kunde til ordre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 964, 427);
		getContentPane().add(table);
		
		getContentPane();
		setVisible(true);
	}
	
	public void findCustomer(String search) {
		
	}
}
