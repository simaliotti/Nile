package com.aliottisimon.nile.pojos;

import java.io.Serializable;

import com.aliottisimon.nile.model.CamionType;

public class Camion implements Serializable{

	CamionType type = null;
	int id = 0;
	boolean disponible = true;
	
	
	
	public Camion(CamionType type, int id) {

		this.type = type;
		this.id = id;
	}



	public CamionType getType() {
		return type;
	}



	public void setType(CamionType type) {
		this.type = type;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public boolean isDisponible() {
		return disponible;
	}



	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
	

	
}
