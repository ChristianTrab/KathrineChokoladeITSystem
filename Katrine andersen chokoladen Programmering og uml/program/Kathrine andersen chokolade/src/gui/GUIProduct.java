package gui;

import javax.swing.*;

import ctr.CustomerCtr;
import ctr.IngredientsCtr;
import ctr.OrderCtr;
import ctr.ProductCtr;
import ctr.RecipeCtr;
import model.Customer;
import model.Ingredients;
import model.Order;
import model.Product;
import model.Recipe;
import model.SaleLineItem;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Font;
import java.awt.ScrollPane;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import java.awt.List;
import java.awt.Color;
import java.awt.Component;

import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import ctr.ProductCtr;
import java.awt.Label;
import java.awt.Button;

public class GUIProduct extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtSgProdukt;
	DefaultTableModel proList = new DefaultTableModel();
	DefaultTableModel RecList = new DefaultTableModel();
	DefaultTableModel IngrList = new DefaultTableModel();
	DefaultTableModel EmbaList = new DefaultTableModel();
	private JTable table_1;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTextField txtSgOpskrift;
	private JTable table_5;
	private JTextField txtSgIngrediens;
	private JTable table_6;
	private JTextField txtSgEmballage;
	private JTable table_7;
	private ProductCtr productCtr = new ProductCtr();
	private RecipeCtr recipeCtr = new RecipeCtr();
	private IngredientsCtr ingredientsCtr = new IngredientsCtr();
	LinkedList<Product> productList = new LinkedList<>();
	LinkedList<Recipe> recipeList = new LinkedList<>();
	LinkedList<Ingredients> ingredientsList = new LinkedList<>();
	private int selectedProduct;
	private JTextArea txtDescription, txtDetails;

	public GUIProduct() {

		setTitle("Kathrine Andersen Chokolade ApS");
		setSize(1920, 1045);

		getContentPane().setLayout(null);

		txtSgProdukt = new JTextField();
		txtSgProdukt.setText("S\u00F8g produkt");
		txtSgProdukt.setBounds(20, 28, 147, 31);
		txtSgProdukt.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgProdukt.setText("");
			}
		});

		getContentPane().add(txtSgProdukt);
		txtSgProdukt.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 70, 1324, 376);
		getContentPane().add(scrollPane);

		table_1 = new JTable(proList);
		table_1.setDefaultEditor(Object.class, null); // Makes the cell
														// non-editable

		proList.addColumn("ID");
		proList.addColumn("Navn");
		proList.addColumn("Pris");
		proList.addColumn("Stk. lagerantal");
		proList.addColumn("Boks lagerantal");

		table_1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(500);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(70);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(70);
		
		scrollPane.setViewportView(table_1);

		txtDescription = new JTextArea();
		txtDescription.setBounds(1356, 98, 314, 175);
		getContentPane().add(txtDescription);

		txtDetails = new JTextArea();
		txtDetails.setBounds(1356, 312, 314, 134);
		getContentPane().add(txtDetails);

		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					showProductDescriptionAndDetails();
				}
			}
		});

		Label label = new Label("Beskrivelse:");
		label.setBounds(1350, 70, 62, 22);
		getContentPane().add(label);

		Label label_1 = new Label("Detaljer:");
		label_1.setBounds(1350, 290, 62, 22);
		getContentPane().add(label_1);

		JButton btnNytProdukt = new JButton("Nyt Produkt");
		btnNytProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIProductNew productGui = new GUIProductNew();
			}
		});
		btnNytProdukt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNytProdukt.setBounds(1694, 70, 181, 37);
		getContentPane().add(btnNytProdukt);

		JButton btnRedigereProdukt = new JButton("Redigere Produkt");
		btnRedigereProdukt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereProdukt.setBounds(1694, 118, 181, 37);
		getContentPane().add(btnRedigereProdukt);

		JButton btnSletProdukt = new JButton("Slet Produkt");
		btnSletProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette det markerede produkt?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						deleteProduct();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSletProdukt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletProdukt.setBackground(Color.RED);
		btnSletProdukt.setBounds(1694, 409, 181, 37);
		getContentPane().add(btnSletProdukt);

		txtSgOpskrift = new JTextField();
		txtSgOpskrift.setText("S\u00F8g opskrift");
		txtSgOpskrift.setColumns(10);
		txtSgOpskrift.setBounds(20, 483, 147, 31);
		txtSgOpskrift.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgOpskrift.setText("");
			}
		});
		getContentPane().add(txtSgOpskrift);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 525, 575, 186);
		getContentPane().add(scrollPane_1);

		table_5 = new JTable(RecList);
		table_5.setDefaultEditor(Object.class, null); // Makes the cell not editable

		RecList.addColumn("ID");
		RecList.addColumn("Navn");

		scrollPane_1.setViewportView(table_5);

		JButton btnNyOpskrift = new JButton("Ny Opskrift");
		btnNyOpskrift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GUIRecipeNew recipeNew = new GUIRecipeNew();
			}
		});
		btnNyOpskrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNyOpskrift.setBounds(607, 526, 181, 37);
		getContentPane().add(btnNyOpskrift);

		JButton btnRedigereOpskrift = new JButton("Redigere Opskrift");
		btnRedigereOpskrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereOpskrift.setBounds(607, 570, 181, 37);
		getContentPane().add(btnRedigereOpskrift);

		JButton btnSletOpskrift = new JButton("Slet Opskrift");
		btnSletOpskrift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette den markerede opskrift?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						deleteRecipe();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSletOpskrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletOpskrift.setBounds(607, 673, 181, 37);
		btnSletOpskrift.setBackground(Color.RED);
		getContentPane().add(btnSletOpskrift);

		txtSgIngrediens = new JTextField();
		txtSgIngrediens.setText("S\u00F8g ingrediens");
		txtSgIngrediens.setColumns(10);
		txtSgIngrediens.setBounds(20, 753, 147, 31);
		txtSgIngrediens.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgIngrediens.setText("");
			}
		});
		getContentPane().add(txtSgIngrediens);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(20, 795, 582, 163);
		getContentPane().add(scrollPane_2);

		table_6 = new JTable(IngrList);
		table_6.setDefaultEditor(Object.class, null); // Makes the cell
														// non-editable

		IngrList.addColumn("ID");
		IngrList.addColumn("Navn");
		IngrList.addColumn("Price");
		IngrList.addColumn("Kg. på lager");
		IngrList.addColumn("Lager status");

		scrollPane_2.setViewportView(table_6);

		JButton btnNyIngrediens = new JButton("Ny Ingrediens");
		btnNyIngrediens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIIngrediensNew ingredientsGui = new GUIIngrediensNew();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNyIngrediens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNyIngrediens.setBounds(607, 795, 181, 37);
		getContentPane().add(btnNyIngrediens);

		JButton btnRedigereIngrediens = new JButton("Redigere Ingrediens");
		btnRedigereIngrediens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereIngrediens.setBounds(607, 843, 181, 37);
		getContentPane().add(btnRedigereIngrediens);

		JButton btnSletIngrediens = new JButton("Slet Ingrediens");
		btnSletIngrediens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					GUIWarning warning = new GUIWarning("Sikker på du vil slette den markerede ingrediens?");
					warning.setModal(true);
					warning.setVisible(true);
					if (warning.getWarningResult() == 1) {
						warning.dispose();
						deleteIngredient();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSletIngrediens.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletIngrediens.setBounds(607, 921, 181, 37);
		btnSletIngrediens.setBackground(Color.RED);
		getContentPane().add(btnSletIngrediens);

		txtSgEmballage = new JTextField();
		txtSgEmballage.setText("S\u00F8g Emballage");
		txtSgEmballage.setColumns(10);
		txtSgEmballage.setBounds(820, 483, 147, 31);
		txtSgEmballage.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				txtSgEmballage.setText("");
			}
		});
		getContentPane().add(txtSgEmballage);

		JButton btnSletEmballage = new JButton("Slet Emballage");
		btnSletEmballage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUIWarning warning = new GUIWarning("Sikker på du vil slette den markerede emballage?");
				warning.setModal(true);
				warning.setVisible(true);
				if (warning.getWarningResult() == 1) {
					warning.dispose();
				}
			}
		});
		btnSletEmballage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSletEmballage.setBackground(Color.RED);
		btnSletEmballage.setBounds(1694, 921, 181, 37);
		getContentPane().add(btnSletEmballage);

		JButton btnNyEmballage = new JButton("Ny Emballage");
		btnNyEmballage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNyEmballage.setBounds(1694, 522, 181, 37);
		getContentPane().add(btnNyEmballage);

		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(819, 526, 864, 433);
		getContentPane().add(scrollPane_3);

		table_7 = new JTable(EmbaList);

		EmbaList.addColumn("ID");
		EmbaList.addColumn("Navn");
		EmbaList.addColumn("Type");
		EmbaList.addColumn("Størrelse");
		EmbaList.addColumn("Stk. på lager");

		scrollPane_3.setViewportView(table_7);

		JButton btnRedigereEmballage = new JButton("Redigere Emballage");
		btnRedigereEmballage.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRedigereEmballage.setBounds(1694, 570, 181, 37);
		getContentPane().add(btnRedigereEmballage);

		JButton btnSgProdukt = new JButton("S\u00F8g produkt");
		btnSgProdukt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					searchProduct();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnSgProdukt.setBounds(177, 28, 112, 31);
		getContentPane().add(btnSgProdukt);

		JButton btnSgOpskrift = new JButton("S\u00F8g opskrift");
		btnSgOpskrift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refreshRecipeTable();
					searchRecipe();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSgOpskrift.setBounds(177, 483, 112, 31);
		getContentPane().add(btnSgOpskrift);

		table_2 = new JTable();
		table_2.setBackground(UIManager.getColor("Button.background"));
		table_2.setBorder(new TitledBorder(null, "Opskrifter", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_2.setBounds(10, 465, 790, 256);
		getContentPane().add(table_2);

		JButton btnSgIngrediens = new JButton("S\u00F8g ingrediens");
		btnSgIngrediens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					refreshIngredientsTable();
					searchIngredients();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSgIngrediens.setBounds(177, 753, 128, 31);
		getContentPane().add(btnSgIngrediens);

		table_3 = new JTable();
		table_3.setBorder(new TitledBorder(null, "Ingredienser", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_3.setBackground(UIManager.getColor("Button.background"));
		table_3.setBounds(10, 733, 790, 237);
		getContentPane().add(table_3);

		JButton btnSgEmballage = new JButton("S\u00F8g emballage");
		btnSgEmballage.setBounds(977, 483, 119, 31);
		getContentPane().add(btnSgEmballage);

		table_4 = new JTable();
		table_4.setBorder(
				new TitledBorder(null, "Emballage Lager", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table_4.setBackground(UIManager.getColor("Button.background"));
		table_4.setBounds(811, 465, 1083, 505);
		getContentPane().add(table_4);

		JButton btnSeOpskrift = new JButton("Se Opskrift");
		btnSeOpskrift.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GUIPrintIngredients ingredientsGui = new GUIPrintIngredients(
							productList.get(table_1.getSelectedRow()));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnSeOpskrift.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSeOpskrift.setBounds(1694, 169, 181, 37);
		getContentPane().add(btnSeOpskrift);

		JButton btnGenbestil = new JButton("Genbestillingsliste");
		btnGenbestil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					GUIReorder guiReorder = new GUIReorder();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnGenbestil.setBounds(422, 28, 147, 31);
		getContentPane().add(btnGenbestil);

		table = new JTable();
		table.setBorder(new TitledBorder(null, "Produkt Liste", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		table.setBackground(UIManager.getColor("Button.background"));
		table.setBounds(10, 11, 1884, 449);
		getContentPane().add(table);
		
		// ProductListeScrollPane.getv
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	
	
	public void searchIngredients() throws SQLException {
		ingredientsList = ingredientsCtr.findIngredientsByName(txtSgIngrediens.getText());

		int ingredientsListIndex = 0;
		while (ingredientsListIndex < ingredientsList.size()) {
			Ingredients i = ingredientsList.get(ingredientsListIndex);

			// Color row according to stock quantity - Neutral = okay stock,
			// Yellow = min stock, Red = not in stock
			int quantity = i.getQuantity();
			int minQty = i.getMinQuantity();
			double procentOverMinQty = minQty * 1.25;

			String status = "";
			if(quantity <= procentOverMinQty && quantity != 0) {
				status = "Lavt";
			} else if (quantity == 0) {
				status = "Genbestil";
			} else {
				status = "På lager";
			}
			IngrList.addRow(new Object[] { i.getId(), i.getName(), i.getPrice(), i.getQuantity(), status});
			ingredientsListIndex++;
		}
	}
	
	


	public void searchRecipe() throws SQLException {

		recipeList = recipeCtr.findRecipeByName(txtSgOpskrift.getText());

		int recipeListIndex = 0;
		while (recipeListIndex < recipeList.size()) {
			Recipe r = recipeList.get(recipeListIndex);
			RecList.addRow(new Object[] { r.getId(), r.getName() });
			recipeListIndex++;
		}
	}

	public void searchProduct() throws SQLException {
		refreshProductTable();
		productList = productCtr.findProductByName(txtSgProdukt.getText());

		int productListIndex = 0;
		while (productListIndex < productList.size()) {
			Product p = productList.get(productListIndex);

			// Color row according to stock of product - Neutral for okay stock,
			// yellow for mid and red for low

			proList.addRow(new Object[] { p.getId(), p.getName(), p.getPrice(), p.getTotalQty(), p.getBoxQuantity() });
			productListIndex++;
		}
	}

	public void deleteIngredient() throws SQLException {
		int selectedIngredientsRow = table_6.getSelectedRow();
		Ingredients IngList = ingredientsList.get(selectedIngredientsRow);
		ingredientsCtr.deleteIngredients(IngList.getId());
		IngrList.removeRow(selectedIngredientsRow);

	}

	public void deleteRecipe() throws SQLException {
		int selectedRecipeRow = table_5.getSelectedRow();
		Recipe ReciList = recipeList.get(selectedRecipeRow);
		recipeCtr.deleteRecipe(ReciList.getId());
		RecList.removeRow(selectedRecipeRow);

	}

	public void deleteProduct() throws SQLException {
		int selectedProductRow = table_1.getSelectedRow();
		Product prList = productList.get(selectedProductRow);
		productCtr.deleteProduct(prList.getId());
		proList.removeRow(selectedProductRow);
	}

	public void showProductDescriptionAndDetails() {
		selectedProduct = table_1.getSelectedRow();

		if (selectedProduct == -1) {

		} else {

			txtDescription.setText(productList.get(selectedProduct).getDescription());
			txtDetails.setText(productList.get(selectedProduct).getDetails());
		}
	}

	public void refreshRecipeTable() {
		for (int x = RecList.getRowCount() - 1; x >= 0; x--) {
			RecList.removeRow(x);
			System.out.println("Row Remowed");
		}

	}

	public void refreshIngredientsTable() {
		for (int x = IngrList.getRowCount() - 1; x >= 0; x--) {
			IngrList.removeRow(x);
			System.out.println("Row Remowed");
		}

	}

	public void refreshProductTable() {
		for (int x = proList.getRowCount() - 1; x >= 0; x--) {
			proList.removeRow(x);
			System.out.println("Row removed");
		}

		while (!productList.isEmpty()) {
			productList.removeFirst();
			System.out.println("remove order from list");
		}
	}
}
