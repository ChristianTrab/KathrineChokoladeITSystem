package db;

import model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.DBConnection;

public class DBRecipe {
	Connection con = DBConnection.getInstance().getConnection();
	
	public void addRecipe(Recipe r) throws SQLException{
		//Create prepared statement to prevent SQL Injection
		//insert into RecipeDB
		PreparedStatement prepSRecipeDB = con.prepareStatement("Insert into RecipeDB(name, description) values(?,?)");
		prepSRecipeDB.setString(1, r.getName());
		prepSRecipeDB.setString(2, r.getDescription());
		prepSRecipeDB.execute();
		
		System.out.println("Recipe: " + r.getName() + " has been added to the database.");
	}
	
	public Recipe findById(int id) throws SQLException{
		Recipe recipe = new Recipe();
		String sql = "select * from RecipeDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()){
			recipe.setId(rs.getInt("id"));
			recipe.setName(rs.getString("name"));
			recipe.setDescription(rs.getString("description"));
		}
		return recipe;
	}
	
	public LinkedList<Recipe> findByName(String name) throws SQLException{
		LinkedList<Recipe> list = new LinkedList<>();
		String sql = "select * from RecipeDB where name like '%'+?+'%';";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setString(1, name);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()) {
			Recipe recipe = new Recipe();
			recipe.setId(rs.getInt("id"));
			recipe.setName(rs.getString("name"));
			recipe.setDescription(rs.getString("description"));
			list.add(recipe);
		}
		return list;
	}
	
	public void updateRecipe(Recipe r) throws SQLException{
		PreparedStatement prepS = con.prepareStatement("Update RecipeDB set name = ?, description = ? where id = ?");
		prepS.setString(1, r.getName());
		prepS.setString(2, r.getDescription());
		prepS.setInt(3, r.getId());
		prepS.executeUpdate();
	}
	
	public void deleteRecipe(Recipe r) throws SQLException{
		PreparedStatement prepS = con.prepareStatement("delete from RecipeDB where id = ?");
		prepS.setInt(1, r.getId());
		prepS.execute();
	}

	private Recipe createRecipeByFind(ResultSet rs) throws SQLException {
		Recipe recipe = new Recipe();
		while (rs.next()){
			recipe.setId(rs.getInt("id"));
			recipe.setName(rs.getString("name"));
			recipe.setDescription(rs.getString("description"));
		}
		return recipe;
	}
}
