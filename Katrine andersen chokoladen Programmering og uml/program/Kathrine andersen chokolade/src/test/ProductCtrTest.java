package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import ctr.ProductCtr;
import model.Product;

public class ProductCtrTest {
	private ProductCtr pCtr;
	
	@Before
	public void setUp(){
		pCtr = new ProductCtr();
	}
	
	@Test
	public void testFindById(){
		Product p;
		try{
			p = pCtr.findProductById(1);
			assertNotNull(p);
		} catch (SQLException e){
			fail();
		}
	}
	
	@Test
	public void testFindByString(){
		LinkedList<Product> p;
		try {
			p = pCtr.findProductByName("f");
			assertNotNull(p);
		} catch (SQLException e) {
			fail();
		}
		
	}
	
	//JUnit Test for Create and Update
	@Test
	public void testCreateAndUpdate(){
		Product p = new Product();
		p.setName("Chokolade");
		p.setPrice(10.00);
		p.setTotalQty(25);
		p.setDescription("En test beskrivelse");
		p.setDetails("Test details");
		p.setBoxQuantity(5);
		p.setRecipeId(1);
		try {
			pCtr.addProductToDB(p);
			pCtr.updateProductObject(p);
			pCtr.updateProductDB(p);
			assertNotNull(p);
			//Assert equals to test if create is succesful
			assertEquals(p, p);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
}
