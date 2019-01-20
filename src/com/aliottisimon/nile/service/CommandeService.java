package com.aliottisimon.nile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aliottisimon.nile.pojos.Carton;
import com.aliottisimon.nile.pojos.Commande;

import com.aliottisimon.nile.utils.SystemUtils;


public class CommandeService {

	private int numberOfCommand = 0;

	/**
	 * Constructeur qui récupère le dernier numero de commande
	 * 
	 * @param numberOfCommand
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public CommandeService() throws FileNotFoundException, ClassNotFoundException, IOException {
		this.numberOfCommand = dernierNumeroDeCommande();
	}

	/**
	 * Methode utilisé pour les tests, permet de choisir le nombre de commandes
	 * générées ainsi que de cartons
	 * 
	 * @param quantityCommande
	 * @param quantityCarton
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public List<Commande> generateCommandeSpecifyQuantity(int quantityCommande, int quantityCarton)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		List<Commande> listCommandesGenereted = new LinkedList();
		Random random = new Random();

		// numberOfCommandToGenerate
		for (int i = 1; i <= quantityCommande; i++) {
			this.numberOfCommand++;
			String idCommande = "Commande" + this.numberOfCommand;
			Commande commande = new Commande(idCommande, quantityCarton);
			listCommandesGenereted.add(commande);
			writeCommand(commande);

			//
			System.out.println("======================");
			System.out.println(commande.getIdCommande());
			for (Carton carton : commande.getListCarton()) {
				System.out.println(carton.getIdCarton());
				System.out.println(carton.getType());

			}
			System.out.println("Commande n° " + i + " créée avec succès");
		}

		return listCommandesGenereted;
	}

	/**
	 * Methode pour générer une commande
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void generateCommande() throws FileNotFoundException, ClassNotFoundException, IOException {

		Random random = new Random();
		int numberOfCommandToGenerate = (random.nextInt(10) + 1);
		// numberOfCommandToGenerate
		for (int i = 1; i <= numberOfCommandToGenerate; i++) {
			this.numberOfCommand++;
			String idCommande = "Commande" + this.numberOfCommand;
			Commande commande = new Commande(idCommande);
			writeCommand(commande);

			//
			System.out.println("======================");
			System.out.println(commande.getIdCommande());
			for (Carton carton : commande.getListCarton()) {
				System.out.println(carton.getIdCarton());
				System.out.println(carton.getType());

			}
			System.out.println("Commande n° " + i + " créée avec succès");
		}

	}

	public static void writeCommand(Commande commande)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		// créé le repertoire si il n'existe pas
		File filePath = new File(SystemUtils.TEST_FOLDER + "/commandes");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		File fileCommande = new File(SystemUtils.TEST_FOLDER + "/commandes/" + commande.getIdCommande() + ".txt");

		try (FileOutputStream fop = new FileOutputStream(fileCommande);
				ObjectOutputStream oop = new ObjectOutputStream(fop)) {

			if (!fileCommande.exists()) {
				fileCommande.createNewFile();
			}
			oop.writeObject(commande);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<Commande> readCommande() throws FileNotFoundException, IOException, ClassNotFoundException {

		// Liste toutes les commandes
		List<String> listCommandes = new LinkedList<>();

		File file = new File(SystemUtils.TEST_FOLDER + "/commandes");

		System.out.println("Liste des commandes enregistrés :");

		String[] tabCommandes = file.list();

		if (tabCommandes.length == 0) {
			System.out.println("La liste est vide, pas de clubs enregistrés");
		} else {

			for (String string : tabCommandes) {
				String[] nameCommande = string.split("(.txt)");
				System.out.println(nameCommande[0]);
				listCommandes.add(nameCommande[0]);
			}

		}
		

		List<Commande> commandes = new LinkedList<>();

		for (String nameCommande : listCommandes) {
			// Lit chaque commande
			File fileCommande = new File(SystemUtils.TEST_FOLDER + "/commandes/" + nameCommande + ".txt");

			try (FileInputStream fis = new FileInputStream(fileCommande);
					ObjectInputStream ois = new ObjectInputStream(fis)) {

				Commande commande = (Commande) ois.readObject();
				commandes.add(commande);

				// Lecture des cartons de chaque commande
				System.out.println("======================");
				System.out.println(commande.getIdCommande());
				for (Carton carton : commande.getListCarton()) {
					System.out.println(carton.getIdCarton());
					System.out.println(carton.getType());
				}
				System.out.println("----------------");

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return commandes;
	}

	public void deleteCommande(String nameCommande) {

		File fileCommande = new File(SystemUtils.TEST_FOLDER + "/commandes/" + nameCommande + ".txt");
		fileCommande.delete();
	}

	public int dernierNumeroDeCommande() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Commande> listCommandes = readCommande();
		int numeroDerniereCommande = 0;

		if (!(listCommandes.isEmpty())) {
			Stream<Commande> stream = listCommandes.stream();
			List<String> listIdCommande = stream.map(commande -> commande.getIdCommande()).collect(Collectors.toList());
			for (String idCommande : listIdCommande) {
				String[] numero = idCommande.split("e");
				int num = Integer.parseInt(numero[1]);
				if (num > numeroDerniereCommande) {
					numeroDerniereCommande = num;
				}
			}

		}

		return numeroDerniereCommande;
	}
}
