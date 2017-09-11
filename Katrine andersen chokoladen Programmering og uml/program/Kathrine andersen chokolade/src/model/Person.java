package model;
/*
 * @version(25-05-2016)
 */

/*
 * Creates the instance variables id, fName, lName, address, city, zipCode, phone, email
 */
public class Person {
	//Instance variables
	private int id;
	private String fName;
	private String lName;
	private String address;
	private String city;
	private String zipCode;
	private String phone;
	private String email;
	
	
	/*
	 * The Person constructor, will create a new instance of Person, and it is a super class
	 * 
	 * @param	id		int	- Id of the person
	 * @param 	fName 	String - The first name of the person
	 * @param	lName	String - The last name of the person
	 * @param	address String - The address of where the person lives
	 * @param	city 	String - The name of the city, where the person lives
	 * @param 	zipCode	String - The zipCode for the given area where the person lives
	 * @param	Phone	String - The phone number of the person
	 * @param	email	String - The email to the person
	 */
	public Person(int id, String fName, String lName, String address, String city, String zipCode, String phone,
			String email) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.city = city;
		this.zipCode = zipCode;
		this.phone = phone;
		this.email = email;
	}
	
	/*
	 * Create an empty Person object with no instance variables set.
	 */
	public Person() {
		
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id		int	- Id of the person
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Sets the id of the specific object
	 * 
	 * @param	id		int	- Id of the person
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Returns the fName of the specific object
	 * 
	 * @param	fName		String	- fName of the person
	 */
	public String getfName() {
		return fName;
	}
	
	/*
	 * Sets the fName of the specific object
	 * 
	 * @param	fName		String	- fName of the person
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}
	
	/*
	 * Returns the lName of the specific object
	 * 
	 * @param	lName		String	- lName of the person
	 */
	public String getlName() {
		return lName;
	}
	
	/*
	 * Sets the lName of the specific object
	 * 
	 * @param	lName		String	- lName of the person
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}
	
	/*
	 * Returns the address of the specific object
	 * 
	 * @param	address		String	- address of the person
	 */
	public String getAddress() {
		return address;
	}
	
	/*
	 * Sets the address of the specific object
	 * 
	 * @param	address		String	- address of where the person lives
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*
	 * Returns the city of the specific object
	 * 
	 * @param	city		String	- city of where the person lives
	 */
	public String getCity() {
		return city;
	}
	
	/*
	 * Sets the city of the specific object
	 * 
	 * @param	city		String	- city of where the person lives
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	 * Returns the zipCode of the specific object
	 * 
	 * @param	zipCode		String	- zipCode of there area where the person lives
	 */
	public String getZipCode() {
		return zipCode;
	}
	
	/*
	 * Sets the zipCode of the specific object
	 * 
	 * @param	zipCode		String	- zipCode of there area where the person lives
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	/*
	 * Returns the phone number of the specific object
	 * 
	 * @param	phone		String	- Phone number of a person
	 */
	public String getPhone() {
		return phone;
	}
	
	/*
	 * Sets the phone number of the specific object
	 * 
	 * @param	phone		String	- Phone number of a person
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/*
	 * Returns the email of the specific object
	 * 
	 * @param	email		String	- Phone number of a person
	 */
	public String getEmail() {
		return email;
	}
	
	/*
	 * Returns the email of the specific object
	 * 
	 * @param	email		String	- Phone number of a person
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
