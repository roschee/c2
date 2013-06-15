package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import ch.rmuerner.c2.db.dto.AbstractDTO;

public abstract class H2DAO {
	
	abstract protected List<? extends AbstractDTO> convertToDto(ResultSet result);
	
	protected List<? extends AbstractDTO> executeSelectQuery(String query) {
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

}
