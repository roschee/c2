package ch.rmuerner.c2.ui.components;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ch.rmuerner.c2.db.DBAdapter;
import ch.rmuerner.c2.db.dao.h2.H2DAOFactory;
import ch.rmuerner.c2.db.dto.CompetitionDTO;

/**
 * TabAddCompetitor.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class TabAddAthlete extends JPanel implements ActionListener {

	JTextField nameTextField = new JTextField();
	JTextField locationTextField = new JTextField();
	JTextField dateTextField = new JTextField();
	JButton addButton = new JButton("Add");

	/** serial version ui */
	private static final long serialVersionUID = -9223319867902329553L;

	public TabAddAthlete() {
		GridLayout layout = new GridLayout(4, 2, 5, 5);
		this.setLayout(layout);

		this.add(new JLabel("Name: "));
		this.add(nameTextField);

		this.add(new JLabel("Location: "));
		this.add(locationTextField);

		this.add(new JLabel("Date: "));
		this.add(dateTextField);

		this.add(new JLabel());
		this.add(addButton);

		addButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == this.addButton) {
			H2DAOFactory daoFactory = DBAdapter.getInstance().getH2DAOFactory();
			daoFactory.getCompetitionDAO().saveOrUpdate(
					new CompetitionDTO(-1, nameTextField.getText(),
							locationTextField.getText(), Date
									.valueOf(dateTextField.getText())));
		}
	}
}
