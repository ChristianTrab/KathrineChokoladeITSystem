package ctr;

import model.SaleLineItem;
import db.DBSaleLineItem;
import model.Product;
import model.Order;

import java.sql.SQLException;
import java.util.LinkedList;
/*
 * @author 
 * @version (06-06-2017)
 */

/*
 * Creates an object of DBSaleLineItem
 * Creates an object of ProductCtr
 * 
 */
public class SaleLineItemCtr {
	private DBSaleLineItem dbSaleLineItem = new DBSaleLineItem();
	private ProductCtr productCtr = new ProductCtr();
	private OrderCtr orderCtr = new OrderCtr();
	
	
	public SaleLineItemCtr(){
		}
	/*
	 * This is the Create Sale Line Item Method, where it creates an sale line item with the order and product and returns it as "salelineitems"
	 * 
	 * Creates a new object of SaleLineItem
	 * 
	 * @param 	product			Product		- Product object
	 * @param 	order			Order		- Order object
	 * @return	- Returns the created salelineitem object
	 */
	public SaleLineItem createSaleLineItem(Product product, Order order, int quantity) throws SQLException{
		SaleLineItem saleLineItem = new SaleLineItem();
		saleLineItem.setProduct(product);
		saleLineItem.setOrderId(order.getId());
		saleLineItem.setQuantity(quantity);
		saleLineItem.setPrice(quantity*product.getPrice());
		return saleLineItem;		
	}
	
	/*
	 * This is the Add Sale Line Item to the Database method, this method with add an item to the salelineitem and add it to the database.
	 * 
	 * @param	sli			SaleLineItem	- SaleLineItem object
	 */
	
	public void addSaleLineItemToDB(SaleLineItem sli) throws SQLException{
		dbSaleLineItem.addSaleLineItem(sli);
	}
	
	/*
	 * This is the Delete Sale Line Item method, this method will delete the selected item from the SaleLineItem.
	 * 
	 * @param	sli			SaleLineItem	- SaleLineItem object
	 */
	
	public void deleteSaleLineItem(Order order, SaleLineItem sli) throws SQLException{
		double totalPrice = order.getTotalPrice() - sli.getPrice();
		double tax = totalPrice*0.25;
		orderCtr.updateOrderInDB(order, totalPrice, tax, order.getDate(), order.getStatus(), order.getDiscountPrice(), order.getCustomer(), order.getCompany(), order.getEmployee());
		dbSaleLineItem.deleteSaleLineItem(sli);
	}
	
	/*
	 * This is the Update Sale Line Item method, this method will update the SaleLineItem
	 * 
	 * @param sli			SaleLineItem	- SaleLineItem object
	 * 
	 */
	
	public void updateSaleLineItem(Order order, SaleLineItem sli, int quantity) throws SQLException{
		sli.setQuantity(quantity);
		sli.setPrice(quantity * sli.getProduct().getPrice());
		
		orderCtr.UpdateOrderTotalPriceAndTax(order, sli.getPrice());
		
		dbSaleLineItem.updateSaleLineItem(sli);
	}

	/*
	 * This is the Find Sale Line Item method, this method will search for an item by id
	 * 
	 * @param	search			int	-	Which is calling findById method in db.dbSaleLineitem and returning a SaleLineItem
	 * @return	- Returns a salelineitem found by a search
	 */
	
	public SaleLineItem findSaleLineItem(int search) throws SQLException {
		SaleLineItem saleLineItem = dbSaleLineItem.findById(search);
		return saleLineItem;
		
	}
	
	/*
	 * This is the Get All Product in Order, this method will list product by id.
	 * 
	 * @param	list		LinkedList<SaleLineItem>		- Which is listing all Sale Line Items by id in a linkedlist.
	 * @return	- Returns a linkedlist of all product in a order
	 */

	public LinkedList<SaleLineItem> getAllProductsInOrder(int orderId) throws SQLException {
		LinkedList<SaleLineItem> list = dbSaleLineItem.getAllProductsInOrder(orderId);
		return list;
	}
	
}
