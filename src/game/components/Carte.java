package game.components;

import managers.RessourcesManager;

public class Carte {

	private String [][] matrice;

	public Carte (String str){
		matrice = new String[20+2][13+2];

		String rows [] = str.split("'");
		for (int j=1;j<rows.length;j++){
			String columns [] = rows[j].split("\t");
			for (int i=1;i<columns.length;i++){
				if (columns[i].startsWith("TH"))
					matrice[i][j]="BL_TH";
				else if (columns[i].startsWith("T"))
					matrice[i][j]="BL_T";
				else if (columns[i].startsWith("RN"))
					matrice[i][j]="BL_RN";
				else if (columns[i].startsWith("R"))
					matrice[i][j]="BL_R";
			}
		}
	}

	public void render(RessourcesManager rm){
		for (int i=1;i<20+2;i++){
			for (int j=1;j<13+2;j++){
				if (matrice[i][j] != null){
					rm.getImage(matrice[i][j]).draw((i-1)*50, (j-1)*50);
				}
			}
		}
	}



}
