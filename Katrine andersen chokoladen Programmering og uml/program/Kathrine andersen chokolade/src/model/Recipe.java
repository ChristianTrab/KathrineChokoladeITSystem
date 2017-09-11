package model;
/*
 * @version(25-05-2016)
 */

/*
 * Creates the instance variables id, name, description
 */
public class Recipe {
	//Instance variables
	private int id;
	private String name;
	private String description;
	
	/*
	 * The Recipe constructor, will create a new instance of Recipe
	 * 
	 * @param	id			int		- Id of the Recipe
	 * @param	name		String	-	The name of the Recipe
	 * @param	description	String	-	The description of the Recipe	
	 */
	public Recipe(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	/*
	 * Create an empty Recipe object with no instance variables set.
	 */
	public Recipe() {
		
	}

	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id		int	- Id of the person
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Sets the id of the specific object
	 * 
	 * @param	id		int	- Id of the person
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @Returns	name		String	-	The name of the Recipe
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @param	name		String	-	The name of the Recipe
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @Returns	name		String	-	The name of the Recipe
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/*
	 * Returns the id of the specific object
	 * 
	 * @param	name		String	-	The name of the Recipe
	 */
	public String getDescription() {
		return description;
	}
	
	

}
