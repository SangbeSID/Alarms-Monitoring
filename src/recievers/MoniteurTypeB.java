package recievers;

import events.IncendieEvent;

import events.RadiationEvent;
/*
 * Classe utilisee pour represnter les moniteurs de typeA.
 */
public class MoniteurTypeB extends Moniteur{
	
	/*
	 * Le constructeur.
	 */
	public MoniteurTypeB() {
		super();
	}

	@Override
	public void onRadiationEvent(RadiationEvent evt) {
		if(evt != null)
			this.alarms.add(evt);
	}

	@Override
	public void onIncendieEvent(IncendieEvent evt) {
	}
	
}
