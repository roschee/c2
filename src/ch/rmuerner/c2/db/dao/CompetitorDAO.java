package ch.rmuerner.c2.db.dao;

import java.util.List;

import ch.rmuerner.c2.db.dto.CompetitorDTO;

/**
 * CompetitorDAO
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public interface CompetitorDAO {

	/**
	 * Saves a {@link CompetitorDTO} in the database
	 * 
	 * @param dto
	 *            The {@link CompetitorDAO} to save or update
	 * @return Count of affected rows
	 */
	public int saveOrUpdate(CompetitorDTO dto);

	/**
	 * Selects a row from db and converts it in a {@link CompetitorDTO}
	 * 
	 * @param id
	 *            Row id
	 * @return Selected {@link CompetitorDTO} or null if nothing found
	 */
	public CompetitorDTO selectById(long id);

	/**
	 * Selects all rows from db and converts them in a list of
	 * {@link CompetitorDTO}
	 * 
	 * @return List of {@link CompetitorDTO} or null table is empty
	 */
	public List<CompetitorDTO> selectAll();

	/** The create-statement */
	public String getCreateStatement();

	/** The drop-statement */
	public String getDropStatement();

	/** Drops table from db */
	public void doDropTable();

	/** Creates table on db */
	public void doCreateTable();
}
