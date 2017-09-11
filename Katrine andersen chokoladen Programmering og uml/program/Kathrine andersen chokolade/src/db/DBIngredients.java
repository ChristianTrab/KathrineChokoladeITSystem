package db;

import model.Ingredients;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import db.DBConnection;

public class DBIngredients {
	Connection con = DBConnection.getInstance().getConnection();
	
	public void addIngredients(Ingredients i) throws SQLException{
		//Create prepared statement to prevent SQL Injection
		//insert into IngredientsDB
		PreparedStatement prepSIngredientsDB = con.prepareStatement("insert into IngredientsDB(name, price, quantity, monthlyConsumption, minQty, shippingCost, reorderActivate, reorderQuantity) values(?,?,?,?,?,?,?,?)");
		prepSIngredientsDB.setString(1, i.getName());
		prepSIngredientsDB.setDouble(2, i.getPrice());
		prepSIngredientsDB.setInt(3, i.getQuantity());
		prepSIngredientsDB.setInt(4, i.getMonthlyConsumption());
		prepSIngredientsDB.setInt(5, i.getMinQuantity());
		prepSIngredientsDB.setInt(6, i.getShippingCost());
		prepSIngredientsDB.setBoolean(7, i.isReorder());
		prepSIngredientsDB.setInt(8, i.getReorderQuantity());
		prepSIngredientsDB.execute();
		
		System.out.println("Ingredients: " + i.getName() + " has been added to the database.");
	}
	
	public Ingredients findById(int id) throws SQLException{
		String sql = "select * from IngredientsDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		
		Ingredients ingredients = createIngredientsByFind(rs);
		
		return ingredients;
	}
	
	public LinkedList<Ingredients> findByName(String name) throws SQLException{
		LinkedList<Ingredients> list = new LinkedList<>();
		String sql = "select * from IngredientsDB where name like '%'+?+'%';";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setString(1, name);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()) {
			Ingredients ingredients = new Ingredients();
			ingredients.setId(rs.getInt("id"));
			ingredients.setName(rs.getString("name"));
			ingredients.setPrice(rs.getDouble("price"));
			ingredients.setQuantity(rs.getInt("quantity"));
			ingredients.setMonthlyConsumption(rs.getInt("monthlyConsumption"));
			ingredients.setMinQuantity(rs.getInt("minQty"));
			ingredients.setShippingCost(rs.getInt("shippingCost"));
			ingredients.setReorder(rs.getBoolean("reorderActivate"));
			ingredients.setReorderQuantity(rs.getInt("reorderQuantity"));
			list.add(ingredients);
		}
		return list;
	}
	
	public void updateIngredients(Ingredients i) throws SQLException{
		PreparedStatement prepSUpdateIngredients = con.prepareStatement("Update IngredientsDB set name = ?, price = ?, quantity = ?, monthlyConsumption = ?, minQty = ?, shippingCost = ?, reorderActivate = ?, reorderQuantity = ? where id = ?");
		prepSUpdateIngredients.setString(1, i.getName());
		prepSUpdateIngredients.setDouble(2, i.getPrice());
		prepSUpdateIngredients.setInt(3, i.getQuantity());
		prepSUpdateIngredients.setInt(4, i.getMonthlyConsumption());
		prepSUpdateIngredients.setInt(5, i.getMinQuantity());
		prepSUpdateIngredients.setInt(6, i.getShippingCost());
		prepSUpdateIngredients.setBoolean(7, i.isReorder());
		prepSUpdateIngredients.setInt(8, i.getReorderQuantity());
		prepSUpdateIngredients.setInt(9, i.getId());
		prepSUpdateIngredients.executeUpdate();
	}
	
	public void deleteIngredients(Ingredients i) throws SQLException{
		PreparedStatement prepS = con.prepareStatement("delete from IngredientsDB where id = ?");
		prepS.setInt(1, i.getId());
		prepS.execute();
	}

	private Ingredients createIngredientsByFind(ResultSet rs) throws SQLException {
		Ingredients ingredients = new Ingredients();
		while (rs.next()){
			ingredients.setId(rs.getInt("id"));
			ingredients.setName(rs.getString("name"));
			ingredients.setPrice(rs.getDouble("price"));
			ingredients.setQuantity(rs.getInt("quantity"));
			ingredients.setMonthlyConsumption(rs.getInt("monthlyConsumption"));
			ingredients.setMinQuantity(rs.getInt("minQty"));
			ingredients.setShippingCost(rs.getInt("shippingCost"));
			ingredients.setReorder(rs.getBoolean("reorderActivate"));
			ingredients.setReorderQuantity(rs.getInt("reorderQuantity"));
		}
		return ingredients;
	}
	
	public LinkedList<Ingredients> getAllIngredients() throws SQLException {
		LinkedList<Ingredients> ingList = new LinkedList<>();
		
		ingList.parallelStream()
		.sorted();
		
		PreparedStatement prepSIngredients = con.prepareStatement("Select * from IngredientsDB");
		prepSIngredients.execute();
		ResultSet rs = prepSIngredients.executeQuery();
		while(rs.next()) {
			Ingredients ingredients = new Ingredients();
			ingredients.setId(rs.getInt("id"));
			ingredients.setName(rs.getString("name"));
			ingredients.setPrice(rs.getDouble("price"));
			ingredients.setQuantity(rs.getInt("quantity"));
			ingredients.setMonthlyConsumption(rs.getInt("monthlyConsumption"));
			ingredients.setMinQuantity(rs.getInt("minQty"));
			ingredients.setShippingCost(rs.getInt("shippingCost"));
			ingredients.setReorder(rs.getBoolean("reorderActivate"));
			ingredients.setReorderQuantity(rs.getInt("reorderQuantity"));
			ingList.add(ingredients);
		}
		return ingList;
	}
	
	public Ingredients addEmptyIngredients(Ingredients ingredients) throws SQLException {
		PreparedStatement prepSIngreDB = con.prepareStatement("insert into IngredientsDB(name, price, quantity, annualConsumption, minQty, shippingCost, reorderActivate, reorderQuantity) values(null,null,null,null,null,null,null,null)", Statement.RETURN_GENERATED_KEYS);
		prepSIngreDB.execute();
		ResultSet key = prepSIngreDB.getGeneratedKeys();
		int generatedId = -1;
		
		if (key.next()) {
			generatedId = key.getInt(1);
		} else {
			System.out.println("Could not return generated id from last inserted ingredient: Please check error's in DBIngredients addIngredient method.");
		}
		ingredients.setId(generatedId);
		return ingredients;
	}

	public ArrayList<Ingredients> reorderList() throws SQLException{
		ArrayList<Ingredients> reorderList = new ArrayList<Ingredients>();
		PreparedStatement prepSReorderList = con.prepareStatement("select * from IngredientsDB where minQty > quantity and reorderActivate = 1");
		ResultSet rs = prepSReorderList.executeQuery();
		while(rs.next()){
			Ingredients ingredient = new Ingredients();
			ingredient.setId(rs.getInt("id"));
			ingredient.setName(rs.getString("name"));
			ingredient.setPrice(rs.getDouble("price"));
			ingredient.setQuantity(rs.getInt("quantity"));
			ingredient.setMonthlyConsumption(rs.getInt("monthlyConsumption"));
			ingredient.setMinQuantity(rs.getInt("minQty"));
			ingredient.setShippingCost(rs.getInt("shippingCost"));
			ingredient.setReorder(rs.getBoolean("reorderActivate"));
			ingredient.setReorderQuantity(rs.getInt("reorderQuantity"));
			reorderList.add(ingredient);
		}
		return reorderList;
		
	}
}
