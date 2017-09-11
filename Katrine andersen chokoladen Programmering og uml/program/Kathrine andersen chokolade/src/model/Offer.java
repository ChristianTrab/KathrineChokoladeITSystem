package model;

/*
 * @version(25-05-2017)
 */


/*
 * Creates the instance variables id, startDate, endDate, discountPrice, totalPrice
 */
public class Offer {
	//Instance variables
	private int id;
	private String startDate;
	private String endDate;
	private double discountPrice;
	private double totalPrice;
	
	/*
	 * The Offer constructor, will create a new instance of Offer
	 * 
	 * @param	id		int	- Id of the Offer
	 * @param startDate String - The startDate of the Offer
	 * @param endDate	String - The endDate of the Offer
	 * @param discountPrice double - the discountPrice of the Offer
	 * @param totalPrice	double - the totalPrice with the Offer
	 */
	public Offer(int id, String startDate, String endDate, double discountPrice, double totalPrice) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discountPrice = discountPrice;
		this.totalPrice = totalPrice;
	}
	
	/*
	 * Create an empty Offer object with no instance variables set.
	 */
	public Offer() {
		
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
	 * Returns the startDate of the specific object
	 * 
	 * @param startDate String - The startDate of the Offer
	 */
	public String getStartDate() {
		return startDate;
	}
	
	/*
	 * Sets the startDate of the specific object
	 * 
	 * @param startDate String - The startDate of the Offer
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	/*
	 * Returns the endDate of the specific object
	 * 
	 * @param endDate String - The endDate of the Offer
	 */
	public String getEndDate() {
		return endDate;
	}
	
	/*
	 * Sets the endDate of the specific object
	 * 
	 * @param endDate String - The endDate of the Offer
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	/*
	 * Returns the discountPrice of the specific object
	 * 
	 * @param discountPrice double - the discountPrice of the Offer
	 */
	public double getDiscountPrice() {
		return discountPrice;
	}
	
	/*
	 * Sets the discountPrice of the specific object
	 * 
	 * @param discountPrice double - the discountPrice of the Offer
	 */
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	
	/*
	 * Returns the totalPrice of the specific object
	 * 
	 * @param totalPrice	double - the totalPrice with the Offer
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	/*
	 * Sets the totalPrice of the specific object
	 * 
	 * @param totalPrice	double - the totalPrice with the Offer
	 */
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	

}
