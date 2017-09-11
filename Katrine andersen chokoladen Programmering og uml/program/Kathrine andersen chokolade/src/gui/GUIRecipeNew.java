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

import ctr.IngredientsCtr;
import ctr.RecipeCtr;
import model.Ingredients;
import model.Recipe;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIRecipeNew extends JFrame{
	private JTable table;
	private JTextField txtNavn;
	private JTable table_1;
	
	DefaultTableModel ingListLeft = new DefaultTableModel();
	DefaultTableModel ingListRight = new DefaultTableModel();
	private JTable table_2;
	private JTextField txtSgIngredients;
	
	private IngredientsCtr ingredientsCtr = new IngredientsCtr();;
	private LinkedList<Ingredients> ingList;
	private LinkedList<Ingredients> pickedIngList = new LinkedList<>();
	private Recipe recipe;
	private RecipeCtr recipeCtr = new RecipeCtr();
	
	public GUIRecipeNew() {
		recipe = new Recipe();
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(600, 600);
		getContentPane().setLayout(null);
		
		txtNavn = new JTextField();
		txtNavn.setText("Navn");
		txtNavn.setBounds(21, 32, 140, 30);
		txtNavn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtNavn.setText("");
			}
		});
		getContentPane().add(txtNavn);
		txtNavn.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Navn");
		lblNewLabel.setBounds(171, 40, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblBeskrivelse = new JLabel("Beskrivelse");
		lblBeskrivelse.setBounds(457, 73, 77, 14);
		getContentPane().add(lblBeskrivelse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 276, 217, 215);
		getContentPane().add(scrollPane);
		
		table_1 = new JTable(ingListLeft);
		
		ingListLeft.addColumn("ID");
		ingListLeft.addColumn("Navn");
		
		scrollPane.setViewportView(table_1);
		
		JButton btnNewButton = new JButton("Tilf\u00F8j >");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_1.getSelectedRow();
				Ingredients ingredient = ingList.get(selectedRow);
				pickedIngList.add(ingredient);
				ingListRight.addRow(new Object [] {ingredient.getId(), ingredient.getName()});
			}
		});
		btnNewButton.setBounds(246, 275, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("< Fjern");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				pickedIngList.remove(selectedRow);
				ingListRight.removeRow(selectedRow);
			}
		});
		btnNewButton_1.setBounds(246, 467, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Ingrediens Liste");
		lblNewLabel_1.setBounds(22, 256, 77, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNyOpskrift = new JLabel("Ny opskrift");
		lblNyOpskrift.setBounds(345, 256, 65, 14);
		getContentPane().add(lblNyOpskrift);
		
		JButton button = new JButton("Annuller");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(21, 515, 89, 23);
		getContentPane().add(button);
		
		JTextPane txtpnBeskrivelseAfAlt = new JTextPane();
		txtpnBeskrivelseAfAlt.setText("Beskrivelse af opskrift");
		txtpnBeskrivelseAfAlt.setBounds(21, 73, 426, 129);
		txtpnBeskrivelseAfAlt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtpnBeskrivelseAfAlt.setText("");
			}
		});
		getContentPane().add(txtpnBeskrivelseAfAlt);
		
		JButton button_1 = new JButton("Gem");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				recipe.setName(txtNavn.getText());
				recipe.setDescription(txtpnBeskrivelseAfAlt.getText());
				try {
					GUIConfirm confirm = new GUIConfirm("Opskriften er oprettet og gemt i systemet.");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						recipeCtr.addRecipeToDB(recipe, pickedIngList);
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setBounds(471, 515, 89, 23);
		getContentPane().add(button_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(345, 276, 217, 215);
		getContentPane().add(scrollPane_1);
		
		table_2 = new JTable(ingListRight);
		
		ingListRight.addColumn("ID");
		ingListRight.addColumn("Navn");
		
		scrollPane_1.setViewportView(table_2);
		getContentPane();
		
		txtSgIngredients = new JTextField();
		txtSgIngredients.setText("S\u00F8g ingredient");
		txtSgIngredients.setBounds(21, 213, 137, 32);
		txtSgIngredients.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgIngredients.setText("");
			}
		});
		
		getContentPane().add(txtSgIngredients);
		txtSgIngredients.setColumns(10);
		
		JButton btnSgIngredients = new JButton("S\u00F8g Ingredient");
		btnSgIngredients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ingList = ingredientsCtr.findIngredientsByName(txtSgIngredients.getText());
					
					ingListLeft.setRowCount(0);
					
					int ingredientsListIndex = 0;
					while(ingredientsListIndex < ingList.size()){
						Ingredients r = ingList.get(ingredientsListIndex);
						ingListLeft.addRow(new Object[]{r.getId(), r.getName()});
						ingredientsListIndex++;
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnSgIngredients.setBounds(171, 218, 140, 23);
		getContentPane().add(btnSgIngredients);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Opskrift", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 564, 542);
		getContentPane().add(table);
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
