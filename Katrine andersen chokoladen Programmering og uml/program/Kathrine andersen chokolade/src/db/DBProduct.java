package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Ingredients;
import model.Product;
import model.Recipe;

public class DBProduct {
	Connection con = DBConnection.getInstance().getConnection();
	
	public void addProduct(Product p) throws SQLException {
		//Create prepared statement to prevent SQL injection
		PreparedStatement prepSProductDB = con.prepareStatement("Insert into ProductDB (name, price, totalQty, description, details, boxQuantity, RecipeDB_recipeId) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prepSProductDB.setString(1, p.getName());
		prepSProductDB.setDouble(2, p.getPrice());
		prepSProductDB.setInt(3, p.getTotalQty());
		prepSProductDB.setString(4, p.getDescription());
		prepSProductDB.setString(5, p.getDetails());
		prepSProductDB.setInt(6, p.getBoxQuantity());
		prepSProductDB.setInt(7, p.getRecipeId());
		prepSProductDB.execute();
	}
	
	public Product findById(int id) throws SQLException {
		Product product = new Product();
		//Create prepared statement to prevent SQL Injection
		PreparedStatement prepSGetProduct = con.prepareStatement("Select * from ProductDB where id = ?");
		prepSGetProduct.setInt(1, id);
		ResultSet rs = prepSGetProduct.executeQuery();
		while(rs.next()) {
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setTotalQty(rs.getInt("totalQty"));
			product.setDescription(rs.getString("description"));
			product.setDetails(rs.getString("details"));
			product.setBoxQuantity(rs.getInt("boxQuantity"));
			product.setRecipeId(rs.getInt("RecipeDB_recipeId"));
		}
		return product;
	}
	
	public void updateProduct(Product p) throws SQLException {
		//Create prepared statement to prevent SQL injection
		PreparedStatement prepS = con.prepareStatement("Update ProductDB set name = ?, price = ?, totalQty = ?, description = ?, details = ?, boxQuantity = ?, RecipeDB_recipeId = ? where id = ?");
		prepS.setString(1, p.getName());
		prepS.setDouble(2, p.getPrice());
		prepS.setInt(3, p.getTotalQty());
		prepS.setString(4, p.getDescription());
		prepS.setString(5, p.getDetails());
		prepS.setInt(6, p.getBoxQuantity());
		prepS.setInt(7, p.getRecipeId());
		prepS.setInt(8, p.getId());
		prepS.executeUpdate();
		
	}
	
	public LinkedList<Product> findByName(String name) throws SQLException {
		LinkedList<Product> list = new LinkedList<>();
		String sql = "select * from ProductDB where name like '%'+?+'%';";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setString(1, name);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setTotalQty(rs.getInt("totalQty"));
			product.setDescription(rs.getString("description"));
			product.setDetails(rs.getString("details"));
			product.setBoxQuantity(rs.getInt("boxQuantity"));
			product.setRecipeId(rs.getInt("RecipeDB_recipeId"));
			list.add(product);
		}
		return list;
	}
	
	public void deleteProduct(Product p) throws SQLException {
		PreparedStatement prepS = con.prepareStatement("delete from ProductDB where id = ?");
		prepS.setInt(1, p.getId());
		prepS.execute();
		
	}
}
