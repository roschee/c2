package ch.rmuerner.c2.db.dto;


/**
 * DTO representing a tableau
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TableauDTO extends AbstractDTO {

	private long encounterNr;
	private long firstCompetitorId;
	private long secondCompetitorId;
	private long winnerId;
	private long nextEncounterNr;

	public TableauDTO(long id, long encounterNr, long firstCompetitorId,
			long secondCompetitorId, long winnerId, long nextEncounterNr) {
		setId(id);
		this.encounterNr = encounterNr;
		this.firstCompetitorId = firstCompetitorId;
		this.secondCompetitorId = secondCompetitorId;
		this.winnerId = winnerId;
		this.nextEncounterNr = nextEncounterNr;
	}

	public long getEncounterNr() {
		return encounterNr;
	}

	public void setEncounterNr(long encounterNr) {
		this.encounterNr = encounterNr;
	}

	public long getFirstCompetitorId() {
		return firstCompetitorId;
	}

	public void setFirstCompetitorId(long firstCompetitorId) {
		this.firstCompetitorId = firstCompetitorId;
	}

	public long getSecondCompetitorId() {
		return secondCompetitorId;
	}

	public void setSecondCompetitorId(long secondCompetitorId) {
		this.secondCompetitorId = secondCompetitorId;
	}

	public long getWinnerId() {
		return winnerId;
	}

	public void setWinnerId(long winnerId) {
		this.winnerId = winnerId;
	}

	public long getNextEncounterNr() {
		return nextEncounterNr;
	}

	public void setNextEncounterNr(long nextEncounterNr) {
		this.nextEncounterNr = nextEncounterNr;
	}
}
