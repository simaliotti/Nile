package com.aliottisimon.nile.model;

public enum CamionType {

	TYPE_S(280, 210, 1),
	TYPE_M(350, 210, 2),
	TYPE_L(490, 210, 3);


	
	private int longueur;
	private int largeur;
	private int hauteur;
	
	
	private CamionType(int largeur, int longueur, int hauteur) {
		this.largeur = largeur;
		this.longueur = longueur;
		this.hauteur = hauteur;
	}


	public int getLongueur() {
		return longueur;
	}


	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}


	public int getLargeur() {
		return largeur;
	}


	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}


	public int getHauteur() {
		return hauteur;
	}


	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}
	
	
	
}
