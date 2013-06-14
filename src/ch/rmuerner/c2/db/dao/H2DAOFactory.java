package ch.rmuerner.c2.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
		return null;
	}

	@Override
	public CategoryDAO getCategorieDAO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompetitorDAO getCompetitorDAO() {
		// TODO implement
		return null;
	}

	@Override
	public TableauDAO getTableauDAO() {
		// TODO Auto-generated method stub
		return null;
	}
}