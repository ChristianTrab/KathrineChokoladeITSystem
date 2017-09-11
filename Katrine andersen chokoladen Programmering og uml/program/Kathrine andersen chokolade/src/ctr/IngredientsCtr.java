package ctr;
import model.Ingredients;
import model.RecipeLineItem;
import db.DBRecipe;
import db.DBRecipeLineItem;
import model.Recipe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Service;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import db.DBConnection;
import db.DBIngredients;
import db.DBProduct;
/*
 * @author Kasper Pedersen
 * @version (06-06-2016)
 */

/*
 *  Creates an object of DBIngredients.
 */
public class IngredientsCtr {
	private DBIngredients dbIngredients = new DBIngredients();
	
	/*
	 *  Creates an object of an empty ingredient
	 *  @return	- Return a empty ingredient object
	 */
	public Ingredients createEmptyIngredients() throws SQLException {
		Ingredients emptyIngredient = new Ingredients();
		Ingredients ingredients = dbIngredients.addEmptyIngredients(emptyIngredient);
		return ingredients;
	}
	
	/*
	 * The Update ingredient Method, this method will update the current ingredient with new values.
	 * 
	 * @param	ingredients	Ingredients - Ingredients Object from model.Ingredients
	 * @param	id			int			- Id of the ingredient
	 * @param	name		String		- Name of the ingredient
	 * @param	price		double		- price of the ingredient
	 * @param	quantity	int			- The quantity of the ingredient
	 * @return	- Returns a updated ingredient object
	 */
	public Ingredients updateIngredientsObject (Ingredients ingrediens, int id, String name, double price, int quantity, int monthlyConsumption, int minQuantity, int shippingCost, boolean reorder, int reorderQuantity) {
		Ingredients i = ingrediens;
		i.setId(id);
		i.setName(name);
		i.setPrice(price);
		i.setQuantity(quantity);
		i.setMonthlyConsumption(monthlyConsumption);
		i.setMinQuantity(minQuantity);
		i.setShippingCost(shippingCost);
		i.setReorder(reorder);
		i.setReorderQuantity(reorderQuantity);
		return i;
	}
	
	/*
	 * The Update ingredient in database Method, this method will update the current ingredient with new values.
	 * 
	 * @param	ingredients	Ingredients - Ingredients Object from model.Ingredients
	 * @param	id			int			- Id of the ingredient
	 * @param	name		String		- Name of the ingredient
	 * @param	price		double		- price of the ingredient
	 * @param	quantity	int			- The quantity of the ingredient
	 * 
	 */
	public void updateIngredientsDB(Ingredients ingrediens, int id, String name, double price, int quantity, int monthlyConsumption, int minQuantity, int shippingCost, boolean reorder, int reorderQuantity) throws SQLException {
		Ingredients i = updateIngredientsObject(ingrediens, id, name, price, quantity, monthlyConsumption, minQuantity, shippingCost, reorder, reorderQuantity);
		dbIngredients.updateIngredients(i);
	}
	
	/*
	 * The add ingredient to database Method, this method will add a ingredient object to the database
	 * 
	 * @param	ingredients	Ingredients - Ingredients Object from model.Ingredients
	 * 
	 */
	public void addIngredientsDB(Ingredients ingrediens) throws SQLException {
		Ingredients i = updateIngredientsObject(ingrediens, ingrediens.getId(), ingrediens.getName(), ingrediens.getPrice(), ingrediens.getQuantity(), ingrediens.getMonthlyConsumption(), ingrediens.getMinQuantity(), ingrediens.getShippingCost(), ingrediens.isReorder(), ingrediens.getReorderQuantity());
		dbIngredients.addIngredients(i);
	}
	
	/*
	 * This is the find ingredients by id method
	 * @param	id			int			- Which is calling findById method in db.DBIngredients and returning an object of Ingredients.
	 * @return	- Returns the ingrediens found by id
	 */
	public Ingredients findIngredientsById(int id) throws SQLException {
		return dbIngredients.findById(id);
	}
	
	/*
	 * This is the find ingredients by name method
	 * @param	name		String			- Which is calling findById method in db.DBIngredients and returning an object of Ingredients.
	 * @return	- Returns a linkedlist of the ingredients found by name
	 */
	public LinkedList<Ingredients> findIngredientsByName(String name) throws SQLException {
		LinkedList<Ingredients> iList = dbIngredients.findByName(name);
		iList.parallelStream()
		.sorted();
		return iList;
	}
	
	/*
	 * This is the delete ingredients from database method
	 * @param	id			int			- Which is calling findById method in db.DBIngredients and returning an object of Ingredients and then removes it from the database.
	 * 
	 */
	public void deleteIngredients(int id) throws SQLException {
		Ingredients i = findIngredientsById(id);
		dbIngredients.deleteIngredients(i);
	}
	
	public ArrayList<Ingredients> getReorderList() throws SQLException{
		return dbIngredients.reorderList();
	}
	
	public Ingredients reorderIngredients(Ingredients ingredient){
		double wf = Math.sqrt((2*ingredient.getMonthlyConsumption()*ingredient.getShippingCost())/(ingredient.getPrice()*0.15));
		int result = (int) wf;
		System.out.println("Resultat: " + result);
		ingredient.setReorderQuantity(result);
		return ingredient;
		}
	
	public LinkedList<Ingredients> getAllIngredients() throws SQLException {
		LinkedList<Ingredients> allIngredientsList = dbIngredients.getAllIngredients();
		allIngredientsList.parallelStream()
		.sorted();
		return allIngredientsList;
		
	}
	
	public void resetMonthlyConsumption() throws SQLException {
		Calendar c = Calendar.getInstance();
		int today = c.get(Calendar.DATE);
		
		if(today == 1) {
			LinkedList<Ingredients> ingredientsListMonthlyReset = getAllIngredients();
			for(Ingredients i : ingredientsListMonthlyReset) {
				int monthlyWf = (int) Math.sqrt((2*i.getMonthlyConsumption()*i.getShippingCost())/(i.getPrice()*0.15));
				i.setReorderQuantity(monthlyWf);
				dbIngredients.updateIngredients(i);
				System.out.println(i.getName() + " er blevet sat til tidligere månedes forbrug");
			}	
		}
	}
	
	
	
	
	public void sendMail(String receiver, ArrayList<Ingredients> reoderList){
		Properties props = new Properties();
		String to = receiver;
	    InternetAddress toAddress = null;

	    
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		

		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("admin@degladevikinger.dk", "krr22gxa");
			}
		});
		
		
		try {
			toAddress = new InternetAddress(to);
		}
		catch(AddressException e) {
			e.printStackTrace();
		}
		
		try{
			StringBuilder str = new StringBuilder();
			
			for(Ingredients i : reoderList) {
				str.append("Id: " + i.getId() + "| Navn: " + i.getName() + "| Bestillings Antal. " + i.getReorderQuantity() + "\n");
			}
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("reorder@kac.dk"));
			message.setRecipient(RecipientType.TO, toAddress);
			message.setSubject("Bestillings Liste");
			message.setText(str.toString());
			
			
			Transport.send(message);
			
			System.out.println("Genbestilling sendt til indskrevet email");
			
		} catch(MessagingException e){
			throw new RuntimeException(e);
		}
		
	}
}
		
		
	

