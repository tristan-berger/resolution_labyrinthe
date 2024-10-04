package fr.tristan.projet.ressources;

import java.awt.*;
import java.util.HashMap;

public class PaletteCouleur
{
    public static final Color ERREUR = new Color(153, 96, 0);
    public static final Color GRIS = new Color(160, 179, 184);
    public static final Color BLANC = new Color(203, 225, 231);
    public static final Color BLANC_FONCE = new Color(138, 197, 229, 182);
    public static final Color VERT = new Color(14, 234, 104);
    public static final Color ROUGE = new Color(204, 15, 84);
    public static final Color BLEU = new Color(47, 69, 187);
    public static final Color NOIR = new Color(64, 74, 90);

    public static final HashMap<Types, Color> getPalette()
    {
        HashMap<Types, Color> couleurs = new HashMap<>();

        couleurs.put(Types.VIDE, BLANC);
        couleurs.put(Types.EXPLORER, BLANC_FONCE);
        couleurs.put(Types.DEPART, VERT);
        couleurs.put(Types.MUR, NOIR);
        couleurs.put(Types.ARRIVEE, ROUGE);
        couleurs.put(Types.OUVERT, BLEU);

        return couleurs;
    }
}
