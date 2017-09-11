package model;

/*
 * @version(25-05-2017)
 */

/*
 * Creates the instance variables id, totalPrice, tax, date, status, discountPrice, customer, company, employee
 */
public class Order {
	//Instance variables
	private int id;
	private double totalPrice;
	private double tax;
	private String date;
	private int status;
	private double discountPrice;
	private Customer customer;
	private Company company;
	private Employee employee;
	
	
	/*
	 *Creates a new empty (null) Order object 
	 */
	public Order() {
		
	}
	
	/*
	 * Creates a new Order object with all variables
	 * 
	 * @param id		 	int			-	The id of the Order
	 * @param totalPrice 	double		-	The totalPrice of the Order
	 * @param tax		 	double		-	The tax of the Order
	 * @param date		 	String		-	The date when the Order where created
	 * @param status	 	int			-	The status of the Order done/not done
	 * @param discountPrice	double		-	The discountPrice of the Order
	 * @param customer 		Customer	-	The Customer to the Order
	 * @param company		Company		-	The Company that made the Order
	 * @param emplyee		Employee	-	The Employee who created the Order for the customer
	 */
	public Order(int id, double totalPrice, double tax, String date, int status, double discountPrice, Customer customer, Company company, Employee employee) {
		this.id = id;
		this.totalPrice = totalPrice;
		this.tax = tax;
		this.date = date;
		this.status = status;
		this.discountPrice = discountPrice;
		this.customer = customer;
		this.company = company;
		this.employee = employee;
	}

	
	/*
	 * Returns the company of the specific object
	 * 
	  * @param company		Company		-	The Company that made the Order
	 */
	public Company getCompany() {
		return company;
	}

	/*
	 * Sets the company of the specific object
	 * 
	 * @param company		Company		-	The Company that made the Order
	 */
	public void setCompany(Company company) {
		this.company = company;
	}

	/*
	 * Returns the employee of the specific object
	 * 
	 * @param emplyee		Employee	-	The Employee who created the Order for the customer
	 */
	public Employee getEmployee() {
		return employee;
	}

	/*
	 * Sets the employee of the specific object
	 * 
	 * @param emplyee		Employee	-	The Employee who created the Order for the customer
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/*
	 * Sets the customer of the specific object
	 * 
	 * @param customer 		Customer	-	The Customer to the Order
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/*
	 * Returns the customer of the specific object
	 * 
	 * @param customer 		Customer	-	The Customer to the Order
	 */
	public Customer getCustomer() {
		return customer;
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
	 * Returns the totalPrice of the specific object
	 * 
	 * @param totalPrice 	double		-	The totalPrice of the Order
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/*
	 * Sets the totalPrice of the specific object
	 * 
	 * @param totalPrice 	double		-	The totalPrice of the Order
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	/*
	 * Returns the tax of the specific object
	 * 
	 * @param tax		 	double		-	The tax of the Order
	 */
	public double getTax() {
		return tax;
	}
	
	/*
	 * Sets the tax of the specific object
	 * 
	 * @param tax		 	double		-	The tax of the Order
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}
	
	/*
	 * Returns the date of the specific object
	 * 
	 * @param date		 	String		-	The date when the Order where created
	 */
	public String getDate() {
		return date;
	}
	
	/*
	 * Sets the date of the specific object
	 * 
	 * @param date		 	String		-	The date when the Order where created
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/*
	 * Returns the status of the specific object
	 * 
	 * @param status	 	int			-	The status of the Order done/not done
	 */
	public int getStatus() {
		return status;
	}
	
	/*
	 * Sent the status of the specific object
	 * 
	 * @param status	 	int			-	The status of the Order done/not done
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/*
	 * Returns the discountPrice of the specific object
	 * 
	 * @param discountPrice	double		-	The discountPrice of the Order
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	
	/*
	 * Sets the discountPrice of the specific object
	 * 
	 * @param discountPrice	double		-	The discountPrice of the Order
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
}
