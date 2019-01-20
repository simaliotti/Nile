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
	private int camionFloors = 0;
	private int idCamion = 0;
	private List<Commande> listCommandes = null;
	boolean isCamionFull = false;

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

		// set le type de camio,
		this.camionType = camionType;
		// recupère la liste de commandes
		this.listCommandes = commandeService.readCommande();
		// set le nombre d'étages du camion
		this.camionFloors = camionType.getHauteur();
		// set l'id du camion
		this.idCamion = idCamion;
	}

	/**
	 * Methode permettant de charger un camion avec le plus de commandes possible.
	 * Si une commande est trop importante pour être chargée, le programme essayera
	 * quand même de charger les suivantes et ne sera pas bloqué
	 * 
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public void loadAllPossibleCommandsInTruck() throws FileNotFoundException, ClassNotFoundException, IOException {

		while (!(this.listCommandes.isEmpty())) {
			this.isCamionFull = false;
			loadCamion();

		}

	}

	/**
	 * Methode de chargement du camion. Essaye de charger les commandes dans
	 * l'ordre.
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public void loadCamion() throws FileNotFoundException, ClassNotFoundException, IOException {

		List<Commande> listCommandesToRemove = new LinkedList();
		Commande commandeEnCoursDeTraitement = null;
		Commande commandeEnCours = listCommandes.get(0);
		List<CartonLoaded> listCartonLoaded = new LinkedList();
		CamionLoaded camionLoaded = null;

		// valeur utilisées lorsqu'on charge une nouvelle commande dans le camion
		int floorEnCoursChargement = 1;
		int rackEnCoursChargement = 1;
		int counteridPlace = 0;
		int longueurCartonCumul = 0;

		// Boucle sur chaque commande
		for (Commande commande : listCommandes) {
			commandeEnCoursDeTraitement = commande;

			// recupere liste des cartons d'une commande par type
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
			for (int i = floorEnCoursChargement; i <= camionFloors; i++) {
				floorEnCoursChargement = i;

				// remplissage d'un rack
				if (commandeEnCours.equals(commande)) {
					rackEnCoursChargement = 1;
				}

				for (int rackNumber = rackEnCoursChargement; rackNumber <= 3; rackNumber++) {
					rackEnCoursChargement = rackNumber;

					if (commandeEnCours.equals(commande)) {
						counteridPlace = 0;
						longueurCartonCumul = 0;
					}
					commandeEnCours = commande;

					// Charge les cartons de type L
					if (listCartonsL.size() > 0) {

						List<Carton> listDeleteCartonL = new LinkedList();
						for (Carton cartonL : listCartonsL) {
							longueurCartonCumul += cartonL.getType().getLongueur();
							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonL.getIdCarton(),
										rackNumber, i, cartonL.getType()); // load carton
								listCartonLoaded.add(cl); // ajoute à la liste de cartons chargés
								listDeleteCartonL.add(cartonL); // remove carton from list
							} else {
								longueurCartonCumul -= cartonL.getType().getLongueur();
								break;
							}
						}
						for (Carton carton : listDeleteCartonL) {
							listCartonsL.remove(carton); // retire les cartons chargés de la liste de cartons à charger
						}
					}

					// charge les cartons de type M
					int counterM = 0;
					if (listCartonsM.size() > 0) {
						List<Carton> listDeleteCartonM = new LinkedList();
						for (Carton cartonM : listCartonsM) {
							counterM++;
							if ((counterM % 2) != 0) { // il faut deux cartons M (70*35) pour faire avancer la longueur
														// gloable utilisée dans un rack
								longueurCartonCumul += cartonM.getType().getLongueur();
							}
							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonM.getIdCarton(),
										rackNumber, i, cartonM.getType()); // load carton
								listCartonLoaded.add(cl);
								listDeleteCartonM.add(cartonM); // remove carton from list
							} else {
								if ((counterM % 2) != 0) {
									longueurCartonCumul -= cartonM.getType().getLongueur();
									counterM--;
								}
								break;
							}
						}
						for (Carton carton : listDeleteCartonM) {
							listCartonsM.remove(carton);
						}
					}

					// charge les cartons de type S
					int counterS1 = 0;
					int counterS2 = 0;

					if (listCartonsS.size() > 0) {
						List<Carton> listDeleteCartonS = new LinkedList();
						for (Carton cartonS : listCartonsS) {
							counterS1++;
							counterS2++;
							if (((counterM % 2) != 0) && (counterS1 == 1)
									|| ((counterM % 2) != 0) && (counterS1 == 2)) {
								// on ne change pas la longueur
							} else {
								if ((counterS2 % 2) != 0) {
									longueurCartonCumul += cartonS.getType().getLongueur();
								}
							}

							if (longueurCartonCumul <= camionType.getLongueur()) {
								counteridPlace++;
								CartonLoaded cl = new CartonLoaded(idCamion, counteridPlace, cartonS.getIdCarton(),
										rackNumber, i, cartonS.getType()); // load carton
								listCartonLoaded.add(cl);
								listDeleteCartonS.add(cartonS); // remove carton from list
							} else {
								if (((counterM % 2) != 0) && (counterS1 == 1)
										|| ((counterM % 2) != 0) && (counterS1 == 2)) {
									// on ne change pas la longueur
								} else {
									if ((counterS2 % 2) != 0) {
										longueurCartonCumul -= cartonS.getType().getLongueur();
									}
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

				} // end of loop rack

				if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {
					// la commande est ajoutée à la liste de cartons chargés
					commandeService.deleteCommande(commande.getIdCommande()); // on supprime la commande de la liste
																				// (supprime le fichier)

					break; // on sort du remplissage de l'étage

				} else if ((!listCartonsL.isEmpty() || !listCartonsM.isEmpty() || !listCartonsS.isEmpty())
						&& i == camionFloors) {
					this.isCamionFull = true; // camion plein et il reste des elements de commande en cours
					// retirer la commande incomplète du camion
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
			} // end of loop floor

			if (this.isCamionFull == true) { // si le camion est plein on sort de la boucle sur des commandes
				listCommandesToRemove.add(commandeEnCoursDeTraitement);
				break;
			} else if (listCartonsL.isEmpty() && listCartonsM.isEmpty() && listCartonsS.isEmpty()) {

			}
			listCommandesToRemove.add(commandeEnCoursDeTraitement);
		} // end of loop commande
		if (this.isCamionFull && listCommandes.size() == 1) {
			// si 1 une seule commande et qu'elle ne tien pas de la camion, on ne créé pas
			// de fichier camionLoaded. La commande a été retirée. Le camion n'est pas plein

			this.isCamionFull = false;
		} else {
			if (isCamionFull) {
				camionService.makeCamionNonDisponible(idCamion); // passe le camion à l'état non disponible
				camionLoaded = new CamionLoaded(this.camionType, this.idCamion, listCartonLoaded);
				camionLoadedService.writeCamionLoaded(camionLoaded);

			}
			camionLoaded = new CamionLoaded(this.camionType, this.idCamion, listCartonLoaded);
			camionLoadedService.writeCamionLoaded(camionLoaded);
		}
		for (Commande commandeToRemove : listCommandesToRemove) {
			this.listCommandes.remove(commandeToRemove);
		}
	}
}
