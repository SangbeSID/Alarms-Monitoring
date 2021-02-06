package generators;

import java.util.List;

import events.AlarmEvent;

import java.util.ArrayList;

/*
 * Classe utilisee pour representer les localisations (salles, batiments, ...).
 */
public class Localisation {
	private String name; // Nom de la localisation
	private List<AlarmEvent> alarm = new ArrayList<>(); // Liste des alarmes detectees
	
	/*
	 * Le constructeur.
	 * 
	 * @param n : Nom de la localisation
	 */
	public Localisation(String n) {
		this.name = n;
	}
	
	/*
	 * 
	 */
	public String getName() {
		return this.name;
	}
	
	/*
	 * 
	 */
	public void addAlarmEvent(AlarmEvent a) {
		if(a!=null)
			this.alarm.add(a);
	}
	
	/*
	 * 
	 */
	public List<AlarmEvent> getAlarms(){
		return this.alarm;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
