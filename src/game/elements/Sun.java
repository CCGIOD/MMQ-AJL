package game.elements;

import game.elements.interfaces.Updatable;

import org.newdawn.slick.Image;

import managers.RessourcesManager;

/**
 * Cette classe permet d'ins�rer un soleil animer en haut � gauche de la fen�tre.
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
