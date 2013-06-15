package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ch.rmuerner.c2.db.dto.AbstractDTO;

public abstract class H2DAO<o extends AbstractDTO> {

	/**
	 * TODO Set comment
	 * 
	 * @param result
	 * @return
	 */
	abstract protected List<o> convertToDto(ResultSet result);
	
	/**
	 * TODO Set comment or remove method
	 * 
	 * @param dto
	 * @return
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
				// TODO Remove debug output
				System.out.println("Execute query: " + query);
				return convertToDto(stmt.executeQuery(query));
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
				// TODO Remove debug output
				System.out.println("Execute query: " + query);
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
