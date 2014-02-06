package game.elements;

import org.newdawn.slick.Image;

import game.elements.interfaces.Activable;
import game.elements.interfaces.Deletable;
import game.personnage.Personnage;
import managers.RessourcesManager;

public class Coccinelle extends Element implements Activable, Deletable {

	private int part = 1;
	private int cptD=0;
	
	private float x = 44f;
	private float y = 188f;
	
	private float angle = 0;

	public Coccinelle(int z) {
		super(z);
	}

	@Override
	public void render(RessourcesManager rm) {
		Image img = rm.getImage("COCCI").getSubImage(part*50, 0, 50, 46);
		img.rotate(angle);
		img.draw(x,y);		
	}

	@Override
	public void update(int d) {
		cptD += d;
		
		x -= d / 7f;
		y -= d / 15f;
		
		angle -= 1.2;
		
		if (cptD > 30){		
			if (part == 0)
				part = 2;
			else
				part = 0;
			cptD=0;
		}
	}
	
	

	@Override
	public void testActivation(Personnage p) {
		if (p.getX() < 100){
			part=0;
		}
	}

	@Override
	public boolean testDelete() {
		if (x < -50)
			return true;
		else
			return false;
	}

	@Override
	public boolean testActive() {
		return part != 1;
	}

}
