package ihm;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import events.TypeGaz;
import generators.Capteur;
import generators.Localisation;

/*
 * Classe utilisee pour representer la zone d'edition des alarmes.
 */
public class ZoneAlarme extends Box{
	
	private JButton btn_envoyer = new JButton("Envoyer"); 
	
	private JLabel lbl_gestion = new JLabel("Gestion des alarmes");
	private String[] list_importahce = {"1", "2", "3"};
	
	private BatimentZone cmb_batiment = new BatimentZone();
	private JComboBox<String> cmb_importance;
	private JComboBox<TypeGaz> cmb_type_gaz = new JComboBox<>(TypeGaz.values());
	
	private JTextField txt_niv_radiation = new JTextField("", 5);
	
	private JRadioButton rbtn_incendie = new JRadioButton("incendie");
	private JRadioButton rbtn_gaz = new JRadioButton("gaz toxique");
	private JRadioButton rbtn_radiation = new JRadioButton("radiation");
	
	private Capteur capteur = null; // Capteur qui declenche l'alarme
	

	/*
	 * Le constructeur.
	 */
	public ZoneAlarme(){
		super(BoxLayout.Y_AXIS);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// On definit le format de la zone permettant de definir le niveau de radiation
		// On specifie que cette zone ne prend que des entiers et rien d'autre.
		NumberFormatter sizeFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
		sizeFormatter.setValueClass(Integer.class);
		sizeFormatter.setAllowsInvalid(false); // On autorise que les entiers
		sizeFormatter.setMinimum(1); // On definit la valeur minimum pour cette zone
		sizeFormatter.setMaximum(100); // On definit la valeur maximale pour cette zone
		// On applique le format a la zone de saisie
		this.txt_niv_radiation = new JFormattedTextField(sizeFormatter);
		
		this.btn_envoyer.addActionListener(new ZoneAlarmController());
		this.rbtn_gaz.addMouseListener(new ZoneAlarmController());
		this.rbtn_incendie.addMouseListener(new ZoneAlarmController());
		this.rbtn_radiation.addMouseListener(new ZoneAlarmController());
		
		this.cmb_type_gaz.setEnabled(false);
		this.txt_niv_radiation.setEnabled(false);
		
		// -- On cree les differentes listes pour la gestion des alarmes
		this.cmb_importance = new JComboBox<String>(this.list_importahce);
		
		JLabel lbl_type = new JLabel("Type Alarme");
		JLabel lbl_niveau = new JLabel("Importance");
		
		// =============================  ZONE TYPE
		Box box_lbl_type = new Box(BoxLayout.X_AXIS);
		box_lbl_type.add(Box.createHorizontalGlue());
		box_lbl_type.add(lbl_type);
		box_lbl_type.add(Box.createHorizontalGlue());
		
		// ----- Type incendie
		Box box_incendie = new Box(BoxLayout.X_AXIS);
		this.rbtn_incendie.setOpaque(false);
		box_incendie.add(this.rbtn_incendie);
		box_incendie.add(Box.createHorizontalGlue());
		
		// ----- Type gaz
		Box box_gaz = new Box(BoxLayout.X_AXIS);	
		this.rbtn_gaz.setOpaque(false);
		box_gaz.add(this.rbtn_gaz);
		box_gaz.add(Box.createHorizontalStrut(10));
		
		Box box_type_gaz = new Box(BoxLayout.X_AXIS);
		JLabel lbl_type_gaz = new JLabel("Type");		
		box_type_gaz.add(lbl_type_gaz);
		box_type_gaz.add(Box.createVerticalStrut(10));
		box_type_gaz.add(this.cmb_type_gaz);
		
		box_gaz.add(box_type_gaz);
		
		// ----- Type radiation
		Box box_radiation = new Box(BoxLayout.X_AXIS);
		this.rbtn_radiation.setOpaque(false);
		box_radiation.add(this.rbtn_radiation);
		box_radiation.add(Box.createHorizontalStrut(20));
		
		Box box_niv_radiation = new Box(BoxLayout.X_AXIS);	
		JLabel lbl_radiation = new JLabel("Niveau");
		box_niv_radiation.add(lbl_radiation);
		box_niv_radiation.add(Box.createHorizontalStrut(10));
		box_niv_radiation.add(this.txt_niv_radiation);
		
		box_radiation.add(box_niv_radiation);
		
		// -- On insere les elements dans la zone type
		Box box_type = new Box(BoxLayout.Y_AXIS);
		box_type.setBorder(BorderFactory.createLineBorder(Color.black));
		box_type.add(box_lbl_type);
		box_type.add(Box.createVerticalStrut(10));
		box_type.add(box_incendie);
		box_type.add(Box.createVerticalStrut(10));
		box_type.add(box_gaz);
		box_type.add(Box.createVerticalStrut(10));
		box_type.add(box_radiation);
		box_type.add(Box.createVerticalStrut(10));
		
		
		// =============================  ZONE NIVEAU
		Box box_niveau = new Box(BoxLayout.X_AXIS);		
		box_niveau.add(lbl_niveau);
		box_niveau.add(Box.createHorizontalStrut(10));
		box_niveau.add(cmb_importance);
		
		Box box_lbl_gestion = new Box(BoxLayout.X_AXIS);
		box_lbl_gestion.add(Box.createHorizontalGlue());
		box_lbl_gestion.add(this.lbl_gestion);
		box_lbl_gestion.add(Box.createHorizontalGlue());
		
		Box box_btn_envoyer = new Box(BoxLayout.X_AXIS);
		box_btn_envoyer.add(Box.createHorizontalGlue());
		box_btn_envoyer.add(this.btn_envoyer);
		box_btn_envoyer.add(Box.createHorizontalGlue());
		
		// ------ On met toutes les boites dans la zone de gestion
		this.setOpaque(true);
		this.setBackground(Color.green);
		this.add(box_lbl_gestion);
		this.add(Box.createVerticalStrut(20));
		this.add(this.cmb_batiment);
		this.add(Box.createVerticalStrut(20));
		this.add(box_type);
		this.add(Box.createVerticalStrut(20));
		this.add(box_niveau);
		this.add(Box.createVerticalStrut(20));
		this.add(box_btn_envoyer);
		this.add(Box.createVerticalStrut(20));
	}
	
	
	/*
	 * Methode utilisee pour recuperer le bouton Envoyer.
	 */
	public JButton getBtEnvoyer(){
		return this.btn_envoyer;
	}
	
	
	/*
	 * Methode utilisee pour recuperer le bouton option radiation.
	 */
	public JRadioButton getBtnRadiation(){
		return this.rbtn_radiation;
	}
	
	
	/*
	 * Methode utilisee pour recuperer le bouton option incendie.
	 */
	public JRadioButton getBtnIncendie(){
		return this.rbtn_incendie;
	}
	
	
	/*
	 * Methode utilisee pour recuperer le bouton option gaz toxique.
	 */
	public JRadioButton getBtnGaz(){
		return this.rbtn_gaz;
	}
	
	
	/*
	 * Cette methode permet de recuperer la valeur indiquant le niveau 
	 * d'importance de l'alarme
	 * @return  : Valeur selectionnee dans la liste
	 */
	public int getImportance(){
		return (Integer.valueOf((String) this.cmb_importance.getSelectedItem())).intValue();
	}
	
	
	/*
	 * Methode utilisee pour recuperer le niveau de radiation.
	 * Elle retourne (-1) si le champ est vide.
	 * @return : Niveau de radiation
	 */
	public int getNiveauRadiation(){
		if(this.txt_niv_radiation.getText().isEmpty()){
			JOptionPane.showMessageDialog(this, "Niveau de Radiation Obligatoire", "Niveau de Radiation", 
					JOptionPane.INFORMATION_MESSAGE);
			return (-1);
		}
		else
			return (Integer.valueOf(this.txt_niv_radiation.getText())).intValue();
	}
	
	
	/*
	 * Methode utilisee pour recuperer le type de gaz selectionne
	 * @return : Type de Gaz 
	 */
	public TypeGaz getTypeGaz(){
		return (TypeGaz) this.cmb_type_gaz.getSelectedItem();
	}
	
	
	
	/*
	 * Cette methode permet de recuperer le batiment selectionne
	 * @return : Batiment selectionne
	 */
	public Localisation getBatiment(){
		//return (String) this.cmb_batiment.getSelectedItem();
		return (Localisation) this.cmb_batiment.getListeBatiments().getSelectedItem();
	}
	
	
	/*
	 * Cette methode permet de recuperer le type d'alarme selectionne
	 * @return : Type d'alarme selectionne
	 */
	public Capteur getCapteur(){
		return this.capteur;
	}
	
	/*
	 * Methode utilisee pour modifier le capteur de la zone.
	 * 
	 * @param c : Nouveau capteur
	 */
	public void setCapteur(Capteur c){
		if(c != null)
			this.capteur = c;
	}
	
	/*
	 * Classe utilisee pour gerer les interactions de la zone.
	 */
	private class ZoneAlarmController extends MouseAdapter implements ActionListener{

		@Override
		public void mouseClicked(MouseEvent evt) {
			if(evt.getButton() == MouseEvent.BUTTON1){ // Si l'on fait un clique gauche
				
				if(evt.getSource() == ZoneAlarme.this.rbtn_incendie){
					ZoneAlarme.this.rbtn_gaz.setSelected(false);
					ZoneAlarme.this.rbtn_radiation.setSelected(false);
					ZoneAlarme.this.cmb_type_gaz.setEnabled(false);
					ZoneAlarme.this.txt_niv_radiation.setEnabled(false);
				}
				
				if(evt.getSource() == ZoneAlarme.this.rbtn_gaz){
					ZoneAlarme.this.rbtn_incendie.setSelected(false);
					ZoneAlarme.this.rbtn_radiation.setSelected(false);
					ZoneAlarme.this.txt_niv_radiation.setEnabled(false);
					
					if(ZoneAlarme.this.rbtn_gaz.isSelected())
						ZoneAlarme.this.cmb_type_gaz.setEnabled(true);
					else
						ZoneAlarme.this.cmb_type_gaz.setEnabled(false);
					
				}
				
				if(evt.getSource() == ZoneAlarme.this.rbtn_radiation){
					ZoneAlarme.this.rbtn_gaz.setSelected(false);
					ZoneAlarme.this.rbtn_incendie.setSelected(false);
					ZoneAlarme.this.cmb_type_gaz.setEnabled(false);
					
					if(ZoneAlarme.this.rbtn_radiation.isSelected())
						ZoneAlarme.this.txt_niv_radiation.setEnabled(true);
					else
						ZoneAlarme.this.txt_niv_radiation.setEnabled(true);
						
				}
				
			}
			
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if(evt.getSource() == btn_envoyer){
				if(!rbtn_gaz.isSelected() && !rbtn_incendie.isSelected() && !rbtn_radiation.isSelected()){
					JOptionPane.showMessageDialog(ZoneAlarme.this, "Selection du type d'alarme est OBLIGATOIRE", 
							"Generation d'alarme", JOptionPane.ERROR_MESSAGE);
				}
			}
			
		}
		
	} // --- End of the private class
}
