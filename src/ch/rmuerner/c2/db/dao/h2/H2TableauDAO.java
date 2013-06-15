package ch.rmuerner.c2.db.dao.h2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ch.rmuerner.c2.db.dao.TableauDAO;
import ch.rmuerner.c2.db.dto.TableauDTO;

public class H2TableauDAO extends H2DAO<TableauDTO> implements TableauDAO {

	/** TODO comment */
	private static final String TABLE_NAME = "tableau";

	/** TODO comment */
	private enum Column {
		ID("id", "INT PRIMARY KEY AUTO_INCREMENT(1,1) NOT NULL"), //
		ENCOUNTERNR("encounterNr", "int NOT NULL"), //
		FIRSTCOMPETITORID("firstCompetitorId", "int NOT NULL"), //
		SECONDCOMPETITORID("secondCompetitorId", "int NOT NULL"), //
		WINNERID("winnerId", "int NOT NULL"), //
		NEXTENCOUNTERNR("nextEncounterNr", "int NOT NULL");
		
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
	public int saveOrUpdate(TableauDTO dto) {
		String query = getSaveOrUpdateQuery(dto);
		return executeSaveUpdateQuery(query);
	}
	
	@Override
	protected String getSaveOrUpdateQuery(TableauDTO dto) {
		StringBuilder queryBuilder = new StringBuilder();
		// Save
		if (dto.getId() == -1) {
			// SetUp Query
			queryBuilder.append("INSERT INTO " + TABLE_NAME);
			StringBuilder columns = new StringBuilder(" (");
			StringBuilder values = new StringBuilder(" VALUES(");
			// Map CompetitionDTO
			// encounterNr
			columns.append(Column.ENCOUNTERNR.name);
			values.append(dto.getEncounterNr());
			// firstCompetitorId
			columns.append(", " + Column.FIRSTCOMPETITORID.name);
			values.append(", " + dto.getFirstCompetitorId());
			// secondCompetitorId
			columns.append(", " + Column.SECONDCOMPETITORID.name);
			values.append(", " + dto.getSecondCompetitorId());
			// winnerId
			columns.append(", " + Column.WINNERID.name);
			values.append(", " + dto.getWinnerId());
			// nextEncounterNr
			columns.append(", " + Column.NEXTENCOUNTERNR.name);
			values.append(", " + dto.getNextEncounterNr());
			// endline
			columns.append(")");
			values.append(")");
			queryBuilder.append(columns).append(values).append(";");
		} else { // Update
			// SetUp Query
			queryBuilder.append("UPDATE " + TABLE_NAME + " SET ");
			// Map CompetitionDTO
			// encounterNr
			queryBuilder.append(Column.ENCOUNTERNR.name + //
					"=" + dto.getEncounterNr() + ", ");
			// firstCompetitorId
			queryBuilder.append(Column.FIRSTCOMPETITORID.name + //
					"=" + dto.getFirstCompetitorId() + ", ");
			// secondCompetitorId
			queryBuilder.append(Column.SECONDCOMPETITORID.name + //
					"=" + dto.getSecondCompetitorId() + ", ");
			// winnerId
			queryBuilder.append(Column.WINNERID.name + //
					"=" + dto.getWinnerId() + ", ");
			// nextEncounterNr
			queryBuilder.append(Column.NEXTENCOUNTERNR.name + //
					"=" + dto.getNextEncounterNr());
			// endline
			queryBuilder.append(" WHERE " + Column.ID.name + "=" + dto.getId()
					+ ";");

		}
		return queryBuilder.toString();
	}
	
	
	@Override
	public TableauDTO selectById(long id) {
		List<TableauDTO> dtos = executeSelectQuery("SELECT * FROM "
				+ TABLE_NAME + " WHERE " + Column.ID.name + "=" + id + ";");
		if (dtos != null && !dtos.isEmpty())
			return dtos.get(0);
		return null;
	}
	
	@Override
	public List<TableauDTO> selectAll() {
		return executeSelectQuery("SELECT * FROM " + TABLE_NAME
				+ ";");
	}
	
	@Override
	protected List<TableauDTO> convertToDto(ResultSet result) {
		try {
			if (result != null && result.first()) {
				List<TableauDTO> dtos = new ArrayList<TableauDTO>();
				do {
					dtos.add(new TableauDTO(result.getLong(Column.ID.name), //
							result.getLong(Column.ENCOUNTERNR.name), //
							result.getLong(Column.FIRSTCOMPETITORID.name), //
							result.getLong(Column.SECONDCOMPETITORID.name), //
							result.getLong(Column.WINNERID.name), //
							result.getLong(Column.NEXTENCOUNTERNR.name)) //
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
	public void doCreateTable() {
		executeSaveUpdateQuery(getCreateStatement());
	}
	
	@Override
	public void doDropTable() {
		executeSaveUpdateQuery(getDropStatement());
		
	}
}
