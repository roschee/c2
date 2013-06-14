package ch.rmuerner.c2.db.dto;

public class CompetitorDTO extends AbstractDTO {

	
	private String lastName;
	private String firstName;
	
	public CompetitorDTO(long id, String lastName, String firstName){
		setId(id);
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getFirstName() {
		return firstName;
	}
}
