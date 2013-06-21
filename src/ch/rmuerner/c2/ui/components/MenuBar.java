package ch.rmuerner.c2.ui.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;

/**
 * MenuBar.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class MenuBar extends JMenuBar implements ActionListener {

	/** serial version id */
	private static final long serialVersionUID = -1092067407803323569L;

	JMenu mFile;
	// JMenuItem miParabel;
	JMenuItem miExit;

	JMenu mEdit;
	JMenuItem miReset;
	JMenuItem miLoad;
	JMenuItem miPreferences;

	JMenu mHelp;
	JMenuItem miHelp;
	JMenuItem miInfo;

	public MenuBar() {
		mFile = new JMenu("Datei");
		mEdit = new JMenu("Bearbeiten");
		mHelp = new JMenu("Hilfe");

		// miParabel = new JMenuItem("Parabel ausgeben");
		miExit = new JMenuItem("Programm beenden");

		miReset = new JMenuItem("Programm zurücksetzen");
		miReset.addActionListener(this);
		miLoad = new JMenuItem("Lade testdaten");
		miLoad.addActionListener(this);
		miPreferences = new JMenuItem("Einstellungen");

		miHelp = new JMenuItem("Hilfe");
		miInfo = new JMenuItem("Über...");

		// file.add(parabel);
		mFile.add(miExit);

		mEdit.add(miReset);
		mEdit.add(miLoad);
		mEdit.add(miPreferences);

		mHelp.add(miHelp);
		mHelp.add(miInfo);

		this.add(mFile);
		this.add(mEdit);
		this.add(mHelp);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			if(e.getSource() == miReset){
				H2DAOFactory daoFactory = DBAdapter.getInstance().getH2DAOFactory();
				daoFactory.initDatabase();
			} else if(e.getSource() == miLoad){
				H2DAOFactory daoFactory = DBAdapter.getInstance().getH2DAOFactory();
				daoFactory.loadTestData();
			}
	}
}
