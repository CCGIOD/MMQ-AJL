package game.states;

import game.components.Carte;
import game.components.Personnage;
import game.elements.Element;
import game.elements.interfaces.Activable;
import game.elements.interfaces.Deletable;
import game.elements.interfaces.Updatable;

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

	// Liste des éléments affichables.
	protected List<Element> listElements = new ArrayList<>();

	// Liste des éléments updatables.
	protected List<Updatable> listUpdatables = new ArrayList<>();

	// Liste des éléments activables.
	protected List<Activable> listActivables = new ArrayList<>();

	// Liste des éléments activables.
	protected List<Deletable> listDeletables = new ArrayList<>();

	protected Image background;

	protected static Personnage perso = new Personnage();

	protected Carte carte;

	public void addElement (Element e){
		listElements.add(e);
		if (e instanceof Updatable)
			listUpdatables.add((Updatable) e);
		if (e instanceof Activable)
			listActivables.add((Activable) e);
		if (e instanceof Deletable)
			listDeletables.add((Deletable) e);
	}	

	/*// Affiche tous les éléments du niveau z
	public void renderListElements (int z){
		Iterator<Element> i = listElements.iterator();
		while(i.hasNext())
			if (z < 0 || z == i.next().getZindex())
				i.next().render(ressourcesManager);
	}

	// Affiche tous les éléments dans l'ordre des z.
	public void renderListElements (){
		renderListElements(-1);
	}*/

	// Affiche tous les éléments du niveau z.
	public void renderListElementsNegatifs (){
		Iterator<Element> i = listElements.iterator();
		while(i.hasNext()){
			Element e = i.next();
			if (e.getZindex() < 0)
				e.render(ressourcesManager);
		}

	}
	
	// Affiche tous les éléments du niveau z.
	public void renderListElementsPositifs (){
		Iterator<Element> i = listElements.iterator();
		while(i.hasNext()){
			Element e = i.next();
			if (e.getZindex() > 0)
				e.render(ressourcesManager);
		}
	}

	// Update tous les éléments updatables.
	public void updateListUpdatables (int d){
		Iterator<Updatable> i = listUpdatables.iterator();
		while(i.hasNext())
			i.next().update(d);
	}

	// Update tous les éléments activables (que s'ils sont activés).
	public void updateListActivables (int d){
		Iterator<Activable> i = listActivables.iterator();
		while(i.hasNext()){
			Activable a = i.next();
			if (a.testActive())
				a.update(d);
			else
				a.testActivation(perso);
		}
	}

	// Update tous les éléments deletables.
	public void updateListDeletables (int d){
		List<Deletable> toDelete = new ArrayList<>();		
		for (int i=0;i<listDeletables.size();i++){
			if (listDeletables.get(i).testDelete())
				toDelete.add(listDeletables.get(i));
		}
		for (int i=0;i<toDelete.size();i++){
			listElements.remove(toDelete.get(i));
			if (listActivables.contains(toDelete.get(i)))
				listActivables.remove(toDelete.get(i));
			if (listUpdatables.contains(toDelete.get(i)))
				listUpdatables.remove(toDelete.get(i));
			if (listDeletables.contains(toDelete.get(i)))
				listDeletables.remove(toDelete.get(i));
		}
	}

	@Override
	public void render(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {
		background.draw(0,0);
		renderListElementsNegatifs();
		carte.render(ressourcesManager);
		renderListElementsPositifs();
		renderIn(arg0, arg1, arg2);
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

		updateListUpdatables(arg2);
		updateListActivables(arg2);
		updateListDeletables(arg2);

		updateIn(arg0, arg1, arg2);
	}

	public abstract void updateIn (GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException;

}
