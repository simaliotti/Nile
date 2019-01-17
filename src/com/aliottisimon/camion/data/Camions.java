package com.aliottisimon.camion.data;

public enum Camions {

	TYPE_S(280, 210, 1),
	TYPE_M(350, 210, 2),
	TYPE_L(490, 210, 3);


	
	private int longueur;
	private int largeur;
	private int hauteur;
	
	
	private Camions(int largeur, int longueur, int hauteur) {
		this.largeur = largeur;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	
	
}
