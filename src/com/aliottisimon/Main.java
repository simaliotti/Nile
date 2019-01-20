package com.aliottisimon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.aliottisimon.nile.App;
import com.aliottisimon.nile.utils.SystemUtils;

public class Main {

	/**
	 * Run
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException  {

		File filePath = new File(SystemUtils.TEST_FOLDER + "/camions");
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File filePath2 = new File(SystemUtils.TEST_FOLDER + "/camionLoaded");
		if (!filePath2.exists()) {
			filePath2.mkdirs();
		}
		File filePath3 = new File(SystemUtils.TEST_FOLDER + "/commandes");
		if (!filePath3.exists()) {
			filePath3.mkdirs();
		}
		
		App app = new App();
		app.launchApp();

	}

}
