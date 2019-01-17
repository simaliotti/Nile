package com.aliottisimon;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.service.CamionService;
import com.aliottisimon.nile.service.CommandeService;
import com.aliottisimon.nile.service.LoadService;
import com.aliottisimon.nile.utils.MyScanner;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {

		MyScanner sc = new MyScanner();
		//CommandeService cs = new CommandeService();
		// cs.generateCommande();
		// cs.readCommande();
		// System.out.println("---------------------------------------");
		// cs.generateCommande();
		//cs.readCommande();

		 CamionService camionService = new CamionService();
		// camionService.menuCreateCamionPark(sc);
		 //camionService.readCamion();
	LoadService ls = new LoadService(CamionType.TYPE_L);
	//ls.loadCamion();

	List<Integer> listInt = new LinkedList<>();
	listInt.add(45);
	System.out.println(listInt);
	for (Integer integer : listInt) {
		System.out.println("--");
		System.out.println("integer :"+integer);
		
	}
	
	
	}

}
