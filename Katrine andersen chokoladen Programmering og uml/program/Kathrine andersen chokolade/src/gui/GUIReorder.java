package gui;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;

import ctr.IngredientsCtr;
import model.Ingredients;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

public class GUIReorder extends JFrame {
	private IngredientsCtr iCtr = new IngredientsCtr();
	
	public GUIReorder() throws SQLException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Genbestilling");
		setSize(1061, 300);
		getContentPane().setLayout(null);
		
		JTextArea txtReorder = new JTextArea();
		txtReorder.setText("");
		txtReorder.setBounds(0, 0, 882, 253);
		ArrayList<Ingredients> ingReorderList = iCtr.getReorderList();
		for(Ingredients i : ingReorderList){
			txtReorder.append("ID: " + i.getId() + " | Navn: " + i.getName() + " | Pris: " + i.getPrice() + " | Antal: " + i.getQuantity() + " | Genbestillings antal: " + i.getReorderQuantity() + " |" + "\n");
		}
		
		getContentPane().add(txtReorder);
		
		JButton btnReorder = new JButton("Send genbestilling");
		btnReorder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIEnterEmail GUIEnterEmailWindow = new GUIEnterEmail();
			}
		});
		btnReorder.setBounds(894, 13, 137, 25);
		getContentPane().add(btnReorder);
		
		JButton btnCloseWindow = new JButton("Luk");
		btnCloseWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCloseWindow.setBounds(894, 215, 137, 25);
		getContentPane().add(btnCloseWindow);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
}
