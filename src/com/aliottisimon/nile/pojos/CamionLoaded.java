package com.aliottisimon.nile.pojos;

import java.util.List;

import com.aliottisimon.nile.model.CamionType;

public class CamionLoaded {

	private CamionType type = null;
	private int id = 0;
	private List<CartonLoaded> listCartonLoaded = null;
	
	
	
	public CamionLoaded(CamionType type, int id, List<CartonLoaded> listCartonLoaded) {
		super();
		this.type = type;
		this.id = id;
		this.listCartonLoaded = listCartonLoaded;
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
	public List<CartonLoaded> getListCartonLoaded() {
		return listCartonLoaded;
	}
	public void setListCartonLoaded(List<CartonLoaded> listCartonLoaded) {
		this.listCartonLoaded = listCartonLoaded;
	}

	
	
}
