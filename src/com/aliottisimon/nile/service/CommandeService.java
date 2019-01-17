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

import com.aliottisimon.nile.pojos.Carton;
import com.aliottisimon.nile.pojos.Commande;

import com.aliottisimon.nile.utils.SystemUtils;

public class CommandeService {


	private int numberOfCommand = 1;



	public void generateCommande() throws FileNotFoundException, ClassNotFoundException, IOException {

		Random random = new Random();
		int numberOfCommandToGenerate = (random.nextInt(6) + 2);

		for (int i = 1; i <= numberOfCommandToGenerate; i++) {

			String nameCommande = "Commande" + this.numberOfCommand;
			this.numberOfCommand++;
			Commande commande = new Commande(nameCommande);
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

		
		File filePath = new File(SystemUtils.TEST_FOLDER+"/commandes");
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
	
		
		File fileCommande = new File(SystemUtils.TEST_FOLDER+"/commandes/" + commande.getIdCommande() + ".txt");

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

		File file = new File(SystemUtils.TEST_FOLDER+"/commandes");

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
			File fileCommande = new File(SystemUtils.TEST_FOLDER+"/commandes/" + nameCommande + ".txt");

			try (FileInputStream fis = new FileInputStream(fileCommande);
					ObjectInputStream ois = new ObjectInputStream(fis)) {

				Commande commande = (Commande) ois.readObject();
				commandes.add(commande);
				
				//Lecture des cartons de chaque commande
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
		
		File fileCommande = new File(SystemUtils.TEST_FOLDER+"/commandes/" + nameCommande + ".txt");
		fileCommande.delete();
	}
	
}
