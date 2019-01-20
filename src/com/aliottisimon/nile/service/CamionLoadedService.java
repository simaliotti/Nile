package com.aliottisimon.nile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import com.aliottisimon.nile.pojos.Camion;
import com.aliottisimon.nile.pojos.CamionLoaded;
import com.aliottisimon.nile.pojos.CartonLoaded;
import com.aliottisimon.nile.utils.SystemUtils;

public class CamionLoadedService {

	public static void writeCamionLoaded(CamionLoaded camionLoaded)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		// créé le repertoire /camionLoaded
		File filePath = new File(SystemUtils.TEST_FOLDER + "/camionLoaded");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}

		// Récupère le contenu du fichier
		CamionLoaded camionLoadedAlreadyExisting = null;
		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + camionLoaded.getId() + ".txt");
		if (fileCamionLoaded.exists()) {
			try (FileInputStream fis = new FileInputStream(fileCamionLoaded);
					ObjectInputStream ois = new ObjectInputStream(fis)) {

				camionLoadedAlreadyExisting = (CamionLoaded) ois.readObject();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// écrit dans le fichier (overwrite)

		try (FileOutputStream fop = new FileOutputStream(fileCamionLoaded);
				ObjectOutputStream oop = new ObjectOutputStream(fop)) {

			if (!fileCamionLoaded.exists()) {
				fileCamionLoaded.createNewFile();
			}
			oop.writeObject(camionLoaded);
			if (camionLoadedAlreadyExisting != null) {
				oop.writeObject(camionLoadedAlreadyExisting);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	
	
	public static CamionLoaded readCamionLoaded(int idCamionLoaded)
			throws FileNotFoundException, IOException, ClassNotFoundException {

		// Lit chaque commande
		File fileCamionLoaded = new File(
				SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-" + idCamionLoaded + ".txt");
		CamionLoaded camionLoaded = null;
		try (FileInputStream fis = new FileInputStream(fileCamionLoaded);
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			camionLoaded = (CamionLoaded) ois.readObject();
			System.out.println(camionLoaded.getType());
			System.out.println(camionLoaded.getId());
			System.out.println(camionLoaded.getListCartonLoaded());
			for (CartonLoaded cartonLoaded : camionLoaded.getListCartonLoaded()) {
				System.out.println("-------CartonLoaded---------");
				System.out.println("type du carton: " + cartonLoaded.getCartonType());
				System.out.println("id du carton: " + cartonLoaded.getId());
				System.out.println("Emplacement dans le rack: " + cartonLoaded.getIdPlace());
				System.out.println("numero du rack: " + cartonLoaded.getIdRack());
				System.out.println("numero de l'étage: " + cartonLoaded.getFloor());
				System.out.println("id du camion: " + cartonLoaded.getIdCamion());

				System.out.println("---------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return camionLoaded;
	}

	public void deleteCamionLoaded(int idCamionLoaded) {
		
		File fileCamionLoaded = new File(SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-"+idCamionLoaded+".txt");
		fileCamionLoaded.delete();
		
	}

}
