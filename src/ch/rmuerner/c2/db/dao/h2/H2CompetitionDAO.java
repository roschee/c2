package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dao.CompetitionDAO;
import ch.rmuerner.c2.db.dto.CompetitionDTO;

/**
 * H2CompetitionDAO.
 * 
 * @version V0.1
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2CompetitionDAO extends H2DAO<CompetitionDTO> implements
		CompetitionDAO {

	private final static Logger LOGGER = Logger
			.getLogger(H2CompetitionDAO.class.getName());

	/** Database table name */
	private static final String TABLE_NAME = "competition";

	/** Database attributes of competition */
	private enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		NAME("name", "VARCHAR(255) NOT NULL"), //
		LOCATION("location", "VARCHAR(255) NOT NULL"), //
		DATE("date", "DATE NOT NULL");

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
	public int saveOrUpdate(CompetitionDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}

	@Override
	protected String getSaveOrUpdateQuery(CompetitionDTO dto) {
		StringBuilder queryBuilder = new StringBuilder();
		if (dto.getId() == -1) {
			// SetUp Query
			queryBuilder.append("INSERT INTO " + TABLE_NAME);
			StringBuilder columns = new StringBuilder(" (");
			StringBuilder values = new StringBuilder(" VALUES(");
			// Map CompetitionDTO
			// name
			columns.append(Column.NAME.name);
			values.append("'" + dto.getName() + "'");
			// location
			columns.append(", " + Column.LOCATION.name);
			values.append(", '" + dto.getLocation() + "'");
			// date
			columns.append(", " + Column.DATE.name);
			values.append(", '" + dto.getDate().toString() + "'");
			// endline
			columns.append(")");
			values.append(")");
			queryBuilder.append(columns).append(values).append(";");
		} else {
			// SetUp Query
			queryBuilder.append("UPDATE " + TABLE_NAME + " SET ");
			// Map CompetitionDTO
			// name
			queryBuilder.append(Column.NAME.name + //
					"='" + dto.getName() + "', ");
			// location
			queryBuilder.append(Column.LOCATION.name + //
					"='" + dto.getLocation() + "', ");
			// date
			queryBuilder.append(Column.DATE.name + //
					"='" + dto.getDate() + "'");
			// endline
			queryBuilder.append(" WHERE " + Column.ID.name + "=" + dto.getId()
					+ ";");

		}
		return queryBuilder.toString();
	}

	@Override
	public CompetitionDTO selectById(long id) {
		List<CompetitionDTO> dtos = executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + " WHERE " + Column.ID.name + "=" + id + ";");
		if (dtos != null && !dtos.isEmpty())
			return dtos.get(0);
		return null;
	}

	@Override
	public List<CompetitionDTO> selectAll() {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME + ";");
	}

	@Override
	protected List<CompetitionDTO> convertToDto(ResultSet result) {
		try {
			if (result != null && result.first()) {
				List<CompetitionDTO> dtos = new ArrayList<CompetitionDTO>();
				do {
					dtos.add(new CompetitionDTO(result.getLong(Column.ID.name), //
							result.getString(Column.NAME.name), //
							result.getString(Column.LOCATION.name), //
							result.getDate(Column.DATE.name)) //
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
	public int delete(CompetitionDTO dto) {
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
