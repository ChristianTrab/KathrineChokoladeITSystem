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

public class GUIEmployee extends JFrame {
	private JTable table;
	private JTextField txtIdNavnTlf;
	private JTable table_1;
	DefaultTableModel EmplList = new DefaultTableModel();
	public GUIEmployee() {
		
		
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1920, 1045);
		getContentPane().setLayout(null);
		
		txtIdNavnTlf = new JTextField();
		txtIdNavnTlf.setText("Id, Navn, Tlf nr");
		txtIdNavnTlf.setColumns(10);
		txtIdNavnTlf.setBounds(23, 31, 140, 28);
		getContentPane().add(txtIdNavnTlf);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 74, 1860, 814);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(EmplList);
		
		EmplList.addColumn("ID");
		EmplList.addColumn("Navn");
		EmplList.addColumn("Addresse");
		EmplList.addColumn("Telefon Nr.");
		EmplList.addColumn("E-mail");
		EmplList.addColumn("Job Title");
		
		scrollPane.setViewportView(table_1);
		
		JButton btnSletMedarbejder = new JButton("Slet Medarbejder");
		btnSletMedarbejder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletMedarbejder.setBackground(Color.RED);
		btnSletMedarbejder.setBounds(1715, 899, 166, 37);
		getContentPane().add(btnSletMedarbejder);
		
		JButton btnTilfjMedarbedjer = new JButton("Tilf\u00F8j Medarbedjer");
		btnTilfjMedarbedjer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTilfjMedarbedjer.setBounds(23, 899, 166, 37);
		getContentPane().add(btnTilfjMedarbedjer);
		
		JButton btnRedigereMedarbejder = new JButton("Redigere Medarbejder");
		btnRedigereMedarbejder.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereMedarbejder.setBounds(199, 899, 166, 37);
		getContentPane().add(btnRedigereMedarbejder);
		
		JButton btnSgMedarbejder = new JButton("S\u00F8g medarbejder");
		btnSgMedarbejder.setBounds(173, 34, 121, 23);
		getContentPane().add(btnSgMedarbejder);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Medarbejder", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 1884, 959);
		getContentPane().add(table);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnOrdre = new JButton("Ordre");
		menuBar.add(btnOrdre);
		
		JButton btnProdukt = new JButton("Produkt");
		menuBar.add(btnProdukt);
		
		JButton btnKunde = new JButton("Kunde");
		menuBar.add(btnKunde);
		
		JButton btnAnsatte = new JButton("Medarbejder");
		menuBar.add(btnAnsatte);
	}
}
