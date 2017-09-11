package model;

/*
 * @version(25-05-2017)
 */

/*
 * Creates the instance variables id, name, price, quantity
 */
public class Ingredients {
	//Instance variables
	private int id;
	private String name;
	private double price;
	private int quantity;
	private int monthlyConsumption;
	private int shippingCost;
	private int minQuantity;
	private boolean reorder;
	private int reorderQuantity;
	
	/*
	 * The Ingredients, will create a new instance of Ingredients
	 * 
	 * @param	id		int	- Id of the Ingredietns
	 * @param 	name 	String - The name of the ingredients
	 * @param	price	double - The price of the ingredients
	 * @param	quantity int - The amount in stock of ingredients
	 */
	public Ingredients(int id, String name, double price, int quantity, int monthlyConsumption, int shippingCost,
			int minQuantity, boolean reorder, int reorderQuantity) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.monthlyConsumption = monthlyConsumption;
		this.shippingCost = shippingCost;
		this.minQuantity = minQuantity;
		this.reorder = reorder;
		this.reorderQuantity = reorderQuantity;
	}

	/*
	 * Create an empty ingredients object with no instance variables set.
	 */
	public Ingredients() {

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
	 * Returns the name of the specific object
	 * 
	 * @param	name		String	- fName of the person
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Returns the name of the specific object
	 * 
	 * @param	name		String	- fName of the person
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Returns the price of the specific object
	 * 
	 * @param	price	double	- The price of the ingredients
	 */
	public double getPrice() {
		return price;
	}
	/*
	 * Sets the price of the specific object
	 * 
	 * @param	price	double	- The price of the ingredients
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/*
	 * Returns the quantity of the specific object
	 * 
	 * @param	quantity	int	- The amount in stock of ingredients
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/*
	 * Returns the quantity of the specific object
	 * 
	 * @param	quantity	int	- The amount in stock of ingredients
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(int monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	public int getMinQuantity() {
		return minQuantity;
	}
	
	public int getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(int shippingCost) {
		this.shippingCost = shippingCost;
	}
	
	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public boolean isReorder() {
		return reorder;
	}

	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}

	public int getReorderQuantity() {
		return reorderQuantity;
	}


	public void setReorderQuantity(int reorderQuantity) {
		this.reorderQuantity = reorderQuantity;
	}
	
	
	
}
