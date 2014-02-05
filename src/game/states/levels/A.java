package game.states.levels;

import java.util.Collections;

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
		
		// Ajout des éléments.
		addElement(new Nuages(1,50));
		addElement(new Sun(0));
		
		// Se termine par le tri des éléments par ordre de zindex :
		Collections.sort(listElements);		
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		ressourcesManager.getImage("BG").draw(0,0);
		renderListElements();
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}


}
