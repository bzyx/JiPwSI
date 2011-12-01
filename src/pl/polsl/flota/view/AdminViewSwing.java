package pl.polsl.flota.view;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class AdminViewSwing extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 */
	public AdminViewSwing() {
		super();
		JLabel jlbempty = new JLabel("Test tekst");
		this.add(jlbempty);
		this.setVisible(true);
	}
}
