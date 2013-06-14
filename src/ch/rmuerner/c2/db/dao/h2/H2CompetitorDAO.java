package ch.rmuerner.c2.db.dao.h2;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dao.AbstractDAO;
import ch.rmuerner.c2.db.dao.CompetitorDAO;
import ch.rmuerner.c2.db.dto.CompetitorDTO;

public class H2CompetitorDAO extends AbstractDAO<CompetitorDTO> implements CompetitorDAO{

	public static final String TABLE_NAME = "competitor";

	private enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		LASTNAME("name", "VARCHAR(255) NOT NULL"), //
		FIRSTNAME("firstname", "VARCHAR(255) NOT NULL");

		private String name;

		private String type;

		private Column(String name, String type) {
			this.name = name;
			this.type = type;
		}

		private String getName() {
			return name;
		}

		private String getType() {
			return type;
		}
	}

	// TODO Clean up H2CompetitorDAO
	public H2CompetitorDAO(DBAdapter dbAdapter){
		super(dbAdapter, Column.ID.getName());
	}
	
	@Override
	public String getTableName() {
		return TABLE_NAME;
	}

	public static String getCreateStatement() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(");
		for (Column col : Column.values()) {
			sb.append(col.getName() + " " + col.getType() + ", ");
		}
		sb.delete(sb.length()-2, sb.length());
		sb.append(");");
		return sb.toString();
	}

	public static String getDropStatement() {
		return "DROP TABLE IF EXISTS " + TABLE_NAME;
	}

}
