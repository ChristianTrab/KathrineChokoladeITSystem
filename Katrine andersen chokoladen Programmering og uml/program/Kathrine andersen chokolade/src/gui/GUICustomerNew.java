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

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUICustomerNew extends JFrame {
	private JTable table;
	private JTextField txtFornavn;
	private JTextField txtEfter;
	private JTextField txtAddresse;
	private JTextField txtBy;
	private JTextField txtPostn;
	private JTextField txtTelefonnummer;
	private JTextField txtEmail;
	private JTextField txtFirmaNavn;
	private JTextField txtCvrNummer;
	private JTextField txtKontoregNummer;
	private CustomerCtr customerCtr = new CustomerCtr();
	private LinkedList<String> categoryList;
	
	public GUICustomerNew() throws SQLException {
		setTitle("Ny Kunde");
		setSize(465, 720);
		getContentPane().setLayout(null);
		
		txtFornavn = new JTextField();
		txtFornavn.setText("Fornavn");
		txtFornavn.setBounds(22, 32, 294, 38);
		getContentPane().add(txtFornavn);
		txtFornavn.setColumns(10);
		txtFornavn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtFornavn.setText("");
			}
		});
		
		txtEfter = new JTextField();
		txtEfter.setText("Efternavn");
		txtEfter.setColumns(10);
		txtEfter.setBounds(22, 81, 294, 38);
		getContentPane().add(txtEfter);
		txtEfter.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtEfter.setText("");
			}
		});
		
		txtAddresse = new JTextField();
		txtAddresse.setText("Adresse");
		txtAddresse.setColumns(10);
		txtAddresse.setBounds(22, 130, 294, 38);
		getContentPane().add(txtAddresse);
		txtAddresse.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtAddresse.setText("");
			}
		});
		
		txtBy = new JTextField();
		txtBy.setText("By");
		txtBy.setColumns(10);
		txtBy.setBounds(22, 179, 169, 38);
		getContentPane().add(txtBy);
		txtBy.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBy.setText("");
			}
		});
		
		txtPostn = new JTextField();
		txtPostn.setText("Postnummer");
		txtPostn.setColumns(10);
		txtPostn.setBounds(254, 179, 62, 38);
		getContentPane().add(txtPostn);
		txtPostn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPostn.setText("");
			}
		});
		
		txtTelefonnummer = new JTextField();
		txtTelefonnummer.setText("Telefonnummer");
		txtTelefonnummer.setColumns(10);
		txtTelefonnummer.setBounds(22, 228, 169, 38);
		getContentPane().add(txtTelefonnummer);
		txtTelefonnummer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtTelefonnummer.setText("");
			}
		});
		
		txtEmail = new JTextField();
		txtEmail.setText("E-mail");
		txtEmail.setColumns(10);
		txtEmail.setBounds(22, 277, 294, 38);
		getContentPane().add(txtEmail);
		txtEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtEmail.setText("");
			}
		});
		
		txtFirmaNavn = new JTextField();
		txtFirmaNavn.setText("Firma navn");
		txtFirmaNavn.setColumns(10);
		txtFirmaNavn.setBounds(22, 351, 294, 38);
		getContentPane().add(txtFirmaNavn);
		txtFirmaNavn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtFirmaNavn.setText("");
			}
		});
		
		txtCvrNummer = new JTextField();
		txtCvrNummer.setText("CVR Nummer");
		txtCvrNummer.setColumns(10);
		txtCvrNummer.setBounds(22, 500, 201, 38);
		getContentPane().add(txtCvrNummer);
		txtCvrNummer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtCvrNummer.setText("");
			}
		});
		
		txtKontoregNummer = new JTextField();
		txtKontoregNummer.setText("Konto/Reg nummer");
		txtKontoregNummer.setColumns(10);
		txtKontoregNummer.setBounds(22, 549, 201, 38);
		getContentPane().add(txtKontoregNummer);
		txtKontoregNummer.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtKontoregNummer.setText("");
			}
		});
		
		JLabel lblFornavn = new JLabel("Fornavn");
		lblFornavn.setBounds(335, 46, 46, 14);
		getContentPane().add(lblFornavn);
		
		JLabel lblEfternavn = new JLabel("Efternavn");
		lblEfternavn.setBounds(335, 95, 63, 14);
		getContentPane().add(lblEfternavn);
		
		JLabel lblAddresse = new JLabel("Adresse");
		lblAddresse.setBounds(335, 144, 83, 14);
		getContentPane().add(lblAddresse);
		
		JLabel lblBy = new JLabel("By");
		lblBy.setBounds(198, 191, 46, 14);
		getContentPane().add(lblBy);
		
		JLabel lblPostnummer = new JLabel("Postnummer");
		lblPostnummer.setBounds(335, 193, 83, 14);
		getContentPane().add(lblPostnummer);
		
		JLabel lblTelefonnummer = new JLabel("Tlf");
		lblTelefonnummer.setBounds(201, 240, 115, 14);
		getContentPane().add(lblTelefonnummer);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBounds(335, 291, 46, 14);
		getContentPane().add(lblEmail);
		
		JLabel lblFirmaNavn = new JLabel("Firma navn");
		lblFirmaNavn.setBounds(335, 365, 83, 14);
		getContentPane().add(lblFirmaNavn);
		
		JLabel lblKundeType = new JLabel("Kunde type");
		lblKundeType.setBounds(233, 412, 83, 14);
		getContentPane().add(lblKundeType);
		
		JLabel lblCvrNummer = new JLabel("CVR Nummer");
		lblCvrNummer.setBounds(233, 512, 83, 14);
		getContentPane().add(lblCvrNummer);
		
		JLabel lblKontoOgRegistrerings = new JLabel("Konto og registrerings nummer");
		lblKontoOgRegistrerings.setBounds(233, 561, 169, 14);
		getContentPane().add(lblKontoOgRegistrerings);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuller.setBounds(22, 627, 89, 23);
		getContentPane().add(btnAnnuller);
		
		JComboBox<String> comboBox = new JComboBox();
		comboBox.setBounds(22, 402, 201, 38);
		getContentPane().add(comboBox);
		comboBox.addItem("Vælg");
		comboBox.addItem("Ervherv");
		comboBox.addItem("Privat");
		
		JComboBox<String> comboBox_1 = new JComboBox();
		comboBox_1.setBounds(22, 453, 201, 38);
		getContentPane().add(comboBox_1);
		comboBox_1.addItem("Vælg");
		categoryList = customerCtr.getAllCatgory();
		
		int categoryListIndex = 0;
		while(categoryListIndex < categoryList.size()) {
			comboBox_1.addItem(categoryList.get(categoryListIndex));
			categoryListIndex++;
		}
		
		
		JButton btnGem = new JButton("Gem");
		btnGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Customer customer = new Customer();
				try {
					GUIConfirm confirm = new GUIConfirm("Kunde er oprettet og gemt i systemet.");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						customerCtr.addCustomerToDB(customer, 0, txtFornavn.getText(), txtEfter.getText(), txtAddresse.getText(), txtBy.getText(), txtPostn.getText(), txtTelefonnummer.getText(), txtEmail.getText(), comboBox.getItemAt(comboBox.getSelectedIndex()), comboBox_1.getSelectedIndex()-1, txtKontoregNummer.getText(), txtCvrNummer.getText(), txtFirmaNavn.getText());
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnGem.setBounds(335, 627, 89, 23);
		getContentPane().add(btnGem);
		
		getContentPane();
		JLabel lblKundeKategori = new JLabel("Kunde kategori");
		lblKundeKategori.setBounds(233, 459, 104, 27);
		getContentPane().add(lblKundeKategori);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Ny kunde", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 432, 656);
		getContentPane().add(table);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
