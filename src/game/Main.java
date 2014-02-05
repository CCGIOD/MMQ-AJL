package game;

import game.states.Level_A;

import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Main extends StateBasedGame {

	private AppGameContainer container;

	public Main () {
		super("Mamad Quest : An Adventure in JohanLand");
	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException
	{
		if (container instanceof AppGameContainer) {
			this.container = (AppGameContainer) container;
			
			container.setShowFPS(true);
			addState(new Level_A());
		}
	}


	public static void main(String[] args)
	{
		try{
			AppGameContainer container = new AppGameContainer(new Main());
			container.setDisplayMode(1000, 650, false);
			container.setTargetFrameRate(60);
			container.start();
		}
		catch (SlickException e) {e.printStackTrace();}
	}
}
