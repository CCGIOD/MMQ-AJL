package game.states;

import org.newdawn.slick.state.BasicGameState;

import managers.RessourcesManager;

public abstract class AbstractLevel extends BasicGameState {

	protected RessourcesManager ressourcesManager = RessourcesManager.getInstance();
		
}
