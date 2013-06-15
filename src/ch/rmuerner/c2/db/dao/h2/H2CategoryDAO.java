package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.rmuerner.c2.db.dao.CategoryDAO;
import ch.rmuerner.c2.db.dto.CategoryDTO;

/**
 * H2CategoryDAO. Implementation of {@link CategoryDAO}.
 * 
 * @version 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class H2CategoryDAO extends H2DAO<CategoryDTO> implements CategoryDAO {

	/** Table name in database */
	private static final String TABLE_NAME = "category";

	/** Represents the category in the database */
	private enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		NAME("name", "VARCHAR(255) NOT NULL"), //
		MODUS("modusId", "INT NOT NULL"), //
		TABLEAUID("tableauId", "INT NOT NULL");

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
	public int saveOrUpdate(CategoryDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}

	/**
	 * Creates the save- or update-query.
	 * 
	 * @param dto
	 *            The {@link CategoryDTO} to save in the database
	 * @return Query with the save/update statement
	 */
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
			// location
			columns.append(", " + Column.MODUS.name);
			values.append(", " + dto.getModus());
			// date
			columns.append(", " + Column.TABLEAUID.name);
			values.append(", " + dto.getTableauId());
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
			// location
			queryBuilder.append(Column.MODUS.name + //
					"=" + dto.getModus() + ", ");
			// date
			queryBuilder.append(Column.TABLEAUID.name + //
					"=" + dto.getTableauId());
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
		return executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + ";");
	}

	@Override
	protected List<CategoryDTO> convertToDto(ResultSet result) {
		try {
			if (result != null && result.first()) {
				List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
				do {
					dtos.add(new CategoryDTO(result.getLong(Column.ID.name), //
							result.getString(Column.NAME.name), //
							result.getLong(Column.MODUS.name), //
							result.getLong(Column.TABLEAUID.name)) //
					);
				} while (result.next());
				return dtos;
			}
		} catch (SQLException e) {
			// TODO logging
			e.printStackTrace();
		}
		return null;
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
