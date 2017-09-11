package test;
import java.sql.SQLException;

import ctr.CustomerCtr;
import model.Customer;;
public class Main {

	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		CustomerCtr cCtr = new CustomerCtr();
		System.out.println("Testing creating new customer object: ");
		Customer c = cCtr.createEmyptyCustomer();
		try {
		cCtr.addCustomerToDB(c, 1, "Peter", "nr2", "Gammelvej", "Aalborg", "9898", "23568956", "dkald", "Privat", 1, "dad", "eae", "dæaø");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
