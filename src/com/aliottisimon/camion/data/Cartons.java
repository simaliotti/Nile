package com.aliottisimon.camion.data;

public enum Cartons {

	TYPE_S(35, 35, 1),
	TYPE_M(70, 35, 1),
	TYPE_L(70, 70, 1);

	private int longueur;
	private int largeur;
	private int hauteur;
	
	private Cartons(int largeur, int longueur, int hauteur) {
		this.largeur = largeur;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}
	
	
	
}
