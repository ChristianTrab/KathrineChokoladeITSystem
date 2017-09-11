package db;

import model.Order;
import model.Company;
import model.Customer;
import model.Offer;
import model.Employee;
import db.DBCustomer;
import db.DBCompany;
import db.DBEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.DBConnection;

public class DBOrder {
	Connection con = DBConnection.getInstance().getConnection();
	DBCompany dBCompany = new DBCompany();
	DBEmployee dBEmployee = new DBEmployee();
	DBCustomer dBCustomer = new DBCustomer();
	
	public boolean addOrder(Order o) throws SQLException{
		//Create prepared statement to prevent SQL injection.
		PreparedStatement prepSOrderDB = con.prepareStatement("Insert into OrderDB(totalPrice, tax, date, status, discountPrice, CustomerDB_customerId, EmployeeDB_employeeId, CompanyDB_companyId) values(?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		prepSOrderDB.setDouble(1, o.getTotalPrice());
		prepSOrderDB.setDouble(2, o.getTax());
		prepSOrderDB.setString(3, o.getDate());
		prepSOrderDB.setInt(4, o.getStatus());
		prepSOrderDB.setDouble(5, o.getDiscountPrice());
		System.out.println("DB Id: " + o.getCustomer().getId());
		prepSOrderDB.setInt(6, o.getCustomer().getId());
		prepSOrderDB.setInt(7, 1);
		prepSOrderDB.setInt(8, 1);
		prepSOrderDB.execute();
		
		ResultSet key = prepSOrderDB.getGeneratedKeys();
		int generatedId = -1;
		// Get PersonDB id from last inserted person
		if (key.next()) {
			generatedId = key.getInt(1);
		} 
		
		o.setId(generatedId);
		return true;
	}
	
	public Order addEmptyOrder(Order order) throws SQLException {
		PreparedStatement prepSOrderDB = con.prepareStatement("Insert into OrderDB (totalPrice, tax, date, status, discountPrice, CustomerDB_customerId, EmployeeDB_employeeId, CompanyDB_companyId) values(null, null, null, null, null, null, null, null)", Statement.RETURN_GENERATED_KEYS);
		prepSOrderDB.execute();
		ResultSet key = prepSOrderDB.getGeneratedKeys();
		int generatedId = -1;
		// Get OrderDB id from last inserted order
		if (key.next()) {
			generatedId = key.getInt(1);
		} else {
			System.out.println(
					"Could not return generated id from last inserted person: Please check error's in DBCustomer addCustomer method.");
		}
		order.setId(generatedId);
		return order;
		
	}
	
	public LinkedList<Order> findByString(String search) throws SQLException {
		LinkedList<Order> orderList = new LinkedList<>();
		PreparedStatement prepSFindOrder = con.prepareStatement("select * from OrderDB INNER JOIN CustomerDB on OrderDB.CustomerDB_customerId = CustomerDB.id INNER JOIN PersonDB on CustomerDB.PersonDB_personId = PersonDB.id where fName like '%'+?+'%' or lName like '%'+?+'%' or cvr like '%'+?+'%' or companyName like '%'+?+'%'");
		
		prepSFindOrder.setString(1, search);
		prepSFindOrder.setString(2, search);
		prepSFindOrder.setString(3, search);
		prepSFindOrder.setString(4, search);
		ResultSet rs = prepSFindOrder.executeQuery();
		while (rs.next()) {
			Order order = new Order();
			order.setId(rs.getInt("ID"));
			order.setDate(rs.getString("date"));
			order.setTotalPrice(rs.getDouble("totalPrice"));
			order.setTax(rs.getDouble("tax"));
			order.setStatus(rs.getInt("status")); //Ændre til string
			order.setDiscountPrice(rs.getDouble("discountPrice"));
			order.setCompany(dBCompany.findById(rs.getInt("CompanyDB_companyId")));
			order.setCustomer(dBCustomer.findById(rs.getInt("CustomerDB_customerId")));
			order.setEmployee(dBEmployee.findById(rs.getInt("EmployeeDB_EmployeeId")));
			orderList.add(order);
		}
		return orderList;
	}
	
	public Order findById(int id) throws SQLException{
		Order order = new Order();
		Company company = new Company();
		Customer customer = new Customer();
		Employee employee = new Employee();
		String sql = "select * from OrderDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()){
			order.setId(rs.getInt("id"));
			order.setTotalPrice(rs.getDouble("totalPrice"));
			order.setTax(rs.getDouble("tax"));
			order.setDate(rs.getString("date"));
			order.setStatus(rs.getInt("status")); //Ændre til string
			order.setDiscountPrice(rs.getDouble("discountPrice"));
			order.setCompany(dBCompany.findById(rs.getInt("CompanyDB_companyId")));
			order.setCustomer(dBCustomer.findById(rs.getInt("CustomerDB_customerId")));
			order.setEmployee(dBEmployee.findById(rs.getInt("EmployeeDB_EmployeeId")));
			
		}
		return order;	
	}
	
	public void deleteOrder(Order o) throws SQLException{
		//Create prepared statement to prevent SQL injection.
		PreparedStatement prepSDelete = con.prepareStatement("Delete from OrderDB where id = ?");
		prepSDelete.setInt(1, o.getId());
		prepSDelete.execute();
	}

	public void updateOrder(Order order) throws SQLException {
		PreparedStatement prepS = con.prepareStatement("Update OrderDB set totalPrice = ?, tax = ?, date = ?, status = ?, discountPrice = ? where id = ?");
		prepS.setDouble(1, order.getTotalPrice());
		prepS.setDouble(2, order.getTax());
		prepS.setString(3, order.getDate());
		prepS.setInt(4, order.getStatus());
		prepS.setDouble(5, order.getDiscountPrice());
		prepS.setInt(6, order.getId());
		prepS.executeUpdate();
	}
	
	public void endOrder(Order o) throws SQLException{
		PreparedStatement prepS = con.prepareStatement("Update OrderDB set status = ? where id = ?");
		prepS.setInt(1, 0);
		prepS.setInt(2, o.getId());
		prepS.executeUpdate();
	}
	
	public LinkedList<Order> getAllOrders() throws SQLException {
		LinkedList<Order> list = new LinkedList<>();
		
		list.parallelStream()
		.sorted();
		
		PreparedStatement prepSActiveOrders = con.prepareStatement("Select * from OrderDB where CustomerDB_customerId is not null");
		prepSActiveOrders.execute();
		ResultSet rs = prepSActiveOrders.executeQuery();
		while(rs.next()) {
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setTotalPrice(rs.getDouble("totalPrice"));
			order.setTax(rs.getDouble("tax"));
			order.setDate(rs.getString("date"));
			order.setStatus(rs.getInt("status"));
			order.setDiscountPrice(rs.getDouble("discountPrice"));
			
			//Get Customer for order object
			order.setCustomer(dBCustomer.findById(rs.getInt("CustomerDB_customerId")));
			//Get Company
			order.setCompany(dBCompany.findById(rs.getInt("CompanyDB_companyId")));
			
			//Get Employee
			order.setEmployee(dBEmployee.findById(rs.getInt("EmployeeDB_EmployeeId")));
			
			//Insert into linkedlist
			list.add(order);
		}		
		
		return list;
	}	

	
	
	public LinkedList<Order> getAllActiveOrders() throws SQLException {
		
		LinkedList<Order> list = new LinkedList<>();
		
		list.parallelStream()
		.sorted();
		
		PreparedStatement prepSActiveOrders = con.prepareStatement("Select * from OrderDB where status = ?");
		prepSActiveOrders.setInt(1, 1);
		prepSActiveOrders.execute();
		ResultSet rs = prepSActiveOrders.executeQuery();
		while(rs.next()) {
			Order order = new Order();
			order.setId(rs.getInt("id"));
			order.setTotalPrice(rs.getDouble("totalPrice"));
			order.setTax(rs.getDouble("tax"));
			order.setDate(rs.getString("date"));
			order.setStatus(rs.getInt("status"));
			order.setDiscountPrice(rs.getDouble("discountPrice"));
			
			//Get Customer for order object
			order.setCustomer(dBCustomer.findById(rs.getInt("CustomerDB_customerId")));
			//Get Company
			order.setCompany(dBCompany.findById(rs.getInt("CompanyDB_companyId")));
			
			//Get Employee
			order.setEmployee(dBEmployee.findById(rs.getInt("EmployeeDB_EmployeeId")));
			
			//Insert into linkedlist
			list.add(order);
		}		
		
		return list;
	}	
}
