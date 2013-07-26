package ch.rmuerner.c2.db.dto;

import java.sql.Date;

/**
 * DTO representing a competitor
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class AthleteDTO extends AbstractDTO {

	private String identNr;
	private String lastName;
	private String firstName;
	private Date dateOfBirth;
	private float weight;
	private String organisation;
	private String country;

	public AthleteDTO(long id, String identNr, String lastName,
			String firstName, Date dateOfBirth, float weight,
			String organsation, String country) {
		setId(id);
		this.identNr = identNr;
		this.lastName = lastName;
		this.firstName = firstName;
		this.dateOfBirth = dateOfBirth;
		this.weight = weight;
		this.organisation = organsation;
		this.country = country;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public String getOrganisation() {
		return organisation;
	}
	
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
