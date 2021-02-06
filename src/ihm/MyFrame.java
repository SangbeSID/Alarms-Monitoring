package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyFrame extends JFrame{
	private JButton bt_quit = new JButton("Quitter"); // Le bouton permettant de quitter le jeu

	/**
	 * Le constructeur.
	 * @param title  : titre de la fenetre
	 */
	public MyFrame(String title) {
		super(title);
		this.bt_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r = JOptionPane.showConfirmDialog(null, "Voulez-vous quitter?", "Fermeture",
						JOptionPane.YES_NO_OPTION);
				if (r == JOptionPane.YES_OPTION)
					System.exit(0);
			}
		});
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		panel.add(this.bt_quit);
		this.add(panel, BorderLayout.SOUTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
	
	/**
	 * Cette methode permet de recuperer le bouton permettant de quitter le jeu.
	 * 
	 * @return : Le bouton bt_quit
	 */
	public JButton getBtnQuitter() { 
		return this.bt_quit; 
	}

}
