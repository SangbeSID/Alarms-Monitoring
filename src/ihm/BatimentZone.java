package ihm;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import generators.Localisation;

public class BatimentZone extends Box {
	private JComboBox<Localisation> listeBatiments;
	
	public BatimentZone() {
		super(BoxLayout.X_AXIS);
		Localisation[] tab = new Localisation[14];
		int numero = 3;
		for(int i=0 ; i<14 ; i++) {
			tab[i] = new Localisation("C20" + (numero + i));
			//tab.add(l);
		}
		
		this.listeBatiments = new JComboBox<Localisation>(tab);
		
		
		JLabel lbl_batiment = new JLabel("Batiment");
		this.add(lbl_batiment);
		this.add(Box.createHorizontalStrut(10));
		this.add(this.listeBatiments);
		this.setVisible(true);
		
	}
	
	/*
	 * 
	 */
	public JComboBox<Localisation> getListeBatiments(){
		return this.listeBatiments;
	}
}
