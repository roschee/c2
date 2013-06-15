package ch.rmuerner.c2.db.dto;

/**
 * DTO representing a category
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class CategoryDTO extends AbstractDTO {

	private String name;
	private long modus;
	private long tableauId;
	
	public CategoryDTO(long id, String name, long modus, long tableauId){
		setId(id);
		this.name = name;
		this.modus = modus;
		this.tableauId = tableauId;
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

	public long getTableauId() {
		return tableauId;
	}

	public void setTableauId(long tableauId) {
		this.tableauId = tableauId;
	}
}
