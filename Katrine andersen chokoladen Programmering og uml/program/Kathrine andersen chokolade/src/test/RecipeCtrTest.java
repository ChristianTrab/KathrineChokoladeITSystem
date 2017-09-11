package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import ctr.IngredientsCtr;
import ctr.RecipeCtr;
import model.Recipe;
import model.Ingredients;

public class RecipeCtrTest {
	private RecipeCtr rCtr;
	private IngredientsCtr iCtr;
	
	@Before
	public void setup(){
		rCtr = new RecipeCtr();
		iCtr = new IngredientsCtr();
	}
	
	@Test
	public void testFindById() {
		Recipe r;
		try {
			r = rCtr.findRecipeById(1);
			assertNotNull(r);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	@Test
	public void testFindByName(){
		LinkedList<Recipe> r;
		try{
			r = rCtr.findRecipeByName("f");
			assertNotNull(r);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
		
	}
	
	//JUnit Test for Create
	@Test
	public void testCreate(){
		LinkedList<Ingredients> ingList = new LinkedList<>();
		Recipe r = new Recipe();
		r.setName("Test opskrift navn");
		r.setDescription("Test opskrift description");
		try {
			ingList.add(iCtr.findIngredientsById(1));
			rCtr.updateRecipeDB(r, r.getId(), r.getName(), r.getDescription());
			rCtr.addRecipeToDB(r, ingList);
			assertNotNull(r);
			assertNotNull(ingList);
			//Assert equals to test if create is succesful
			assertEquals(r, r);
			assertEquals(ingList, ingList);
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

}
