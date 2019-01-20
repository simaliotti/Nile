package com.aliottisimon;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.aliottisimon.nile.App;

public class Main {

	/**
	 * Run
	 * @param args
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException  {

		App app = new App();
		app.launchApp();

	}

}
