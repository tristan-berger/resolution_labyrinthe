package fr.tristan.projet.models.algos;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.ressources.Noeud;
import fr.tristan.projet.ressources.Types;

import java.util.*;

public class Profondeur extends Algorithme {
    private Stack<Noeud> pile;
    private boolean[][] visite;
    private Types[][] grille;
    private Noeud[][] parents;

    public Profondeur(Labyrinthe labyrinthe) {
        super(labyrinthe);
        this.pile = new Stack<>();
    }

    @Override
    public void resoudre() {
        grille = labyrinthe.getLabyrinthe_grille().getGrille();
        visite = new boolean[grille.length][grille[0].length];
        parents = new Noeud[grille.length][grille[0].length];

        Noeud depart = null;
        Noeud arrivee = null;

        // Trouver le départ et l'arrivée
        for (int y = 0; y < grille.length; y++) {
            for (int x = 0; x < grille[y].length; x++) {
                if (grille[y][x] == Types.DEPART) {
                    depart = new Noeud(x, y, Types.DEPART);
                } else if (grille[y][x] == Types.ARRIVEE) {
                    arrivee = new Noeud(x, y, Types.ARRIVEE);
                }
            }
        }

        if (depart == null || arrivee == null) {
            System.out.println("Départ ou arrivée non trouvé");
            return;
        }

        pile.push(depart);
        visite[depart.getY()][depart.getX()] = true;

        boolean cheminTrouve = false;

        while (!pile.isEmpty() && !cheminTrouve) {
            Noeud courant = pile.pop();

            if (courant.getX() == arrivee.getX() && courant.getY() == arrivee.getY()) {
                cheminTrouve = true;
                break;
            }

            if (grille[courant.getY()][courant.getX()] != Types.DEPART &&
                    grille[courant.getY()][courant.getX()] != Types.ARRIVEE) {
                grille[courant.getY()][courant.getX()] = Types.EXPLORER;
            }

            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Gauche, Droite, Haut, Bas
            for (int[] dir : directions) {
                int newX = courant.getX() + dir[0];
                int newY = courant.getY() + dir[1];

                if (newX >= 0 && newX < grille[0].length && newY >= 0 && newY < grille.length &&
                        !visite[newY][newX] && grille[newY][newX] != Types.MUR) {
                    Noeud voisin = new Noeud(newX, newY, grille[newY][newX]);
                    pile.push(voisin);
                    visite[newY][newX] = true;
                    parents[newY][newX] = courant;
                }
            }
        }

        if (cheminTrouve) {
            reconstruireChemin(arrivee);
        } else {
            System.out.println("Aucun chemin trouvé");
        }

        labyrinthe.set_grille(grille);
    }

    private void reconstruireChemin(Noeud arrivee) {
        Noeud courant = parents[arrivee.getY()][arrivee.getX()];
        while (courant != null && grille[courant.getY()][courant.getX()] != Types.DEPART) {
            if (grille[courant.getY()][courant.getX()] != Types.ARRIVEE) {
                grille[courant.getY()][courant.getX()] = Types.OUVERT;
            }
            courant = parents[courant.getY()][courant.getX()];
        }
    }
}