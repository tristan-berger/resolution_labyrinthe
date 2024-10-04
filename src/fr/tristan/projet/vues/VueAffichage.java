package fr.tristan.projet.vues;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.ressources.PaletteCouleur;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**Affiche des informations sur les algorithmes.*/
public class VueAffichage extends JPanel implements Observer
{

    private JLabel texte, longueur_chemin, longueur_explorer;

    private final int taille_texte = 20;
    private final String police_texte = "Arial";
    private final int font_texte = Font.BOLD;
    private Border bordur_texte = new CompoundBorder(new LineBorder(PaletteCouleur.GRIS, 3), new EmptyBorder(2, 5, 2, 5));


    public VueAffichage() {
        super();

        texte = new JLabel("Chemin trouvé :");


        longueur_chemin = new JLabel("0");
        longueur_chemin.setBackground(PaletteCouleur.BLEU);
        longueur_chemin.setForeground(PaletteCouleur.BLANC);
        longueur_chemin.setOpaque(true);
        longueur_chemin.setBorder(bordur_texte);
        longueur_chemin.setFont(new Font(police_texte, font_texte, taille_texte));



        longueur_explorer = new JLabel("0");
        longueur_explorer.setBackground(PaletteCouleur.BLANC_FONCE);
        longueur_explorer.setForeground(PaletteCouleur.BLANC);
        longueur_explorer.setOpaque(true);
        longueur_explorer.setBorder(bordur_texte);
        longueur_explorer.setFont(new Font(police_texte, font_texte, taille_texte));





        add(texte);
        add(longueur_chemin);
        add(longueur_explorer);
    }


    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Labyrinthe)
        {
            Labyrinthe labyrinthe = (Labyrinthe) o;
            longueur_chemin.setText("" + labyrinthe.getLongueur_chemin());
            longueur_explorer.setText("" + labyrinthe.getNb_case_exploree());

            repaint();
            revalidate();
        }
    }
}
