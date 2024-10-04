package fr.tristan.projet;

import fr.tristan.projet.controleurs.EcouteurBoutons;
import fr.tristan.projet.controleurs.EcouteurGrille;

public class Main
{
    public static void main(String [] args)
    {
        //Model
        Labyrinthe labyrinthe = new Labyrinthe();


        //Ecouteurs
        EcouteurGrille ecouteurGrille = new EcouteurGrille(labyrinthe);
        EcouteurBoutons ecouteurBoutons = new EcouteurBoutons(labyrinthe);

        //Vues
        VueFenetre vueFenetre = new VueFenetre("Résolution Labyrinthe", labyrinthe, ecouteurGrille, ecouteurBoutons);




        labyrinthe.addObserver(vueFenetre);
        labyrinthe.addObserver(vueFenetre.getVueBoutons());
        labyrinthe.addObserver(vueFenetre.getVueAffichage());
        labyrinthe.addObserver(vueFenetre.getVueGrille());




    }
}
