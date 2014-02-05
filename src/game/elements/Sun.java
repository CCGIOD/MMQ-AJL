package game.elements;

import game.elements.interfaces.Updatable;

import org.newdawn.slick.Image;

import managers.RessourcesManager;

/**
 * Cette classe permet d'insérer un soleil animer en haut à gauche de la fenêtre.
 */
public class Sun extends Element implements Updatable {

	private Image rotatedImage;
	
	public Sun (int z, Image i){
		super(z);
		rotatedImage=i;
	}	

	@Override
	public void render(RessourcesManager rm) {
		rotatedImage.draw(-187,-187);
	}

	@Override
	public void update(int d) {
		rotatedImage.rotate(0.007f*d);		
	}

}
