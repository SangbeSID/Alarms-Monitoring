package events;

import ihm.GestionDetails;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EventObject;

import generators.Localisation;

/*
 * Classe utilisee pour gerer les alarmes.
 */
public class AlarmEvent extends EventObject{
	private String dateApparution; // Date d'apparution de l'alarme
	private Localisation localisation; // Localisation de l'alarme
	private int importance; // Importance de l'alarme
	private int nbDetails = 0; // Nbre de fois que l'alarme a ete visualisee
	private GestionDetails zoneDetails; // Fenetre affichant les details de l'alarme
	
	/*
	 * Le constructeur.
	 * 
	 * @param source 	: Générateur de l'alarme
	 * @param date 		: Date d'apparution de l'alarme
	 * @param l 		: Localisation de l'alarme
	 * importance 		: Importance du niveau d'alarme
	 */
	public AlarmEvent(Object source, LocalDateTime date, Localisation l, int importance) {
		super(source);
		
		DateTimeFormatter myFormat  =DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		this.dateApparution = date.format(myFormat);
		this.localisation = l;
		this.importance = importance;
	}
	
	
	/*
	 * Methode utilisee pour recuperer la date d'apparution de l'alarme.
	 * @return : Date d'apparution de l'alarme
	 */
	public String getDateApparution() {
		return dateApparution;
	}
	
	
	/*
	 * Methode utilisee pour recuperer la localisation de l'alarme.
	 * @return : Localisation de l'alarme
	 */
	public Localisation getLocalisation() {
		return localisation;
	}
	
	
	/*
	 * Methode utilisee pour recuperer la l'importance de l'alarme.
	 * @return : Importance de l'alarme
	 */
	public int getImportance() {
		return importance;
	}
	
	
	/*
	 * Lethode utilisee pour recuperer le nbre de fois que les details
	 * de l'alarme ont ete vus.
	 * @return : Nombre de fois que les details de l'alarme ont ete vus
	 */
	public int getNbreDetails(){
		return this.nbDetails;
	}
	
	
	/*
	 * Methode utilisee pour modifier le nbre de fois que les details de l'alarme
	 * ont ete vus.
	 * On incremente le nbre de details a chaque appel de la methode.
	 */
	public void setNbreDetails(){
		this.nbDetails++;
	}
	
	@Override
	public String toString(){
		return (this.dateApparution + " -->" + this.localisation + " -->" + this.importance);
	}
	
	/*
	 * Methode utilisee pour afficher les details de l'alarme
	 */
	public void showDetails(){
		if(this.zoneDetails == null)
			this.zoneDetails = new GestionDetails(this);
	}
	
	/*
	 * Methode utilisee pour fermer la fenetre des details
	 */
	public void closeDetails(){
		if(this.zoneDetails != null){			
			this.zoneDetails.dispose();
			this.zoneDetails = null;
		}
	}
}
