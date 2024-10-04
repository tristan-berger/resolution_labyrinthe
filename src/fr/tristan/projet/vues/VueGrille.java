package fr.tristan.projet.vues;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.ressources.PaletteCouleur;
import fr.tristan.projet.ressources.Types;
import fr.tristan.projet.controleurs.EcouteurGrille;
import fr.tristan.projet.models.Labyrinthe_grille;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class VueGrille extends JPanel implements Observer {


    //Propriété de la grille
    private int largeur_bordure;
    private int hauteur_bordure;
    private int padding_grille;
    private int padding_case;

    /**Grille de base*/
    private Types [][] grille;


    /**Palette de couleurs*/
    private HashMap<Types, Color> couleurs;


    /**Ecoute la grille*/
    private EcouteurGrille ecouteurGrille;
    /**Modele de la grille*/
    private Labyrinthe_grille labyrinthe_grille;




    /**Construit la vue à partir du model et d'un ecouteur.*/
    public VueGrille(Labyrinthe labyrinthe, EcouteurGrille ecouteurGrille) {
        super();

        this.labyrinthe_grille = labyrinthe.getLabyrinthe_grille();
        this.ecouteurGrille = ecouteurGrille;

        //Init des propriétées et de la grille
        if(this.grille == null) this.grille = labyrinthe_grille.getGrille(); //Init de la grille
        if(this.largeur_bordure == 0)   this.largeur_bordure = labyrinthe_grille.getLargeur_bordure();
        if(this.hauteur_bordure == 0)   this.hauteur_bordure = labyrinthe_grille.getHauteur_bordure();
        if(this.padding_grille == 0)    this.padding_grille = labyrinthe_grille.getPadding_grille();
        if(this.padding_case == 0)      this.padding_case = labyrinthe_grille.getPadding_case();



        couleurs = PaletteCouleur.getPalette();



        this.addMouseListener(ecouteurGrille);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);




        //Bordure de la grille
        g.setColor(PaletteCouleur.GRIS);
        g.fillRect(0, 0, largeur_bordure, hauteur_bordure);




        //Ajout des cases de la grille
        int nb_case_largeur = grille[0].length;
        int nb_case_hauteur = grille.length;


        int largeur_case = (largeur_bordure - padding_grille * 2) / nb_case_largeur;
        int hauteur_case = (hauteur_bordure - padding_grille * 2) / nb_case_hauteur;


        for (int i = 0; i < grille[0].length; i++)
        {
            for (int j = 0; j < grille.length; j++)
            {
                if(couleurs.containsKey(grille[j][i]))
                {
                    g.setColor(couleurs.get(grille[j][i]));
                }
                else
                {
                    g.setColor(PaletteCouleur.ERREUR);
                }




                g.fillRect(
                        padding_grille + padding_case + i*largeur_case,
                        padding_grille + padding_case + j*hauteur_case,
                        largeur_case - padding_case * 2,
                        hauteur_case - padding_case * 2
                );
            }
        }




    }


    @Override
    public void update(Observable o, Object arg) {

        if(o instanceof Labyrinthe)
        {
            Labyrinthe labyrinthe = (Labyrinthe) o;
            grille = labyrinthe.getLabyrinthe_grille().getGrille();
            repaint();
            revalidate();


        }
    }

}
