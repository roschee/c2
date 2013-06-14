package ch.rmuerner.c2.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import ch.rmuerner.c2.db.dao.h2.H2CompetitorDAO;

public class DBAdapter {

	// TODO Remove DBAdapter
	private static DBAdapter instance = null;
	private Connection dbConnection;
	// Export in DBProperties
	private static String location = "resources/c2Database";
	private static String user = "";
	private static String password = "";
	
	// DAO
	private H2CompetitorDAO competitorDAO;

	private DBAdapter() {
	};

	public static DBAdapter getInstance() {
		if (instance == null) {
			instance = new DBAdapter();
		}
		return instance;
	}
	
	public H2CompetitorDAO getCompetitorDAO(){
		if(competitorDAO == null){
			competitorDAO = new H2CompetitorDAO(this);
		}
		return competitorDAO;
	}
	
	public void save(String table){
		executeQuery("INSERT INTO " + table + ";");
	}
	

	public void initDB() {
		// Drop all
		executeQuery(H2CompetitorDAO.getDropStatement());
		
		// Create all
		executeQuery(H2CompetitorDAO.getCreateStatement());
	}
	
	private void executeQuery(String query){
		try {
			openConnection();
			Statement stmt = dbConnection.createStatement();
			stmt.execute(query);
			closeConnection();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (dbConnection != null)
				try {
					dbConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}

	private void openConnection() throws Exception {
		Class.forName("org.h2.Driver");
		dbConnection = DriverManager.getConnection(
				"jdbc:h2:"+location, user , password);
	}

	private void closeConnection() throws Exception {
		dbConnection.close();
	}

}
