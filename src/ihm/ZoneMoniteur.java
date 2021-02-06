package ihm;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import recievers.Moniteur;
import recievers.MoniteurTypeA;
import recievers.MoniteurTypeB;
import events.AlarmEvent;
import events.GazEvent;
import events.IncendieEvent;
import events.RadiationEvent;

/*
 * Classe utilisee pour representer la zone de visualisation
 * du moniteur.
 */
public class ZoneMoniteur extends JPanel{
	
	private Moniteur moniteur; // Moniteur de la zone
	private JButton btn_detail = new JButton("Details"); // Bouton utilise pour afficher les details
	private JButton btn_traitee = new JButton("Traitee"); // Bouton utilise pour traiter les alarmes
	private JComboBox<AlarmEvent> list_evt; // Liste des alarmes(evenements)
	
	/*
	 * Le constructeur
	 */
	public ZoneMoniteur() {
		super();
		this.moniteur = new Moniteur();
		this.btn_traitee.setEnabled(false);
		
		// -- On cree la liste des evenements
		this.list_evt = new JComboBox<AlarmEvent>();
		this.list_evt.addItemListener(new ZoneMoniteurController());
		
		// -- On place les composantes dans une boite
		Box box_list_evt = new Box(BoxLayout.X_AXIS);
		JLabel lbl_list = new JLabel("Evenement");
		box_list_evt.add(lbl_list);
		box_list_evt.add(Box.createHorizontalStrut(10));
		box_list_evt.add(this.list_evt);
		box_list_evt.add(Box.createHorizontalStrut(10));
		box_list_evt.add(this.btn_detail);
		box_list_evt.add(Box.createHorizontalStrut(10));
		box_list_evt.add(this.btn_traitee);
		
		// -- On met la boite dans un panel
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.add(box_list_evt);
		
		this.add(panel);
		this.setVisible(true);
		this.setOpaque(true);
	}
	
	
	/*
	 * Methode utilisee pour recuperer la liste des alarmes
	 * 
	 * @return : Liste des alarmes de la zone
	 */
	public JComboBox<AlarmEvent> getListeAlarmes(){
		return this.list_evt;
	}
	
	/*
	 * Methode utilisee pour recuperer le bouton detail.
	 */
	public JButton getBtnDetails(){
		return this.btn_detail;
	}
	
	/*
	 * Methode utilisee pour recuperer le bouton traitee.
	 */
	public JButton getBtnTraitee(){
		return this.btn_traitee;
	}
	
	/*
	 * Methode utilisee pour recuperer le moniteur.
	 * 
	 * @return : Moniteur de la zone
	 */
	public Moniteur getMoniteur(){
		return this.moniteur;
	}
	
	
	/*
	 * Methode utilisee pour modifier le moniteur de la zone
	 * 
	 *  @param m : Nouveau moniteur
	 */
	public void setMoniteur(Moniteur m) {
		if(m != null)
			this.moniteur = m;
	}
	
	/*
	 * Methode utilisee pour recuperer le nombre d'alarmes
	 * 
	 * @return : Nombre d'alarme detectes par le moniteur
	 */
	public int getNbreAlarmes(){
		return this.list_evt.getItemCount();
	}
	
	
	/*
	 * Methode utilisee pour supprimer une alamre de la liste des alarmes.
	 * 
	 * @param alarme : Alarme a supprimer
	 */
	public void removeAlarme(AlarmEvent alarme){
		if((alarme != null) && (this.list_evt.getItemCount() > 0)){
			this.moniteur.removeAlarmEvent(alarme);
			this.list_evt.removeItem(alarme);
			this.repaint();
		}
		if(this.list_evt.getItemCount() == 0)
			this.btn_traitee.setEnabled(false);
	}
	
	/*
	 * Methode utilisee pour remplir la liste des Alarmes lors de 
	 * la generation d'une alarme.
	 * On recupere la derniere alarme recue par le moniteur puis on
	 * on ajouite cette derniere a la liste des alarmes.
	 * Selon le type d'alarme, on cree une notification adaptee.
	 */
	public void refresh(){
		if(this.moniteur.getAllAlarms().size() > 0){
			int s = this.moniteur.getAllAlarms().size();
			// -- On recupère le dernier evt cree
			AlarmEvent evt = this.moniteur.getAllAlarms().get(s-1);

			if(evt instanceof IncendieEvent){
				JOptionPane.showMessageDialog(this, "Alarme feu", "Nouvelle Alarme", JOptionPane.WARNING_MESSAGE);
			}
			else if(evt instanceof GazEvent){
				JOptionPane.showMessageDialog(this, "Alarme Gaz", "Nouvelle Alarme", JOptionPane.WARNING_MESSAGE);
			}
			else if(evt instanceof RadiationEvent){
				JOptionPane.showMessageDialog(this, "Alarme Radiation", "Nouvelle Alarme", JOptionPane.WARNING_MESSAGE);
			}
			this.updateListeAlarmes();
		}
	}
	
	
	/*
	 * Methode utilisee pour rafraichir la liste des alarmes.
	 */
	public void updateListeAlarmes(){
		this.list_evt.removeAllItems();
		for(AlarmEvent a : this.moniteur.getAllAlarms()){
			this.list_evt.addItem(a);
		}
		this.repaint();
	}
	
	
	/*
	 * Methode utilisee pour mettre a jour la liste des alarmes
	 * en fonction du moniteur.
	 * 
	 * @param m : Moniteur concerne
	 */
	public void upgradeListeAlarmes(Moniteur m){
		this.list_evt.removeAllItems();
		for(AlarmEvent a : this.moniteur.getAllAlarms()){
			if(m instanceof MoniteurTypeA){
				if((a instanceof IncendieEvent) || (a instanceof GazEvent))
					this.list_evt.addItem(a);
			}
			
			else if(m instanceof MoniteurTypeB){
				if((a instanceof IncendieEvent) || (a instanceof RadiationEvent))
					this.list_evt.addItem(a);
			}
			else{
				this.list_evt.addItem(a);
			}
			
		}
		this.repaint();
	}
	
	
	/*
	 * Classe utilisee pour gerer les interactions sur la liste des alarmes.
	 */
	private class ZoneMoniteurController implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent itemEvent) {
			int state = itemEvent.getStateChange();
			if(state == itemEvent.SELECTED){
				if(list_evt.getItemCount() > 0 && (AlarmEvent) list_evt.getSelectedItem() != null){
					if(((AlarmEvent) list_evt.getSelectedItem()).getNbreDetails() > 0){
						btn_traitee.setEnabled(true);
					}
					else{
						btn_traitee.setEnabled(false);
					}
					ZoneMoniteur.this.repaint();
				}
			}
		}
	}
}
