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

public class GUIEmployeeNew extends JFrame {
	private JTable table;
	private JTextField txtFornavn;
	private JTextField txtEfter;
	private JTextField txtAddresse;
	private JTextField txtBy;
	private JTextField txtPostn;
	private JTextField txtTelefonnummer;
	private JTextField txtEmail;
	private JTextField txtFirmaNavn;
	public GUIEmployeeNew() {
		
		setTitle("Ny Medarbejder");
		setSize(465, 470);
		getContentPane().setLayout(null);
		
		txtFornavn = new JTextField();
		txtFornavn.setText("Fornavn");
		txtFornavn.setBounds(22, 32, 339, 38);
		getContentPane().add(txtFornavn);
		txtFornavn.setColumns(10);
		
		txtEfter = new JTextField();
		txtEfter.setText("Efternavn");
		txtEfter.setColumns(10);
		txtEfter.setBounds(22, 81, 339, 38);
		getContentPane().add(txtEfter);
		
		txtAddresse = new JTextField();
		txtAddresse.setText("Addresse");
		txtAddresse.setColumns(10);
		txtAddresse.setBounds(22, 130, 339, 38);
		getContentPane().add(txtAddresse);
		
		txtBy = new JTextField();
		txtBy.setText("By");
		txtBy.setColumns(10);
		txtBy.setBounds(22, 179, 169, 38);
		getContentPane().add(txtBy);
		
		txtPostn = new JTextField();
		txtPostn.setText("Postnummer");
		txtPostn.setColumns(10);
		txtPostn.setBounds(254, 179, 107, 38);
		getContentPane().add(txtPostn);
		
		txtTelefonnummer = new JTextField();
		txtTelefonnummer.setText("Telefonnummer");
		txtTelefonnummer.setColumns(10);
		txtTelefonnummer.setBounds(22, 228, 169, 38);
		getContentPane().add(txtTelefonnummer);
		
		txtEmail = new JTextField();
		txtEmail.setText("E-mail");
		txtEmail.setColumns(10);
		txtEmail.setBounds(22, 277, 339, 38);
		getContentPane().add(txtEmail);
		
		txtFirmaNavn = new JTextField();
		txtFirmaNavn.setText("Job Title");
		txtFirmaNavn.setColumns(10);
		txtFirmaNavn.setBounds(22, 326, 169, 38);
		getContentPane().add(txtFirmaNavn);
		
		JLabel lblFornavn = new JLabel("Fornavn");
		lblFornavn.setBounds(371, 44, 46, 14);
		getContentPane().add(lblFornavn);
		
		JLabel lblEfternavn = new JLabel("Efternavn");
		lblEfternavn.setBounds(371, 93, 63, 14);
		getContentPane().add(lblEfternavn);
		
		JLabel lblAddresse = new JLabel("Addresse");
		lblAddresse.setBounds(371, 142, 46, 14);
		getContentPane().add(lblAddresse);
		
		JLabel lblBy = new JLabel("By");
		lblBy.setBounds(198, 191, 46, 14);
		getContentPane().add(lblBy);
		
		JLabel lblPostnummer = new JLabel("Postnummer");
		lblPostnummer.setBounds(371, 191, 83, 14);
		getContentPane().add(lblPostnummer);
		
		JLabel lblTelefonnummer = new JLabel("Telefonnummer");
		lblTelefonnummer.setBounds(201, 240, 89, 14);
		getContentPane().add(lblTelefonnummer);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(371, 289, 46, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblFirmaNavn = new JLabel("Job Title");
		lblFirmaNavn.setBounds(201, 338, 63, 14);
		getContentPane().add(lblFirmaNavn);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.setBounds(22, 386, 89, 23);
		getContentPane().add(btnAnnuller);
		
		JButton btnGem = new JButton("Gem");
		btnGem.setBounds(345, 386, 89, 23);
		getContentPane().add(btnGem);
		
		table = new JTable();
		table.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ny medarbejder", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 432, 413);
		getContentPane().add(table);
		getContentPane();
		setVisible(true);
	}

}
