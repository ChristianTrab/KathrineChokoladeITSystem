package ctr;

import model.Order;
import model.Product;
import model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import db.DBConnection;
import db.DBProduct;
/*
 * @author 
 * @version (06-06-2016)
 */

/*
 *  Creates an object of DBProduct and RecipeCtr.
 */
public class ProductCtr {
	private DBProduct dbProduct = new DBProduct();
	private RecipeCtr recipeCtr = new RecipeCtr();
	
	/*
	 *  Creates an object of an empty product.
	 *  @return	- Returns a product object
	 */
	public Product createEmptyProduct() {
		Product p = new Product(0, null, 0, 0, null, null, 0, 0);
		{
			return p;
		}
	}
	
	/*
	 * The Update Product Object Method, this method will update the current product with new values.
	 * 
	 * @param	product		Product 	- Product Object from model.product
	 * @param	id			int			- Id of the product
	 * @param	name		String		- Name of the product
	 * @param	price		double		- price of the product
	 * @param	totalQty	int			- total quantity in stock of the product
	 * @param	description	String		- description of the product
	 * @param	details		String		- details of the product
	 * @param	boxQuantity	int			- boxquantity sizes
	 * @return	- Returns the updated product object
	 */
	public Product updateProductObject(Product product) {
		Product p = new Product();
		p.setId(product.getId());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setTotalQty(product.getTotalQty());
		p.setDescription(product.getDescription());
		p.setDetails(product.getDetails());
		p.setBoxQuantity(product.getBoxQuantity());
		p.setRecipeId(product.getRecipeId());
		return p;
	}

	/*
	 * The Update Product Object in Database Method, this method will update the current product with new values.
	 * 
	 * @param	product		Product 	- Product Object from model.product
	 * @param	id			int			- Id of the product
	 * @param	name		String		- Name of the product
	 * @param	price		double		- price of the product
	 * @param	totalQty	int			- total quantity in stock of the product
	 * @param	description	String		- description of the product
	 * @param	details		String		- details of the product
	 * @param	boxQuantity	int			- boxquantity sizes
	 * 
	 */
	public void updateProductDB(Product product) throws SQLException {
		Product p = updateProductObject(product);
		dbProduct.updateProduct(p);
	}
	
	/*
	 * The Add Product to Database Method, this method will add a product to the database.
	 * 
	 * @param	product		Product 	- Product Object from model.product
	 * @param	id			int			- Id of the product
	 * @param	name		String		- Name of the product
	 * @param	price		double		- price of the product
	 * @param	totalQty	int			- total quantity in stock of the product
	 * @param	description	String		- description of the product
	 * @param	details		String		- details of the product
	 * @param	boxQuantity	int			- boxquantity sizes
	 * 
	 */
	public void addProductToDB(Product product) throws SQLException {
		Product p = updateProductObject(product);
		dbProduct.addProduct(p);
	}
	
	/*
	 * This is the Find Product by Id Method
	 * @param	id			Int 		- Which is calling findById method in db.DBProduct
	 * @return	- Returns the product found by id
	 */
	public Product findProductById(int id) throws SQLException {
		return dbProduct.findById(id);
	}
	
	/*
	 * This is the Find Product by Name Method
	 * @param	name		String 		- Which is calling findByName method in db.DBProduct
	 * @return	- Returns a linkedlist of products found by name
	 */
	public LinkedList<Product> findProductByName(String name) throws SQLException {
		LinkedList<Product> list = dbProduct.findByName(name);
		list.parallelStream()
		.sorted();
		return list;
	}
	
	/*
	 * This is the Delete Product Method
	 * @param	id			Int 		- Which is calling findById method in db.DBProduct and then removes it from the database.
	 */
	public void deleteProduct(int id) throws SQLException {
		Product p = findProductById(id);
		dbProduct.deleteProduct(p);
	}
	
	/*
	 * This is the get all Products in order Method
	 * @param	list		LinkedList<Product>		- Which is listing all products by id in a linkedlist
	 * @return	- Returns a linkedlist of all product in a order
	 */
	public LinkedList<Product> getAllProductsInOrder() {
		DBProduct list = dbProduct;
		return list;
	}

}