package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//Test Suite to run all JUnit Tests
@RunWith(Suite.class)
@Suite.SuiteClasses({
	CustomerCtrTest.class,
	IngredientsCtrTest.class,
	OrderCtrTest.class,
	ProductCtrTest.class,
	RecipeCtrTest.class
})

public class TestSuite {


}
