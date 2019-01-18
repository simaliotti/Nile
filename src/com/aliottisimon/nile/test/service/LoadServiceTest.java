package com.aliottisimon.nile.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.pojos.CamionLoaded;
import com.aliottisimon.nile.pojos.CartonLoaded;
import com.aliottisimon.nile.pojos.Commande;
import com.aliottisimon.nile.service.CamionLoadedService;

import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.service.LoadService;

class LoadServiceTest {

	@Test
	void test_Chargement_camion() throws FileNotFoundException, ClassNotFoundException, IOException {

		// 1 commande
		//

		CommandeService cs = new CommandeService();
	//	cs.generateCommande();

		List<Commande> listCommandes = cs.readCommande();

		LoadService ls = new LoadService(CamionType.TYPE_L, 2);
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();
		CamionLoaded camionLoaded = cls.readCamionLoaded(2);

		List<CartonLoaded> listCartonLoaded = camionLoaded.getListCartonLoaded();

		List<CartonLoaded> rack1etage1 = new LinkedList();
		List<CartonLoaded> rack2etage1 = new LinkedList();
		List<CartonLoaded> rack3etage1 = new LinkedList();

		List<CartonLoaded> rack1etage2 = new LinkedList();
		List<CartonLoaded> rack2etage2 = new LinkedList();
		List<CartonLoaded> rack3etage2 = new LinkedList();

		List<CartonLoaded> rack1etage3 = new LinkedList();
		List<CartonLoaded> rack2etage3 = new LinkedList();
		List<CartonLoaded> rack3etage3 = new LinkedList();

		for (CartonLoaded cartonLoaded : listCartonLoaded) {
			if (cartonLoaded.getFloor() == 1) {
				if (cartonLoaded.getIdRack() == 1) {
					rack1etage1.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 2) {
					rack2etage1.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 3) {
					rack3etage1.add(cartonLoaded);
				}
			}
			if (cartonLoaded.getFloor() == 2) {
				if (cartonLoaded.getIdRack() == 1) {
					rack1etage2.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 2) {
					rack2etage2.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 3) {
					rack3etage2.add(cartonLoaded);
				}
			}
			if (cartonLoaded.getFloor() == 3) {
				if (cartonLoaded.getIdRack() == 1) {
					rack1etage3.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 2) {
					rack2etage3.add(cartonLoaded);
				} else if (cartonLoaded.getIdRack() == 3) {
					rack3etage3.add(cartonLoaded);
				}
			}
		}

	
		System.out.println("Etage1");
			
			Integer longueurutilisee = rack1etage1.stream()
					.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
					.reduce(0, (x, y) -> x + y);
			
			Integer longueurutilisee2 = rack2etage1.stream()
					.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
					.reduce(0, (x, y) -> x + y);
			
			Integer longueurutilisee3 = rack3etage1.stream()
					.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
					.reduce(0, (x, y) -> x + y);
			
		System.out.println(longueurutilisee);
		System.out.println(longueurutilisee2);
		System.out.println(longueurutilisee3);
		
		System.out.println("Etage2");
		
		Integer longueurutilisee4 = rack1etage2.stream()
				.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
				.reduce(0, (x, y) -> x + y);
		
		Integer longueurutilisee5 = rack2etage2.stream()
				.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
				.reduce(0, (x, y) -> x + y);
		
		Integer longueurutilisee6 = rack3etage2.stream()
				.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
				.reduce(0, (x, y) -> x + y);
		
	System.out.println(longueurutilisee4);
	System.out.println(longueurutilisee5);
	System.out.println(longueurutilisee6);
	
	
	System.out.println("Etage1");
	
	Integer longueurutilisee7 = rack1etage3.stream()
			.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
			.reduce(0, (x, y) -> x + y);
	
	Integer longueurutilisee8 = rack2etage3.stream()
			.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
			.reduce(0, (x, y) -> x + y);
	
	Integer longueurutilisee9 = rack3etage3.stream()
			.map(cartonLoaded -> cartonLoaded.getCartonType().getLongueur())
			.reduce(0, (x, y) -> x + y);
	
System.out.println(longueurutilisee7);
System.out.println(longueurutilisee8);
System.out.println(longueurutilisee9);

		
		
	}

}
