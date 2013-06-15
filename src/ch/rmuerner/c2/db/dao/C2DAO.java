package ch.rmuerner.c2.db.dao;

import java.util.List;

import ch.rmuerner.c2.db.dto.AbstractDTO;

public interface C2DAO<o extends AbstractDTO> {

	/**
	 * Saves a DTO in the database
	 * 
	 * @param dto
	 *            The DTO to save or update
	 * @return Count of affected rows
	 */
	public int saveOrUpdate(o dto);

	/**
	 * Selects a row from database and converts it in a DTO
	 * 
	 * @param id
	 *            Row id
	 * @return Selected DTO or null if nothing found
	 */
	public o selectById(long id);

	/**
	 * Selects all rows from database and converts them in a list of DTO
	 * 
	 * @return List of DTOs or null table is empty
	 */
	public List<o> selectAll();

	/**
	 * Deletes a row from the database.
	 * 
	 * @param dto
	 *            DTO to delete.
	 * @return Count of affected rows.
	 */
	public int delete(o dto);

	/** The create-statement */
	public String getCreateStatement();

	/** The drop-statement */
	public String getDropStatement();

	/** Drops table from db */
	public void doDropTable();

	/** Creates table on db */
	public void doCreateTable();
}
