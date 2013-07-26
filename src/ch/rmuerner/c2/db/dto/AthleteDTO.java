package ch.rmuerner.c2.db.dto;


/**
 * DTO representing a competitor
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class AthleteDTO extends AbstractDTO {

	private String identNr;
	private String lastName;
	private String firstName;
	private String country;
	private String organisation;

	public AthleteDTO(long id, String identNr, String lastName,
			String firstName, String country, String organsation) {
		setId(id);
		this.identNr = identNr;
		this.lastName = lastName;
		this.firstName = firstName;
		this.country = country;
		this.organisation = organsation;
	}

	public String getIdentNr() {
		return identNr;
	}

	public void setIdentNr(String identNr) {
		this.identNr = identNr;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
}
