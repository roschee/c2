package ch.rmuerner.c2.db.dto;



public abstract class AbstractDTO {

	private long id = -1L;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
}
