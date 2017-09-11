package model;
/*
 * @version(06-06-2016)
 */

/*
 * Creates the instance variables id, sectionName, amountOfEmployees
 */
public class Section {
	//Instance variables
	private int id;
	private String sectionName;
	private int amountOfEmployees;
	
	/*
	 * The Section constructor, will create a new instance of Section
	 * 
	 * @param	id					int	-	The id of the section
	 * @param	sectionName			String	- Name of the section
	 * @param	amountOfEmployees	int	-	The amount of employees
	 */
	public Section(int id, String sectionName, int amountOfEmployees) {
		this.id = id;
		this.sectionName = sectionName;
		this.amountOfEmployees = amountOfEmployees;
	}
	
	/*
	 * Create an empty section object with no instance variables set.
	 */
	public Section() {

	}

	/*
	 * Returns the id of the specific object
	 * 
	 * @param	id		int	- Id of the object
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * Sets the id of the specific object
	 * 
	 * @param	id		int	- Id of the specific object
	 */
	public void setId(int id) {
		this.id = id;
	}
	/*
	 * Returns the sectionName of the specific object
	 * 
	 * @param	sectionName		String	- The name of the section
	 */
	public String getSectionName() {
		return sectionName;
	}
	
	/*
	 * Sets the sectionName of the specific object
	 * 
	 * @param	sectionName		String	- Name of the section
	 */
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
	/*
	 * Returns the amountOfEmployees of the specific object
	 * 
	 * @param	amountOfEmployees		int	- The amount of employees
	 */
	public int getAmountOfEmployees() {
		return amountOfEmployees;
	}
	
	/*
	 * Sets the amountOfEmployees of the specific object
	 * 
	 * @param	amountOfEmployees		int	- The amount of employees
	 */
	public void setAmountOfEmployees(int amountOfEmployees) {
		this.amountOfEmployees = amountOfEmployees;
	}
	
	

}
