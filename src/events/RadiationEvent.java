package events;

import java.time.LocalDateTime;

import generators.Localisation;

public class RadiationEvent extends AlarmEvent{
	private int level;
	
	public RadiationEvent(Object source, LocalDateTime date, Localisation l, int importance) {
		super(source, date, l, importance);
	}
	
	public RadiationEvent(Object source, LocalDateTime date, Localisation l, int importance, int level) {
		super(source, date, l, importance);
		this.level = level;
	}
	
	/*
	 * 
	 */
	public int getNiveauRadiation() {
		return this.level;
	}
	
	/*
	 * 
	 */
	public void setLevel(int level) {
		if(level > 0 && level <= 100)
			this.level = level;
	}
	
	
	@Override
	public String toString(){
		String txt = super.toString();
		return (txt + "-->" + this.level);
	}
		
}
