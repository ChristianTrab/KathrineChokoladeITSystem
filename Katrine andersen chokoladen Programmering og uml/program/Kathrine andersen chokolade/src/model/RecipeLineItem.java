package model;
import model.Ingredients;
/*
 * @version(06-06-2016)
 */

/*
 * Creates the instance variables recipeId, ingredient, quantity
 */

public class RecipeLineItem {
	//Instance variables
	private int recipeId;
	private Ingredients ingredient;
	private int quantity;
	
	/*
	 * The RecipeSaleLineItem constructor, will create a new instance of RecipeSaleLineItem
	 * 
	 * @param	recipeId	int	-	The recipeId of the RecipeLineItem
	 * @param	ingredient	Ingredients	- The Recipe ingredients
	 * @param	quantity	int	-	The quantity of the 
	 */
	public RecipeLineItem(int recipeId, Ingredients ingredient, int quantity) {
		this.recipeId = recipeId;
		this.ingredient = ingredient;
		this.quantity = quantity;
	}
	
	/*
	 * Create an empty Recipe SaleLineItem object with no instance variables set.
	 */
	public RecipeLineItem(){
		
	}
	
	/*
	 * Sets the ingredient of the specific object
	 * 
	 * @param	ingredient		String	- ingredients of the specific object
	 */
	public void setIngredient(Ingredients ingredient) {
		this.ingredient = ingredient;
	}
	
	/*
	 * Returns the ingredient of the specific object
	 * 
	 * @param	ingredient		String	- ingredients of the specific object
	 */
	public Ingredients getIngredient(){
		return ingredient;
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id		int	- Id of the ingredient
	 */
	public int getId() {
		return recipeId;
		
		/*
		 * Sets the id of the specific object
		 * 
		 * @param	id		int	- Id of the specific object
		 */
	}
	public void setId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	/*
	 * Returns the quantity of the specific object
	 * 
	 * @param	quantity		int	- Quantity of the specific object
	 */
	public int getQuantity() {
		return quantity;
	}
	
	/*
	 * Sets the quantity of the specific object
	 * 
	 * @param	quantity		int	- Quantity of specific object
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	

}
