package generators;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import recievers.Moniteur;

import events.AlarmEvent;
import events.RadiationEvent;
import events.TypeGaz;
import listeners.RadiationEventListener;

public class RadiationGenerator extends Capteur {
	
	private List<RadiationEventListener> raditionListeners = new  ArrayList<>();
	
	/*
	 * Le constructeur par defaut
	 * 
	 * @param n : Nom du capteur
	 */
	public RadiationGenerator(String n) {
		super(n);
	}
	
	
	/*
	 * Le constructeur par defaut
	 * 
	 * @param n : Nom du capteur
	 */
	public RadiationGenerator(String n, Localisation l) {
		super(n, l);
	}
	
	
	@Override
	public void generateAlarm(int importance, int niveau) {
		AlarmEvent evt;
		LocalDateTime d = LocalDateTime.now();
		evt = new RadiationEvent(this, d, this.localisation, importance, niveau);
		this.localisation.addAlarmEvent(evt);
		for(RadiationEventListener l : this.raditionListeners)
			l.onRadiationEvent((RadiationEvent) evt);
		
	}
	

	
	
	/*
	 * 
	 */
	public void addRadiationEventListener(RadiationEventListener l) {
		if(l!=null)
			this.raditionListeners.add(l);
	}

	/*
	 * 
	 */
	public void removeIncendieEventListener(RadiationEventListener l) {
		if(l != null) 
			this.raditionListeners.remove(l);
	}


	@Override
	public void generateAlarm(int importance, TypeGaz t) {
	}
	
	
	@Override
	public void generateAlarm(int importance) {

	}


	@Override
	public void addMoniteur(Moniteur m) {
		this.addRadiationEventListener(m);
	}
}
