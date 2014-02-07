package game.components;

import managers.RessourcesManager;

/**
 * Cette classe permet de gérer un personnage.
 */
public class Personnage {

	// Position du personnage.
	private float x = 200;
	private float y = 200;
	
	// Image active
	private String image = "P_S";

	// Variables de déplacement.
	private boolean droite = true;
	private int cptWalk = -1;
	private float speed = 10f;
	
	public void droite (int d){
			droite = true;
			x += d / speed;
			
			if (cptWalk == -1)
				image="P_W1";
			else if (cptWalk > 180){
				image=(image.compareTo("P_W1") == 0)? "P_W2" : "P_W1";
				cptWalk = 0;
			}
			cptWalk+=d;
	}

	public void gauche (int d){
			droite = false;
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
		cptWalk=-1;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void render(RessourcesManager rm){
		if (droite)
			rm.getImage(image).draw((int) x, (int) y);
		else if (rm.testImage(image+"_L")){
			rm.getImage(image+"_L").draw((int) x, (int) y);
		}
		else{
			rm.getImage(image).getScaledCopy(-1*rm.getImage(image).getWidth(),rm.getImage(image).getHeight()).draw((int) x+rm.getImage(image).getHeight(), (int) y);
		}
	}
}
