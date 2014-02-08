package game.components;

import org.newdawn.slick.Color;

import org.newdawn.slick.Graphics;

import managers.RessourcesManager;

/**
 * Cette classe permet de gérer un personnage.
 */
public class Personnage {

	private final boolean DROITE = true;
	private final boolean GAUCHE = false;

	// Position du personnage.
	private float x = 200;
	private float y = 200;

	private boolean closeOeil=false;
	private int cptOeil=0;

	// Image active
	private String image = "P_S";

	// Variables de déplacement.
	private boolean sens = DROITE;
	private int cptWalk = -1;
	private float speed = 10f;

	private void swap (boolean newSens){
		if (newSens == DROITE && sens == GAUCHE) {
			sens=DROITE;
			x-=50;
		}
		else if (newSens == GAUCHE && sens == DROITE){
			sens=GAUCHE;
			x+=50;
		}
	}

	public void droite (int d){
		swap(DROITE);
		x += d / speed;

		if (cptWalk == -1)
			image="P_W1";
		else if (cptWalk > 180){
			image=(image.compareTo("P_W1") == 0)? "P_W2" : "P_W1";
			cptWalk = 0;
		}
		cptWalk += d;
	}

	public void gauche (int d){
		swap(GAUCHE);
		x -= d / speed;

		if (cptWalk == -1)
			image="P_W1";
		else if (cptWalk > 180){
			image=(image.compareTo("P_W1") == 0)? "P_W2" : "P_W1";
			cptWalk = 0;
		}
		cptWalk+=d;
	}

	public void haut (int d){
		y -=  d / speed;

	}

	public void bas (int d){
		y +=  d / speed;

	}

	public void stop (){
		image = "P_S";
		cptWalk = -1;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void update (int d){
		cptOeil+=d;

		if (cptOeil > 1500)
			closeOeil=true;
		if (cptOeil > 1700){
			closeOeil=false;
			cptOeil=0;
		}
	}

	public void render(RessourcesManager rm, Graphics g){
		if (sens == DROITE){
			rm.getImage(image).draw((int) x, (int) y);
		}
		else if (rm.testImage(image+"_L")){
			rm.getImage(image+"_L").draw((int) x, (int) y);
		}
		else{
			rm.getImage(image).getScaledCopy(-1*rm.getImage(image).getWidth(),rm.getImage(image).getHeight()).draw((int) x, (int) y);
		}

		if (closeOeil){
			g.setColor(Color.black);

			if (sens == DROITE)
				g.fillRect(((int) x)+31, ((int) y)+19, 2, 2);
			else
				g.fillRect(((int) x)-33, ((int) y)+19, 2, 2);
		}
	}
}
