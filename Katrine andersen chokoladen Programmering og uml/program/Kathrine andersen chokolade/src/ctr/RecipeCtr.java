package ctr;

import model.Ingredients;

import model.RecipeLineItem;
import db.DBRecipe;
import db.DBRecipeLineItem;
import model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import db.DBConnection;
import db.DBProduct;
/*
 * @author 
 * @version (06-06-2016)
 */

/*
 *  Creates an object of DBRecipe, IngredientsCtr and DBRecipeLineItem.
 */
public class RecipeCtr {
	private DBRecipe dbRecipe = new DBRecipe();
	private IngredientsCtr ingredientsCtr = new IngredientsCtr();
	private DBRecipeLineItem dbRecipeLineItem = new DBRecipeLineItem();
	
	/*
	 * The Update recipe Method, this method will update the current recipe with new values.
	 * 
	 * @param	recipe		Recipe		- Recipe Object from model.Recipe
	 * @param	id			int			- Id of the recipe
	 * @param	name		String		- Name of the recipe
	 * @param	description	String		- Description of the recipe
	 * @return	- Returns a updated recipe object
	 */
	public Recipe updateRecipeObject (Recipe recipe, int id, String name, String description) {
		Recipe r = recipe;
		r.setId(id);
		r.setName(name);
		r.setDescription(description);
		return r;
	}
	
	/*
	 * The Update recipe in database Method, this method will update the current recipe with new values.
	 * 
	 * @param	recipe		Recipe 		- Recipe Object from model.Recipe
	 * @param	id			int			- Id of the recipe
	 * @param	name		String		- Name of the recipe
	 * @param	description	String		- Description of the recipe
	 * 
	 */
	public void updateRecipeDB(Recipe recipe, int id, String name, String description) throws SQLException {
		Recipe r = updateRecipeObject(recipe, id, name, description);
		dbRecipe.updateRecipe(r);
	}
	
	/*
	 * The add recipe to database Method, this method will add a recipe object to the database
	 * 
	 * @param	recipe	Recipe - Recipe Object from model.Ingredients
	 * 
	 */
	public void addRecipeToDB(Recipe recipe, LinkedList<Ingredients> pickedIngList) throws SQLException {
		Recipe r = updateRecipeObject(recipe, recipe.getId(), recipe.getName(), recipe.getDescription());
		dbRecipe.addRecipe(r);
		for(Ingredients ing : pickedIngList) {
			ingredientsCtr.addIngredientsDB(ing);
		}
		
	}
	
	/*
	 * This is the find recipe by id method
	 * @param	id			int			- Which is calling findById method in db.DBRecipe and returning an object of recipe.
	 * @return	- Returns the recipe found by id
	 */
	public Recipe findRecipeById(int id) throws SQLException {
		return dbRecipe.findById(id);
	}
	
	/*
	 * This is the find recipe by name method
	 * @param	name		String			- Which is calling findById method in db.DBRecipe and returning an object of Recipe.
	 * @return	- Returns a linkedlist of the recipes found by name
	 */
	public LinkedList<Recipe> findRecipeByName(String name) throws SQLException {
		LinkedList<Recipe> rList = dbRecipe.findByName(name);
		rList.parallelStream()
		.sorted();
		return rList;	
	}
	
	/*
	 * This is the delete recipe from database method
	 * @param	id			int			- Which is calling findById method in db.DBRecipe and returning an object of Recipe and then removes it from the database.
	 * 
	 */
	public void deleteRecipe(int id) throws SQLException {
		Recipe r = findRecipeById(id);
		dbRecipe.deleteRecipe(r);
	}
	
	/*
	 * This is the get all ingrediens in recipe Method
	 * @param	recipeId		int		- Id of the recipe
	 * @return	- Returns a linkedlist of all ingrediens in a recipe
	 */
	public LinkedList<RecipeLineItem> getAllIngredientInRecipe(int recipeId) throws SQLException {
		return dbRecipeLineItem.getAlleRecipeLineItemFromRecipe(recipeId);
	}

	
}

