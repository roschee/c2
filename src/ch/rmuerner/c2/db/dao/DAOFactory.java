package ch.rmuerner.c2.db.dao;

/**
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public abstract class DAOFactory {

	public enum Database {
		H2;
	}

	public abstract CompetitionDAO getCompetitionDAO();

	public abstract CategoryDAO getCategorieDAO();

	public abstract CompetitorDAO getCompetitorDAO();

	public abstract TableauDAO getTableauDAO();

	public static DAOFactory getDAOFactory(Database database) {

		switch (database) {
		case H2:
			return new H2DAOFactory();
		default:
			return null;
		}
	}
}
