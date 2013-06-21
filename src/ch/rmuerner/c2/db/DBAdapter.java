package ch.rmuerner.c2.db;

import ch.rmuerner.c2.db.dao.DAOFactory;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;

/**
 * DBAdapter.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class DBAdapter {

	private static DBAdapter instance = null;

	private DBAdapter() {
	}

	public static DBAdapter getInstance() {
		if (instance == null) {
			instance = new DBAdapter();
		}
		return instance;
	}

	public H2DAOFactory getH2DAOFactory() {
		H2DAOFactory h2DAOFactory = (H2DAOFactory) DAOFactory
				.getDAOFactory(DAOFactory.Database.H2);
		return h2DAOFactory;
	}
}
