package ch.rmuerner.c2.db.dto;

import java.sql.Date;

/**
 * DTO representing a competition
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class CompetitionDTO extends AbstractDTO {
	
	private String name;
	private String location;
	private Date date;
	
	public CompetitionDTO(long id, String name, String location, Date date){
		setId(id);
		this.name = name;
		this.location = location;
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
