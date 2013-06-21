package ch.rmuerner.c2.ui.components;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;
import ch.rmuerner.c2.db.dto.CategoryDTO;

/**
 * TabCategory.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TabCategory extends JPanel {

	/** serial version id */
	private static final long serialVersionUID = -2209859861709576783L;

	public TabCategory() {
		super.addFocusListener(new FListener());
	}

	@Override
	public void repaint() {
		super.repaint();
		this.removeAll();
		H2DAOFactory daoFactory = DBAdapter.getInstance().getH2DAOFactory();
		List<CategoryDTO> categories = daoFactory.getCategoryDAO().selectAll();
		for (CategoryDTO categoryDTO : categories) {
			this.add(new JLabel(categoryDTO.getName()));
		}
	}
	
	private class FListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			repaint();
		}

		@Override
		public void focusLost(FocusEvent e) {
			// do nothing
		}
	}
}
