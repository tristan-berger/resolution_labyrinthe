package fr.tristan.projet.models.algos;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.ressources.Noeud;
import fr.tristan.projet.ressources.Types;

import java.util.*;


public class GreedyBestFirst extends Algorithme {
    private PriorityQueue<Noeud> openList;
    private Set<Noeud> closedList;
    private Noeud[][] noeuds;
    private Noeud depart;
    private Noeud arrivee;

    public GreedyBestFirst(Labyrinthe labyrinthe) {
        super(labyrinthe);
        this.openList = new PriorityQueue<>((a, b) -> Integer.compare(a.getFCost(), b.getFCost()));
        this.closedList = new HashSet<>();
    }

    @Override
    public void resoudre() {
        Types[][] grille = labyrinthe.getLabyrinthe_grille().getGrille();
        initialiserNoeuds(grille);

        if (depart == null || arrivee == null) {
            System.out.println("Départ ou arrivée non trouvé");
            return;
        }

        depart.setGCout(0);
        depart.setHCout(calculerHeuristique(depart, arrivee));
        openList.add(depart);

        while (!openList.isEmpty()) {
            Noeud current = openList.poll();
            closedList.add(current);

            if (current != depart && current != arrivee) {
                grille[current.getY()][current.getX()] = Types.EXPLORER;
            }

            if (current == arrivee) {
                if (verifierCheminValide(current)) {
                    reconstruireChemin(grille);
                    labyrinthe.set_grille(grille);
                    return;
                }
            }

            for (Noeud voisin : getVoisins(current)) {
                if (closedList.contains(voisin)) continue;

                int newGCost = current.getGCost() + 1;
                if (newGCost < voisin.getGCost() || !openList.contains(voisin)) {
                    voisin.setGCout(newGCost);
                    voisin.setHCout(calculerHeuristique(voisin, arrivee));
                    voisin.setParent(current);

                    if (!openList.contains(voisin)) {
                        openList.add(voisin);
                    } else {
                        openList.remove(voisin);
                        openList.add(voisin);
                    }
                }
            }
        }

        System.out.println("Aucun chemin trouvé");
        labyrinthe.set_grille(grille);
    }

    private void initialiserNoeuds(Types[][] grille) {
        int hauteur = grille.length;
        int largeur = grille[0].length;
        noeuds = new Noeud[hauteur][largeur];

        for (int y = 0; y < hauteur; y++) {
            for (int x = 0; x < largeur; x++) {
                noeuds[y][x] = new Noeud(x, y, grille[y][x]);
                noeuds[y][x].setGCout(Integer.MAX_VALUE);
                if (grille[y][x] == Types.DEPART) depart = noeuds[y][x];
                if (grille[y][x] == Types.ARRIVEE) arrivee = noeuds[y][x];
            }
        }
    }

    private List<Noeud> getVoisins(Noeud noeud) {
        List<Noeud> voisins = new ArrayList<>();
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Droite, Bas, Gauche, Haut

        for (int[] dir : directions) {
            int newX = noeud.getX() + dir[0];
            int newY = noeud.getY() + dir[1];

            if (newX >= 0 && newX < noeuds[0].length && newY >= 0 && newY < noeuds.length) {
                Noeud voisin = noeuds[newY][newX];
                if (voisin.getType() != Types.MUR) {
                    voisins.add(voisin);
                }
            }
        }

        return voisins;
    }

    private int calculerHeuristique(Noeud a, Noeud b) {
        // Distance de Manhattan
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private void reconstruireChemin(Types[][] grille) {
        Noeud current = arrivee;

        while (current != depart) {
            int x = current.getX();
            int y = current.getY();
            if (grille[y][x] != Types.DEPART && grille[y][x] != Types.ARRIVEE) {
                grille[y][x] = Types.OUVERT;
            }
            current = current.getParent();
        }
    }

    private boolean verifierCheminValide(Noeud noeud) {
        while (noeud != null) {
            if (noeud.getType() == Types.MUR) {
                return false;
            }
            noeud = noeud.getParent();
        }
        return true;
    }
}