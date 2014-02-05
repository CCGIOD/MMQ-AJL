package game.elements;

import java.util.ArrayList;
import java.util.Collections;

import managers.RessourcesManager;

public class Nuages {

	public static final int NB_MAX_NUAGES = 21;
	public static final int NB_VIDES = 10;

	private ArrayList<Integer> sequence;
	private ArrayList<Integer> hauteurs;

	private int ymax;
	private int x0 = 900;
	
	private boolean depl = false;

	private void initSequence (){
		sequence = new ArrayList<Integer>();
		for (int i=1;i<=NB_MAX_NUAGES;sequence.add(i++));
		for (int i=0;i<10;sequence.add(0),i++);
		Collections.shuffle(sequence);		
		hauteurs = new ArrayList<Integer>();
		for (int i=0;i<NB_MAX_NUAGES+NB_VIDES;hauteurs.add((int) (Math.random()*100)%ymax),i++);
		System.out.println(sequence);
	}

	public Nuages (int ymax){
		this.ymax=ymax;
		initSequence();
	}

	public void drawNuages (RessourcesManager rm){
		int xl = x0,i=0;
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
	
	public void update (){
		if (depl){			
			x0--;
			if (x0 <= -280){
				sequence.remove(0);
				x0=0;
			}
			if (sequence.size() == 0)
				initSequence();
		}
		depl=!depl;
	}
}
