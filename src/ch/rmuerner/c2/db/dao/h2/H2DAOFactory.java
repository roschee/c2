package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dao.CategoryDAO;
import ch.rmuerner.c2.db.dao.CompetitionDAO;
import ch.rmuerner.c2.db.dao.AthleteDAO;
import ch.rmuerner.c2.db.dao.DAOFactory;
import ch.rmuerner.c2.db.dto.CategoryDTO;
import ch.rmuerner.c2.db.dto.CategoryDTO.State;
import ch.rmuerner.c2.db.dto.CompetitionDTO;

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
	public static Connection createConnection() {
		try {
			Class.forName(DRIVER_CLASS);
			dbConnection = DriverManager.getConnection("jdbc:h2:" + LOCATION,
					USER, PASSWORD);
		} catch (SQLException e) {
			LOGGER.severe("SQLExeption (" + e.getErrorCode()
					+ ") occured while trying to connect to database.");
		} catch (ClassNotFoundException e) {
			LOGGER.severe("Driver class (" + DRIVER_CLASS + ") not found.");
		}
		return dbConnection;
	}

	@Override
	public CompetitionDAO getCompetitionDAO() {
		return new H2CompetitionDAO();
	}

	@Override
	public CategoryDAO getCategoryDAO() {
		return new H2CategoryDAO() {
		};
	}

	@Override
	public AthleteDAO getAthleteDAO() {
		return new H2AthleteDAO();
	}

	@Override
	public void initDatabase() {
		LOGGER.info("********* START Initialize Database H2 *********");
		// Drop all
		getAthleteDAO().doDropTable();
		getCategoryDAO().doDropTable();
		getCompetitionDAO().doDropTable();

		// Create all
		getCompetitionDAO().doCreateTable();
		getCategoryDAO().doCreateTable();
		getAthleteDAO().doCreateTable();
		LOGGER.info("********* END Initialize Database H2 *********");
	}

	public void loadTestData() {
		getCompetitionDAO().saveOrUpdate(
				new CompetitionDTO(-1, "43. Schülerturnier Spiez 2013",
						"Spiez", Date.valueOf("2013-06-01")));

		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler B -26", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler B -28", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler B -30", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler B -33", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler B -36", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler A -30", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler A -33", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler A -36", -1, 1, "", State.NEW));
		getCategoryDAO().saveOrUpdate(new CategoryDTO(-1, "Schüler A -40", -1, 1, "", State.NEW));
	}
}
