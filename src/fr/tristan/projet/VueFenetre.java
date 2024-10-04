package fr.tristan.projet;

import fr.tristan.projet.controleurs.EcouteurBoutons;
import fr.tristan.projet.controleurs.EcouteurGrille;
import fr.tristan.projet.vues.VueAffichage;
import fr.tristan.projet.vues.VueBoutons;
import fr.tristan.projet.vues.VueGrille;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**Vue principale*/
public class VueFenetre extends JFrame implements Observer
{
    /**Dimension par défaut de la fenêtre.*/
    private final Dimension dimension = new Dimension(535, 638);

    private VueAffichage vueAffichage;
    private VueBoutons vueBoutons;
    private VueGrille vueGrille;





    public VueFenetre(String titre, Labyrinthe labyrinthe, EcouteurGrille ecouteurGrille, EcouteurBoutons ecouteurBoutons)
    {
        super(titre);


        vueAffichage    =   new VueAffichage();
        vueBoutons      =   new VueBoutons(ecouteurBoutons);
        vueGrille       =   new VueGrille(labyrinthe, ecouteurGrille);



        this.add(vueBoutons, BorderLayout.NORTH);
        this.add(vueGrille, BorderLayout.CENTER);
        this.add(vueAffichage, BorderLayout.SOUTH);




        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.pack();
    }




    public VueAffichage getVueAffichage() {
        return vueAffichage;
    }
    public VueBoutons getVueBoutons() {
        return vueBoutons;
    }
    public VueGrille getVueGrille() {
        return vueGrille;
    }



    @Override
    public void update(Observable o, Object arg) {

    }
}
