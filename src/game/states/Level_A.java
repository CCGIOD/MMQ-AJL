package game.states;

import game.elements.Nuages;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level_A extends AbstractLevel {

	public static final int ID = 1;
	
	private Nuages nuages;

	@Override
	public void init(GameContainer arg0, StateBasedGame arg1) throws SlickException {
		nuages = new Nuages();
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		ressourcesManager.getImage("BG").draw(0,0);
		nuages.drawNuages(ressourcesManager);
	}

	@Override
	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		nuages.update();
	}

	@Override
	public int getID() {
		return ID;
	}


}
