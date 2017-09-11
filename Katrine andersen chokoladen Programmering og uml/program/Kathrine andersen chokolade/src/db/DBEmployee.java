package db;

import model.Employee;
import db.DBPerson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DBConnection;

public class DBEmployee extends DBPerson {
	Connection con = DBConnection.getInstance().getConnection();
	DBPerson dbPerson = new DBPerson();

	public void addEmployee(Employee e) throws SQLException {
		int personId = dbPerson.addPerson(e);

		// Create PreparedStatement to prevent SQL injection
		// Inserts into EmployeeDB
		PreparedStatement prepSEmployeeDB = con.prepareStatement(
				"insert into EmployeeDB (jobTitle, PersonDB_personId, SectionDB_sectionId) values(?,?,?)");
		prepSEmployeeDB.setString(1, e.getJobTitle());
		prepSEmployeeDB.setInt(2, personId);
		prepSEmployeeDB.setInt(3, e.getSectionDB_sectionId());
		prepSEmployeeDB.execute();

		System.out.println(
				"Employee: " + e.getfName() + " " + e.getlName() + " has succesfully been added to the database.");
	}

	public Employee findById(int id) throws SQLException {
		Employee employee = new Employee();
		String sql = "select * from PersonDB Inner join EmployeeDB on PersonDB.id = EmployeeDB.PersonDB_personId where EmployeeDB.id = ? ";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setInt(1, id);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			employee.setId(rs.getInt("id"));
			employee.setfName(rs.getString("fName"));
			employee.setlName(rs.getString("lName"));
			employee.setAddress(rs.getString("address"));
			employee.setZipCode(rs.getString("zipCode"));
			employee.setCity(rs.getString("city"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("email"));
			employee.setJobTitle(rs.getString("jobTitle"));
			employee.setSectionDB_sectionId(rs.getInt("SectionDB_sectionId"));
		}
		return employee;
	}

	public Employee findByPhone(String phone) throws SQLException {
		Employee employee = new Employee();
		String sql = "select * from EmployeeDB where id = ?";
		PreparedStatement prepS = con.prepareStatement(sql);
		prepS.setString(1, phone);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();
		while (rs.next()) {
			employee.setId(rs.getInt("id"));
			employee.setfName(rs.getString("fName"));
			employee.setlName(rs.getString("lName"));
			employee.setAddress(rs.getString("address"));
			employee.setZipCode(rs.getString("zipCode"));
			employee.setCity(rs.getString("city"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("mail"));
			employee.setJobTitle(rs.getString("jobTitle"));
			employee.setSectionDB_sectionId(rs.getInt("SectionDB_sectionId"));
		}
		return employee;
	}

	public Employee findByName(String name) throws SQLException {
		PreparedStatement prepS = con.prepareStatement("select * from EmployeeDB where name like %?%");
		prepS.setString(1, name);
		prepS.execute();
		ResultSet rs = prepS.executeQuery();

		Employee employee = createEmployeeByFind(rs);

		return employee;
	}

	public void updateEmployee(Employee e) throws SQLException {
		//dbPerson.updatePerson(e); Maybe?

		PreparedStatement prepS = con.prepareStatement("Update EmployeeDB set jobTitle = ?, SectionDB_sectionId = ? where id = ?");
		prepS.setString(1, e.getJobTitle());
		prepS.setInt(2, e.getSectionDB_sectionId());
		prepS.executeUpdate();
	}
	
	public void deleteEmployee(Employee e) throws SQLException{
		dbPerson.deletePerson(e);
		PreparedStatement prepS = con.prepareStatement("delete from EmployeeDB where id = ?");
		prepS.setInt(1, e.getId());
		prepS.execute();
	}

	private Employee createEmployeeByFind(ResultSet rs) throws SQLException {
		Employee employee = new Employee();
		while (rs.next()) {
			employee.setId(rs.getInt("id"));
			employee.setfName(rs.getString("fName"));
			employee.setlName(rs.getString("lName"));
			employee.setAddress(rs.getString("address"));
			employee.setZipCode(rs.getString("zipCode"));
			employee.setCity(rs.getString("city"));
			employee.setPhone(rs.getString("phone"));
			employee.setEmail(rs.getString("mail"));
			employee.setJobTitle(rs.getString("jobTitle"));
			employee.setSectionDB_sectionId(rs.getInt("SectionDB_sectionId"));
		}
		return employee;
	}
	
}
