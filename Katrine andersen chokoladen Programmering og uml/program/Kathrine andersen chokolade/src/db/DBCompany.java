package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Company;

public class DBCompany {
	Connection con = DBConnection.getInstance().getConnection();
	
	public void addCompany(Company c) throws SQLException{
		//Create prepared statement to prevent SQL injection.
		PreparedStatement prepSCompanyDB = con.prepareStatement("Insert into CompanyDB (name, address, city, zipCode, cvr, phone, email) values(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
		prepSCompanyDB.setString(1, c.getName());
		prepSCompanyDB.setString(2, c.getAddress());
		prepSCompanyDB.setString(3, c.getCity());
		prepSCompanyDB.setString(4, c.getZipCode());
		prepSCompanyDB.setString(5, c.getCvr());
		prepSCompanyDB.setString(6, c.getPhone());
		prepSCompanyDB.setString(7, c.getEmail());
		prepSCompanyDB.execute();
	}
	
	public Company findById(int id) throws SQLException {
		Company company = new Company();
		//Create prepared statement to prevent SQL Injection
		PreparedStatement prepSGetCompany = con.prepareStatement("Select * from CompanyDB where id = ?");
		prepSGetCompany.setInt(1, 1);
		ResultSet rs = prepSGetCompany.executeQuery();
		while(rs.next()) {
			company.setId(rs.getInt("id"));
			company.setName(rs.getString("name"));
			company.setAddress(rs.getString("address"));
			company.setCity(rs.getString("city"));
			company.setZipCode(rs.getString("zipCode"));
			company.setPhone(rs.getString("phone"));
			company.setCvr(rs.getString("cvr"));
			company.setEmail(rs.getString("email"));
		}
		return company;
	}
	
	public void updateCompany(Company c) throws SQLException {
		 
		PreparedStatement prepSUpdate = con.prepareStatement("Update CompanyDB set name = ?, address = ?, city = ?, zipCode = ?, phone = ?, cvr = ?, email = ? where id = ?");
		prepSUpdate.setString(1, c.getName());
		prepSUpdate.setString(2, c.getAddress());
		prepSUpdate.setString(3, c.getCity());
		prepSUpdate.setString(4, c.getZipCode());
		prepSUpdate.setString(5, c.getCvr());
		prepSUpdate.setString(6, c.getPhone());
		prepSUpdate.setString(7, c.getEmail());
		prepSUpdate.setInt(8, c.getId());
		prepSUpdate.executeUpdate();
	}
	
}
