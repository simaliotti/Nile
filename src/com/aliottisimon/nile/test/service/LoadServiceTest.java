package com.aliottisimon.nile.test.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import com.aliottisimon.nile.pojos.Camion;
import com.aliottisimon.nile.pojos.CamionLoaded;
import com.aliottisimon.nile.pojos.CartonLoaded;
import com.aliottisimon.nile.pojos.Commande;
import com.aliottisimon.nile.service.CamionLoadedService;
import com.aliottisimon.nile.service.CamionService;
import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.service.LoadService;
import com.aliottisimon.nile.utils.SystemUtils;
import com.aliottisimon.nile.utils.Tools;

class LoadServiceTest {

	/**
	 * Test qui vérifie que le chargement est bien effectué dans chaque rack Vérifie
	 * que la longueur des cartons chargé dans chaque rack est égale ou inférieure à
	 * la longueur du rack (490 pour un camion L)
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	void test_Chargement_camion_1_commande_camionTypeL()
			throws FileNotFoundException, ClassNotFoundException, IOException {

		int numberOfCommandToGenerate = 1;
		int numberOfCartonToGenerateByCommand = 60;

		// genere une commande
		CommandeService cs = new CommandeService();
		List<Commande> listCommandeGenereted = cs.generateCommandeSpecifyQuantity(numberOfCommandToGenerate, numberOfCartonToGenerateByCommand);

		// créé un camion de type L (écrit dans le fichier listCamions)
		CamionService camionService = new CamionService();
		List<Camion> listCamions = new LinkedList();
		listCamions.add(camionService.createCamion(3));
		camionService.writeCamion(listCamions);

		// charge le camion
		LoadService ls = new LoadService(listCamions.get(0).getType(), listCamions.get(0).getId());
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();

		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + listCamions.get(0).getId() + ".txt");
		if (fileCamionLoaded.exists()) {

			CamionLoaded camionLoaded = cls.readCamionLoaded(listCamions.get(0).getId());

			// liste des cartons chargés
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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage1) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage2) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage3) <= listCamions.get(0).getType().getLongueur());

			// supprime le camionLoaded pour le test
			cls.deleteCamionLoaded(camionLoaded.getId());

		} else {
			// supprime la commande crée pour le test dans le cas où elle n'a pas été
			// chargée
			cs.deleteCommande(listCommandeGenereted.get(0).getIdCommande());
		}

		// supprime le camion créé pour le test
		camionService.deleteCamionFile();

	}

	/**
	 * Test qui vérifie que le chargement est bien effectué dans chaque rack Vérifie
	 * que la longueur des cartons chargé dans chaque rack est égale ou inférieure à
	 * la longueur du rack (350 pour un camion M)
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	void test_Chargement_camion_1_commande_camionTypeM()
			throws FileNotFoundException, ClassNotFoundException, IOException {

		int numberOfCommandToGenerate = 1;
		int numberOfCartonToGenerateByCommand = 50;

		// genere une commande
		CommandeService cs = new CommandeService();
		List<Commande> listCommandeGenereted = cs.generateCommandeSpecifyQuantity(numberOfCommandToGenerate, numberOfCartonToGenerateByCommand);

		// créé un camion de type L (écrit dans le fichier listCamions)
		CamionService camionService = new CamionService();
		List<Camion> listCamions = new LinkedList();
		listCamions.add(camionService.createCamion(2));
		camionService.writeCamion(listCamions);

		// charge le camion
		LoadService ls = new LoadService(listCamions.get(0).getType(), listCamions.get(0).getId());
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();

		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + listCamions.get(0).getId() + ".txt");
		if (fileCamionLoaded.exists()) {

			CamionLoaded camionLoaded = cls.readCamionLoaded(listCamions.get(0).getId());

			// liste des cartons chargés
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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage1) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage2) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage3) <= listCamions.get(0).getType().getLongueur());

			// supprime le camionLoaded pour le test
			cls.deleteCamionLoaded(camionLoaded.getId());

		} else {
			// supprime la commande crée pour le test dans le cas où elle n'a pas été
			// chargée
			cs.deleteCommande(listCommandeGenereted.get(0).getIdCommande());
		}

		// supprime le camion créé pour le test
		camionService.deleteCamionFile();

	}

	/**
	 * Test qui vérifie que le chargement est bien effectué dans chaque rack Vérifie
	 * que la longueur des cartons chargé dans chaque rack est égale ou inférieure à
	 * la longueur du rack (280 pour un camion S)
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@Test
	void test_Chargement_camion_1_commande_camionTypeS()
			throws FileNotFoundException, ClassNotFoundException, IOException {

		int numberOfCommandToGenerate = 1;
		int numberOfCartonToGenerateByCommand = 12;

		// genere une commande
		CommandeService cs = new CommandeService();
		List<Commande> listCommandeGenereted = cs.generateCommandeSpecifyQuantity(numberOfCommandToGenerate, numberOfCartonToGenerateByCommand);

		// créé un camion de type L (écrit dans le fichier listCamions)
		CamionService camionService = new CamionService();
		List<Camion> listCamions = new LinkedList();
		listCamions.add(camionService.createCamion(1));
		camionService.writeCamion(listCamions);

		// charge le camion
		LoadService ls = new LoadService(listCamions.get(0).getType(), listCamions.get(0).getId());
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();

		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + listCamions.get(0).getId() + ".txt");
		if (fileCamionLoaded.exists()) {

			CamionLoaded camionLoaded = cls.readCamionLoaded(listCamions.get(0).getId());

			// liste des cartons chargés
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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage1) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage1) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage2) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage2) <= listCamions.get(0).getType().getLongueur());

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

			assertTrue(Tools.calculTailleUtiliseeParRack(rack1etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack2etage3) <= listCamions.get(0).getType().getLongueur());
			assertTrue(Tools.calculTailleUtiliseeParRack(rack3etage3) <= listCamions.get(0).getType().getLongueur());

			// supprime le camionLoaded pour le test
			cls.deleteCamionLoaded(camionLoaded.getId());

		} else {
			// supprime la commande crée pour le test dans le cas où elle n'a pas été
			// chargée
			cs.deleteCommande(listCommandeGenereted.get(0).getIdCommande());
		}

		// supprime le camion créé pour le test
		camionService.deleteCamionFile();

	}

	/**
	 * Test qui vérifie qu'il y a le même nombre de cartons chargés que de cartons
	 * dans la commande à charger
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	@Test
	void verifie_cartonLoaded_equals_cartonCommande_pour_1_commande()
			throws FileNotFoundException, ClassNotFoundException, IOException {

		int numberOfCommandToGenerate = 1;
		int numberOfCartonToGenerateByCommand = 200;

		// genere une commande
		CommandeService cs = new CommandeService();
		List<Commande> listCommandeGenereted = cs.generateCommandeSpecifyQuantity(numberOfCommandToGenerate, numberOfCartonToGenerateByCommand);

		List<Commande> listCommandes = cs.readCommande();

		// créé un camion de type L (écrit dans le fichier listCamions)
		CamionService camionService = new CamionService();
		List<Camion> listCamions = new LinkedList();
		listCamions.add(camionService.createCamion(3));
		camionService.writeCamion(listCamions);

		// charge le camion
		LoadService ls = new LoadService(listCamions.get(0).getType(), listCamions.get(0).getId());
		ls.loadCamion();
		CamionLoadedService cls = new CamionLoadedService();

		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + listCamions.get(0).getId() + ".txt");
		if (fileCamionLoaded.exists()) {

			CamionLoaded camionLoaded = cls.readCamionLoaded(listCamions.get(0).getId());

			// liste des cartons chargés
			List<CartonLoaded> listCartonLoaded = camionLoaded.getListCartonLoaded();

			int quantiteCartonsCommande = listCommandes.get(0).getListCarton().size();
			int quantiteCartonsLoaded = listCartonLoaded.size();
			assertTrue(quantiteCartonsCommande == quantiteCartonsLoaded);
			

			// supprime le camionLoaded pour le test
			cls.deleteCamionLoaded(camionLoaded.getId());
		} else {
			// supprime la commande crée pour le test dans le cas où elle n'a pas été
			// chargée
			cs.deleteCommande(listCommandeGenereted.get(0).getIdCommande());
		}

		// supprime le camion créé pour le test
		camionService.deleteCamionFile();

	}

}
