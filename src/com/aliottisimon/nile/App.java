package com.aliottisimon.nile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.aliottisimon.nile.pojos.Camion;
import com.aliottisimon.nile.pojos.Commande;
import com.aliottisimon.nile.service.CamionLoadedService;
import com.aliottisimon.nile.service.CamionService;
import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.service.LoadService;
import com.aliottisimon.nile.utils.MyScanner;

public class App {

	CamionLoadedService camionLoadedService = new CamionLoadedService();
	CommandeService commandeService = new CommandeService();
	CamionService camionService = new CamionService();
	MyScanner sc = new MyScanner();

/**
 * Lance le programme et permet de naviguer dans les menus
 * @throws FileNotFoundException
 * @throws ClassNotFoundException
 * @throws IOException
 */
	public App() throws FileNotFoundException, ClassNotFoundException, IOException {

	}

	public void launchApp() throws FileNotFoundException, ClassNotFoundException, IOException {

		System.out.println("");
		System.out.println("               ==============  NILE  ==============");
		System.out.println("			      @Simon");
		System.out.println("");
		System.out.println("===========================================================================");
		System.out.println("Bienvenue dans votre application de gestion de chargement des stocks !");
		System.out.println("");
		System.out.println("");
		System.out.println("Pour une première utilisation, ne pas oublier de configurer les variables System dans le package \"utils\" et la classe \"SystemUtils\" ");
		System.out.println("");
		System.out.println("");
		System.out.println("Pour que les tests Junit fonctionnent normalement, les différents dossiers de l'application à savoir : \"camion\", \"commandes\" et \"camionLoaded\" doivent êtres vides ");
		System.out.println("==============================================================================");
		System.out.println("");
		System.out.println("");
		boolean again = true;
		do {
			displayMenu();
			String menu = sc.input();

			switch (menu) {
			case "1":
				menu1(sc);
				break;
			case "2":
				menu2(sc);
				break;
			case "3":
				menu3(sc);
				break;
			case "4":
				menu4(sc);
				break;
			case "5":
				menu5();
				break;
			case "6":
				menu6();
				break;
			case "7":
				menu7();
				break;
			case "8":
				menu8();
				break;
			case "9":
				menu9();
				break;
			case "exit":
				again = false;
				break;
			}

			System.out.println("");
			System.out.println("");
		} while (again);
		System.out.println("Bye bye");
	}

	public void displayMenu() {
		System.out.println("Vous pouvez effectuer les actions suivantes : ");
		System.out.println("1 - Générer des commandes (aléatoires ou manuelles)");
		System.out.println("2 - Générer votre parc de camions");
		System.out.println(
				"3 - Lancer le chargement d'un camion (avec le maximum de commandes possible) pour être exépdié");
		System.out.println("4 - Expédier un maximum de commandes en fonction des camions disponibles");
		System.out.println("5 - Visualiser votre parc de camions");
		System.out.println("6 - Visualiser les camions disponibles");
		System.out.println("7 - Visualiser vos commandes en attente");
		System.out.println("8 - Visualiser le détail d'un commande en attente");
		System.out.println("9 - Visualiser vos commandes expediées (chargées)");

	}

	public void menu1(MyScanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println("               ==============  Génération de commandes  ==============");
		System.out.println("");
		System.out.println("1- Souhaitez-vous générer des commandes automoatiques ?");
		System.out.println(
				"2- Souhaitez-vous choisir le nombre de commandes à générer ainsi que le nombre de cartons par commande ?");
		String menu1 = sc.input();

		switch (menu1) {
		case "1":
			System.out.println("Le nombre de commandes générées est compris entre 1 et 10");
			System.out.println("Le nombre de cartons générés par commande est compris entre 1 et 20");
			System.out.println("===========GENERATING==========");
			commandeService.generateCommande();
			break;
		case "2":
			System.out.println("Veuillez saisir le nombre de commandes à générer :");
			String nombreCommandes = sc.input();
			System.out.println("Veuillez saisir le nombre de cartons à générer par commande :");
			String nombreCartons = sc.input();
			commandeService.generateCommandeSpecifyQuantity(Integer.parseInt(nombreCommandes),
					Integer.parseInt(nombreCartons));
			break;

		}
	}

	private void menu2(MyScanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {
		camionService.menuCreateCamionPark(sc);

	}

	private void menu3(MyScanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {

		// vérifie qu'il y a des commandes en cours avant de lancer le chargement
		List<Commande> listCommandes = commandeService.readCommande();
		if (listCommandes.isEmpty()) {
			System.out.println("Vous n'avez aucune commande en attente d'expedition, veuillez d'abord en générer.");

		} else {
			List<Camion> listCamionDispo = camionService.returnCamionDisponible();
			if (listCamionDispo.isEmpty()) {
				System.out.println("Vous n'avez pas de camion disponible");
			} else {
				LoadService loadService = null;

				System.out.println(
						"               ==============  Expédiez vos commandes dans le camion de votre choix  ==============");
				System.out.println("");
				System.out.println("Vos camions disponibles sont affichés ci-dessous :");
				camionService.readCamionDisponible();
				System.out.println("");
				System.out.println(
						"Veuillez saisir le numéro du camion que vous souhaitez expédier parmis la liste suivante :");
				String numeroCamion = sc.input();
				Camion camion = camionService.readCamionById(Integer.parseInt(numeroCamion));
				loadService = new LoadService(camion.getType(), camion.getId());
				loadService.loadAllPossibleCommandsInTruck();
				camionService.makeCamionNonDisponible(camion.getId());
			}

		}

	}

	private void menu4(MyScanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println(
				"               ==============  Expédiez un maximum de commandes parmis vos camions disponibles  ==============");
		System.out.println("");
		List<Camion> listCamionsDispo = camionService.returnCamionDisponible();

		for (Camion camion : listCamionsDispo) {
			List<String> listCommandes = commandeService.returnAllCommandes();
			if (listCommandes.isEmpty()) {
				break;
			} else {
				LoadService loadService = new LoadService(camion.getType(), camion.getId());
				loadService.loadAllPossibleCommandsInTruck();
				camionService.makeCamionNonDisponible(camion.getId());
			}

		}
		System.out.println("Vos commandes sont chargées");
	}

	private void menu5() throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println("               ==============  Votre parc de camions  ==============");
		System.out.println("Voici la liste de tous vos camions : ");
		System.out.println("");
		camionService.readCamion();

	}

	private void menu6() throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println("               ==============  Votre parc de camions disponibles  ==============");

		List<Camion> listCamionDispo = camionService.returnCamionDisponible();
		if (listCamionDispo.isEmpty()) {
			System.out.println("");
			System.out.println("Vous n'avez pas de camion disponible");
		} else {
			System.out.println("Voici la liste de vos camions disponibles : ");
			System.out.println("");
			camionService.readCamionDisponible();

		}

	}

	private void menu7() throws FileNotFoundException, ClassNotFoundException, IOException {

		System.out.println("               ==============  Vos commandes en attente   ==============");
		System.out.println("");
		List<String> listCommandes = commandeService.returnAllCommandes();
		if (listCommandes.isEmpty()) {
			System.out.println("Vous n'avez aucune commande en attente");
		} else {

			System.out.println("Voici la liste de vos commandes en attente de chargement :");
			System.out.println("");
			commandeService.displayAllCommandes();
		}

	}

	private void menu8() throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println("               ==============  Détail d'une commande  ==============");
		System.out.println("");
		menu7();
		System.out.println("");
		System.out.println("Veuillez saisir le numéro d'une commande pour afficher son détail");
		String numeroCommande = sc.input();
		commandeService.readCommandeById(Integer.parseInt(numeroCommande));
	}

	private void menu9() throws FileNotFoundException, ClassNotFoundException, IOException {
		System.out.println("               ==============  Vos commandes chargées   ==============");
		System.out.println("");
		System.out.println("Liste des camions chargés :");
		System.out.println("");
		camionService.readCamionNonDisponible();
		System.out.println("");
		System.out.println("Veuillez sélectionner un camion chargé pour afficher ses commandes");
		System.out.println("");
		String camionCharge = sc.input();
		System.out.println("Camion selectionné :");
		camionService.readCamionById(Integer.parseInt(camionCharge));
		System.out.println("");
		System.out.println("Commandes chargées dans le camion :");
		System.out.println("");
		camionLoadedService.readCamionLoaded(Integer.parseInt(camionCharge));

	}
}
