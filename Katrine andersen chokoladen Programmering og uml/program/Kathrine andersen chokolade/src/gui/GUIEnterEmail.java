package gui;

import javax.swing.JFrame;

import ctr.IngredientsCtr;
import model.SaleLineItem;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class GUIEnterEmail extends JFrame {
	private IngredientsCtr iCtr = new IngredientsCtr();
	private JTextField txtIndtastEmail;
	private GUIReorder GUIreorder;
	
	public GUIEnterEmail() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle ("Indtast Email");
		setSize(346, 199);
		getContentPane().setLayout(null);
		
		txtIndtastEmail = new JTextField();
		txtIndtastEmail.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtIndtastEmail.setText("");
			}
		});
		txtIndtastEmail.setText("Indtast Email");
		txtIndtastEmail.setBounds(14, 58, 292, 25);
		getContentPane().add(txtIndtastEmail);
		txtIndtastEmail.setColumns(10);
		
		JLabel lblIndtastEmailDu = new JLabel("Indtast email du vil have sendt genbestillings liste til");
		lblIndtastEmailDu.setBounds(14, 25, 308, 20);
		getContentPane().add(lblIndtastEmailDu);
		
		JButton btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuller.setBounds(14, 96, 127, 37);
		btnAnnuller.setBackground(Color.RED);
		getContentPane().add(btnAnnuller);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIConfirm confirm = new GUIConfirm("Emailen er blevet sendt!");
				confirm.setModal(true);
				confirm.setVisible(true);
				if (confirm.getConfirmResult() == 1) {
					try {
						iCtr.sendMail(txtIndtastEmail.getText(), iCtr.getReorderList());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
		});
		btnSend.setBounds(179, 96, 127, 37);
		setLocationRelativeTo(null);
		getContentPane().add(btnSend);
		setVisible(true);
	}
}

