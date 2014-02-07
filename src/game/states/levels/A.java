package game.states.levels;

import java.util.Collections;

import game.components.Carte;
import game.elements.Coccinelle;
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
		// Définition du background
		background=ressourcesManager.getImage("BG");

		// Ajout des blocs
		String rep;
		rep=    "	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20 '"+
				"1	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V	V  '"+
				"2	V	V	V	V	V	V	V	V	V	V	V	RN	RN	RN	V	V	V	V	V	V  '"+
				"3	V	V	V	V	V	V	V	V	V	V	RN	V	V	V	RN	V	RN	RN	RN	V  '"+
				"4	V	V	V	V	V	V	V	V	V	V	R	R	R	R	R	RN	V	V	V	RN '"+
				"5	V	TH	V	V	V	V	V	V	RN	RN	V	V	V	V	R	V	V	V	V	V  '"+
				"6	TH	V	TH	TH	TH	TH	TH	TH	R	V	V	V	V	V	T	V	V	T	R	R  '"+
				"7	T	V	T	R	R	R	R	R	R	V	V	V	V	R	V	R	R	V	V	R  '"+
				"8	T	T	R	V	V	T	V	V	T	V	V	R	R	V	T	V	V	V	V	R  '"+
				"9	T	V	R	R	V	R	T	T	R	R	T	T	V	V	T	V	T	V	V	R  '"+
				"10	T	V	V	V	R	V	V	V	R	V	V	R	T	T	R	R	R	R	V	R  '"+
				"11	T	V	V	V	V	T	T	T	T	T	T	T	T	V	T	T	V	R	V	R  '"+
				"12	R	V	R	T	T	T	T	R	R	T	R	R	R	T	R	R	R	R	V	R  '"+
				"13	R	R	R	R	R	T	R	V	V	V	R	V	T	V	T	T	T	T	V	R  '";
		carte=new Carte(rep);

		// Ajout des éléments.
		addElement(new Nuages(-2,50));
		addElement(new Sun(-1,ressourcesManager.getImage("SUN")));
		addElement(new Coccinelle(1));

		// Se termine par le tri des éléments par ordre de zindex :
		Collections.sort(listElements);		
	}

	public void renderIn(GameContainer arg0, StateBasedGame arg1, Graphics arg2) throws SlickException {

	}

	public void updateIn(GameContainer arg0, StateBasedGame arg1, int arg2) throws SlickException {

	}

	@Override
	public int getID() {
		return ID;
	}


}
