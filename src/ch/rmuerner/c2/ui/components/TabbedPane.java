package ch.rmuerner.c2.ui.components;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * TabbedPane.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TabbedPane extends JPanel {

	/** serial version id */
	private static final long serialVersionUID = -2951319262064117277L;

	public TabbedPane() {
		super(new GridLayout(1, 1));

		JTabbedPane tabbedPane = new JTabbedPane();

		tabbedPane.addTab("Category", new TabCategory());

		tabbedPane.addTab("Athlete", new TabAthlete());

		tabbedPane.addTab("Add Athlete", new TabAddAthlete());

		add(tabbedPane);

		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	// /** Returns an ImageIcon, or null if the path was invalid. */
	// protected static ImageIcon createImageIcon(String path) {
	// java.net.URL imgURL = TabbedPane.class.getResource(path);
	// if (imgURL != null) {
	// return new ImageIcon(imgURL);
	// } else {
	// System.err.println("Couldn't find file: " + path);
	// return null;
	// }
	// }
}
