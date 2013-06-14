package ch.rmuerner.c2.db.dao;

public abstract class AbstractDAOFactory {

	public abstract String getCompetitionDAO();
	public abstract String getCategorieDAO();
	public abstract String getCompetitorDAO();
}
