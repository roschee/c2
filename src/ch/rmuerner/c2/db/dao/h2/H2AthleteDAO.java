package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dao.AthleteDAO;
import ch.rmuerner.c2.db.dto.AthleteDTO;

/**
 * H2CompetitorDAO. Implementation of {@link AthleteDAO}.
 * 
 * @version V0.1
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2AthleteDAO extends H2DAO<AthleteDTO> implements
		AthleteDAO {

	/** Logger */
	private final static Logger LOGGER = Logger.getLogger(H2AthleteDAO.class
			.getName());

	/** Database table name */
	public static final String TABLE_NAME = "competitor";

	/** Database attributes of competitor */
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
	public int saveOrUpdate(AthleteDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}

	@Override
	public AthleteDTO selectById(long id) {
		List<AthleteDTO> dtos = executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + " WHERE " + Column.ID.name + "=" + id + ";");
		if (dtos != null && !dtos.isEmpty())
			return dtos.get(0);
		return null;
	}

	@Override
	public List<AthleteDTO> selectAll() {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME + ";");
	}

	@Override
	protected String getSaveOrUpdateQuery(AthleteDTO dto) {
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
	protected List<AthleteDTO> convertToDto(ResultSet result) {
		try {
			if (result != null && result.first()) {
				List<AthleteDTO> dtos = new ArrayList<AthleteDTO>();
				do {
					dtos.add(new AthleteDTO(result.getLong(Column.ID.name), //
							result.getString(Column.IDENTNR.name), //
							result.getString(Column.LASTNAME.name), //
							result.getString(Column.FIRSTNAME.name), //
							result.getString(Column.COUNTRY.name), //
							result.getString(Column.ORGANISATION.name)) //
					);
				} while (result.next());
				return dtos;
			}
		} catch (SQLException e) {
			LOGGER.warning("SQLExeption (" + e.getErrorCode()
					+ ") occured while converting result to dto.");
		}
		return null;
	}

	@Override
	public int delete(AthleteDTO dto) {
		return executeSaveUpdateQuery("DELETE FROM " + TABLE_NAME + " WHERE "
				+ Column.ID.name + "=" + dto.getId() + ";");
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
		LOGGER.info("Drop table: " + TABLE_NAME);
		executeSaveUpdateQuery(getDropStatement());
	}

	@Override
	public void doCreateTable() {
		LOGGER.info("Create table: " + TABLE_NAME);
		executeSaveUpdateQuery(getCreateStatement());
	}
}
