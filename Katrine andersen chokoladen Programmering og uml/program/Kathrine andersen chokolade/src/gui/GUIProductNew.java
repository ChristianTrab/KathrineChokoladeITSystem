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

import ctr.ProductCtr;
import ctr.RecipeCtr;
import model.Ingredients;
import model.Product;
import model.Recipe;

import java.awt.List;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class GUIProductNew extends JFrame {
	private JTable table;
	private JTextField txtNavn;
	private JTextField txtPris;
	private JTextField txtStkMngde;
	private JTextField txtBoksMngde;
	private JLabel lblStkMngde;
	private JLabel lblBoksMngde;
	private JTable table_1;
	private JTextField txtSgOpskrift;
	private JTable table_2;
	private JTable table_3;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	DefaultTableModel recipeList = new DefaultTableModel();
	DefaultTableModel chosenRecipeList = new DefaultTableModel();
	private JButton btnNewButton;
	private JButton button;
	private JButton btnAnnuller;
	private JButton btnGem;
	private JButton btnSgOpskrift;
	private LinkedList<Recipe> foundRecipe;
	private LinkedList<Recipe> pickedRecipeList = new LinkedList<>();
	private RecipeCtr recipeCtr = new RecipeCtr();
	private Product product;
	private JTextPane txtDescription, txtDetails;
	private ProductCtr productCtr = new ProductCtr();
	
	public GUIProductNew() {
		product = new Product();
		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(745, 715);
		getContentPane().setLayout(null);
		
		txtNavn = new JTextField();
		txtNavn.setText("Navn");
		txtNavn.setBounds(21, 33, 251, 33);
		getContentPane().add(txtNavn);
		txtNavn.setColumns(10);
		txtNavn.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtNavn.setText("");
			}
		});
		
		txtPris = new JTextField();
		txtPris.setText("Pris");
		txtPris.setColumns(10);
		txtPris.setBounds(21, 77, 251, 33);
		getContentPane().add(txtPris);
		txtPris.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtPris.setText("");
			}
		});
		
		JLabel lblNavn = new JLabel("Navn");
		lblNavn.setBounds(282, 42, 46, 14);
		getContentPane().add(lblNavn);
		
		JLabel lblPris = new JLabel("Pris");
		lblPris.setBounds(282, 86, 46, 14);
		getContentPane().add(lblPris);
		
		txtStkMngde = new JTextField();
		txtStkMngde.setText("Stk. m\u00E6ngde");
		txtStkMngde.setColumns(10);
		txtStkMngde.setBounds(371, 33, 251, 33);
		getContentPane().add(txtStkMngde);
		txtStkMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtStkMngde.setText("");
			}
		});
		
		txtBoksMngde = new JTextField();
		txtBoksMngde.setText("Boks m\u00E6ngde");
		txtBoksMngde.setColumns(10);
		txtBoksMngde.setBounds(371, 77, 251, 33);
		getContentPane().add(txtBoksMngde);
		txtBoksMngde.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtBoksMngde.setText("");
			}
		});
		
		lblStkMngde = new JLabel("Stk. m\u00E6ngde");
		lblStkMngde.setBounds(632, 42, 86, 14);
		getContentPane().add(lblStkMngde);
		
		lblBoksMngde = new JLabel("Boks m\u00E6ngde");
		lblBoksMngde.setBounds(632, 86, 102, 14);
		getContentPane().add(lblBoksMngde);
		
		txtDetails = new JTextPane();
		txtDetails.setText("Her er der plads til at man kan skrive detaljer om produktet. Fx fortrukken tempratur, transport notater, fif og andre ting");
		txtDetails.setBounds(371, 129, 251, 153);
		getContentPane().add(txtDetails);
		txtDetails.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtDetails.setText("");
			}
		});
		
		txtDescription = new JTextPane();
		txtDescription.setText("Her er der plads plads til en beskrivelse af produktet. fx hvorn\u00E5r er opskriften fra, produktens historie osv. ");
		txtDescription.setBounds(21, 129, 251, 153);
		getContentPane().add(txtDescription);
		txtDescription.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtDescription.setText("");
			}
		});
		
		JLabel lblBeskrivelse = new JLabel("Beskrivelse");
		lblBeskrivelse.setBounds(282, 129, 79, 14);
		getContentPane().add(lblBeskrivelse);
		
		JLabel lblDetaljer = new JLabel("Detaljer");
		lblDetaljer.setBounds(632, 129, 46, 14);
		getContentPane().add(lblDetaljer);
		
		table = new JTable();
		table.setBorder(new TitledBorder(null, "Produkt detaljer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 707, 293);
		getContentPane().add(table);
		
		txtSgOpskrift = new JTextField();
		txtSgOpskrift.setText("S\u00F8g opskrift");
		txtSgOpskrift.setBounds(28, 333, 154, 26);
		getContentPane().add(txtSgOpskrift);
		txtSgOpskrift.setColumns(10);
		txtSgOpskrift.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgOpskrift.setText("");
			}
		});
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(38, 370, 270, 227);
		getContentPane().add(scrollPane);
		
		table_2 = new JTable(recipeList);
		table_2.setDefaultEditor(Object.class, null);					// Makes the cell non-editable
		
		recipeList.addColumn("ID");
		recipeList.addColumn("Navn");
		
		scrollPane.setViewportView(table_2);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(429, 370, 270, 227);
		getContentPane().add(scrollPane_1);
		
		table_3 = new JTable(chosenRecipeList);
		table_3.setDefaultEditor(Object.class, null); 					// Makes the cell non-editable
		
		
		chosenRecipeList.addColumn("ID");
		chosenRecipeList.addColumn("Navn");
		
		scrollPane_1.setViewportView(table_3);
		
		btnNewButton = new JButton("Tilf\u00F8j >");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_2.getSelectedRow();
				Recipe recipe = foundRecipe.get(selectedRow);
				pickedRecipeList.add(recipe);
				chosenRecipeList.addRow(new Object [] {recipe.getId(), recipe.getName()});
			}
		});
		btnNewButton.setBounds(318, 370, 101, 26);
		getContentPane().add(btnNewButton);
		
		button = new JButton("< Fjern");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table_3.getSelectedRow();
				pickedRecipeList.remove(selectedRow);
				chosenRecipeList.removeRow(selectedRow);
			}
		});
		button.setBounds(318, 571, 101, 26);
		getContentPane().add(button);
		
		btnAnnuller = new JButton("Annuller");
		btnAnnuller.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAnnuller.setBounds(10, 639, 101, 26);
		getContentPane().add(btnAnnuller);
		
		btnGem = new JButton("Gem");
		btnGem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				product.setName(txtNavn.getText());
				product.setPrice(Double.parseDouble(txtPris.getText()));
				product.setTotalQty(Integer.parseInt(txtStkMngde.getText()));
				product.setBoxQuantity(Integer.parseInt(txtBoksMngde.getText()));
				product.setDescription(txtDescription.getText());
				product.setDetails(txtDetails.getText());
				product.setRecipeId(foundRecipe.get(table_2.getSelectedRow()).getId());
				System.out.println(foundRecipe.get(table_2.getSelectedRow()).getId());
//				product.setRecipeId(pickedRecipeList.get(0).getId());
				try {
					GUIConfirm confirm = new GUIConfirm("Produktet er oprettet og gemt i systemet.");
					confirm.setModal(true);
					confirm.setVisible(true);
					if (confirm.getConfirmResult() == 1) {
						productCtr.addProductToDB(product);
						dispose();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnGem.setBounds(617, 639, 101, 26);
		getContentPane().add(btnGem);
		
		btnSgOpskrift = new JButton("S\u00F8g opskrift");
		btnSgOpskrift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refreshProductRecipeTable();
					searchRecipe();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSgOpskrift.setBounds(192, 335, 102, 23);
		getContentPane().add(btnSgOpskrift);
		
		table_1 = new JTable();
		table_1.setBorder(new TitledBorder(null, "Tilf\u00F8j en opskrift", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_1.setBackground(UIManager.getColor("Button.background"));
		table_1.setBounds(10, 315, 708, 299);
		getContentPane().add(table_1);
		getContentPane();
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public void searchRecipe() throws SQLException {		// Search Recipe Call
		foundRecipe = recipeCtr.findRecipeByName(txtSgOpskrift.getText());
		
		int recipeListIndex = 0;
		while (recipeListIndex < foundRecipe.size()) {
			Recipe r = foundRecipe.get(recipeListIndex);
			recipeList.addRow(new Object[] {r.getId(), r.getName()});
			recipeListIndex++;
		}
	}
	
	public void refreshProductRecipeTable() {
		for (int x = recipeList.getRowCount()-1; x>= 0; x--) {
			recipeList.removeRow(x);
			System.out.println("Row Remowed");
		}
			
	}
}
