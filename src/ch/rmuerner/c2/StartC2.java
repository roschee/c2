/**
 * 
 */
package ch.rmuerner.c2;

import javax.swing.JFrame;

import ch.rmuerner.c2.ui.StartC2Frame;

/**
 * Starter class to start CompetitionControl application
 * 
 * @author Roger Muerner (roger.muerner@gmx.ch)
 */
public class StartC2 extends JFrame {

	/** serial version id */
	private static final long serialVersionUID = 5617715851643223182L;

	/**
	 * Starts CompetitionControl
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		 * Erzeugung eines neuen Frames mit dem Titel "Beispiel JFrame "
		 */
		JFrame meinFrame = new StartC2Frame();
	}

}
