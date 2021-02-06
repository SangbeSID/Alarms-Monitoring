package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import events.AlarmEvent;

/*
 * Classe utilisee pour dessiner l'etat de l'alarme.
 */
public class ZoneDetails extends JPanel{
	private AlarmEvent alarmEvent; // Alarme dont on veut les details
	private int size; // Taille de la fenetre
	public final static int EMOTICONE_SIZE = 150;
	public final static int EYE_HEIGHT = 30;
	public final static int EYE_WIDTH = 15;
	public final static int MOUTH_HEIGHT = 70;
	public final static int MOUTH_WIDTH = 75;
	/*
	 * Le constructeur.
	 * 
	 * @param evt : Alarme dont on dessine les details
	 * @oaram s   : Taille de la zone de dessin
	 */
	public ZoneDetails(AlarmEvent evt, int s){
		super();
		this.size = s;
		this.alarmEvent = evt;
		this.setPreferredSize(new Dimension(this.size, this.size));
		this.setOpaque(true);
	}
	
	
	/*
	 * Methode utilisee pour recupere l'alarme.
	 */
	public AlarmEvent getAlarmEvent(){
		return this.alarmEvent;
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.drawDetails(g, this.alarmEvent);
	}
	
	
	/*
	 * Methode utilisee pour dessiner l'etat de l'alarme.
	 * Jaune  ==> Niveau 1
	 * Orange ==> Niveau 2
	 * Rouge  ==> Niveau 3
	 */
	private void drawDetails(Graphics g, AlarmEvent evt){
		switch(this.alarmEvent.getImportance()){
			case 1: g.setColor(Color.yellow); break;
			case 2: g.setColor(Color.orange); break;
			case 3: g.setColor(Color.red); break;
		}
		this.drawEmoticon(g);
	}
	
	/*
	 * Methode utilisee pour dessiner l'emoticone correspondant
	 * au niveau d'importance de l'alarme.
	 *
	 *@param c : Couleur de l'emoticone
	 */
	private void drawEmoticon(Graphics g){
		this.drawHead(g);
		this.drawEyes(g);
		this.drawMouth(g, 1);
	}
	
	/*
	 * Methode utilisee pour dessiner la tete de l'emoticone.
	 */
	private void drawHead(Graphics g){
		int x_head = this.size/2 - EMOTICONE_SIZE/2;
		int y_head = this.size/2 - EMOTICONE_SIZE/2;
		
		g.fillOval(x_head, y_head, EMOTICONE_SIZE, EMOTICONE_SIZE);
		g.setColor(Color.black);
		g.drawOval(x_head, y_head, EMOTICONE_SIZE, EMOTICONE_SIZE);
	}
	
	
	/*
	 * Methode utilisee pour dessiner les yeux de l'emoticone.
	 */
	private void drawEyes(Graphics g){
		int lEye_x = this.size/2 - EMOTICONE_SIZE/4;
		int lEye_y = this.size/2 - EMOTICONE_SIZE/4;
		int rEye_x = this.size/2 + EMOTICONE_SIZE/4 - EYE_WIDTH;;
		int rEye_y = this.size/2 - EMOTICONE_SIZE/4;
		
		g.setColor(Color.black);
		g.fillOval(rEye_x, rEye_y, EYE_WIDTH, EYE_HEIGHT);
		g.fillOval(lEye_x, lEye_y, EYE_WIDTH, EYE_HEIGHT);
	}
	
	
	/*
	 * Methode utilisee pour dessiner la bouche de l'émoticone.
	 */
	public void drawMouth(Graphics g, int shape){
		int x = this.size/2 - EMOTICONE_SIZE/4;
		int y = this.size/2 - EMOTICONE_SIZE/4 + EMOTICONE_SIZE/3;
		g.drawArc(x, y, MOUTH_WIDTH, MOUTH_HEIGHT, 0, 180);
		
	}
	
}
