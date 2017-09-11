package model;

/*
 * @version (25-05-2016)
 */


/*
 * Creates instance variables id, name, address, city, zipCode, cvr, phone, email
 */

public class Company {
	// instance variables 
	private int id;
	private String name;
	private String address;
	private String city;
	private String zipCode;
	private String cvr;
	private String phone;
	private String email;
	
	/*
	 * The Company constructor, will create a new instance of Company
	 * 
	 * @param	id		int	- Id of the company
	 * @param 	name 	String - The full name of the company
	 * @param	address String - The address of the company
	 * @param	city 	String - The name of the city, where the company is located
	 * @param 	zipCode	String - The zipCode for the given area
	 * @param	cvr		String - The cvr of the company
	 * @param	Phone	String - The phone number for the company
	 * @param	email	String - The email of the company
	 * 
	 */
	public Company(int id,String name,String address,String city,String zipCode,String cvr,String phone, String email) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.cvr = cvr;
		this.phone = phone;
		this.email = email;
	}
	
	/*
	 * Creates an empty Company Object with no instance variables set.
	 */
	public Company() {
		
	}

	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id	int - the id of the company
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Sets the id of the specific object
	 * 
	 * @param	id	int - the id of the company
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Returns the name of the specific object
	 * 
	 * @param	name	String - the name of the company
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Sets the name of the specific object
	 * 
	 * @param	name	String - the name of the company
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Returns the address of the specific object
	 * 
	 * @param	address	String - the address of the company
	 */
	public String getAddress() {
		return address;
	}
	
	/*
	 * sets the address of the specific object
	 * 
	 * @param	address	String - the address of the company
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*
	 * Returns the city of the specific object
	 * 
	 * @param	city	String - the city of where the company
	 */
	public String getCity() {
		return city;
	}
	
	/*
	 * Sets the city of the specific object
	 * 
	 * @param	city	String - the city of where the company
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	 * Returns the zipCode of the specific object
	 * 
	 * @param	zipCode	String - the zipCode of the area where the company is located
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/*
	 * Sets the zipCode of the specific object
	 * 
	 * @param	zipCode	String - the zipCode of the area where the company is located
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/*
	 * Returns the cvr of the specific object
	 * 
	 * @param	cvr	String - the cvr of the company
	 */
	public String getCvr() {
		return cvr;
	}
	
	/*
	 * Sets the cvr of the specific object
	 * 
	 * @param	cvr	String - the cvr of the company
	 */
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}
	
	/*
	 * Returns the phone number of the specific object
	 * 
	 * @param	phone	String - the number of the company
	 */
	public String getPhone() {
		return phone;
	}
	
	/*
	 * Sets the phone number of the specific object
	 * 
	 * @param	phone	String - the phone number of the company
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	 * Returns the email of the specific object
	 * 
	 * @param	email	String - the email of the company
	 */
	public String getEmail() {
		return email;
	}
	
	/*
	 * Sets the email of the specific object
	 * 
	 * @param	email	String - the email of the company
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
