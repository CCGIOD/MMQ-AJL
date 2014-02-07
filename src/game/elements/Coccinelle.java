package game.elements;

import org.newdawn.slick.Image;

import game.components.Personnage;
import game.elements.interfaces.Activable;
import game.elements.interfaces.Deletable;
import managers.RessourcesManager;

public class Coccinelle extends Element implements Activable, Deletable {

	private int part = 0;
	
	private float x = 37f;
	private float y = 190f;
	
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
		x -= d / 7f;
		y -= d / 10f;
		
		angle -= 0.70;
	}
	
	

	@Override
	public void testActivation(Personnage p) {
		if (p.getX() < 120){
			part=1;
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
		return part != 0;
	}

}
