package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dto.AbstractDTO;

/**
 * H2DAO.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 * 
 * @param <o>
 */
public abstract class H2DAO<o extends AbstractDTO> {

	/** Logger */
	private final static Logger LOGGER = Logger
			.getLogger(H2DAO.class.getName());

	/**
	 * Converts a {@link ResultSet} into a list of dtos of type
	 * {@link AbstractDTO}.
	 * 
	 * @param result
	 *            The {@link ResultSet} returned after query execution.
	 * @return A {@link List} of DTOs of type {@link AbstractDTO}.
	 */
	abstract protected List<o> convertToDto(ResultSet result);

	/**
	 * Creates the save- or update-query.
	 * 
	 * @param dto
	 *            The DTO of type {@link AbstractDTO} to save/update in the
	 *            database.
	 * @return Query with the save/update statement.
	 */
	abstract protected String getSaveOrUpdateQuery(o dto);

	/**
	 * Executes a select query.
	 * 
	 * @param query
	 *            The query to execute
	 * @return The list of selected rows converted in dtos.
	 */
	protected List<o> executeSelectQuery(String query) {
		Connection dbConnection = H2DAOFactory.createConnection();
		if (dbConnection != null) {
			try {
				Statement stmt = dbConnection.createStatement();
				LOGGER.info("Execute query: " + query);
				return convertToDto(stmt.executeQuery(query));
			} catch (SQLException e) {
				LOGGER.warning("SQLExeption (" + e.getErrorCode()
						+ ") occured trying to execute query.");
			} finally {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					LOGGER.warning("SQLExeption (" + e.getErrorCode()
							+ ") occured while closing connection.");
				}
			}
		}
		return null;
	}

	/**
	 * Executes an update Query.
	 * 
	 * @param query
	 *            The query to execute.
	 * @return Count of affected rows in the database or -1 on error.
	 */
	protected int executeSaveUpdateQuery(String query) {
		Connection dbConnection = H2DAOFactory.createConnection();
		if (dbConnection != null) {
			try {
				Statement stmt = dbConnection.createStatement();
				LOGGER.info("Execute query: " + query);
				return stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO logging
				e.printStackTrace();
			} finally {
				try {
					dbConnection.close();
				} catch (SQLException e) {
					// TODO logging
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
}
