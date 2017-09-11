package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import model.Order;
import model.Product;
import model.SaleLineItem;

public class DBSaleLineItem {
	Connection con = DBConnection.getInstance().getConnection();
	DBProduct dbProduct = new DBProduct();
	
	public void addSaleLineItem(SaleLineItem s) throws SQLException {
		//Create prepared statement to prevent SQL injection
		//Inserts into SaleLineItemDB
		PreparedStatement prepSSaleLineItemDB = con.prepareStatement("Insert into SaleLineItemDB(quantity, price, ProductDB_productId, OrderDB_orderId) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prepSSaleLineItemDB.setInt(1, s.getQuantity());
		prepSSaleLineItemDB.setDouble(2, s.getPrice());
		prepSSaleLineItemDB.setInt(3, s.getProduct().getId());
		prepSSaleLineItemDB.setInt(4, s.getOrderId());
		prepSSaleLineItemDB.execute();
		
		
		ResultSet key = prepSSaleLineItemDB.getGeneratedKeys();
		int generatedId = -1;
		// Get SaleLineItemDB id from last inserted saleLineItem
		if (key.next()) {
			generatedId = key.getInt(1);
		}
		s.setId(generatedId);
		
	}
	
	public SaleLineItem findById(int id) throws SQLException {
		SaleLineItem saleLineItem = new SaleLineItem();
		String sql = "select * from SaleLineItemDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()){
			saleLineItem.setId(rs.getInt("id"));
			saleLineItem.setQuantity(rs.getInt("quantity"));
			saleLineItem.setPrice(rs.getDouble("price"));
			saleLineItem.setOrderId(rs.getInt("OrderDB_orderId"));
			
			int productId = rs.getInt("ProductDB_productId");
			//Get product object from DBProduct
			DBProduct dbProduct = new DBProduct();
			Product product =  dbProduct.findById(productId);
			saleLineItem.setProduct(product);
			
		}
		return saleLineItem;
	}
	
	public void deleteSaleLineItem(SaleLineItem s) throws SQLException {
		//Create a prepared statement to prevent SQL injection
		System.out.println(s.getId());
		PreparedStatement prepS = con.prepareStatement("Delete From SaleLineItemDB where id = ?");
		prepS.setInt(1, s.getId());
		prepS.execute();
	}
	
	public void updateSaleLineItem(SaleLineItem s) throws SQLException {
		//Create prepared statement to prevent SQL injection
		
		PreparedStatement prepSUpdate = con.prepareStatement("update SaleLineItemDB set quantity = ?, price = ?, ProductDB_productId = ? where id = ?;");
		prepSUpdate.setInt(1, s.getQuantity());
		prepSUpdate.setDouble(2, s.getPrice());
		prepSUpdate.setInt(3, s.getProduct().getId());
		prepSUpdate.setInt(4, s.getId());
		prepSUpdate.executeUpdate();
		System.out.println("DB sli quantity: " + s.getQuantity() + " Id: " + s.getId());
	}

	public LinkedList<SaleLineItem> getAllProductsInOrder(int orderId) throws SQLException {
		LinkedList<SaleLineItem> list = new LinkedList<>();
		PreparedStatement prepSGetOrderProducts = con.prepareStatement("select * from SaleLineItemDB where OrderDB_orderId = ?");
		prepSGetOrderProducts.setInt(1, orderId);
		prepSGetOrderProducts.execute();
		ResultSet rs = prepSGetOrderProducts.executeQuery();
		while(rs.next()) {
			Product product = dbProduct.findById(rs.getInt("ProductDB_productId"));
			SaleLineItem saleLineItem = new SaleLineItem(rs.getInt("id"), rs.getInt("OrderDB_orderId"), product, rs.getInt("quantity"), rs.getDouble("price"));
			list.add(saleLineItem);
		}
		return list;
	}



}
