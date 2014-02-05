package game.states;

import game.elements.Element;
import game.elements.interfaces.Updatable;
import game.personnage.Personnage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import managers.RessourcesManager;

/**
 * Classe abstraite englobant les classes de level.
 */
public abstract class AbstractLevel extends BasicGameState {

	// Le ressource manager
	protected static RessourcesManager ressourcesManager = RessourcesManager.getInstance();

	// Liste des �l�ments affichables.
	protected List<Element> listElements = new ArrayList<>();

	// Liste des �l�ments updatable.
	protected List<Updatable> listUpdatable = new ArrayList<>();

	protected Image background;

	protected static Personnage perso = new Personnage();
	
	public void addElement (Element e){
		listElements.add(e);
		if (e instanceof Updatable)
			listUpdatable.add((Updatable) e);
	}	

	// Affiche tous les �l�ments du niveau z
	public void renderListElements (int z){
		Iterator<Element> i = listElements.iterator();
		while(i.hasNext())
			if (z < 0 || z == i.next().getZindex())
				i.next().render(ressourcesManager);
	}

	// Affiche tous les �l�ments dans l'ordre des z.
	public void renderListElements (){
		renderListElements(-1);
	}

	// Update tous les �l�ments updatables.
	public void updateListUpdatable (int d){
		Iterator<Updatable> i = listUpdatable.iterator();
		while(i.hasNext())
			i.next().update(d);
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		background.draw(0,0);
		renderIn(arg0, arg1, arg2);
		renderListElements();
		perso.render(ressourcesManager);
	}

	public abstract void renderIn (GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException;

	public void update(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {
		Input input = arg0.getInput ();
		
		// Test input clavier :
		boolean stop = true;
		if (input.isKeyDown(Input.KEY_D)){ perso.droite(arg2); stop = false; } 
		else if (input.isKeyDown(Input.KEY_Q)){ perso.gauche(arg2); stop = false; } 
		if (input.isKeyDown(Input.KEY_Z)){ perso.haut(arg2); stop = false;} 
		else if (input.isKeyDown(Input.KEY_S)){ perso.bas(arg2); stop = false; }
		
		if (stop)
			perso.stop();
		
		updateListUpdatable(arg2);
		
		updateIn(arg0, arg1, arg2);
	}

	public abstract void updateIn (GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException;

}
