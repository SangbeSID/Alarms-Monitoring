package events;

import java.time.LocalDateTime;
import generators.Localisation;

public class GazEvent extends AlarmEvent {
	private TypeGaz typeGaz;
	public static final int SEUIL_SECURITE = 50;
	
	public GazEvent(Object source, LocalDateTime date, Localisation l, int importance) {
		super(source, date, l, importance);
	}
	
	/*
	 * 
	 */
	public GazEvent(Object source, LocalDateTime date, Localisation l, int importance, TypeGaz t) {
		super(source, date, l, importance);
		this.typeGaz = t;
	}
	
	/*
	 * 
	 */
	public TypeGaz getTypeGaz() {
		return this.typeGaz;
	}
	
	/*
	 * 
	 */
	public void setType(TypeGaz g) {
		if(g != null)
			this.typeGaz = g;
	}
	
	@Override
	public String toString(){
		String txt = super.toString();
		return (txt + "-->" + this.typeGaz);
	}
}
