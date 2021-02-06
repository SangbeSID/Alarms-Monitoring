package control;

import events.AlarmEvent;
import events.TypeGaz;
import recievers.Moniteur;
import recievers.MoniteurTypeA;
import recievers.MoniteurTypeB;
import generators.Capteur;
import generators.GazGenerator;
import generators.IncendieGenerator;
import generators.Localisation;
import generators.RadiationGenerator;

public class MainConsol {

	public static void main(String[] args) {
		Localisation l = new Localisation("C214");
		Capteur cpt1 = new IncendieGenerator("Cpt_Inc", l);
		Capteur cpt2 = new GazGenerator("Cpt_Gaz", l);
		Capteur cpt3 = new RadiationGenerator("Cpt_Rad", l);
		
		Moniteur mA = new Moniteur();
		//Moniteur mB = new MoniteurTypeB();
		cpt1.addMoniteur(mA);
		cpt2.addMoniteur(mA);
		cpt3.addMoniteur(mA);
		
		cpt1.generateAlarm(1);
		cpt1.generateAlarm(2);
		cpt2.generateAlarm(2, TypeGaz.CO2);
		cpt3.generateAlarm(3, 50);
		
//		System.out.println(cpt1.getMoniteur().getAllAlarms());
//		System.out.println(cpt2.getMoniteur().getAllAlarms());
//		System.out.println(cpt3.getMoniteur().getAllAlarms());
//		System.out.println("\n");
		//System.out.println(mA.getAllAlarms());
		//System.out.println(mB.getAllAlarms().size());
		for(AlarmEvent a : mA.getAllAlarms())
			System.out.println(a);
		System.out.println("Bien Joué");
	}

}
