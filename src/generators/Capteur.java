package generators;

import events.TypeGaz;
import recievers.Moniteur;

/*
 * Classe utilisee pour representer les capateurs.
 */
public abstract class Capteur{
	private String name; // Nom du capteur
	protected Localisation localisation; // Localisation du capteur

	/*
	 * 
	 */ 
	public abstract void generateAlarm(int importance);
	public abstract void generateAlarm(int importance, TypeGaz t);
	public abstract void generateAlarm(int importance, int niveau);
	/*
	 * Le constructeur par defaut
	 * 
	 * @param n : Nom du capteur
	 */
	public Capteur(String n) {
		this.name = n;	
	}
	
	
	/*
	 * Un autre constructeur.
	 * 
	 * @param n : Nom du capteur
	 * @param l : Localisation du capteur
	 */
	public Capteur(String n, Localisation l) {
		this.name = n;
		this.localisation = l;
	}
	
	
	
	/*
	 * Methode utilisee pour ajouter un moniteur du capteur.
	 * 
	 * @param m : Nouveau moniteur
	 */
	public abstract void addMoniteur(Moniteur m);
		
	
	/*
	 * Methode utilisee pour recuperer le nom du capteur.
	 */
	public String getName() {
		return this.name;
	}
	
	
	/*
	 * Methode utilisee pour recuperer la localisation du capteur.
	 */
	public Localisation getLocalisation() {
		return this.localisation;
	}
	

	
	/*
	 * Methode utilisee pour modifier la localisation du capteur.
	 * 
	 * @param l : Nouvelle localisation
	 */
	public void setLocalisation(Localisation l) {
		if(l != null) {
			this.localisation = l;
		}
	}
}
