package ihm;


import generators.Capteur;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import recievers.Moniteur;

/*
 * Classe utilisee pour representer la fenetre 
 * de gestion des alarmes.
 */
public class GestionAlarme extends MyFrame{
	
	private ZoneAlarme zone_alarme; // Zone alarme
	private JPanel myPanel;
	
	/*
	 * Le constructeur.
	 * 
	 * @param titre : Titre de la fenetre
	 */
	public GestionAlarme(String titre){
		super(titre);
		
		this.zone_alarme = new ZoneAlarme();
		this.myPanel = new JPanel();
		this.myPanel.add(this.zone_alarme);
		
		
		this.add(myPanel, BorderLayout.NORTH);
		this.setVisible(true);
		this.pack();
	}
	
	
	/*
	 * Methode utilisee pour modifier le moniteur de la zone 
	 */
	public void setMoniteur(Moniteur m){
		if(m!=null){
			this.zone_alarme.getCapteur().addMoniteur(m);
			System.out.println(m.getAllAlarms());
		}
	}
	
	/*
	 * Methode utilisee pour recuperer le capteur de la zone.
	 * 
	 * @return : Capteur associe a la zone
	 */
	public Capteur getCapteur(){
		return this.zone_alarme.getCapteur();
	}
	
	
	/*
	 * Methode utilisee pour recuperer la zone.
	 * 
	 * @return : Zone associee a la fenetre
	 */
	public ZoneAlarme getZoneAlarme(){
		return this.zone_alarme;
	}
	
}
