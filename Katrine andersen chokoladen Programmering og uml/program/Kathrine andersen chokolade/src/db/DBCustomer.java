package db;

import model.Customer;
import db.DBPerson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import db.DBConnection;

public class DBCustomer {
	Connection con = DBConnection.getInstance().getConnection();
	DBPerson dbPerson = new DBPerson();

	public void addCustomer(Customer c) throws SQLException {
		int personId = dbPerson.addPerson(c);
		c.setPersonDB_personId(personId);

		// Created prepared statement to prevent SQL injection.
		// Inserts into CustomerDB
		PreparedStatement prepS = con.prepareStatement(
				"insert into CustomerDB (type, CustomerCategoryDB_categoryId, cvr, companyName, PersonDB_personId) values(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		prepS.setString(1, c.getType());
		prepS.setInt(2, c.getCategory());
		prepS.setString(3, c.getCvr());
		prepS.setString(4, c.getCompanyName());
		prepS.setInt(5, personId);
		prepS.execute();
		
		ResultSet key = prepS.getGeneratedKeys();
		int generatedId = -1;
		// Get PersonDB id from last inserted person
		if (key.next()) {
			generatedId = key.getInt(1);
		} else {
			System.out.println(
					"Could not return generated id from last inserted person: Please check error's in DBCustomer addCustomer method.");
		}
		c.setId(generatedId);

		System.out.println(
				"Customer: " + c.getfName() + " " + c.getlName() + " has succesful been added to the database.");
	}

	public Customer findById(int id) throws SQLException {
		Customer customer = new Customer();
		String sql = "select * from CustomerDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			
			customer = dbPerson.findCustomerById(rs.getInt("PersonDB_personId"));
			
			customer.setType(rs.getString("type"));
			customer.setCategory(rs.getInt("CustomerCategoryDB_categoryId"));
			customer.setCvr(rs.getString("cvr"));
			customer.setCompanyName(rs.getString("companyName"));
		}
		return customer;
	}

	public LinkedList<Customer> findByString(String search) throws SQLException {
		LinkedList<Customer> customerList = new LinkedList<>();
		String sql = "select * from PersonDB Inner join CustomerDB On PersonDB.id = CustomerDB.PersonDB_personId where phone like '%'+?+'%' or fName like '%'+?+'%' or lName like '%'+?+'%' or CONCAT(fName, ' ', lName) like '%'+?+'%'";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setString(1, search);
		prepS.setString(2, search);
		prepS.setString(3, search);
		prepS.setString(4, search);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			Customer customer = new Customer();
			PreparedStatement prepSId = con.prepareStatement("Select id from CustomerDB where PersonDB_personid = ?");
			prepSId.setInt(1, rs.getInt("id"));
			ResultSet getId = prepSId.executeQuery();
			while(getId.next()) {
				customer.setId(getId.getInt("id"));
			}
			customer.setPersonDB_personId(rs.getInt("PersonDB_personId"));
			customer.setfName(rs.getString("fname"));
			customer.setlName(rs.getString("lname"));
			customer.setAddress(rs.getString("address"));
			customer.setZipCode(rs.getString("zipCode"));
			customer.setCity(rs.getString("city"));
			customer.setPhone(rs.getString("phone"));
			customer.setEmail(rs.getString("email"));
			customer.setType(rs.getString("type"));
			customer.setCategory(rs.getInt("CustomerCategoryDB_categoryId"));
			customer.setCvr(rs.getString("cvr"));
			customer.setCompanyName(rs.getString("companyName"));
			customerList.add(customer);
		}
		return customerList;
		
	}

	

	public Customer createCustomerByFind(ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		while (rs.next()) {
			customer.setId(rs.getInt("id"));
			customer.setfName(rs.getString("fname"));
			customer.setlName(rs.getString("lname"));
			customer.setAddress(rs.getString("address"));
			customer.setZipCode(rs.getString("zipCode"));
			customer.setCity(rs.getString("city"));
			customer.setPhone(rs.getString("phone"));
			customer.setEmail(rs.getString("email"));
			customer.setType(rs.getString("type"));
			customer.setCategory(rs.getInt("category"));
			customer.setPayment(rs.getString("payment"));
			customer.setCvr(rs.getString("cvr"));
			customer.setCompanyName(rs.getString("companyName"));
		}
		return customer;
	}

	public void deleteCustomer(Customer c) throws SQLException {
		dbPerson.deletePerson(c);
		PreparedStatement prepS = con.prepareStatement("delete from CustomerDB where CustomerDB.id = ?");
		prepS.setInt(1, c.getId());
		prepS.execute();
	}

	public void updateCustomer(Customer c) throws SQLException {
		//type, CustomerCategory_categoryId, cvr, companyName, PersonDB_personId
		PreparedStatement prepS = con.prepareStatement("Update CustomerDb set type = ?, CustomerCategory_categoryId = ?, cvr = ?, companyName = ? PersonDB_personId = ? where id = ? ");
		prepS.setString(1, c.getType());
		prepS.setInt(2, c.getCategory());
		prepS.setString(3, c.getPayment());
		prepS.setString(4, c.getCvr());
		prepS.setString(5, c.getCompanyName());
		prepS.executeUpdate();
	}

	public LinkedList<String> getAllCategory() throws SQLException {
		LinkedList<String> list = new LinkedList<>();
		
		PreparedStatement prepSCategory = con.prepareStatement("Select * from CustomerCategoryDB");
		ResultSet r = prepSCategory.executeQuery();
		while (r.next()) {
			list.add(r.getString("categoryName"));
		}
		return list;
	}
}
