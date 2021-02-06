package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import events.AlarmEvent;

import generators.Capteur;
import generators.GazGenerator;
import generators.IncendieGenerator;
import generators.RadiationGenerator;
import ihm.GestionAlarme;
import ihm.GestionMoniteur;
import ihm.Historique;
import ihm.ZoneAlarme;

/*
 * Clase utilisee pour gerer le systeme de controle.
 */
public class TourDeControle {
	private GestionAlarme gestion_alarme; // Generateur d'alarmes
	private GestionMoniteur gestion_moniteur; // Le moniteur principal
	private Historique historique; // Historique des alarmes declenchees
	/*
	 * Constructeur.
	 */
	public TourDeControle(){
		this.gestion_alarme = new GestionAlarme("Gestion des Alarmes");
		this.gestion_moniteur = new GestionMoniteur("Gestion des Moniteurs");
		this.historique = new Historique();
		this.gestion_alarme.getZoneAlarme().getBtEnvoyer().addActionListener(new MyController());
		this.gestion_moniteur.getZoneMoniteur().getBtnDetails().addMouseListener(new MyController());
		this.gestion_moniteur.getZoneMoniteur().getBtnTraitee().addMouseListener(new MyController());
	}
	
	/*
	 * Classe utilisee comme Controler pour gerer les interactions entre les
	 * generateurs d'alarmes et les moniteurs. 
	 */
	private class MyController extends MouseAdapter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			ZoneAlarme zone = gestion_alarme.getZoneAlarme(); // On recupere la zone des alarmes
			String bat = zone.getBatiment().getName(); // On recupere le nom du batiment
			
			// Selon l'option selectionnee, on cree le capteur correspondant
			// qui est ensuite associe a la zone des alarmes. Puis, a ce capteur, on associe
			// le moniteur de correspondant. Enfin, on genere l'alarme correspondante, et
			// on l'ajoute a l'historique
			
			if(zone.getBtnIncendie().isSelected()) { // Si on selectionne le btn incendie
				Capteur cpt = new IncendieGenerator("Inc_" + bat, zone.getBatiment());
				gestion_alarme.getZoneAlarme().setCapteur(cpt);
				cpt.addMoniteur(gestion_moniteur.getZoneMoniteur().getMoniteur());
				cpt.addMoniteur(historique.getMoniteur());
				cpt.generateAlarm(zone.getImportance());
				gestion_moniteur.refresh();
				historique.upgradeStatistiques();
			}
			
			// Si on selectionne l'option radiation
			if(zone.getBtnRadiation().isSelected() && zone.getNiveauRadiation() > 0) {
				Capteur cpt = new RadiationGenerator("Inc_" + bat, zone.getBatiment());
				gestion_alarme.getZoneAlarme().setCapteur(cpt);
				cpt.addMoniteur(gestion_moniteur.getZoneMoniteur().getMoniteur());		
				cpt.addMoniteur(historique.getMoniteur());
				cpt.generateAlarm(zone.getImportance(), zone.getNiveauRadiation());
				gestion_moniteur.refresh();
				historique.upgradeStatistiques();
			}
			
			if(zone.getBtnGaz().isSelected()) { // Si on selectionne l'option gaz toxique.
				Capteur cpt = new GazGenerator("Inc_" + bat, zone.getBatiment());
				gestion_alarme.getZoneAlarme().setCapteur(cpt);
				cpt.addMoniteur(gestion_moniteur.getZoneMoniteur().getMoniteur());	
				cpt.addMoniteur(historique.getMoniteur());
				cpt.generateAlarm(zone.getImportance(), zone.getTypeGaz());
				gestion_moniteur.refresh();
				historique.upgradeStatistiques();
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent evt){
			// Si l'on clique sur le bouton "details".
			// On affiche les details de l'alarme selectionnee dans la liste
			if(evt.getSource() == gestion_moniteur.getZoneMoniteur().getBtnDetails()){
				if(gestion_moniteur.getZoneMoniteur().getNbreAlarmes() > 0){
					AlarmEvent alarm = (AlarmEvent) gestion_moniteur.getZoneMoniteur().getListeAlarmes().getSelectedItem();
					alarm.setNbreDetails();
					gestion_moniteur.updateListeAlarmes();
					alarm.showDetails();
				}
			}
			// Si l'on clique sur le bouton traitee.
			// On ferme la fenetre de details de l'alrme selectionnee et on supprime
			// cette derniere de la liste des alarmes du moniteur.
			if(evt.getSource() == gestion_moniteur.getZoneMoniteur().getBtnTraitee()){
				if(gestion_moniteur.getZoneMoniteur().getListeAlarmes().getItemCount() > 0){
					AlarmEvent alarme = (AlarmEvent) gestion_moniteur.getZoneMoniteur().getListeAlarmes().getSelectedItem();
					alarme.closeDetails();
					gestion_moniteur.getZoneMoniteur().removeAlarme(alarme);
				}
			}
		}
	}
}
