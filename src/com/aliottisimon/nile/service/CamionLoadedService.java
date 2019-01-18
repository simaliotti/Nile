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

		File filePath = new File(SystemUtils.TEST_FOLDER+"/camionLoaded");
		if(!filePath.exists()) {
			filePath.mkdirs();
		}
	
		
		
		File fileCamion = new File(SystemUtils.TEST_FOLDER+"/camionLoaded/CamionLoaded-"+camionLoaded.getId()+".txt");

		try (FileOutputStream fop = new FileOutputStream(fileCamion);
				ObjectOutputStream oop = new ObjectOutputStream(fop)) {

			if (!fileCamion.exists()) {
				fileCamion.createNewFile();
			}
			oop.writeObject(camionLoaded);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void readCamionLoaded(int idCamionLoaded) throws FileNotFoundException, IOException, ClassNotFoundException {

		// Lit chaque commande
		File fileCamionLoaded = new File(SystemUtils.TEST_FOLDER + "/camionLoaded/CamionLoaded-"+idCamionLoaded+".txt");

		try (FileInputStream fis = new FileInputStream(fileCamionLoaded);
				ObjectInputStream ois = new ObjectInputStream(fis)) {

			CamionLoaded camionLoaded = (CamionLoaded) ois.readObject();

			for (CartonLoaded cartonLoaded : camionLoaded.getListCartonLoaded()) {
				System.out.println("-------CartonLoaded---------");
				System.out.println("id du carton: "+cartonLoaded.getId());
				System.out.println("Emplacement dans le rack: "+cartonLoaded.getIdPlace());
				System.out.println("numero du rack: "+cartonLoaded.getIdRack());
				System.out.println("numero de l'étage: "+cartonLoaded.getFloor());
				System.out.println("id du camion: "+cartonLoaded.getIdCamion());
			
				System.out.println("---------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
