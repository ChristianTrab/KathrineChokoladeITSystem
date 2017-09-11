package gui;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;

import ctr.IngredientsCtr;
import model.Customer;
import model.Ingredients;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIIngrediensNew extends JFrame{
	private JTable table;
	private JTextField txtMngde;
	private JTextField txtPris;
	private JTextField textField_2;
	private JTextField txtMinimumMngde;
	private JTextField txtrsforbrug;
	private JTextField txtBestillingsomkostninger;
	public GUIIngrediensNew() throws SQLException {
		IngredientsCtr ingredientsCtr = new IngredientsCtr();
		Ingredients ingredients = new Ingredients();
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(330, 429);
		getContentPane().setLayout(null);
		
		txtMngde = new JTextField();
		txtMngde.setText("M\u00E6ngde");
		txtMngde.setColumns(10);
		txtMngde.setBounds(21, 214, 138, 32);
		txtMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMngde.setText("");
			}
		});
		getContentPane().add(txtMngde);
		
		txtPris = new JTextField();
		txtPris.setText("Indk\u00F8bs pris");
		txtPris.setColumns(10);
		txtPris.setBounds(21, 79, 138, 32);
		txtPris.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPris.setText("");
			}
		});
		getContentPane().add(txtPris);
		
		textField_2 = new JTextField();
		textField_2.setText("Navn");
		textField_2.setColumns(10);
		textField_2.setBounds(21, 36, 138, 32);
		textField_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				textField_2.setText("");
			}
		});
		getContentPane().add(textField_2);
		
		JLabel label = new JLabel("Navn");
		label.setBounds(169, 45, 46, 14);
		getContentPane().add(label);
		
		JLabel lblPris = new JLabel("Indk\u00F8bs pris");
		lblPris.setBounds(169, 88, 89, 14);
		getContentPane().add(lblPris);
		
		JLabel lblMngde = new JLabel("M\u00E6ngde");
		lblMngde.setBounds(171, 223, 60, 14);
		getContentPane().add(lblMngde);
		
		txtMinimumMngde = new JTextField();
		txtMinimumMngde.setText("Minimum m\u00E6ngde");
		txtMinimumMngde.setColumns(10);
		txtMinimumMngde.setBounds(21, 259, 138, 32);
		getContentPane().add(txtMinimumMngde);
		txtMinimumMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtMinimumMngde.setText("");
			}
		});
		
		JLabel lblMinMngde = new JLabel("Min. m\u00E6ngde");
		lblMinMngde.setBounds(169, 268, 89, 14);
		getContentPane().add(lblMinMngde);
		
		JCheckBox reorderCheckbox = new JCheckBox("Automatisk genbestilling?");
		reorderCheckbox.setBounds(21, 300, 237, 25);
		getContentPane().add(reorderCheckbox);
		
		txtrsforbrug = new JTextField();
		txtrsforbrug.setText("M\u00E5nedsforbrug");
		txtrsforbrug.setColumns(10);
		txtrsforbrug.setBounds(21, 124, 138, 32);
		getContentPane().add(txtrsforbrug);
		txtrsforbrug.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtrsforbrug.setText("");
			}
		});
		
		JLabel lblMnedsforbrug = new JLabel("M\u00E5nedsforbrug");
		lblMnedsforbrug.setBounds(169, 132, 89, 14);
		getContentPane().add(lblMnedsforbrug);
		
		txtBestillingsomkostninger = new JTextField();
		txtBestillingsomkostninger.setText("Best/modt omkostn.");
		txtBestillingsomkostninger.setColumns(10);
		txtBestillingsomkostninger.setBounds(21, 169, 138, 32);
		getContentPane().add(txtBestillingsomkostninger);
		txtBestillingsomkostninger.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBestillingsomkostninger.setText("");
			}
		});
		
		JLabel lblBestmodtOmkostn = new JLabel("Best/modt omkostn.");
		lblBestmodtOmkostn.setBounds(169, 177, 116, 14);
		getContentPane().add(lblBestmodtOmkostn);
		
		JButton button = new JButton("Annuller");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(21, 334, 89, 23);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Gem");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingredients.setName(textField_2.getText());
				ingredients.setPrice(Double.parseDouble(txtPris.getText()));
				ingredients.setMonthlyConsumption(Integer.parseInt(txtrsforbrug.getText()));
				ingredients.setShippingCost(Integer.parseInt(txtBestillingsomkostninger.getText()));
				ingredients.setQuantity(Integer.parseInt(txtMngde.getText()));
				ingredients.setMinQuantity(Integer.parseInt(txtMinimumMngde.getText()));
				ingredients.setReorder(reorderCheckbox.isSelected());
				try {
					GUIConfirm confirm = new GUIConfirm("Ingrediens er oprettet og gemt i systemet.");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						ingredientsCtr.reorderIngredients(ingredients);
						ingredientsCtr.addIngredientsDB(ingredients);
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(196, 334, 89, 23);
		getContentPane().add(button_1);
		getContentPane();
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Ingrediens", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 290, 358);
		getContentPane().add(table);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
