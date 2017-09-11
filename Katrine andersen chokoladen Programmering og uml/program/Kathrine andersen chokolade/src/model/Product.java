package model;

/*
 * @version(25-05-2017)
 */

/*
 * Creates the instance variables id, name, price, totalQty, description, details, boxQuantity
 */
public class Product {
	//instance variables
	private int id;
	private String name;
	private double price;
	private int totalQty;
	private String description;
	private String details;
	private int boxQuantity;
	private int recipeId;
	
	/*
	 * The Product constructor, will create a new instance of Product.
	 * 
	 * @param	id		int	- Id of the Product
	 * @param 	name 	String - The name of the name
	 * @param	price	double - The price of the product
	 * @param	totalQty int - The totalQty of the Product
	 * @param	description 	String - The description of the Product
	 * @param 	details	String - The details about the product
	 * @param	boxQuantity	int - The boxQuantity of the Product
	 */
	public Product(int id, String name, double price, int totalQty, String description, String details,
			int boxQuantity, int recipeId) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.totalQty = totalQty;
		this.description = description;
		this.details = details;
		this.boxQuantity = boxQuantity;
		this.recipeId = recipeId;
	}
	
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	/*
	 * Create an empty Product object with no instance variables set.
	 */
	public Product() {
		
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @Return	id		int	- Id of the person
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
	 * Returns the name of the specific object
	 * 
	 * @param 	name 	String - The name of the name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Sets the name of the specific object
	 * 
	 * @param 	name 	String - The name of the name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Returns the price of the specific object
	 * 
	 * @param	price	double - The price of the product
	 */
	public double getPrice() {
		return price;
	}
	
	/*
	 * Sets the price of the specific object
	 * 
	 * @param	price	double - The price of the product
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/*
	 * Returns the totalQty of the specific object
	 * 
	 * @param	totalQty int - The totalQty of the Product
	 */
	public int getTotalQty() {
		return totalQty;
	}
	
	/*
	 * Sets the totalQty of the specific object
	 * 
	 * @param	totalQty int - The totalQty of the Product
	 */
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	
	/*
	 * Returns the description of the specific object
	 * 
	 * @param	description 	String - The description of the Product
	 */
	public String getDescription() {
		return description;
	}
	
	/*
	 * Sets the description of the specific object
	 * 
	 * @param	description 	String - The description of the Product
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Returns the details of the specific object
	 * 
	 * @param 	details	String - The details about the product
	 */
	public String getDetails() {
		return details;
	}
	
	/*
	 * Sets the details about the specific object
	 * 
	 * @param 	details	String - The details about the product
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	/*
	 * Returns the boxQuantity of the specific object
	 * 
	 * @param	boxQuantity	int - The boxQuantity of the Product
	 */
	public int getBoxQuantity() {
		return boxQuantity;
	}
	
	/*
	 * Sets the boxQuantity of the specific object
	 * 
	 * @param	boxQuantity	int - The boxQuantity of the Product
	 */
	public void setBoxQuantity(int boxQuantity) {
		this.boxQuantity = boxQuantity;
	}
	
	

}
