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

public class GUIPackingNew extends JFrame{
	private JTable table;
	private JTextField txtNavn;
	private JTextField txtType;
	private JTextField txtStrrelse;
	private JTextField txtMngde;
	public GUIPackingNew() {
		
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(265, 320);
		getContentPane().setLayout(null);
		
		txtNavn = new JTextField();
		txtNavn.setText("Navn");
		txtNavn.setBounds(22, 40, 138, 32);
		getContentPane().add(txtNavn);
		txtNavn.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Navn");
		lblNewLabel.setBounds(170, 49, 46, 14);
		getContentPane().add(lblNewLabel);
		
		txtType = new JTextField();
		txtType.setText("Type");
		txtType.setColumns(10);
		txtType.setBounds(22, 83, 138, 32);
		getContentPane().add(txtType);
		
		txtStrrelse = new JTextField();
		txtStrrelse.setText("St\u00F8rrelse");
		txtStrrelse.setColumns(10);
		txtStrrelse.setBounds(22, 126, 138, 32);
		getContentPane().add(txtStrrelse);
		
		txtMngde = new JTextField();
		txtMngde.setText("M\u00E6ngde");
		txtMngde.setColumns(10);
		txtMngde.setBounds(22, 169, 138, 32);
		getContentPane().add(txtMngde);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(170, 92, 46, 14);
		getContentPane().add(lblType);
		
		JLabel lblStrrelse = new JLabel("St\u00F8rrelse");
		lblStrrelse.setBounds(170, 135, 46, 14);
		getContentPane().add(lblStrrelse);
		
		JLabel lblMngde = new JLabel("M\u00E6ngde");
		lblMngde.setBounds(170, 178, 46, 14);
		getContentPane().add(lblMngde);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(22, 241, 89, 23);
		getContentPane().add(btnAnnuller);
		
		JButton btnGem = new JButton("Gem");
		btnGem.setBounds(141, 241, 89, 23);
		getContentPane().add(btnGem);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Emballage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 229, 262);
		getContentPane().add(table);
	}
	


}
