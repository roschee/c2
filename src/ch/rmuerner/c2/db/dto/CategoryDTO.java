package ch.rmuerner.c2.db.dto;

/**
 * DTO representing a category
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class CategoryDTO extends AbstractDTO {

	private String name;
	private long modus;
	private long competitionId;
	private String tableauLink;
	private String status;
	
	public CategoryDTO(long id, String name, long modus, long competitionId, String tableauLink, String status){
		setId(id);
		this.name = name;
		this.modus = modus;
		this.competitionId = competitionId;
		this.tableauLink = tableauLink;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getModus() {
		return modus;
	}

	public void setModus(long modus) {
		this.modus = modus;
	}
	
	public long getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(long competitionId) {
		this.competitionId = competitionId;
	}

	public String getTableauLink() {
		return tableauLink;
	}

	public void setTableauLink(String tableauLink) {
		this.tableauLink = tableauLink;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
