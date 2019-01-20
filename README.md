# Nile
Nile-TP

Programme pour générer des commandes et les expédier.
3 tailles de camions.
3 tailles de cartons.

Le chargement s'effectue de la manière suivante :
chaque étage d 'un camion est divisé en 3 racks.
les racks sont chargés les un à la suite des autres.
Lorsqu'un étage est plein, le même processus s'applique à l'étage supérieur. (si le camion a plusieurs étages)

Une commande sera toujours dans le même camion.

Il est possible de générer les commandes de manière automoatique ou manuelle.

Reglages : dans le package "utils" / classe "SystemUtils",  penser à regler les variabes :
DISK_NAME 
FILE_SEPARATOR
TEST_FOLDER = DISK_NAME + FILE_SEPARATOR
en fonction de votre machine et O'S

Tests Junit :
Pour que les tests fonctionnent correctement, il faut que les dossiers utilisés par l'application, à savoir : 
- /camionLoaded
- /camions
- /commandes

soient vides !

@Simon
