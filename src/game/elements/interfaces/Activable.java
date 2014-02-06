package game.elements.interfaces;

import game.personnage.Personnage;

/**
 * Interface demandant les fonctions update et testActiver sur un �l�ments activable.
 */
public interface Activable {
	
	public void update (int d);

	public boolean testActive ();
	
	public void testActivation(Personnage p);
	
}
