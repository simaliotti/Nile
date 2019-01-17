package com.aliottisimon.nile.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import com.aliottisimon.nile.pojos.Camion;
import com.aliottisimon.nile.pojos.CamionLoaded;
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
	
}
