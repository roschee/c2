package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dao.CategoryDAO;
import ch.rmuerner.c2.db.dao.CompetitionDAO;
import ch.rmuerner.c2.db.dao.CompetitorDAO;
import ch.rmuerner.c2.db.dao.DAOFactory;
import ch.rmuerner.c2.db.dao.TableauDAO;

/**
 * H2DAOFactory.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2DAOFactory extends DAOFactory {

	/** Logger */
	private final static Logger LOGGER = Logger.getLogger(H2DAOFactory.class
			.getName());
	
	/** Database attributes */
	private static Connection dbConnection;
	private static String LOCATION = "resources/c2Database";
	private static String USER = "";
	private static String PASSWORD = "";
	private static String DRIVER_CLASS = "org.h2.Driver";
	
	/**
	 * Creates a database connection
	 * 
	 * @return Database connection
	 */
	public static Connection createConnection(){
		try {
		Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection(
					"jdbc:h2:"+LOCATION, USER , PASSWORD);
		} catch (SQLException e) {
			LOGGER.severe("SQLExeption (" + e.getErrorCode()
					+ ") occured while trying to connect to database.");
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Driver class ("+DRIVER_CLASS+") not found.");
		}
		return dbConnection;
	}
	
	@Override
	public CompetitionDAO getCompetitionDAO() {
		return new H2CompetitionDAO();
	}

	@Override
	public CategoryDAO getCategorieDAO() {
		return new H2CategoryDAO() {
		};
	}

	@Override
	public CompetitorDAO getCompetitorDAO() {
		return new H2CompetitorDAO();
	}

	@Override
	public TableauDAO getTableauDAO() {
		return new H2TableauDAO();
	}
	
	@Override
	public void initDatabase(){
		// Drop all
		getTableauDAO().doDropTable();
		getCompetitorDAO().doDropTable();
		getCategorieDAO().doDropTable();
		getCompetitionDAO().doDropTable();
		
		// Create all
		getCompetitionDAO().doCreateTable();
		getCategorieDAO().doCreateTable();
		getCompetitorDAO().doCreateTable();
		getTableauDAO().doCreateTable();
	}
}
