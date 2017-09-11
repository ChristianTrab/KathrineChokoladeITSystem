package ctr;

import model.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import db.DBCustomer;
/*
 * @version (06-06-2017)
 */

/*
 *  Creates an object of DBCustomer.
 */
public class CustomerCtr {
	DBCustomer dbCustomer = new DBCustomer();
	
	/*
	 *  Creates an object of and empty customer.
	 *  @return	- returns a empty customer object
	 */
	public Customer createEmyptyCustomer() {
		Customer c = new Customer();
		return c;
	}

	/*
	 * The Update Customer Method, this method will update the current customer with new values.
	 * 
	 * @param	customer	Customer 	- Customer Object from model.customer
	 * @param	id			int			- Id of the customer
	 * @param	fName		String		- First Name of the customer
	 * @param	lName		String		- Last Name of the customer
	 * @param	adress		String		- Adress of the customer
	 * @param	city		String		- City of the customer
	 * @param	zipCode		String		- zipCode of the customer
	 * @param	phone		String		- email of the customer
	 * @param	type		String		- What type of customer (private or profession)
	 * @param	category	int			- What type of category the profesion belongs to
	 * @param 	payment		String		- Mostly an option to attach further text if an order needs further attention to its payment.
	 * @param	cvr			String		- The CVR of the profession to the customer (if any)
	 * @param	companyName	String		- The Company Name of the profession (if any)
	 * @return	- Returns a updated customer
	 */
	
	public Customer updateCustomer(Customer customer, int id, String fName, String lName, String address, String city, String zipCode, String phone,
			String email, String type, int category, String payment, String cvr, String companyName) {
		Customer c = customer;
		c.setId(id);
		c.setfName(fName);
		c.setlName(lName);
		c.setAddress(address);
		c.setCity(city);
		c.setZipCode(zipCode);
		c.setPhone(phone);
		c.setEmail(email);
		c.setType(type);
		c.setCategory(category);
		c.setPayment(payment);
		c.setCvr(cvr);
		c.setCompanyName(companyName);
		return c;
	}
	
	/*
	 * The Update Customer In Database Method, this method will update the current customer with new values.
	 * @param	customer	Customer 	- Customer Object from model.customer
	 * @param	id			int			- Id of the customer
	 * @param	fName		String		- First Name of the customer
	 * @param	lName		String		- Last Name of the customer
	 * @param	adress		String		- Adress of the customer
	 * @param	city		String		- City of the customer
	 * @param	zipCode		String		- zipCode of the customer
	 * @param	phone		String		- email of the customer
	 * @param	type		String		- What type of customer (private or profession)
	 * @param	category	int			- What type of category the profesion belongs to
	 * @param 	payment		String		- Mostly an option to attach further text if an order needs further attention to its payment.
	 * @param	cvr			String		- The CVR of the profession to the customer (if any)
	 * @param	companyName	String		- The Company Name of the profession (if any)
	 */
	
	public void updateCustomerInDB(Customer customer, int id, String fName, String lName, String address, String city, String zipCode, String phone,
			String email, String type, int category, String payment, String cvr, String companyName) throws SQLException {
		
		Customer c = updateCustomer(customer, id, fName, lName, address, city, zipCode, 
				phone, email, type, category, payment, cvr, companyName);
		
		dbCustomer.updateCustomer(c);
	}
	
	/*
	 * This is The Add Customer Method, this method will add the current customer to the database.
	 * @param	customer	Customer 	- Customer Object from model.customer
	 * @param	id			int			- Id of the customer
	 * @param	fName		String		- First Name of the customer
	 * @param	lName		String		- Last Name of the customer
	 * @param	adress		String		- Adress of the customer
	 * @param	city		String		- City of the customer
	 * @param	zipCode		String		- zipCode of the customer
	 * @param	phone		String		- email of the customer
	 * @param	type		String		- What type of customer (private or profession)
	 * @param	category	int			- What type of category the profesion belongs to
	 * @param 	payment		String		- Mostly an option to attach further text if an order needs further attention to its payment.
	 * @param	cvr			String		- The CVR of the profession to the customer (if any)
	 * @param	companyName	String		- The Company Name of the profession (if any)
	 */
	
	public void addCustomerToDB(Customer customer, int id, String fName, String lName, String address, String city, String zipCode, String phone,
									String email, String type, int category, String payment, String cvr, String companyName) throws SQLException {
		//Creates a new Customer object and insert values on defined variables
		Customer c = updateCustomer(customer, id, fName, lName, address, city, zipCode, 
									phone, email, type, category, payment, cvr, companyName); 
		dbCustomer.addCustomer(c);		//Adds the object Customer "c" object to the database
	}
	
	/*
	 * This is the Find Customer by String Method
	 * @param	search		String 		- Which is calling findByString method in db.DBCustomer and returning a resultset
	 * @return a list of customers
	 */
	
	public LinkedList<Customer> findCustomerByString(String search) throws SQLException {
		LinkedList<Customer> LList = dbCustomer.findByString(search);
		return LList;
	}
	/*
	 * This is the Find Customer by Id Method
	 * @param	id			Int 		- Which is calling findById method in db.DBCustomer and returning an object of Customer.
	 * @return	- Return a customer object
	 */
	public Customer findCustomerById(int id) throws SQLException {
		Customer c = dbCustomer.findById(id);
		return c;
	}
	
	/*
	 * This is the Delete Customer Method
	 * @param	id			Int 		- Which is calling findById method in db.DBCustomer and returning an object of Customer and then removes it from the database.
	 */
	
	public void deleteCustomer(int id) throws SQLException {
		Customer c = findCustomerById(id);
		dbCustomer.deleteCustomer(c);
	}
	
	/*
	 * This is the get all category method
	 * @return 	- All the categorys from the customer database
	 */
	public LinkedList<String> getAllCatgory() throws SQLException {
		return dbCustomer.getAllCategory();
	}

}