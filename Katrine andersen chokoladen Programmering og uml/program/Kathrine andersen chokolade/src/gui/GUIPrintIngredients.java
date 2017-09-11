package gui;

import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import ctr.RecipeCtr;
import ctr.IngredientsCtr;
import model.Ingredients;
import model.Order;
import model.Product;
import model.Recipe;
import model.RecipeLineItem;
import model.SaleLineItem;

public class GUIPrintIngredients extends JFrame {
		private JTextArea textArea;
		private RecipeCtr recipeCtr = new RecipeCtr();
	
	public GUIPrintIngredients(Product product) throws SQLException {
		setTitle("Ingredienser og Opskrift: " + product.getName());
		setSize(549, 463);
		getContentPane().setLayout(null);
	
		textArea = new JTextArea();
		textArea.setBounds(0, 0, 531, 416);
		getContentPane().add(textArea);
		textArea.setEditable(false);
		
		Recipe recipe = recipeCtr.findRecipeById(product.getRecipeId());
	
		//Printing out the recipe
		textArea.append("Opskrift: " + recipe.getName() + "\n");
		textArea.append(recipe.getDescription() + "\n" + "\n");
		
		//Get ingredients for recipe
		LinkedList<RecipeLineItem> list = recipeCtr.getAllIngredientInRecipe(product.getRecipeId());
		
		for(RecipeLineItem rli : list) {
			textArea.append("Ingrediens: " + rli.getIngredient().getName() + " antal: " + rli.getIngredient().getQuantity() + "\n");
		}
		
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
