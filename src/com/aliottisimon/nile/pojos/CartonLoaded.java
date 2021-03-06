package com.aliottisimon.nile.pojos;

import java.io.Serializable;

import com.aliottisimon.nile.model.CartonType;

public class CartonLoaded implements Serializable {

	private int idCamion = 0;
	private int idPlace = 0;
	private String id = null;
	private int idRack = 0;
	private int floor = 0;
	private CartonType cartonType = null;
	
	
	public CartonLoaded(int idCamion, int idPlace, String id, int idRack, int floor, CartonType cartonType) {
		super();
		this.idCamion = idCamion;
		this.idPlace = idPlace;
		this.id = id;
		this.idRack = idRack;
		this.floor = floor;
		this.cartonType = cartonType;
	}
	
	
	public int getIdCamion() {
		return idCamion;
	}
	public void setIdCamion(int idCamion) {
		this.idCamion = idCamion;
	}
	public int getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(int idPlace) {
		this.idPlace = idPlace;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIdRack() {
		return idRack;
	}
	public void setIdRack(int idRack) {
		this.idRack = idRack;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public CartonType getCartonType() {
		return cartonType;
	}
	public void setCartonType(CartonType cartonType) {
		this.cartonType = cartonType;
	}
	
	
	
	
	
	
	
	
	
	
	
}
