package game.elements;

import org.newdawn.slick.Image;

import managers.RessourcesManager;

public class Sun extends Element {

	public Sun (int z){
		super(z);
	}	

	@Override
	public void render(RessourcesManager rm) {
		Image rotatedImage = rm.getImage("SUN");
		rotatedImage.rotate(0.1f);		
		rotatedImage.draw(-187,-187);
	}

}
