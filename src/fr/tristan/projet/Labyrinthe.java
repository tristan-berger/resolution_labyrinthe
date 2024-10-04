package fr.tristan.projet;

import fr.tristan.projet.ressources.Algos;
import fr.tristan.projet.models.Labyrinthe_grille;
import fr.tristan.projet.ressources.Types;

import java.util.Observable;


/**Modele*/
public class Labyrinthe extends Observable
{

    private Labyrinthe_grille labyrinthe_grille;

    /**Type actuelement selectionné par l'utilisateur.*/
    private Types type_actuel;
    /**Algo actuelement selectionné par l'utilisateur.*/
    private Algos algo_actuel;

    /**Longueur du chemin*/
    private int longueur_chemin;

    /**Nombre de cases explorée*/
    private  int nb_case_exploree;




    /**Construit un labyrinthe sans rien.*/
    public Labyrinthe()
    {
        labyrinthe_grille = new Labyrinthe_grille();

        this.type_actuel = Types.VIDE;
        this.algo_actuel = Algos.LARGEUR;
        longueur_chemin = 0;
        nb_case_exploree = 0;
    }


    /******************** Methode ********************/
    /**Modifie une case en fonction des coordonnées entrées en paramètre et du type actuel.*/
    public void setCase(int x, int y)
    {
        labyrinthe_grille.setCase(x, y, type_actuel);
        this.notif();
    }


    /**Modifie le type actuel.*/
    public void setType_actuel(Types type)
    {
        this.type_actuel = type;
    }

    /**Modifie l'algo actuel*/
    public void setAlgo_actuel(Algos algo)
    {
        this.algo_actuel = algo;
    }

    public void set_grille(Types [][] grille)
    {
        labyrinthe_grille.setGrille(grille);
        longueur_chemin = labyrinthe_grille.compter_type(Types.OUVERT);
        nb_case_exploree = labyrinthe_grille.compter_type(Types.EXPLORER);
        notif();
    }



    public boolean verification_grille()
    {
        return labyrinthe_grille.verification_grille();
    }





    /******************** Getter ********************/
    public Labyrinthe_grille getLabyrinthe_grille() {
        return labyrinthe_grille;
    }

    public Algos getAlgo_actuel() {
        return algo_actuel;
    }
    /**Notifi les observer*/
    public void notif() {
        setChanged();
        notifyObservers();
    }
    public int getLongueur_chemin() {
        return longueur_chemin;
    }
    public int getNb_case_exploree() {
        return nb_case_exploree;
    }
}
