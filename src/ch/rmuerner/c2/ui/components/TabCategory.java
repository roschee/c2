package ch.rmuerner.c2.ui.components;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ch.rmuerner.c2.util.TableHelper;

/**
 * TabCategory.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TabCategory extends JPanel{

	/** serial version id */
	private static final long serialVersionUID = -2209859861709576783L;

	public TabCategory() {
		repaint();
	}

	@Override
	public void repaint() {
		super.repaint();
		this.removeAll();
		ResourceBundle messages = ResourceBundle
				.getBundle("ch.rmuerner.c2.i18n.messages");

		setLayout(new BorderLayout(0, 0));

		// Put CategoryTable in a ScrollPane
		JScrollPane scrollPane = new JScrollPane(
				TableHelper.getFullCategoryTable());

		// Add components to panel
		add(scrollPane, BorderLayout.CENTER);
		add(new JLabel(messages.getString("category.title")), BorderLayout.NORTH);
	}
}
