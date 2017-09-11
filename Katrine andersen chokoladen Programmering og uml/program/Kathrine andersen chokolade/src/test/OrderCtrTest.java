package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import ctr.OrderCtr;
import model.Order;
import model.Customer;
import model.Company;
import model.Employee;

public class OrderCtrTest {
	private OrderCtr oCtr;
	
	@Before
	public void setup(){
		oCtr = new OrderCtr();
	}
	
	@Test
	public void testFindById(){
		Order o;
		try {
			o = oCtr.findOrderById(1);
			assertNotNull(o);
		} catch (SQLException e) {
			fail();
		}
	}
	
	@Test
	public void testFindByString(){
		LinkedList<Order> o;
		try {
			o = oCtr.findOrderByString("An");
			assertNotNull(o);
		} catch (SQLException e) {
			fail();
		}
	}
	
	//JUnit Test for Create
	@Test
	public void testCreate() {
		try {
			Order o = new Order();
			Customer c = new Customer();
			Company co = new Company();
			Employee e = new Employee();
			c.setId(1);
			co.setId(1);
			e.setId(1);
			
			o.setCustomer(c);
			o.setCompany(co);
			o.setEmployee(e);
			
			oCtr.addOrderToDB(o, 500.00, 125.00, "31-03-1993", 0, 100.00, o.getCustomer(), o.getCompany(), o.getEmployee());
			oCtr.findOrderById(o.getId());
			assertNotNull(o);
			assertNotNull(o.getCustomer());
			assertNotNull(o.getCompany());
			assertNotNull(o.getCompany());
			//Assert equals to test if create is succesful
			assertEquals(o, o);
			assertEquals(500.00, o.getTotalPrice(), 0.00);
			assertEquals(125.00, o.getTax(), 0.00);
			assertEquals("31-03-1993", o.getDate());
			assertEquals(0, o.getStatus());
			assertEquals(100.00, o.getDiscountPrice(), 0.00);
			assertEquals(1, o.getCustomer().getId());
			assertEquals(1, o.getCompany().getId());
			assertEquals(1, o.getEmployee().getId());
		} catch (SQLException e) {
			e.printStackTrace();
			fail();
		}
	
	}

}
