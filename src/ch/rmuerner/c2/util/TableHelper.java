package ch.rmuerner.c2.util;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JTable;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dao.h2.H2CategoryDAO.Column;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;
import ch.rmuerner.c2.db.dto.CategoryDTO;

/**
 * TableHelper.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TableHelper {

	private static H2DAOFactory daoFactory = DBAdapter.getInstance()
			.getH2DAOFactory();

	/**
	 * Creates a JTable with all categories
	 * 
	 * @return JTable
	 */
	public static JTable getFullCategoryTable() {
		// Get Data
		List<CategoryDTO> categories = daoFactory.getCategoryDAO().selectAll();
		// Convert data
		Vector<Vector<String>> tableVector = new Vector<Vector<String>>();
		for (CategoryDTO categoryDto : categories) {
			Vector<String> row = new Vector<String>();
			row.add(categoryDto.getName());
			row.add(String.valueOf(categoryDto.getModus()));
			row.add(String.valueOf(categoryDto.getTableauLink()));
			row.add(categoryDto.getStatus().toString());
			tableVector.add(row);
		}

		// Get Title Labels
		ResourceBundle messages = ResourceBundle
				.getBundle("ch.rmuerner.c2.i18n.messages");

		Vector<String> columnTitles = new Vector<String>();
		columnTitles.add(messages.getString(Column.NAME.getName()));
		columnTitles.add(messages.getString(Column.MODUS.getName()));
		columnTitles.add(messages.getString(Column.TABLEAULINK.getName()));
		columnTitles.add(messages.getString(Column.STATUS.getName()));

		// Make Table
		JTable table = new JTable(tableVector, columnTitles);
		table.setFillsViewportHeight(true);

		return table;
	}
}
