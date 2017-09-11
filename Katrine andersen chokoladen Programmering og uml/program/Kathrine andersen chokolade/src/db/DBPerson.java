package db;

import model.Customer;
import model.Person;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import db.DBConnection;

public class DBPerson {
	Connection con = DBConnection.getInstance().getConnection();

	public int addPerson(Person p) throws SQLException {
		// Create prepared statement to prevent SQL injection
		// insert into personDB
		PreparedStatement prepSPersonDB = con.prepareStatement(
				"Insert into PersonDB(fName, lName, address, city, zipCode, phone, email) values(?, ?, ?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		prepSPersonDB.setString(1, p.getfName());
		prepSPersonDB.setString(2, p.getlName());
		prepSPersonDB.setString(3, p.getAddress());
		prepSPersonDB.setString(4, p.getCity());
		prepSPersonDB.setString(5, p.getZipCode());
		prepSPersonDB.setString(6, p.getPhone());
		prepSPersonDB.setString(7, p.getEmail());
		prepSPersonDB.execute();

		ResultSet key = prepSPersonDB.getGeneratedKeys();
		int generatedId = -1;
		// Get PersonDB id from last inserted person
		if (key.next()) {
			generatedId = key.getInt(1);
		} else {
			System.out.println(
					"Could not return generated id from last inserted person: Please check error's in DBCustomer addCustomer method.");
		}
		p.setId(generatedId);
		System.out
				.println("Person: " + p.getfName() + " " + p.getlName() + " has succesful been added to the database.");
		return p.getId();
	}

	public Person findById(int id) throws SQLException {
		Person person = new Person();
		String sql = "select * from PersonDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			person.setfName(rs.getString("fName"));
			person.setlName(rs.getString("lName"));
			person.setAddress(rs.getString("address"));
			person.setCity(rs.getString("city"));
			person.setZipCode(rs.getString("zipCode"));
			person.setPhone(rs.getString("phone"));
			person.setEmail(rs.getString("mail"));
		}
		return person;
	}
	
	public Customer findCustomerById(int id) throws SQLException {
		Customer customer = new Customer();
		String sql = "select * from PersonDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			customer.setfName(rs.getString("fName"));
			customer.setlName(rs.getString("lName"));
			customer.setAddress(rs.getString("address"));
			customer.setCity(rs.getString("city"));
			customer.setZipCode(rs.getString("zipCode"));
			customer.setPhone(rs.getString("phone"));
			customer.setEmail(rs.getString("email"));
		}
		return customer;
	}

	public void deletePerson(Person p) throws SQLException {
		PreparedStatement prepS = con.prepareStatement("delete from PersonDB where id =?");
		prepS.setInt(1, p.getId());
		prepS.execute();

	}

	public void updatePerson(Person p) throws SQLException {
		PreparedStatement prepS = con.prepareStatement(
				"Update PersonDB set fName = ?, lName = ?, address = ?, city = ?, zipCode = ?, phone = ?, email = ? where id = ?");
		prepS.setString(1, p.getfName());
		prepS.setString(2, p.getlName());
		prepS.setString(3, p.getAddress());
		prepS.setString(4, p.getCity());
		prepS.setString(5, p.getZipCode());
		prepS.setString(6, p.getPhone());
		prepS.setString(7, p.getEmail());
		prepS.executeUpdate();
	} 

}
