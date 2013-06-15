package ch.rmuerner.c2.db.dao;

import java.util.List;

import ch.rmuerner.c2.db.dto.CategoryDTO;

/**
 * CategoryDAO
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public interface CategoryDAO {

	/**
	 * Saves a {@link CategoryDTO} in the database
	 * 
	 * @param dto
	 *            The {@link CategoryDTO} to save or update
	 * @return Count of affected rows
	 */
	public int saveOrUpdate(CategoryDTO dto);

	/**
	 * Selects a row from db and converts it in a {@link CategoryDTO}
	 * 
	 * @param id
	 *            Row id
	 * @return Selected {@link CategoryDTO} or null if nothing found
	 */
	public CategoryDTO selectById(long id);

	/**
	 * Selects all rows from db and converts them in a list of
	 * {@link CategoryDTO}
	 * 
	 * @return List of {@link CategoryDTO} or null table is empty
	 */
	public List<CategoryDTO> selectAll();

	/** The create-statement */
	public String getCreateStatement();

	/** The drop-statement */
	public String getDropStatement();

	/** Drops table from db */
	public void doDropTable();

	/** Creates table on db */
	public void doCreateTable();
}
