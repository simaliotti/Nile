package com.aliottisimon.nile.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.model.CartonType;
import com.aliottisimon.nile.pojos.Carton;
import com.aliottisimon.nile.pojos.Commande;


public class LoadService {

	private boolean isEmptyRack_1 = true;
	private boolean isEmptyRack_2 = true;
	private boolean isEmptyRack_3 = true;
	
	private CamionType camionType = null;
	private List<Commande> listCommandes = null;
	
	
	CommandeService commandeService = new CommandeService();

	public LoadService(CamionType camionType) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		//Set le type de camion
		this.camionType = camionType;
		//Récupère la liste de commandes
		this.listCommandes = commandeService.readCommande();
	}



	public void loadCamion() {
		
		Commande commande = listCommandes.get(0);
		Stream<Carton> stream = commande.getListCarton().stream();
		
		stream = stream.filter(carton -> carton.getType().equals(CartonType.TYPE_L));
		
		List<Carton> listCartonsL = stream.collect(Collectors.toList());
		
		int longueurCartonCumul = 0;
		for (Carton cartonL : listCartonsL) {
			 longueurCartonCumul += cartonL.getType().getLongueur();
			 if(longueurCartonCumul<=CamionType.TYPE_L.getLongueur()) {
				//loading truck
			 } else {
				 
				 
			 }
		}
		
		
		
	}
}
