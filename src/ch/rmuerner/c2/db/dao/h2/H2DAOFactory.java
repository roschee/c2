package ch.rmuerner.c2.db.dao.h2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ch.rmuerner.c2.db.dao.CategoryDAO;
import ch.rmuerner.c2.db.dao.CompetitionDAO;
import ch.rmuerner.c2.db.dao.CompetitorDAO;
import ch.rmuerner.c2.db.dao.DAOFactory;
import ch.rmuerner.c2.db.dao.TableauDAO;

public class H2DAOFactory extends DAOFactory {

	private static Connection dbConnection;
	private static String location = "resources/c2Database";
	private static String user = "";
	private static String password = "";
	
	public static Connection createConnection(){
		try {
		Class.forName("org.h2.Driver");
			dbConnection = DriverManager.getConnection(
					"jdbc:h2:"+location, user , password);
		} catch (SQLException e) {
			// TODO Logging
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Logging
			e.printStackTrace();
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
