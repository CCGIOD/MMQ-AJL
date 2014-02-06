package game.states.levels;

import java.util.Collections;

import game.elements.Coccinelle;
import game.elements.Nuages;
import game.elements.Sun;
import game.states.AbstractLevel;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Level A du jeu.
 */
public class A extends AbstractLevel {

	public static final int ID = 1;
	
	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		// Définition du background
		background=ressourcesManager.getImage("BG");
		
		// Ajout des éléments.
		addElement(new Nuages(1,50));
		addElement(new Sun(0,ressourcesManager.getImage("SUN")));
		addElement(new Coccinelle(2));
		
		// Se termine par le tri des éléments par ordre de zindex :
		Collections.sort(listElements);		
	}

	public void renderIn(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {

	}

	public void updateIn(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}


}
