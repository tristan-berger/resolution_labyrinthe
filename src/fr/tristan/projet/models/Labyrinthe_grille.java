package fr.tristan.projet.models;

import fr.tristan.projet.ressources.Types;

import java.util.HashMap;

public class Labyrinthe_grille
{
    //Propriété de la grille
    private final int largeur_bordure = 520;
    private final int hauteur_bordure = 520;
    private final int padding_grille = 5;
    private final int padding_case = 2;


    /*
     Palette de couleur disponible :
        -gris
        -blanc
        -blanc_fonce
        -vert
        -noir
        -rouge
        -bleu
     */


    private HashMap<String, String> conversion;


    /**Grille de base*/
    private Types[][] grille = new Types[][]{
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE}, {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},

        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE},
        {Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE,    Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE, Types.VIDE}
    };



    public Labyrinthe_grille()
    {
        conversion = new HashMap<>();
        conversion.put("mur", "noir");
        conversion.put("vide", "blanc");
        conversion.put("depart", "vert");
        conversion.put("arrivee", "rouge");
        conversion.put("chemin", "bleu");
        conversion.put("parcouru", "blanc_fonce");
    }



    private void remplacer_tout(Types ancien, Types nouveau)
    {
        int hauteur = grille.length;
        int largeur = grille[0].length;

        for(int j = 0; j < hauteur; j++)
        {
            for(int i = 0; i < largeur;i++)
            {
                if(grille[j][i] == ancien) grille[j][i] = nouveau;
            }
        }
    }

    public boolean verification_grille()
    {
        boolean presence_depart = false;
        boolean presence_arrivee = false;

        int hauteur = grille.length;
        int largeur = grille[0].length;

        for(int j = 0; j < hauteur; j++)
        {
            for(int i = 0; i < largeur;i++)
            {
                if(grille[j][i] == Types.DEPART) presence_depart = true;
                if(grille[j][i] == Types.ARRIVEE) presence_arrivee = true;
            }
        }

        if(presence_arrivee && presence_depart) return true;
        else return false;
    }

    /**Nettoi la grille du chemin et des cases explorés.*/
    public void nettoyer_algo()
    {
        remplacer_tout(Types.EXPLORER, Types.VIDE);
        remplacer_tout(Types.OUVERT, Types.VIDE);
    }

    public int compter_type(Types type)
    {
        int compteur = 0;

        int hauteur = grille.length;
        int largeur = grille[0].length;

        for(int j = 0; j < hauteur; j++)
        {
            for(int i = 0; i < largeur;i++)
            {
                if(grille[j][i] == type) compteur++;
            }
        }


        return compteur;
    }

    /******************** Setter ********************/
    public void setGrille(Types[][] grille)
    {
        this.grille = grille;
    }
    public void setCase(int x, int y, Types type_actuel)
    {

        //Calcul de la position
        if(x < padding_grille || y < padding_grille) return;
        if(x > largeur_bordure - padding_grille || y > hauteur_bordure - padding_grille) return;

        int largeur_case = (largeur_bordure - 2*padding_grille) / grille[0].length;
        int hauteur_case = (hauteur_bordure - 2*padding_grille) / grille.length;

        int x_case = (x - padding_grille) / largeur_case;
        int y_case = (y - padding_grille) / hauteur_case;

        int position_x_case = (x - padding_grille) % largeur_case;
        int position_y_case = (y - padding_grille) % hauteur_case;

        if(position_x_case < padding_case || position_y_case < padding_case) return;
        if(position_x_case > largeur_case - padding_case || position_y_case > hauteur_case - padding_case) return;


        //Verification des cases
        if(type_actuel == Types.DEPART) remplacer_tout(Types.DEPART, Types.VIDE);
        if(type_actuel == Types.ARRIVEE) remplacer_tout(Types.ARRIVEE, Types.VIDE);



        //Set la case en fonction du type actuel
        grille[y_case][x_case] = type_actuel;
        //System.out.println("x : " + x_case + "\ny : " + y_case);


    }


    /******************** Getter ********************/
    public int getLargeur_bordure() {
        return largeur_bordure;
    }
    public int getHauteur_bordure() {
        return hauteur_bordure;
    }
    public int getPadding_grille() {
        return padding_grille;
    }
    public int getPadding_case() {
        return padding_case;
    }
    public Types[][] getGrille() {
        return grille;
    }



}
