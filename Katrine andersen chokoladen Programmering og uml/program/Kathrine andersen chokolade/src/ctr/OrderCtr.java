package ctr;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.stream.Stream;

import db.DBOrder;
import model.Order;
import model.Product;
import model.SaleLineItem;
import model.Company;
import model.Customer;
import model.Employee;
/*
 * @author 
 * @version (06-06-2016)
 */

/*
 * Creates an object of DBOrder
 * Creates an object of SaleLineItemController
 */

public class OrderCtr {
	private DBOrder dbOrder;
	private SaleLineItemCtr saleLineItemCtr;
	
	/*
	 *  Creates an object of DBOrder.
	 */
	public OrderCtr() {
		dbOrder = new DBOrder();
	}
	
	/*
	 *  Creates an object of an empty order.
	 *  @return	- Returns a empty order object
	 */
	public Order createEmptyOrder() throws SQLException {
		Order emptyOrder = new Order();
		Order order = dbOrder.addEmptyOrder(emptyOrder);
		return order;
	}
	
	/*
	 * Creates an new order, sets customer on the object and returns the Order.
	 * 
	 * @param customer		Customer	- Customer Object from model.customer
	 * @return	- Returns a order object
	 */
	public Order createOrder(Customer customer) {
		Order order = new Order();
		order.setCustomer(customer);
		return order;
	}
	
	/*
	 * The Add Order To DB Method, this method will add the current order to the database.
	 * 
	 * @param	o			Order		- Order object
	 * @param	totalPrice	double		- totalPrice of the order
	 * @param	tax			double		- tax price of the order
	 * @param	date		String		- date the order was placed/DELIEVERED??
	 * @param	status		int			- Status of the order, how far in the progress.
	 * @param	discountPrice	double 	- discountPrice of the order.
	 * @param	customer	Customer	- Customer object from model.customer
	 * @param	company		Company		- Company object from model.company
	 * @param	employee	Employee	- Employee object from model.employee
	 */
	
	public void addOrderToDB(Order o, double totalPrice, double tax, String date, int status, double discountPrice, Customer customer, Company company, Employee employee) throws SQLException {
		Order order = updateOrder(o, totalPrice, tax, date, status, discountPrice, customer, company, employee);
		dbOrder.addOrder(order); //Skal ændres så den sender de rigtige oplysninger afsted i stedet for null
	}
	/*
	 * This is the Delete Order Method, this method will delete the selected order.
	 * @param	o			Order 		- Order Object
	 */
	public void deleteOrder(Order o) throws SQLException {
		dbOrder.deleteOrder(o);
	}
	
	/*
	 * The Update Order In DB Method, this method will update the current order in the database.
	 * 
	 * @param	o			Order		- Order object
	 * @param	totalPrice	double		- totalPrice of the order
	 * @param	tax			double		- tax price of the order
	 * @param	date		String		- date the order was placed/DELIEVERED??
	 * @param	status		int			- Status of the order, how far in the progress.
	 * @param	discountPrice	double 	- discountPrice of the order.
	 * @param	customer	Customer	- Customer object from model.customer
	 * @param	company		Company		- Company object from model.company
	 * @param	employee	Employee	- Employee object from model.employee
	 */
	public void updateOrderInDB(Order o, double totalPrice, double tax, String date, int status, double discountPrice, Customer customer, Company company, Employee employee) throws SQLException {
		Order order = updateOrder(o, totalPrice, tax, date, status, discountPrice, customer, company, employee);
		dbOrder.updateOrder(order);
	}
	
	/*
	 * The Update Order Method, this method will update the current order.
	 * 
	 * @param	o			Order		- Order object
	 * @param	totalPrice	double		- totalPrice of the order
	 * @param	tax			double		- tax price of the order
	 * @param	date		String		- date the order was placed/DELIEVERED??
	 * @param	status		int			- Status of the order, how far in the progress.
	 * @param	discountPrice	double 	- discountPrice of the order.
	 * @param	customer	Customer	- Customer object from model.customer
	 * @param	company		Company		- Company object from model.company
	 * @param	employee	Employee	- Employee object from model.employee
	 * @return	- Return the updated order object
	 */
	public Order updateOrder(Order o, double totalPrice, double tax, String date, int status, double discountPrice, Customer customer, Company company, Employee employee) {
		Order order = o;
		order.setTotalPrice(totalPrice);
		order.setTax(tax);
		order.setDate(date);
		order.setStatus(status);
		order.setDiscountPrice(discountPrice);
		order.setCustomer(customer);
		order.setCompany(company);
		order.setEmployee(employee);
		return order;		
	}

	/*
	 * This is the Find Order By Id Method
	 * @param	search		Int 		- Which is calling findById method in db.DBOrder and returning an order.
	 * @return	- Returns a order object found by id
	 */
	public Order findOrderById(int search) throws SQLException {
		Order order = dbOrder.findById(search);
		return order;
	}
	
	/*
	 * This is the Find Order By String Method
	 * @param	search		String 		- Which is calling findByString method in db.DBOrder and returning an order.
	 * @return	- Returns a order object found by name
	 */
	public LinkedList<Order> findOrderByString(String search) throws SQLException{
		LinkedList<Order> order = dbOrder.findByString(search);
		return order;
	}

	/*
	 * This is the get all orders method
	 * @return 	- Return a linkedlist of all the order objects in the database
	 */
	public LinkedList<Order> getAllOrders() throws SQLException {
		LinkedList<Order> list = dbOrder.getAllOrders();
		list.parallelStream()
		.sorted();
		return list;
	}
	
	/*
	 * This is the Get All Active Orders Method
	 * @return	- Returns a linkedlist of all the active orders in the database.
	 */
	
	public LinkedList<Order> getAllActiveOrders() throws SQLException {
		LinkedList<Order> list = dbOrder.getAllActiveOrders();
		list.parallelStream()
		.sorted();
		return list;
	}

	/*
	 * This is the Get All Active Orders Method
	 * @param	int 	Order id for the searched order
	 * @return	- Returns a linkedlist of all product in a order
	 */
	public LinkedList<SaleLineItem> getAllProductsInOrder(int orderId) throws SQLException {
		saleLineItemCtr = new SaleLineItemCtr();
		LinkedList<SaleLineItem> list = saleLineItemCtr.getAllProductsInOrder(orderId);
		list.parallelStream()
		.sorted();
		return list;
	}
	
	/*
	 * A method to set the price to 0, so the salelineitem can be updated
	 * @param	order	Order	- Order object
	 */
	public void updatePriceToZeroForUpdatingSaleLineItem(Order order) {
		order.setTotalPrice(0);
	}
	
	/*
	 * Update orders total and tax total price
	 * @param	order		Order	- Order object
	 * @param	sliPrice	double	- Sale line item price
	 */
	public void UpdateOrderTotalPriceAndTax(Order order, double sliPrice) throws SQLException {
		double totalPrice = order.getTotalPrice() + sliPrice;
		order.setTotalPrice(totalPrice - order.getDiscountPrice());
		order.setTax(order.getTotalPrice()*0.25);
		updateOrderInDB(order, order.getTotalPrice(), order.getTax(), order.getDate(), order.getStatus(), order.getDiscountPrice(), order.getCustomer(), order.getCompany(), order.getEmployee());
	}
	
	/*
	 * Update discount on a order
	 * @param	o		Order	- Order object
	 * @param	discount	double	- The amount of discount
	 */
	public void updateDiscount(Order o, double discount) throws SQLException{
		o.getDiscountPrice();
		o.setTotalPrice(o.getTotalPrice()+o.getDiscountPrice());
		o.setDiscountPrice(discount);
		o.setTotalPrice(o.getTotalPrice()-o.getDiscountPrice());
		o.setTax(o.getTotalPrice()*0.25);
		updateOrderInDB(o, o.getTotalPrice(), o.getTax(), o.getDate(), o.getStatus(), o.getDiscountPrice(), o.getCustomer(), o.getCompany(), o.getEmployee());
	}
	
	/*
	 * A method to end the sale 
	 * @param	o		Order	- Order object
	 */
	public void endSale(Order o) throws SQLException{
		dbOrder.endOrder(o);
	}
}
