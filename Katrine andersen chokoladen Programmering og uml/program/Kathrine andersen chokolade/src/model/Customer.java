package model;

/*
 * @version(06-06-2016)
 */

/*
 * Creates the instance variables type, category, payment, cvr, companyName, PersonDB_personId.
 * and it extends to Person which is a super class.
 */
public class Customer extends Person{

	private String type;
	private int category;
	private String payment;
	private String cvr;
	private String companyName;
	private int PersonDB_personId;
	
	/*
	 * The Customer constructor will create a new instance of Customer
	 * 
	 * @param	id		int		- Id of the person
	 * @param 	fName 	String 	- The first name of the person
	 * @param	lName	String 	- The last name of the person
	 * @param	address String 	- The address of where the person lives
	 * @param	city 	String 	- The name of the city, where the person lives
	 * @param 	zipCode	String 	- The zipCode for the given area where the person lives
	 * @param	Phone	String 	- The phone number of the person
	 * @param	email	String 	- The email to the person
	 * @param	type	String 	- The type of customer
	 * @param 	category	int - the category of the customer
	 * @param 	payment	String 	- The payment of the customer
	 * @param	cvr		String	- The cvr of where the customer works
	 * @param	companyName	String	- The name of the company where the customer works
	 * @param	personDB_personId	int	- The id of the customer from the person class
	 */
	public Customer() {
		//Super class Person
		super(0, null, null, null, null, null, null, null); //Int skal rettes til, så den selv finder id fra databsen
		this.type = null;
		this.category = 0;
		this.payment = null;
		this.cvr = null;
		this.companyName = null;
		this.PersonDB_personId = 0;
	}
	
	/*
	 * Returns the type of the specific object
	 * 
	 * @param	type		String	- type of the customer
	 */
	public String getType() {
		return type;
	}
	
	/*
	 * Sets the type of the specific object
	 * 
	 * @param	type		String	- type of the customer
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/*
	 * Returns the category of the specific object
	 * 
	 * @param	category		int	- category of the customer
	 */
	public int getCategory() {
		return category;
	}

	/*
	 * Sets the category of the specific object
	 * 
	 * @param	category		int	- category of the customer
	 */
	public void setCategory(int category) {
		this.category = category;
	}
	
	
	/*
	 * Returns the payment of the specific object
	 * 
	 * @param	payment		String	- payment of the customer
	 */
	public String getPayment() {
		return payment;
	}
	
	/*
	 * Sets the payment of the specific object
	 * 
	 * @param	payment		String	- payment of the customer
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	/*
	 * Returns the cvr of the specific object
	 * 
	 * @param	cvr		String	- cvr of the company where the customer works
	 */
	public String getCvr() {
		return cvr;
	}
	
	/*
	 * Sets the payment of the specific object
	 * 
	 * @param	cvr		String	- cvr of the company where the customer works
	 */
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}
	
	/*
	 * Returns the companyName of the specific object
	 * 
	 * @param	companyName		String	- name of the company where the customer works
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/*
	 * Sets the companyName of the specific object
	 * 
	 * @param	companyName		String	- name of the company where the customer works
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/*
	 * Returns the PersonDB_personId of the specific object
	 * 
	 * @param	personDB_personId	int	- The id of the customer from the person class
	 */
	public int getPersonDB_personId() {
		return PersonDB_personId;
	}
	
	
	/*
	 * Returns the PersonDB_personId of the specific object
	 * 
	 * @param	personDB_personId	int	- The id of the customer from the person class
	 */
	public void setPersonDB_personId(int personDB_personId) {
		PersonDB_personId = personDB_personId;
	}	
}
