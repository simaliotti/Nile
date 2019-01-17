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

	private CamionType camionType = null;
	private List<Commande> listCommandes = null;
	CommandeService commandeService = new CommandeService();
	boolean isCamionFull = false;
	private int camionFloors = 0;

	/**
	 * Constructeur loadService
	 * 
	 * @param camionType
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public LoadService(CamionType camionType) throws FileNotFoundException, ClassNotFoundException, IOException {

		// Set le type de camion
		this.camionType = camionType;
		// Récupère la liste de commandes
		this.listCommandes = commandeService.readCommande();
		// set le nombre d'étage du camion
		this.camionFloors = camionType.getHauteur();
	}

	/**
	 * Méthode permeettant de charger un camion
	 */
	public void loadCamion() {

		int commandeNumber = listCommandes.size();

		// recupere liste cartons d'une commande

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

		// rempli le camion sur un etage
		for (int i = 1; i <= camionFloors; i++) {

			boolean isFullRack_1 = false;
			boolean isFullRack_2 = false;
			boolean isFullRack_3 = false;

			// remplissage d'un rack
			for (int rackNumber = 1; rackNumber <= 3; rackNumber++) {

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
								isFullRack_1 = true;
								break;
							case 2:
								isFullRack_2 = true;
								break;
							case 3:
								isFullRack_3 = true;
								break;
							}
							break;
						}
					}
				}
				// verifie si la commande est toute chargée
				if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
					break; // on sort du remplissage des racks

				}

			}

			if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
				// commande est chargée completement
				// remove la commande de la liste
				// sortir la ou en est le chargement
				break; // on sort du remplissage de l'étage
			} else if ((listCartonsL.isEmpty() || listCartonsM.isEmpty() || listCartonsS.isEmpty())
					&& i == camionFloors) {
				// camion plein
				this.isCamionFull = true;
				// retirer la derniere commande
			}
		}
	}
}
