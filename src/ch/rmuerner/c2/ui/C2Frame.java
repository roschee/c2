package ch.rmuerner.c2.ui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ch.rmuerner.c2.ui.components.MenuBar;
import ch.rmuerner.c2.ui.components.TabbedPane;

/**
 * StartC2Frame.
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class C2Frame extends JFrame {

	/** serial version id */
	private static final long serialVersionUID = 93950361715461852L;

	public C2Frame() {
		super("C2");

		this.setSize(1024, 768);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(new MenuBar());

		JPanel panel = new JPanel(new BorderLayout());

		panel.add(BorderLayout.CENTER, new TabbedPane());

		this.add(panel);
		this.setVisible(true);
	}
}
