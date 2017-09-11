package db;

import model.Section;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class DBSection {
	Connection con = DBConnection.getInstance().getConnection();
	
	public void addSection(Section s) throws SQLException{
		//Create prepared statement to prevent SQL injection
		//Inserts into SectionDB
		PreparedStatement prepSSectionDB = con.prepareStatement("insert into SectionDB(sectionName, amountOfEmployees) values (?,?)");
		prepSSectionDB.setString(1, s.getSectionName());
		prepSSectionDB.setInt(2, s.getAmountOfEmployees());
		prepSSectionDB.execute();
		
		System.out.println("Section: " + s.getSectionName() + " has succesfully been added to the database.");
	}
	
	public Section findById(int id) throws SQLException{
		Section section = new Section();
		String sql = "select * from SectionDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while(rs.next()){
			section.setId(rs.getInt("id"));
			section.setSectionName(rs.getString("sectionName"));
			section.setAmountOfEmployees(rs.getInt("amountOfEmployees"));
		}
		return section;
	}
	
	public void deleteSection(Section s) throws SQLException {
		//Create a prepared statement to prevent SQL injection
		PreparedStatement prepS = con.prepareStatement("Delete from SectionDB where id = ?");
		prepS.setInt(1, s.getId());
		prepS.execute();
	}
	
	public void updateSection(Section s) {
		
	}
}
