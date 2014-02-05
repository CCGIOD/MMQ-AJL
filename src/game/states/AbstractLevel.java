package game.states;

import game.elements.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.newdawn.slick.state.BasicGameState;

import managers.RessourcesManager;

/**
 * Classe abstraite englobant les classes de level.
 */
public abstract class AbstractLevel extends BasicGameState {

	// Le ressource manager
	protected static RessourcesManager ressourcesManager = RessourcesManager.getInstance();

	// Liste des éléments affichables.
	protected List<Element> listElements = new ArrayList<>();
	

	public void addElement (Element e){
		listElements.add(e);
	}	
	
	// Affiche tous les éléments du niveau z
	public void renderListElements (int z){
		Iterator<Element> i = listElements.iterator();
		while(i.hasNext())
			if (z < 0 || z == i.next().getZindex())
				i.next().render(ressourcesManager);
	}
	
	// Affiche tous les éléments dans l'ordre des z.
	public void renderListElements (){
		renderListElements(-1);
	}
	
}
