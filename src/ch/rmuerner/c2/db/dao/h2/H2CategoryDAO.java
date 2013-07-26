package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ch.rmuerner.c2.db.dao.CategoryDAO;
import ch.rmuerner.c2.db.dto.CategoryDTO;
import ch.rmuerner.c2.db.dto.CategoryDTO.State;

/**
 * H2CategoryDAO. Implementation of {@link CategoryDAO}.
 * 
 * @version V1.0
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2CategoryDAO extends H2DAO<CategoryDTO> implements CategoryDAO {

	/** Logger */
	private final static Logger LOGGER = Logger.getLogger(H2CategoryDAO.class
			.getName());

	/** Database table name */
	private static final String TABLE_NAME = "category";

	/** Database attributes of competition */
	public enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		NAME("name", "VARCHAR(255) NOT NULL"), //
		MODUS("modusId", "INT NOT NULL"), //
		COMPETITIONID("competitionId", "INT NOT NULL"), //
		TABLEAULINK("tableauLink", "VARCHAR(255) NOT NULL"), //
		STATUS("status", "VARCHAR(255) NOT NULL");

		private String name;

		private String type;

		private Column(String name, String type) {
			this.name = name;
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}
	}

	@Override
	public int saveOrUpdate(CategoryDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}

	@Override
	protected String getSaveOrUpdateQuery(CategoryDTO dto) {
		StringBuilder queryBuilder = new StringBuilder();
		// Save
		if (dto.getId() == -1) {
			// SetUp Query
			queryBuilder.append("INSERT INTO " + TABLE_NAME);
			StringBuilder columns = new StringBuilder(" (");
			StringBuilder values = new StringBuilder(" VALUES(");
			// Map CompetitionDTO
			// name
			columns.append(Column.NAME.name);
			values.append("'" + dto.getName() + "'");
			// modus
			columns.append(", " + Column.MODUS.name);
			values.append(", " + dto.getModus());
			// competition
			columns.append(", " + Column.COMPETITIONID.name);
			values.append(", " + dto.getCompetitionId());
			// tableau
			columns.append(", " + Column.TABLEAULINK.name);
			values.append(", '" + dto.getTableauLink() + "'");
			// statuts
			columns.append(", " + Column.STATUS.name);
			values.append(", '" + dto.getStatus().toString() + "'");
			// endline
			columns.append(")");
			values.append(")");
			queryBuilder.append(columns).append(values).append(";");
		} else { // Update
			// SetUp Query
			queryBuilder.append("UPDATE " + TABLE_NAME + " SET ");
			// Map CompetitionDTO
			// name
			queryBuilder.append(Column.NAME.name + //
					"='" + dto.getName() + "', ");
			// modus
			queryBuilder.append(Column.MODUS.name + //
					"=" + dto.getModus() + ", ");
			// competition
			queryBuilder.append(Column.COMPETITIONID.name + //
					"=" + dto.getCompetitionId() + ", ");
			// tableau
			queryBuilder.append(Column.TABLEAULINK.name + //
					"='" + dto.getTableauLink() + "', ");
			// status
			queryBuilder.append(Column.STATUS.name + //
					"='" + dto.getStatus().toString() + "'");
			// endline
			queryBuilder.append(" WHERE " + Column.ID.name + "=" + dto.getId()
					+ ";");

		}
		return queryBuilder.toString();
	}

	@Override
	public CategoryDTO selectById(long id) {
		List<CategoryDTO> dtos = executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + " WHERE " + Column.ID.name + "=" + id + ";");
		if (dtos != null && !dtos.isEmpty())
			return dtos.get(0);
		return null;
	}

	@Override
	public List<CategoryDTO> selectAll() {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME + ";");
	}

	@Override
	protected List<CategoryDTO> convertToDto(ResultSet result) {
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		try {
			if (result != null && result.first()) {
				do {
					dtos.add(new CategoryDTO(result.getLong(Column.ID.name), //
							result.getString(Column.NAME.name), //
							result.getLong(Column.MODUS.name), //
							result.getLong(Column.COMPETITIONID.name), //
							result.getString(Column.TABLEAULINK.name), //
							State.valueOf(result.getString(Column.STATUS.name))) //
					);
				} while (result.next());
			}
		} catch (SQLException e) {
			LOGGER.warning("SQLExeption (" + e.getErrorCode()
					+ ") occured while converting result to dto.");
		}
		return dtos;
	}

	@Override
	public int delete(CategoryDTO dto) {
		int affectedRowsCount = executeSaveUpdateQuery("DELETE FROM " + TABLE_NAME + " WHERE "
				+ Column.ID.name + "=" + dto.getId() + ";");
		return affectedRowsCount;
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
