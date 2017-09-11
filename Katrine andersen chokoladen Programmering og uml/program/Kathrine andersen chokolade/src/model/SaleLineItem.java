package model;
import model.Product;
/*
 * @version(06-06-2016)
 */

/*
 * Creates the instance variables id, orderId, product, quantity, price
 */
public class SaleLineItem {
	//Instance variables
	private int id; 
	private int orderId;
	private Product product;
	private int quantity;
	private double price;
	
	/*
	 * The SaleLineItem constructor, will create a new instance of SaleLineItem
	 * 
	 * @param	id			int	-	Id of the SaleLineItem
	 * @param	orderId		int	- Id from the order
	 * @param	product		product	-	A product object
	 * @param	quantity	int	-	the quantity of the product
	 * @param	price		double	-	Price of the specific sale line item 
	 */
	public SaleLineItem(int id, int orderId, Product product, int quantity, double price) {
		this.id = id;
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	/*
	 * Create an empty SaleLineItem object with no instance variables set.
	 */
	public SaleLineItem() {
		
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id		int	- Id of the specific object
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Sets the id of the specific object
	 * 
	 * @param	id		int	- id of the specific object
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Returns the orderId of the specific object
	 * 
	 * @param	orderId		int	- OrderId of the specific object
	 */
	public int getOrderId() {
		return orderId;
	}
	
	/*
	 * Sets the orderId of the specific object
	 * 
	 * @param	orderId		int	- OrderId of the specific object
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	
	/*
	 * Sets the product of the specific object
	 * 
	 * @param	product		product	- Sets a product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	
	/*
	 * Returns a product object
	 * 
	 * @param	product		product	- A product object
	 */
	public Product getProduct() {
		return product;
	}
	
	/*
	 * Returns the quantity of the specific object
	 * 
	 * @param	quantity		int	- Quantity of the specific object
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/*
	 * Sets the quantity of the specific object
	 * 
	 * @param	quantity		int	- Quantity of the specific object
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/*
	 * Returns the price of the specific object
	 * 
	 * @param	price		double	- Price of the specific object
	 */
	public double getPrice() {
		return price;
	}
	
	/*
	 * Sets the price of the specific object
	 * 
	 * @param	price		double	- Price of the specific object
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	

}
