package game.elements;

import game.elements.interfaces.Updatable;

import java.util.ArrayList;
import java.util.Collections;

import managers.RessourcesManager;

/**
 * Classe permettant d'avoir des nuages qui défilent en haut.
 */
public class Nuages extends Element implements Updatable {

	// Nombre de type de nuages différents (dans l'image nuages.png).
	public static final int NB_MAX_NUAGES = 21;
	
	// Nombre de vides à insérer dans la séquence (1 vide = pas de nuages).
	public static final int NB_VIDES = 10;

	// List des types et des hauteurs.
	private ArrayList<Integer> sequence;
	private ArrayList<Integer> hauteurs;

	// Hauteur maximale.
	private int ymax;
	
	// Position du nuage de tête (déplacement vers la gauche).
	private float x0 = 900;
	
	public Nuages (int z, int ymax){
		super(z);
		this.ymax=ymax;
		initSequence();
	}
	
	// Permet de construire une séquence de nuages.
	private void initSequence (){
		sequence = new ArrayList<Integer>();
		for (int i=1;i<=NB_MAX_NUAGES;sequence.add(i++));
		for (int i=0;i<10;sequence.add(0),i++);
		Collections.shuffle(sequence);		
		hauteurs = new ArrayList<Integer>();
		for (int i=0;i<NB_MAX_NUAGES+NB_VIDES;hauteurs.add((int) (Math.random()*100)%ymax),i++);
		while (sequence.get(0) == 0){
			sequence.remove(0);
			hauteurs.remove(0);
		}		
	}

	// Affichage des nuages qui rentre dans la fenêtre.
	public void render (RessourcesManager rm){
		int xl = (int) x0,i=0;
		while (xl < 1000 && i < sequence.size()){
			int id = sequence.get(i);
			if (id != 0){
				int x = id%3, y = id/3;
				rm.getImage("NUAGES").getSubImage(x*280, y*200, 280, 200).draw(xl,hauteurs.get(id));
			}
			xl+=280;
			i++;
		}
	}

	// Update la position de la séquence et la réinitialise si nécéssaire.
	public void update(int d) {
			x0 -= d / 35f;
			if (x0 <= -280){
				sequence.remove(0);
				x0=0;
			}
			if (sequence.size() == 0){
				initSequence();
				x0=1000;
			}
	}	

}
