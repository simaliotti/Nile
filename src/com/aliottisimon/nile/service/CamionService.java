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

import javax.swing.text.Utilities;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.pojos.Camion;
import com.aliottisimon.nile.utils.MyScanner;
import com.aliottisimon.nile.utils.SystemUtils;

public class CamionService {

	private int idCamion = 1;

	public void menuCreateCamionPark(MyScanner sc) throws FileNotFoundException, ClassNotFoundException, IOException {

		List<Camion> listCamions = new LinkedList();

		System.out.println("               ==============  Gestionnaire du parc de camions  ==============");
		displayCamionChoix();
		System.out.println("");

		System.out.println("Combien de camion(s) de type SMALL possedez vous ?");
		int numberS = Integer.valueOf(sc.input());
		for (int i = 0; i < numberS; i++) {
			Camion camion = createCamion(1);
			listCamions.add(camion);
		}

		System.out.println("Combien de camion(s) de type MEDIUM possedez vous ?");
		int numberM = Integer.valueOf(sc.input());
		for (int i = 0; i < numberM; i++) {
			Camion camion = createCamion(2);
			listCamions.add(camion);

		}

		System.out.println("Combien de camion(s) de type LARGE possedez vous ?");
		int numberL = Integer.valueOf(sc.input());
		for (int i = 0; i < numberL; i++) {
			Camion camion = createCamion(3);
			listCamions.add(camion);
		}
		System.out.println("===========GENERATING==========");
		writeCamion(listCamions);
		System.out.println("Votre parc a été enregistré avec succès");
	}

	public void displayCamionChoix() {

		System.out.println("");
		System.out.println("1 -  CAMION SMALL  : Longueur: " + CamionType.TYPE_S.getLongueur() + " Largeur: "
				+ CamionType.TYPE_S.getLargeur() + " Hauteur: " + CamionType.TYPE_S.getHauteur());
		System.out.println("2 -  CAMION MEDIUM : Longueur: " + CamionType.TYPE_M.getLongueur() + " Largeur: "
				+ CamionType.TYPE_M.getLargeur() + " Hauteur: " + CamionType.TYPE_M.getHauteur());
		System.out.println("3 -  CAMION LARGE  : Longueur: " + CamionType.TYPE_L.getLongueur() + " Largeur: "
				+ CamionType.TYPE_L.getLargeur() + " Hauteur: " + CamionType.TYPE_L.getHauteur());

	}

	public Camion createCamion(int type) {

		Camion camion = null;

		if (type == 1) {
			camion = new Camion(CamionType.TYPE_S, idCamion);
			this.idCamion++;
		} else if (type == 2) {
			camion = new Camion(CamionType.TYPE_M, idCamion);
			this.idCamion++;
		} else if (type == 3) {
			camion = new Camion(CamionType.TYPE_L, idCamion);
			this.idCamion++;
		} else {

		}
		return camion;
	}

	public static void writeCamion(List<Camion> listCamions)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		File filePath = new File(SystemUtils.TEST_FOLDER + "/camions");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		File fileCamion = new File(SystemUtils.TEST_FOLDER + "/camions/listCamions.txt");

		try (FileOutputStream fop = new FileOutputStream(fileCamion);
				ObjectOutputStream oop = new ObjectOutputStream(fop)) {

			if (!fileCamion.exists()) {
				fileCamion.createNewFile();
			}
			oop.writeObject(listCamions);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void readCamion() throws FileNotFoundException, IOException, ClassNotFoundException {

		// Lit chaque commande
		File fileCamion = new File(SystemUtils.TEST_FOLDER + "/camions/listCamions.txt");

		try (FileInputStream fis = new FileInputStream(fileCamion);
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			List<Camion> listCamions = (List<Camion>) ois.readObject();

			for (Camion camion : listCamions) {
				System.out.println("-------CAMION---------");
				System.out.println(camion.getId());
				System.out.println(camion.getType().toString());
				System.out.println(camion.isDisponible());
				System.out.println("---------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void makeCamionNonDisponible(int idCamionToDelete)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		// Lit chaque commande
		File fileCamion = new File(SystemUtils.TEST_FOLDER + "/camions/listCamions.txt");

		try (FileInputStream fis = new FileInputStream(fileCamion);
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			List<Camion> listCamions = (List<Camion>) ois.readObject();
			Camion updatedCamion = null;
			for (Camion camion : listCamions) {
				if (camion.getId() == idCamionToDelete) {
					updatedCamion = new Camion(camion.getType(), camion.getId());
					updatedCamion.setDisponible(false);
					listCamions.remove(camion);
					listCamions.add(updatedCamion);
					break;
				}
			}

			writeCamion(listCamions);
		}
	}

	public void deleteCamionFile() {
		
		File fileCamion = new File(SystemUtils.TEST_FOLDER + "/camions/listCamions.txt");
		fileCamion.delete();
		
	}

}

