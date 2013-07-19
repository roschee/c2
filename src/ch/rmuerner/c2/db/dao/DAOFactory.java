package ch.rmuerner.c2.db.dao;

import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;

/**
 * DAOFactory.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public abstract class DAOFactory {

	public enum Database {
		H2;
	}

	public abstract CompetitionDAO getCompetitionDAO();

	public abstract CategoryDAO getCategoryDAO();

	public abstract AthleteDAO getAthleteDAO();

	public static DAOFactory getDAOFactory(Database database) {

		switch (database) {
		case H2:
			return new H2DAOFactory();
		default:
			return null;
		}
	}
	
	public abstract void initDatabase();
}
