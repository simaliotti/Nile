package com.aliottisimon.nile;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.aliottisimon.nile.service.CamionService;
import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.utils.MyScanner;

public class App {

	CommandeService commandeService = new CommandeService();
	CamionService camionService = new CamionService();
	MyScanner sc = new MyScanner();

	/**
	 * Constructeur par defaut
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public App() throws FileNotFoundException, ClassNotFoundException, IOException {

	}

	public void launchApp() throws FileNotFoundException, ClassNotFoundException, IOException {

		System.out.println("");
		System.out.println("               ==============  NILE  ==============");
		System.out.println("");
		System.out.println("Bienvenue dans votre application de gestion de chargement des stocks !");
		System.out.println("");
		System.out.println("");

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
		default:
			break;
		}

	}



	public void displayMenu() {
		System.out.println("Vous pouvez effectuer les actions suivantes : ");
		System.out.println("1 - Générer des commandes (aléatoires ou manuelles)");
		System.out.println("2 - Générer votre parc de camions");
		System.out.println("3 - Lancer le chargement d'un camion (avec le maximum de commandes possible");
		System.out.println("4 - Expédier un maximum de commandes en fonction des camions disponibles");
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

	
	private void menu3(MyScanner sc) {
		System.out.println("               ==============  Expédiez vos commandes dans le camion de votre choix  ==============");
		System.out.println("");
		System.out.println("Les camions disponibles sont les suivants : ");
		
		
	}
}
