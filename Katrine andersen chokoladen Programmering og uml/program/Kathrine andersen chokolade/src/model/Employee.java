package model;

/*
 * @version(25-05-2016)
 */

/*
 * Creates the instance variables id, fName, lName, address, city, zipCode, phone, email, jobTitle, SectionDB_sectionId
 */
public class Employee extends Person{
	//Instance variables
	private String jobTitle;
	private int SectionDB_sectionId;
	private String username;
	private String password;
	
	/*
	 * The Employee constructor, will create a new instance of Employee, and it extends to Person
	 * 
	 * @param	id		int	- Id of the person
	 * @param 	fName 	String - The first name of the person
	 * @param	lName	String - The last name of the person
	 * @param	address String - The address of where the person lives
	 * @param	city 	String - The name of the city, where the person lives
	 * @param 	zipCode	String - The zipCode for the given area where the person lives
	 * @param	Phone	String - The phone number of the person
	 * @param	email	String - The email to the person
	 * @param 	jobTitle	String - The jobTitle of the person
	 * @param	SectionDB_sectionId	int - The sectionId of the Employee
	 */
	public Employee(String jobTitle, int SectionDB_sectionId) {
		super(0, null, null, null, null, null, null, null); //Int skal rettes til, så den selv finder id fra databsen
		this.jobTitle = jobTitle;
		this.SectionDB_sectionId = SectionDB_sectionId;
	}
	
	/*
	 * Create an empty Employee object with no instance variables set.
	 */
	public Employee() {
		
	}
	
	/*
	 * Returns the jobTitle of the specific object
	 * 
	 * @param 	jobTitle	String - The jobTitle of the person
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/*
	 * Sets the jobTitle of the specific object
	 * 
	 * @param 	jobTitle	String - The jobTitle of the person
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/*
	 * Returns the SectionDB_sectionId of the specific object
	 * 
	 * @param	SectionDB_sectionId	int - The sectionId of the Employee
	 */
	public int getSectionDB_sectionId(){
		return SectionDB_sectionId;
	}
	
	/*
	 * Sets the SectionDB_sectionId of the specific object
	 * 
	 * @param	SectionDB_sectionId	int - The sectionId of the Employee
	 */
	public void setSectionDB_sectionId(int SectionDB_sectionId){
		this.SectionDB_sectionId = SectionDB_sectionId;
	}
}
