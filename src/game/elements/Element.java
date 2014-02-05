package game.elements;

import managers.RessourcesManager;

/**
 * Classe abstraite englobant les �l�ments. Elle donne pour chaque �l�ment :
 * 		- son zindex & une fonction de comparaison de zindex
 */
public abstract class Element implements Comparable<Element> {

	// Position de l'�l�ment sur l'axe des z.
	private int zindex;
	
	public Element (int z){
		this.zindex=z;
	}

	public int getZindex (){
		return this.zindex;
	}
	
	@Override
	public int compareTo(Element o) {
		if (o.zindex > this.zindex)
			return -1;
		else if (o.zindex < this.zindex)
			return 1;
		else 
			return 0;
	}
	
	public abstract void render (RessourcesManager rm);

}
