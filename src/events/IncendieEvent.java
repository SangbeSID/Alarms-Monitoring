package events;

import java.time.LocalDateTime;

import generators.Localisation;

public class IncendieEvent extends AlarmEvent{
	
	public IncendieEvent(Object source, LocalDateTime date, Localisation l, int importance) {
		super(source, date, l, importance);
	}
		
}
