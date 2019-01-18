package com.aliottisimon.nile.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.aliottisimon.nile.model.CamionType;
import com.aliottisimon.nile.model.CartonType;
import com.aliottisimon.nile.pojos.CamionLoaded;
import com.aliottisimon.nile.pojos.Carton;
import com.aliottisimon.nile.pojos.CartonLoaded;
import com.aliottisimon.nile.pojos.Commande;

public class LoadService {

	private CamionType camionType = null;
	boolean isCamionFull = false;
	private int camionFloors = 0;
	private int idCamion = 0;
	private List<Commande> listCommandes = null;

	CommandeService commandeService = new CommandeService();
	CamionService camionService = new CamionService();
	CamionLoadedService camionLoadedService = new CamionLoadedService();

	/**
	 * Constructeur loadService
	 * 
	 * @param camionType
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public LoadService(CamionType camionType, int idCamion)
			throws FileNotFoundException, ClassNotFoundException, IOException {

		// Set le type de camion
		this.camionType = camionType;
		// Récupère la liste de commandes
		this.listCommandes = commandeService.readCommande();
		// set le nombre d'étage du camion
		this.camionFloors = camionType.getHauteur();
		// set l'id du camion
		this.idCamion = idCamion;
	}

	
	/**
	 * Méthode permeettant de charger un camion
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public void loadCamion() throws FileNotFoundException, ClassNotFoundException, IOException {

		int commandeNumber = listCommandes.size();
		List<CartonLoaded> listCartonLoaded = new LinkedList();
		CamionLoaded camionLoaded = null;
		int rackEnCoursDeChargement = 0;
		int longueurDeRackChargee = 0;
		int etageEnCoursDeChargement = 0;

		//Boucle sur les commandes à charger
		
		for (Commande commande : listCommandes) {

			// recupere liste des cartons d'une commande
			Stream<Carton> streamL = commande.getListCarton().stream();
			streamL = streamL.filter(carton -> carton.getType().equals(CartonType.TYPE_L));
			List<Carton> listCartonsL = streamL.collect(Collectors.toList()); // Liste des cartons taille L
			

			Stream<Carton> streamM = commande.getListCarton().stream();
			streamM = streamM.filter(carton -> carton.getType().equals(CartonType.TYPE_M));
			List<Carton> listCartonsM = streamM.collect(Collectors.toList()); // Liste des cartons taille M

			Stream<Carton> streamS = commande.getListCarton().stream();
			streamS = streamS.filter(carton -> carton.getType().equals(CartonType.TYPE_S));
			List<Carton> listCartonsS = streamS.collect(Collectors.toList()); // Liste des cartons taille S

			// rempli le camion sur un etage
			for (int i = 1; i <= camionFloors; i++) {

				boolean isFullRack_1 = false;
				boolean isFullRack_2 = false;
				boolean isFullRack_3 = false;

				// remplissage d'un rack
				for (int rackNumber = 1; rackNumber <= 3; rackNumber++) {
					int counteridPlace = 0;
					int longueurCartonCumul = 0;

					// Charge les cartons type L
					if (listCartonsL.size() > 0) {
						List<Carton>listDeleteCartonL = new LinkedList();
						for (Carton cartonL : listCartonsL) {
							
							longueurCartonCumul += cartonL.getType().getLongueur();
							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonL.getIdCarton(),
										rackNumber, i); // load carton
								listCartonLoaded.add(cl);
								listDeleteCartonL.add(cartonL); // remove carton from list
							} else {
								break;
							}
						}
						for (Carton carton : listDeleteCartonL) {
							listCartonsL.remove(carton);
						}
					}

					// charge les cartons type M
					int counterM = 0;
					if (listCartonsM.size() > 0) {
						List<Carton>listDeleteCartonM = new LinkedList();
						for (Carton cartonM : listCartonsM) {
							counterM++;
							if ((counterM % 2) != 0) {
								longueurCartonCumul += cartonM.getType().getLongueur();
							}
							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonM.getIdCarton(),
										rackNumber, i); // load carton
								listCartonLoaded.add(cl);
								listDeleteCartonM.add(cartonM); // remove carton from list
							} else {
								break;
							}
						}
						for (Carton carton : listDeleteCartonM) {
							listCartonsM.remove(carton);
						}
					}
					

					// charge les cartons type S
					int counterS1 = 0;
					int counterS2 = 0;

					if (listCartonsS.size() > 0) {
						List<Carton>listDeleteCartonS = new LinkedList();
						for (Carton cartonS : listCartonsS) {
							counterS1++;
							counterS2++;
							if (((counterM % 2) != 0) && (counterS1 == 1)
									|| ((counterM % 2) != 0) && (counterS1 == 1)) {
								// on ne change pas la longueur
							} else {
								if ((counterS2 % 2) != 0) {
									longueurCartonCumul += cartonS.getType().getLongueur();
								}
							}

							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonS.getIdCarton(),
										rackNumber, i); // load carton
								listCartonLoaded.add(cl);
								listDeleteCartonS.add(cartonS);  // remove carton from list
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
						for (Carton carton : listDeleteCartonS) {
							listCartonsS.remove(carton);
						}
					}
					// verifie si la commande est toute chargée
					if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
						break; // on sort du remplissage des racks

					}

				}

				if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
					// la commande est ajoutée à la liste de cartons chargés
					commandeService.deleteCommande(commande.getIdCommande()); // on supprime la commande de la liste des
																				// commandes
					break; // on sort du remplissage de l'étage
				} else if ((!listCartonsL.isEmpty() || !listCartonsM.isEmpty() || !listCartonsS.isEmpty())
						&& i == camionFloors) {
					this.isCamionFull = true; // camion plein et il reste des elements de commande en cours
					// retirer la commande du camion
					List<CartonLoaded> listCartonLoadedToDelete = new LinkedList();
					for (CartonLoaded cartonLoaded : listCartonLoaded) {
						if (cartonLoaded.getId().contains(commande.getIdCommande())) {
							listCartonLoadedToDelete.add(cartonLoaded);
							
						}
					}
					for (CartonLoaded cartonLoadedToDelete : listCartonLoadedToDelete) {
						listCartonLoaded.remove(cartonLoadedToDelete);
					}
				}
			}
			camionLoaded = new CamionLoaded(this.camionType, this.idCamion, listCartonLoaded);
			camionLoadedService.writeCamionLoaded(camionLoaded);
			camionService.makeCamionNonDisponible(idCamion); // passe le camion à l'état non disponible

			if(this.isCamionFull==true) {
				break;
			}
		}

	}
}
