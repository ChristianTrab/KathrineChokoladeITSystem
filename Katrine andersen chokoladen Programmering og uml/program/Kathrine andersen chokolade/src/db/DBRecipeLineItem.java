package db;

import model.Ingredients;
import model.RecipeLineItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.DBConnection;

public class DBRecipeLineItem {
	Connection con = DBConnection.getInstance().getConnection();
	private DBIngredients dbIngredients = new DBIngredients();
	
	public void addRecipeLineItem(RecipeLineItem rli) throws SQLException{
		//Create prepared statement to prevent SQL Injection
		//insert into RecipeLineItemDB
		PreparedStatement prepSRecipeLineItemDB = con.prepareStatement("insert into RecipeLineItemDB(quantity, IngredientsDB_ingredientId, RecipeDB_recipeId) values (?,?,?)");
		prepSRecipeLineItemDB.setInt(1, rli.getQuantity());
		prepSRecipeLineItemDB.setInt(2, rli.getIngredient().getId());
		prepSRecipeLineItemDB.setInt(3, rli.getId());
		prepSRecipeLineItemDB.execute();
	}
	
	public RecipeLineItem findById(int id) throws SQLException {
		RecipeLineItem rli = new RecipeLineItem();
		String sql = "select * from RecipeLineItemDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()){
			rli.setId(rs.getInt("id"));
			rli.setQuantity(rs.getInt("quantity"));
			
			int ingredientId = rs.getInt("IngredientsDB_ingredientId");
			DBIngredients dbIngredients = new DBIngredients();
			Ingredients ingredient = dbIngredients.findById(ingredientId);
			rli.setIngredient(ingredient);
		}
		return rli;
	}
	
	public void deleteRecipeLineItem(RecipeLineItem rli) throws SQLException {
		//Create a prepared statement to prevent SQL injection
		PreparedStatement prepS = con.prepareStatement("Delete from RecipeLineItemDB where id = ?");
		prepS.setInt(1, rli.getId());
		prepS.execute();
	}
	
	public void updateRecipeLineItem(RecipeLineItem rli) throws SQLException {
		//Create prepared statement to prevent SQL injection
		PreparedStatement prepSUpdate = con.prepareStatement("Update RecipeLineItemDB set quantity = ?, IngredientsDB_ingredientId = ?, RecipeDB_recipeId where id = ? ");
		prepSUpdate.setInt(1, rli.getQuantity());
		prepSUpdate.setInt(2, rli.getIngredient().getId());
		prepSUpdate.setInt(3, rli.getId());
		prepSUpdate.setInt(4, rli.getId());
		prepSUpdate.executeUpdate();
	}
	
	public LinkedList<RecipeLineItem> getAlleRecipeLineItemFromRecipe(int recipeId) throws SQLException {
		LinkedList<RecipeLineItem> list = new LinkedList<>();
		PreparedStatement prepSRli = con.prepareStatement("Select * from RecipeLineItemDB where RecipeDB_recipeId = ?");
		prepSRli.setInt(1, recipeId);
		ResultSet rs = prepSRli.executeQuery();
		while(rs.next()) {
			Ingredients ingredient = dbIngredients.findById(rs.getInt("IngredientsDB_ingredientsId"));
			RecipeLineItem rli = new RecipeLineItem(recipeId, ingredient, rs.getInt("quantity"));
			list.add(rli);
		}
		return list;
		
	}
}
