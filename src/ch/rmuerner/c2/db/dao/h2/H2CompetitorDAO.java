package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.util.List;

import ch.rmuerner.c2.db.dao.CompetitorDAO;
import ch.rmuerner.c2.db.dto.CompetitorDTO;

/**
 * H2CompetitorDAO.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2CompetitorDAO extends H2DAO<CompetitorDTO> implements
		CompetitorDAO {

	public static final String TABLE_NAME = "competitor";

	/** Represents a competitor in the database */
	private enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		IDENTNR("identNr", "VARCHAR(255) NOT NULL"), //
		LASTNAME("name", "VARCHAR(255) NOT NULL"), //
		FIRSTNAME("firstname", "VARCHAR(255) NOT NULL"), //
		COUNTRY("country", "VARCHAR(255) NOT NULL"), //
		ORGANISATION("organisation", "VARCHAR(255) NOT NULL");

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

	@Override
	public int saveOrUpdate(CompetitorDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}

	@Override
	public CompetitorDTO selectById(long id) {
		List<CompetitorDTO> dtos = executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + " WHERE " + Column.ID.name + "=" + id + ";");
		if (dtos != null && !dtos.isEmpty())
			return dtos.get(0);
		return null;
	}

	@Override
	public List<CompetitorDTO> selectAll() {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME + ";");
	}

	// TODO move this in super class??
	@Override
	protected String getSaveOrUpdateQuery(CompetitorDTO dto) {
		StringBuilder queryBuilder = new StringBuilder();
		// Save
		if (dto.getId() == -1) {
			// SetUp Query
			queryBuilder.append("INSERT INTO " + TABLE_NAME);
			StringBuilder columns = new StringBuilder(" (");
			StringBuilder values = new StringBuilder(" VALUES(");
			// Map CompetitionDTO
			// name
			columns.append(Column.IDENTNR.name);
			values.append("'" + dto.getIdentNr() + "'");
			// lastname
			columns.append(", " + Column.LASTNAME.name);
			values.append(", '" + dto.getLastName() + "'");
			// firstname
			columns.append(", " + Column.FIRSTNAME.name);
			values.append(", '" + dto.getFirstName() + "'");
			// country
			columns.append(", " + Column.COUNTRY.name);
			values.append(", '" + dto.getCountry() + "'");
			// organisation
			columns.append(", " + Column.ORGANISATION.name);
			values.append(", '" + dto.getOrganisation() + "'");
			// endline
			columns.append(")");
			values.append(")");
			queryBuilder.append(columns).append(values).append(";");
		} else { // Update
			// SetUp Query
			queryBuilder.append("UPDATE " + TABLE_NAME + " SET ");
			// Map CompetitionDTO
			// name
			queryBuilder.append(Column.IDENTNR.name + //
					"='" + dto.getIdentNr() + "', ");
			// lastname
			queryBuilder.append(Column.LASTNAME.name + //
					"='" + dto.getLastName() + "', ");
			// firstname
			queryBuilder.append(Column.FIRSTNAME.name + //
					"='" + dto.getFirstName() + "', ");
			// country
			queryBuilder.append(Column.COUNTRY.name + //
					"='" + dto.getCountry() + "', ");
			// organisation
			queryBuilder.append(Column.ORGANISATION.name + //
					"='" + dto.getOrganisation() + "'");
			// endline
			queryBuilder.append(" WHERE " + Column.ID.name + "=" + dto.getId()
					+ ";");

		}
		return queryBuilder.toString();
	}

	@Override
	protected List<CompetitorDTO> convertToDto(ResultSet result) {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME + ";");
	}

	@Override
	public String getCreateStatement() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS "
				+ TABLE_NAME + "(");
		for (Column col : Column.values()) {
			sb.append(col.getName() + " " + col.getType() + ", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(");");
		return sb.toString();
	}

	@Override
	public String getDropStatement() {
		return "DROP TABLE IF EXISTS " + TABLE_NAME;
	}

	@Override
	public void doDropTable() {
		executeSaveUpdateQuery(getDropStatement());
	}

	@Override
	public void doCreateTable() {
		executeSaveUpdateQuery(getCreateStatement());
	}
}
