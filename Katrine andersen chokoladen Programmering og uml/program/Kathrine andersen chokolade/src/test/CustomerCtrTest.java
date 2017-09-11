package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import ctr.CustomerCtr;
import model.Customer;

public class CustomerCtrTest {
	private CustomerCtr cCtr;
	
	@Before
	public void setup(){
		cCtr = new CustomerCtr();
	}
	
	@Test
	public void testFindById() {
		Customer c;
		try{
			c = cCtr.findCustomerById(1);
			assertNotNull(c);
		} catch (SQLException e){
			fail();
		}
	}
	
	@Test
	public void testFindByString(){
		LinkedList<Customer> c;
		try {
			c = cCtr.findCustomerByString("j");
			assertNotNull(c);
		} catch (SQLException e) {
			fail();
		}
	}
	
	//JUnit Test for Create and Update
	@Test
	public void testCreateAndUpdate(){
		try {
			Customer c = cCtr.createEmyptyCustomer();
			cCtr.addCustomerToDB(c, c.getId(), "Mark", 
					"Pedersen", "Testadresse 12", "Testby", 
					"1234", "12345678", "testmail@test.com", 
					"Privat", 1, "1234", "1234", "Null");
			assertNotNull(c);
			//Assert equals to test if create is succesful
			assertEquals(c, c);
			assertEquals(c.getId(), c.getId());
			assertEquals("Mark", c.getfName());
			assertEquals("Pedersen", c.getlName());
			assertEquals("Testadresse 12", c.getAddress());
			assertEquals("Testby", c.getCity());
			assertEquals("1234", c.getZipCode());
			assertEquals("12345678", c.getPhone());
			assertEquals("testmail@test.com", c.getEmail());
			assertEquals("Privat", c.getType());
			assertEquals(1, c.getCategory());
			assertEquals("1234", c.getPayment());
			assertEquals("1234", c.getCvr());
			assertEquals("Null", c.getCompanyName());
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	}

}
