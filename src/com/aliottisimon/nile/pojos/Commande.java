package com.aliottisimon.nile.pojos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.aliottisimon.nile.model.CartonType;

public class Commande implements Serializable{

	List<Carton> listCarton = null;
	String idCommande =  null;
	
	
	public Commande(String idCommande) {
		this.idCommande = idCommande;
		
		
		List<Carton> cartons = new LinkedList();
		Random random = new Random();

		//modifie temporairement le random en fix random.nextInt(20)
		for (int i = 0; i <110; i++) {
			
			String numeroCarton = String.valueOf(i);
			String idCarton = this.idCommande+"-"+"Carton"+numeroCarton;
			
			Carton carton = new Carton(generateCartonType((random.nextInt(3)+1)), idCarton);
			cartons.add(carton);
		}
		
		this.listCarton = cartons;
	}

	

	private CartonType generateCartonType(int type) {
		if(type ==1) {
			return CartonType.TYPE_S;
		} else if (type == 2) {
			return CartonType.TYPE_M;
		}else if(type == 3) {
			return CartonType.TYPE_L;
		} else {
			return null;
		}
	}



	public List<Carton> getListCarton() {
		return listCarton;
	}



	public void setListCarton(List<Carton> listCarton) {
		this.listCarton = listCarton;
	}



	public String getIdCommande() {
		return idCommande;
	}



	public void setIdCommande(String idCommande) {
		this.idCommande = idCommande;
	}


}
