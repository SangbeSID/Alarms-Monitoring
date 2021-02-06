package recievers;


import events.IncendieEvent;
import events.RadiationEvent;
/*
 * Classe utilisee pour represnter les moniteurs de typeA.
 */
public class MoniteurTypeA extends Moniteur{
	
		
	/*
	 * Le constructeur.
	 */
	public MoniteurTypeA() {
		super();
	}
	
		
	@Override
	public void onIncendieEvent(IncendieEvent evt) {
		if(evt != null)
			this.alarms.add(evt);
	}


	@Override
	public void onRadiationEvent(RadiationEvent evt) {
	}

}
