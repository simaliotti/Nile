package com.aliottisimon.nile.pojos;

import java.io.Serializable;

import com.aliottisimon.nile.model.CartonType;

public class Carton implements Serializable{

	CartonType type = null;
	String idCarton = null;
	
	
	public Carton(CartonType type, String idCarton) {
		
		this.type = type;
		this.idCarton = idCarton;
	}


	public CartonType getType() {
		return type;
	}


	public void setType(CartonType type) {
		this.type = type;
	}


	public String getIdCarton() {
		return idCarton;
	}


	public void setIdCarton(String idCarton) {
		this.idCarton = idCarton;
	}
	
	
	
	
}
