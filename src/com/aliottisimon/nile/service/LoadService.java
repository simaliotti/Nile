package com.aliottisimon.nile.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.model.CartonType;
import com.aliottisimon.nile.pojos.Carton;
import com.aliottisimon.nile.pojos.Commande;

public class LoadService {

	private boolean isEmptyRack_1 = true;
	private boolean isEmptyRack_2 = true;
	private boolean isEmptyRack_3 = true;

	private CamionType camionType = null;
	private List<Commande> listCommandes = null;

	CommandeService commandeService = new CommandeService();

	public LoadService(CamionType camionType) throws FileNotFoundException, ClassNotFoundException, IOException {

		// Set le type de camion
		this.camionType = camionType;
		// Récupère la liste de commandes
		this.listCommandes = commandeService.readCommande();
	}

	public void loadCamion() {

		int commandeNumber = listCommandes.size();

		// recupere liste cartons commande n

		Commande commande = listCommandes.get(0);
		Stream<Carton> streamL = commande.getListCarton().stream();
		streamL = streamL.filter(carton -> carton.getType().equals(CartonType.TYPE_L));
		List<Carton> listCartonsL = streamL.collect(Collectors.toList()); // Liste des cartons taille L

		Stream<Carton> streamM = commande.getListCarton().stream();
		streamM = streamM.filter(carton -> carton.getType().equals(CartonType.TYPE_L));
		List<Carton> listCartonsM = streamM.collect(Collectors.toList()); // Liste des cartons taille M

		Stream<Carton> streamS = commande.getListCarton().stream();
		streamS = streamS.filter(carton -> carton.getType().equals(CartonType.TYPE_L));
		List<Carton> listCartonsS = streamS.collect(Collectors.toList()); // Liste des cartons taille S

		
		
		//rempli le camion sur un étage
		for (int rackNumber = 1; rackNumber <= 3; rackNumber++) {

			// remplissage d'un rack
			int longueurCartonCumul = 0;

			// Charge les cartons type L
			if (listCartonsL.size() > 0) {
				for (Carton cartonL : listCartonsL) {
					longueurCartonCumul += cartonL.getType().getLongueur();
					if (longueurCartonCumul <= camionType.getLongueur()) {
						// loading truck
						// remove from list
					} else {
						break;

					}
				}
			}

			// charge les cartons type M
			int counterM = 0;
			if (listCartonsM.size() > 0) {

				for (Carton cartonM : listCartonsM) {
					counterM = counterM++;
					if ((counterM % 2) != 0) {
						longueurCartonCumul += cartonM.getType().getLongueur();
					}
					if (longueurCartonCumul <= camionType.getLongueur()) {
						// loading truck
						// remove from list
					} else {
						break;
					}

				}

			}

			// charge les cartons type S
			int counterS1 = 0;
			int counterS2 = 0;

			if (listCartonsS.size() > 0) {
				for (Carton cartonS : listCartonsS) {
					counterS1++;
					counterS2++;
					if (((counterM % 2) != 0) && (counterS1 == 1) || ((counterM % 2) != 0) && (counterS1 == 1)) {
						// on ne change pas la longueur
					} else {
						if ((counterS2 % 2) != 0) {
							longueurCartonCumul += cartonS.getType().getLongueur();
						}

					}
					if (longueurCartonCumul <= camionType.getLongueur()) {
						// loading truck
						// remove from list
					} else {
						// set rack at full state
						switch (rackNumber) {
						case 1:
							isEmptyRack_1 = false;
							break;
						case 2:
							isEmptyRack_2 = false;
							break;
						case 3:
							isEmptyRack_3 = false;
							break;
						}
						break;
					}

				}
			}

			if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
				// commande est chargée completement
				// remove la commande de la liste
				break; //on sort du remplissage des racks
			}

		}

	}
}
