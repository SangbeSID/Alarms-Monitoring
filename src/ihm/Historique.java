package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import recievers.Moniteur;

import events.AlarmEvent;
import events.GazEvent;
import events.IncendieEvent;
import events.RadiationEvent;

/*
 * Classe utilisee pour gerer l'historique des alarmes declenchees.
 */
public class Historique extends JFrame{
	private JButton btn_fermer = new JButton("Fermer");
	private ArrayList<AlarmEvent> list_alarm; // Liste des alarmes
	private Moniteur moniteur; // Moniteur pour capter toutes les alarmes
	private int nbreAlarmesIncendie = 0;
	private int nbreAlarmesGaz = 0;
	private int nbreAlarmesRadiation = 0;
	private JList<AlarmEvent> vueDetails; // Zone d'apparition des alarmes.
	private JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private JButton btn_toutesLesAlarmes = new JButton("Toutes les alarmes");
	private JButton btn_incendie = new JButton("Alarmes Incendie");
	private JButton btn_gaz = new JButton("Alarmes Gaz Toxique");
	private JButton btn_radiation = new JButton("Alarmes Radiation");
	private JLabel lbl_nbreAlarmesGaz = new JLabel("Total Alarmes Gaz : " + this.nbreAlarmesGaz);
	private JLabel lbl_nbreAlarmesIncendie = new JLabel("Total Alarmes Incendie : " + this.nbreAlarmesIncendie);
	private JLabel lbl_nbreAlarmesRadiation = new JLabel("Total Alarmes Radiation : " + this.nbreAlarmesRadiation);
	
	
	/*
	 * Le controlleur.
	 */
	public Historique(){
		super("Historique des alarmes");
		this.list_alarm = new ArrayList<AlarmEvent>();
		this.moniteur = new Moniteur();
		this.vueDetails = new JList<AlarmEvent>(new DefaultListModel<AlarmEvent>());
		this.vueDetails.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panel_btnFermer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
		panel_btnFermer.add(this.btn_fermer, BorderLayout.SOUTH);
		
		JPanel panel_selection = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel_selection.add(this.btn_toutesLesAlarmes);
		panel_selection.add(this.btn_incendie);
		panel_selection.add(this.btn_gaz);
		panel_selection.add(this.btn_radiation);
		
		Box box_info = new Box(BoxLayout.Y_AXIS);
		box_info.add(this.lbl_nbreAlarmesGaz);
		box_info.add(Box.createVerticalStrut(10));
		box_info.add(this.lbl_nbreAlarmesIncendie);
		box_info.add(Box.createVerticalStrut(10));
		box_info.add(this.lbl_nbreAlarmesRadiation);
		
		this.vueDetails.addListSelectionListener(new HistoriqueController());
		this.btn_fermer.addActionListener(new HistoriqueController());
		this.btn_toutesLesAlarmes.addActionListener(new HistoriqueController());
		this.btn_gaz.addActionListener(new HistoriqueController());
		this.btn_incendie.addActionListener(new HistoriqueController());
		this.btn_radiation.addActionListener(new HistoriqueController());
		
		this.panel.add(new JScrollPane(this.vueDetails));
		this.panel.add(box_info);
		this.add(this.panel);
		this.add(panel_selection, BorderLayout.NORTH);
		this.add(panel_btnFermer, BorderLayout.SOUTH);
		this.setVisible(true);
		this.pack();
	}
	
	/**
	 * Recuperer le moniteur associe a la zone de l'historique.
	 * 
	 * @return : Moniteur de la zone des historiques
	 */
	public Moniteur getMoniteur(){
		return this.moniteur;
	}
	
	
	/**
	 * Methode utilisee pour ajouter une alarme a la laiste.
	 * 
	 * @parma alarme : Alarme a ajouter
	 */
	public void addAlarmEvent(AlarmEvent alarme){
		if(alarme != null){
			this.list_alarm.add(alarme);
			if(alarme instanceof GazEvent)
				this.nbreAlarmesGaz++;
			else if(alarme instanceof IncendieEvent)
				this.nbreAlarmesIncendie++;
			else if(alarme instanceof RadiationEvent)
				this.nbreAlarmesRadiation++;
			this.refresh();
		}
	}
	
	
	/**
	 * Methode pour mettre a jour les statistiques
	 */
	public void upgradeStatistiques(){
		int s = this.moniteur.getAllAlarms().size();
		AlarmEvent alarme = this.moniteur.getAllAlarms().get(s-1);
		
		if(alarme instanceof GazEvent)
			this.nbreAlarmesGaz++;
		else if(alarme instanceof IncendieEvent)
			this.nbreAlarmesIncendie++;
		else if(alarme instanceof RadiationEvent)
			this.nbreAlarmesRadiation++;
		this.refresh();
	}
	
	
	/**
	 * Methode utilisee pour rafraichir la fenetre des historiques.
	 */
	private void refresh(){
		int s = this.moniteur.getAllAlarms().size();
		this.vueDetails.removeAll();
		AlarmEvent[] tab = new AlarmEvent[s];
		for(int i= 0 ; i<s ; i++){
			tab[i] = this.moniteur.getAllAlarms().get(i);
		}
		this.vueDetails.setListData(tab);
		this.lbl_nbreAlarmesGaz.setText("Total Alarmes Gaz : " + this.nbreAlarmesGaz);
		this.lbl_nbreAlarmesIncendie.setText("Total Alarmes Incendie : " + this.nbreAlarmesIncendie);
		this.lbl_nbreAlarmesRadiation.setText("Total Alarmes Radiation : " + this.nbreAlarmesRadiation);
		this.panel.repaint();
	}
	
	/*
	 * Classe utilisee pour gerer les interactions dans la fenetre des historiques.
	 */
	private class HistoriqueController implements ActionListener, ListSelectionListener{

		@Override
		public void actionPerformed(ActionEvent evt) {
			// Si on clique sur le bouton "Fermer"
			// On ferme la fenetre
			if(evt.getSource() == btn_fermer){
				Historique.this.dispose();
			}
			
			// Si on clique sur le bouton "Toutes les alarmes".
			// On affiche toutes les alarmes
			if(evt.getSource() == btn_toutesLesAlarmes){
				Historique.this.refresh();
			}
			
			// Si on clique sur le bouton "Alarmes Gaz toxique"
			// On affiche que les alarmes de type Gaz
			if(evt.getSource() == btn_gaz){
				int s = moniteur.getAllAlarms().size();
				vueDetails.removeAll();
				AlarmEvent[] tab = new AlarmEvent[s];
				for(int i= 0 ; i<s ; i++){
					if(moniteur.getAllAlarms().get(i) instanceof GazEvent)
						tab[i] = moniteur.getAllAlarms().get(i);
				}
				vueDetails.setListData(tab);
				panel.repaint();
			}
			
			// Si on clique sur le bouton "Alarmes Incendie"
			// On affiche que les alarmes de type Incendie
			if(evt.getSource() == btn_incendie){
				int s = moniteur.getAllAlarms().size();
				vueDetails.removeAll();
				AlarmEvent[] tab = new AlarmEvent[s];
				for(int i= 0 ; i<s ; i++){
					if(moniteur.getAllAlarms().get(i) instanceof IncendieEvent)
						tab[i] = moniteur.getAllAlarms().get(i);
				}
				vueDetails.setListData(tab);
				panel.repaint();
			}
			
			// Si on clique sur le bouton "Alarmes Radiation"
			// On affiche que les alarmes de type Radiation
			if(evt.getSource() == btn_radiation){
				int s = moniteur.getAllAlarms().size();
				vueDetails.removeAll();
				AlarmEvent[] tab = new AlarmEvent[s];
				for(int i= 0 ; i<s ; i++){
					if(moniteur.getAllAlarms().get(i) instanceof RadiationEvent)
						tab[i] = moniteur.getAllAlarms().get(i);
				}
				vueDetails.setListData(tab);
				panel.repaint();
			}
		}
		
		// Si l'on clique sur un element de la liste on montre ses details.
		@Override
		public void valueChanged(ListSelectionEvent evt) {
			if(evt.getSource() == vueDetails){
				AlarmEvent a = vueDetails.getSelectedValue();
				if(a != null){
					a.showDetails();
					vueDetails.clearSelection();
					vueDetails.repaint();
				}
				
			}
		}
		
	}
}
