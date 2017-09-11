package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import ctr.IngredientsCtr;
import model.Ingredients;

public class IngredientsCtrTest {
	private IngredientsCtr iCtr;
	
	@Before
	public void setup(){
		iCtr = new IngredientsCtr();
	}
	
	
	@Test
	public void testFindById(){
		Ingredients i;
		try{
			i = iCtr.findIngredientsById(1);
			assertNotNull(i);
		} catch (SQLException e){
			fail();
		}
	}
	
	@Test
	public void testFindByString(){
		LinkedList<Ingredients> i;
		try {
			i = iCtr.findIngredientsByName("a");
			assertNotNull(i);
		} catch (SQLException e) {
			fail();
		}
		
	}
	
	//JUnit Test for Create
	@Test
	public void testCreate() {
	Ingredients i = new Ingredients();
	i.setName("Vanilje");
	i.setPrice(10.00);
	i.setQuantity(50);
	i.setMonthlyConsumption(0);
	i.setShippingCost(25);
	i.setMinQuantity(10);
	i.setReorder(true);
	i.setReorderQuantity(30);
		try {
			iCtr.updateIngredientsObject(i, i.getId(), i.getName(), i.getPrice(), i.getQuantity(), i.getMonthlyConsumption(), i.getShippingCost(), i.getMinQuantity(), i.isReorder(), i.getReorderQuantity());
			iCtr.addIngredientsDB(i);
			assertNotNull(i);
			//Assert equals to test if create is succesful
			assertEquals(i, i);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		
	}

}
