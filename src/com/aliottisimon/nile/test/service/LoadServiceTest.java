package com.aliottisimon.nile.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.pojos.CamionLoaded;
import com.aliottisimon.nile.pojos.CartonLoaded;
import com.aliottisimon.nile.pojos.Commande;
import com.aliottisimon.nile.service.CamionLoadedService;

import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.service.LoadService;
import com.aliottisimon.nile.utils.Tools;

class LoadServiceTest {

	@Test
	void test_Chargement_camion_1_commande() throws FileNotFoundException, ClassNotFoundException, IOException {

		CommandeService cs = new CommandeService();
		cs.generateCommande();

		LoadService ls = new LoadService(CamionType.TYPE_L, 2);
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();
		CamionLoaded camionLoaded = cls.readCamionLoaded(2);

		List<CartonLoaded> listCartonLoaded = camionLoaded.getListCartonLoaded();

		// etage1

		List<CartonLoaded> rack1etage1 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 1 && cartonLoaded.getIdRack() == 1)
				.collect(Collectors.toList());

		List<CartonLoaded> rack2etage1 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 1 && cartonLoaded.getIdRack() == 2)
				.collect(Collectors.toList());

		List<CartonLoaded> rack3etage1 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 1 && cartonLoaded.getIdRack() == 3)
				.collect(Collectors.toList());

		System.out.println("Etage1");
		System.out.println(Tools.calculTailleUtiliseeParRack(rack1etage1));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack2etage1));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack3etage1));

		// etage 2
		List<CartonLoaded> rack1etage2 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 2 && cartonLoaded.getIdRack() == 1)
				.collect(Collectors.toList());

		List<CartonLoaded> rack2etage2 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 2 && cartonLoaded.getIdRack() == 2)
				.collect(Collectors.toList());

		List<CartonLoaded> rack3etage2 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 2 && cartonLoaded.getIdRack() == 3)
				.collect(Collectors.toList());

		System.out.println("Etage2");
		System.out.println(Tools.calculTailleUtiliseeParRack(rack1etage2));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack2etage2));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack3etage2));

		// etage 2
		List<CartonLoaded> rack1etage3 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 3 && cartonLoaded.getIdRack() == 1)
				.collect(Collectors.toList());

		List<CartonLoaded> rack2etage3 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 3 && cartonLoaded.getIdRack() == 2)
				.collect(Collectors.toList());

		List<CartonLoaded> rack3etage3 = listCartonLoaded.stream()
				.filter(cartonLoaded -> cartonLoaded.getFloor() == 3 && cartonLoaded.getIdRack() == 3)
				.collect(Collectors.toList());

		System.out.println("Etage3");
		System.out.println(Tools.calculTailleUtiliseeParRack(rack1etage3));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack2etage3));
		System.out.println(Tools.calculTailleUtiliseeParRack(rack3etage3));
	}

}
