package ch.rmuerner.c2.db.dao;

import java.util.List;

import ch.rmuerner.c2.db.dto.CompetitionDTO;

public interface CompetitionDAO {

	/**
	 * Saves a {@link CompetitionDTO} in the database
	 * 
	 * @param dto
	 *            The {@link CompetitionDTO} to save or update
	 * @return Count of affected rows
	 */
	public int saveOrUpdate(CompetitionDTO dto);

	/**
	 * Selects a row from db and converts it in a {@link CompetitionDTO}
	 * 
	 * @param id
	 *            Row id
	 * @return Selected {@link CompetitionDTO} or null if nothing found
	 */
	public CompetitionDTO selectById(long id);

	/**
	 * Selects all rows from db and converts them in a list of
	 * {@link CompetitionDTO}
	 * 
	 * @return List of {@link CompetitionDTO} or null table is empty
	 */
	public List<CompetitionDTO> selectAll();

	/** The create-statement */
	public String getCreateStatement();

	/** The drop-statement */
	public String getDropStatement();

	/** Drops table from db */
	public void doDropTable();

	/** Creates table on db */
	public void doCreateTable();
}
