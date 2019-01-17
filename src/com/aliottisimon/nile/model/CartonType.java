package com.aliottisimon.nile.model;

public enum CartonType {

	TYPE_S(35, 35, 1),
	TYPE_M(70, 35, 1),
	TYPE_L(70, 70, 1);

	private int longueur;
	private int largeur;
	private int hauteur;
	
	private CartonType(int largeur, int longueur, int hauteur) {
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
