package generators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import recievers.Moniteur;

import events.AlarmEvent;
import events.IncendieEvent;
import events.TypeGaz;
import listeners.IncendieEventListener;

/*
 * Classe utilisee pour representer les capteurs de type incendie.
 */
public class IncendieGenerator extends Capteur{
	
	private List<IncendieEventListener> incendieListener; // Liste des ecouteurs de type incendie.
	
	/*
	 * Le constructeur par defaut
	 * 
	 * @param n : Nom du capteur
	 */
	public IncendieGenerator(String n) {
		super(n);
		this.incendieListener = new ArrayList<IncendieEventListener>();
	}
	
	
	/*
	 * Un autre constructeur.
	 * 
	 * @param n : Nom du capteur
	 * @param l : Localisation du capteur
	 */
	public IncendieGenerator(String n, Localisation l) {
		super(n, l);
		this.incendieListener = new ArrayList<IncendieEventListener>();
	}
	
	
	// =============================================
	@Override
	public void generateAlarm(int importance) {
		AlarmEvent evt;
		LocalDateTime d = LocalDateTime.now();
		evt = new IncendieEvent(this, d, this.localisation, importance);
		this.localisation.addAlarmEvent(evt);
		for(IncendieEventListener l : this.incendieListener)
			l.onIncendieEvent((IncendieEvent) evt);
		
	}
	
	// =============================================
	public void addIncendieEventListener(IncendieEventListener l) {
		if(l!=null)
			this.incendieListener.add(l);
	}
		
	// =============================================
	/*
	* 
	*/
	public void removeIncendieEventListener(IncendieEventListener l) {
		if(l != null)
			this.incendieListener.remove(l);							
	}


	@Override
	public void generateAlarm(int importance, TypeGaz t) {
	}


	@Override
	public void generateAlarm(int importance, int niveau) {
	}


	@Override
	public void addMoniteur(Moniteur m) {
		this.addIncendieEventListener(m);		
	}
}
