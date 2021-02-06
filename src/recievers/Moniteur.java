package recievers;

import java.util.ArrayList;

import listeners.GazEventListener;
import listeners.IncendieEventListener;
import listeners.RadiationEventListener;
import events.AlarmEvent;
import events.GazEvent;
import events.IncendieEvent;
import events.RadiationEvent;

/*
 * Classe utilisee pour represneter les moniteurs.
 */
public class Moniteur implements GazEventListener, IncendieEventListener, RadiationEventListener{
	protected ArrayList<AlarmEvent> alarms;
	
	/*
	 * Le constructeur.
	 */
	public Moniteur() {
		this.alarms = new ArrayList<AlarmEvent>();
	}
	
	
	/*
	 * Methode utilisee pour recuperer la liste des alarmes.
	 * 
	 * @return : Liste des alarmes du moniteur
	 */
	public ArrayList<AlarmEvent> getAllAlarms(){
		return this.alarms;
	}
	
	
	/*
	 * Methode utilisee pour ajouter une alarme de type gaz toxique
	 * à la liste des alarmes du moniteur.
	 * @see listeners.GazEventListener#onGazEvent(events.GazEvent)
	 */
	@Override
	public void onGazEvent(GazEvent evt) {
		if(evt != null)
			this.alarms.add(evt);		
	}

	
	/*
	 * Methode utilisee pour ajouter une alarme de type radiation
	 * à la liste des alarmes du moniteur.
	 * @see listeners.RadiationEventListener#onRadiationEvent(events.RadiationEvent)
	 */
	@Override
	public void onRadiationEvent(RadiationEvent evt) {
		if(evt != null)
			this.alarms.add(evt);		
	}

	/*
	 * Methode utilisee pour ajouter une alarme de type incendie
	 * à la liste des alarmes du moniteur.
	 * @see listeners.IncendieEventListener#onIncendieEvent(events.IncendieEvent)
	 */
	@Override
	public void onIncendieEvent(IncendieEvent evt) {
		if(evt != null)
			this.alarms.add(evt);		
	}
	
	
	/*
	 * Methode utilisee pour supprimer une alarme de la liste des alarmes.
	 * 
	 * @param alarm : Alarme a supprimer
	 */
	public void removeAlarmEvent(AlarmEvent alarm){
		if(alarm != null)
			this.alarms.remove(alarm);
	}
}
