package generators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import recievers.Moniteur;

import events.AlarmEvent;
import events.GazEvent;
import events.TypeGaz;
import listeners.GazEventListener;

/*
 * Classe utilisee pour gerer les capteurs de type gaz toxique.
 */
public class GazGenerator extends Capteur {
	
	private List<GazEventListener> gazListeners = new  ArrayList<GazEventListener>();
	
	/*
	 * Le constructeur par defaut
	 * 
	 * @param n : Nom du capteur
	 */
	public GazGenerator(String n) {
		super(n);
	}
	
	
	/*
	 * Un autre constructeur.
	 * 
	 * @param n : Nom du capteur
	 * @param l : Localisation du capteur
	 */
	public GazGenerator(String n, Localisation l) {
		super(n, l);
	}
	
	// =============================================
	@Override
	public void generateAlarm(int importance, TypeGaz g) {
		AlarmEvent evt;
		LocalDateTime d = LocalDateTime.now(); // On recupere la date du jour.
		evt = new GazEvent(this, d, this.localisation, importance, g);
		this.localisation.addAlarmEvent(evt);
		for(GazEventListener l : this.gazListeners)
			l.onGazEvent((GazEvent) evt);
	}
	
	
	@Override
	public void generateAlarm(int importance) {
	}
	
	@Override
	public void generateAlarm(int importance, int niveau) {
	}
	
	/*
	 * 
	 */
	public void addGazEventListener(GazEventListener l) {
		if(l!=null)
			this.gazListeners.add(l);
	}
	
	/*
	 * 
	 */
	public void removeGazEventListener(GazEventListener l) {
		if(l != null)
			this.gazListeners.remove(l);			
	}


	@Override
	public void addMoniteur(Moniteur m) {
		this.addGazEventListener(m);
	}


}
