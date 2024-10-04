package fr.tristan.projet.models.algos;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.ressources.Noeud;
import fr.tristan.projet.ressources.Types;

import java.util.ArrayList;
import java.util.List;


public class Astar extends Algorithme {
    private Types[][] grille; // Grille du labyrinthe
    private Noeud[][] nodes; // Matrice de nœuds représentant chaque case de la grille
    private Noeud startNode, endNode; // Nœuds de départ et d'arrivée
    private List<Noeud> openList, closedList; // Listes des nœuds ouverts et fermés

    private List<Types[][]> historique_grille;

    public Astar(Labyrinthe labyrinthe) {
        super(labyrinthe);
        this.grille = labyrinthe.getLabyrinthe_grille().getGrille(); // Récupère la grille du labyrinthe
        this.nodes = new Noeud[grille.length][grille[0].length]; // Initialise la matrice de nœuds
        this.openList = new ArrayList<>(); // Initialise la liste des nœuds ouverts
        this.closedList = new ArrayList<>(); // Initialise la liste des nœuds fermés
        this.historique_grille = new ArrayList<>();
        initializeNodes(); // Initialise les nœuds à partir de la grille
    }

    private void initializeNodes() {
        // Remplit la matrice de nœuds et identifie le départ et l'arrivée
        for (int y = 0; y < grille.length; y++) {
            for (int x = 0; x < grille[y].length; x++) {
                nodes[y][x] = new Noeud(x, y, grille[y][x]) {
                };
                if (grille[y][x] == Types.DEPART) {
                    startNode = nodes[y][x]; // Définit le nœud de départ
                } else if (grille[y][x] == Types.ARRIVEE) {
                    endNode = nodes[y][x]; // Définit le nœud d'arrivée
                }
            }
        }
    }

    public void resoudre() {
        openList.add(startNode);

        while (!openList.isEmpty()) {
            Noeud currentNode = getLowestFCostNode();

            //Trace le chemin et coupe la boucle
            if (currentNode == endNode) {
                trace_chemin();
                break;
            }

            openList.remove(currentNode);
            closedList.add(currentNode);

            // Marque le nœud courant comme exploré, sauf pour le départ et l'arrivée
            if (currentNode != startNode && currentNode != endNode && grille[currentNode.getY()][currentNode.getX()] != Types.MUR) {
                grille[currentNode.getY()][currentNode.getX()] = Types.EXPLORER;
            }

            // Explore les voisins du nœud courant
            for (Noeud neighbor : getVoisins(currentNode)) {
                if (closedList.contains(neighbor) || neighbor.getType() == Types.MUR) {
                    continue; // Ignore les voisins déjà explorés ou les murs
                }

                int newGCost = currentNode.getGCost() + 1; // Calcule le coût G du voisin
                if (newGCost < neighbor.getGCost() || !openList.contains(neighbor)) {
                    neighbor.setGCout(newGCost); // Met à jour le coût G du voisin
                    neighbor.setHCout(getDistance(neighbor, endNode)); // Calcule et met à jour le coût H
                    neighbor.setParent(currentNode); // Définit le parent du voisin

                    if (!openList.contains(neighbor)) {
                        openList.add(neighbor); // Ajoute le voisin à la liste ouverte s'il n'y est pas déjà
                    }
                }
            }



            historique_grille.add(grille);




        }

        labyrinthe.set_grille(grille);
    }


    public  void afficher_grille(Types [][] grillet)
    {
        int hauteur = grillet.length;
        int largeur = grillet[0].length;
        String retour = "";
        for(int j=0; j< hauteur; j++)
        {
            retour += "(";
            for(int i =0; i < largeur; i++)
            {
                retour += grillet[j][i] + ", ";
            }
            retour += ")\n";
        }

        System.out.println(retour);
    }


    private Noeud getLowestFCostNode() {
        Noeud lowestFCostNode = openList.get(0);
        for (int i = 1; i < openList.size(); i++) {
            if (openList.get(i).getFCost() < lowestFCostNode.getFCost()) {
                lowestFCostNode = openList.get(i);
            }
        }
        return lowestFCostNode;
    }

    private List<Noeud> getVoisins(Noeud noeud) {
        List<Noeud> neighbors = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Directions : Haut, Droite, Bas, Gauche

        for (int[] dir : directions) {
            int checkX = noeud.getX() + dir[0];
            int checkY = noeud.getY() + dir[1];

            if (checkX >= 0 && checkX < grille[0].length && checkY >= 0 && checkY < grille.length) {
                neighbors.add(nodes[checkY][checkX]); // Ajoute les voisins valides à la liste des voisins
            }
        }
        return neighbors;
    }
    private int getDistance(Noeud noeudA, Noeud noeudB) {
        return Math.abs(noeudA.getX() - noeudB.getX()) + Math.abs(noeudA.getY() - noeudB.getY());
    }
    private void trace_chemin() {
        Noeud currentNode = endNode;
        while (currentNode != startNode) {
            if (grille[currentNode.getY()][currentNode.getX()] != Types.MUR && grille[currentNode.getY()][currentNode.getX()] != Types.DEPART && grille[currentNode.getY()][currentNode.getX()] != Types.ARRIVEE) {
                grille[currentNode.getY()][currentNode.getX()] = Types.OUVERT;
            }
            currentNode = currentNode.getParent();
        }

        //labyrinthe.set_grille(grille);
    }
}