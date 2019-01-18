package com.aliottisimon.nile.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.model.CartonType;
import com.aliottisimon.nile.pojos.CartonLoaded;

public class Tools {

	public static int calculTailleUtiliseeParRack(List<CartonLoaded> rackxXetageY) {

		List<CartonLoaded> listCartonLoadedL = rackxXetageY.stream()
				.filter(cartonLoadedL -> cartonLoadedL.getCartonType().equals(CartonType.TYPE_L))
				.collect(Collectors.toList());

		Integer longueurCartonL = listCartonLoadedL.stream().map(cartonL -> cartonL.getCartonType().getLongueur())
				.reduce(0, Integer::sum);

		List<CartonLoaded> listCartonLoadedM = rackxXetageY.stream()
				.filter(cartonLoadedM -> cartonLoadedM.getCartonType().equals(CartonType.TYPE_M))
				.collect(Collectors.toList());

		Integer longueurCartonM = (listCartonLoadedM.stream().map(cartonM -> cartonM.getCartonType().getLongueur())
				.reduce(0, Integer::sum)) / 2;
		if ((listCartonLoadedM.size()) % 2 != 0) {
			longueurCartonM += 35;
		}

		List<CartonLoaded> listCartonLoadedS = rackxXetageY.stream()
				.filter(cartonLoadedS -> cartonLoadedS.getCartonType().equals(CartonType.TYPE_S))
				.collect(Collectors.toList());

		Integer longueurCartonS = (listCartonLoadedS.stream().map(cartonS -> cartonS.getCartonType().getLongueur())
				.reduce(0, Integer::sum));
		double longueurCartonSD = (double) longueurCartonS / 2;

		if ((listCartonLoadedS.size()) % 2 != 0) {
			longueurCartonSD += 17.5;
		}

		if ((listCartonLoadedS.size() == 1) && (listCartonLoadedM.size() % 2 != 0)) {
			longueurCartonSD = 0;
		}
		if ((listCartonLoadedS.size() == 2) && (listCartonLoadedM.size() % 2 != 0)) {
			longueurCartonSD = 0;
		}
		if ((listCartonLoadedM.size()) % 2 != 0) {
			longueurCartonSD -= 35;
		}
		
		String string = String.valueOf(longueurCartonSD);
		String[] cast = string.split("\\.");
		longueurCartonS = Integer.valueOf(cast[0]);
		

		int longueurUtilisee = longueurCartonL + longueurCartonM + longueurCartonS;
		return longueurUtilisee;
	}

}
