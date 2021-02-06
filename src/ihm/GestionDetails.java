package ihm;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import events.AlarmEvent;
import events.GazEvent;
import events.IncendieEvent;
import events.RadiationEvent;

/*
 * Classe utilisee pour gerer les details des alarmes.
 */
public class GestionDetails extends JFrame{
	private ZoneDetails zone_detail;
	private JButton btn_fermer = new JButton("Fermer");
	public final static int ZONE_SIZE = 300;
	public final static int FRAME_SIZE = 400;
	
	/*
	 * Le constructeur.
	 * 
	 * @param evt : Alarme dont on represente les details.
	 */
	public GestionDetails(AlarmEvent evt){
		super("Details alarme");
		
		this.setPreferredSize(new Dimension(FRAME_SIZE, FRAME_SIZE)); // On fixe la taille 
		this.setResizable(false); // Impossibilite de redimensionner la fenetre
		this.zone_detail = new ZoneDetails(evt, ZONE_SIZE); // On cree la zone de details
		
		JPanel panel_btnFermer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		panel_btnFermer.add(this.btn_fermer, BorderLayout.SOUTH);
		
		
		JLabel lbl_localisation = new JLabel("Localisation : " + evt.getLocalisation().getName());
		JLabel lbl_date = new JLabel("Date Apparution : " + evt.getDateApparution());
		JLabel lbl_importance = new JLabel("Importance : " + evt.getImportance());
		
		this.btn_fermer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				GestionDetails.this.closeDetails(zone_detail.getAlarmEvent());
			}
		});
		
		Box box_info = new Box(BoxLayout.Y_AXIS);
		JLabel lbl_info = null;
		if(evt instanceof GazEvent){
			lbl_info = new JLabel("ALARME GAZ TOXIQUE");
		}
		else if(evt instanceof IncendieEvent){
			lbl_info = new JLabel("ALARME INCENDIE");
		}
		else{
			lbl_info = new JLabel("ALARME RADIATION");
		}
		lbl_info.setHorizontalAlignment(JLabel.CENTER);
		
		Box box_lbl_info = new Box(BoxLayout.X_AXIS);
		box_lbl_info.add(Box.createHorizontalGlue());
		box_lbl_info.add(lbl_info);
		box_lbl_info.add(Box.createHorizontalGlue());
		
		box_info.add(box_lbl_info);
		box_info.add(lbl_localisation);
		box_info.add(lbl_date);
		box_info.add(lbl_importance);
		
		if(evt instanceof GazEvent)
			box_info.add(new JLabel("Type de Gaz : " + ((GazEvent) evt).getTypeGaz()));
		
		if(evt instanceof RadiationEvent)
			box_info.add(new JLabel("Niveau de radiation : " + ((RadiationEvent) evt).getNiveauRadiation()));
		
		// -----
		JPanel panel = new JPanel();
		panel.add(box_info, BorderLayout.NORTH);
		panel.add(this.zone_detail);
		//this.add(box_info, BorderLayout.NORTH);
		//this.add(this.zone_detail, BorderLayout.CENTER);
		this.add(panel);
		// -----
		this.add(panel_btnFermer, BorderLayout.SOUTH);
		this.setVisible(true);
		this.pack();
	}
	
	/*
	 * Methode utilisee pour fermer la fenetre des details.
	 * Si l'alarme dont on affiche les details est la meme que
	 * celle passee en parametre, on ferme la fenetre.
	 * 
	 * @param evt : Alarme dont on veut fermer la fenetre de details.
	 */
	public void closeDetails(AlarmEvent evt){
		if(this.zone_detail.getAlarmEvent().equals(evt)){
			evt.closeDetails();
			this.dispose();
		}
	}
}
