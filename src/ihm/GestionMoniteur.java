package ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import recievers.Moniteur;
import recievers.MoniteurTypeA;
import recievers.MoniteurTypeB;

/*
 * Classe utilisee pour gerer les moniteurs.
 */
public class GestionMoniteur extends MyFrame{
	
	private ZoneMoniteur zone_moniteur; // Zone de gestion des moniteurs
	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JRadioButton rbtn_moniteurTypeA = new JRadioButton("Pompiers");
	private JRadioButton rbtn_moniteurTypeB = new JRadioButton("Service Environnement");
	private JRadioButton rbtn_moniteurCentral = new JRadioButton("Moniteur Central");
	
	public GestionMoniteur(String title) {
		super(title);
		this.zone_moniteur = new ZoneMoniteur();
		this.rbtn_moniteurCentral.setSelected(true);
		
		this.rbtn_moniteurCentral.addMouseListener(new GestionMoniteurController());
		this.rbtn_moniteurTypeA.addMouseListener(new GestionMoniteurController());
		this.rbtn_moniteurTypeB.addMouseListener(new GestionMoniteurController());
		
		this.rbtn_moniteurCentral.setOpaque(false);
		this.rbtn_moniteurTypeA.setOpaque(false);
		this.rbtn_moniteurTypeB.setOpaque(false);
		
		this.panel.add(this.rbtn_moniteurCentral);
		this.panel.add(this.rbtn_moniteurTypeA);
		this.panel.add(this.rbtn_moniteurTypeB);
		this.add(this.panel, BorderLayout.NORTH);
		this.add(this.zone_moniteur);
		this.setVisible(true);
		this.pack();
	}
	
	/*
	 * Methode utilisee pour recupere la zone des moniteurs.
	 */
	public ZoneMoniteur getZoneMoniteur(){
		return this.zone_moniteur;
	}
	
	/*
	 * Methode utilisee pour rafraichir la liste des alarmes
	 * lors de la generation d'une alarme.
	 */
	public void refresh(){
		this.rbtn_moniteurCentral.setSelected(true);
		this.rbtn_moniteurTypeA.setSelected(false);
		this.rbtn_moniteurTypeB.setSelected(false);
		this.zone_moniteur.refresh();
		this.pack();
	}
	
	/*
	 * Methode utilisee pour rafraichir la liste des alarmes
	 * lors de la visualisation des details.
	 */
	public void updateListeAlarmes(){
		this.zone_moniteur.updateListeAlarmes();
		this.pack();
	}
	
	/*
	 * Classe utilisee pour gerer les differents moniteurs.
	 */
	private class GestionMoniteurController extends MouseAdapter{
		// Selon le moniteur choisi, on affiche les alarmes concernees.
		@Override
		public void mouseClicked(MouseEvent evt){
			if(evt.getSource() == rbtn_moniteurCentral){
				rbtn_moniteurCentral.setSelected(true);
				rbtn_moniteurTypeA.setSelected(false);
				rbtn_moniteurTypeB.setSelected(false);
				zone_moniteur.upgradeListeAlarmes(new Moniteur());
				zone_moniteur.setBackground(Color.white);
				panel.setBackground(Color.white);
			}
			
			if(evt.getSource() == rbtn_moniteurTypeA){
				rbtn_moniteurCentral.setSelected(false);
				rbtn_moniteurTypeA.setSelected(true);
				rbtn_moniteurTypeB.setSelected(false);
				zone_moniteur.upgradeListeAlarmes(new MoniteurTypeA());
				zone_moniteur.setBackground(Color.orange);
				panel.setBackground(Color.orange);
			}
			
			if(evt.getSource() == rbtn_moniteurTypeB){
				rbtn_moniteurCentral.setSelected(false);
				rbtn_moniteurTypeA.setSelected(false);
				rbtn_moniteurTypeB.setSelected(true);
				zone_moniteur.upgradeListeAlarmes(new MoniteurTypeB());
				zone_moniteur.setBackground(Color.green);
				panel.setBackground(Color.green);
			}
			//GestionMoniteur.this.repaint();
		}
	}
}
