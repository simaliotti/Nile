package com.aliottisimon;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.aliottisimon.nile.service.CamionService;
import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.utils.MyScanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		MyScanner sc =  new MyScanner();
	//	CommandeService cs = new CommandeService(sc);
	//	cs.generateCommande();
	//	cs.readCommande();
	//	System.out.println("---------------------------------------");
	//	cs.generateCommande();
	//	cs.readCommande();
		
		
	CamionService camionService = new CamionService();
	camionService.menuCreateCamionPark(sc);
	camionService.readCamion();
	}
	

}
