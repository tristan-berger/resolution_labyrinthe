package fr.tristan.projet.vues;

import fr.tristan.projet.controleurs.EcouteurBoutons;
import fr.tristan.projet.ressources.PaletteCouleur;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class VueBoutons extends JPanel implements Observer
{
    private JLabel texte;
    private JButton mur, depart, arrivee, vide, demarrer;
    private JComboBox<String> liste;
    private String [] options;


    private EcouteurBoutons ecouteurBoutons;


    public VueBoutons(EcouteurBoutons ecouteurBoutons)
    {
        super();

        this.ecouteurBoutons = ecouteurBoutons;

        texte = new JLabel("Composez votre labyrinthe :");
        mur = new JButton("Mur");
        depart = new JButton("Depart");
        arrivee = new JButton("Arrivee");
        vide = new JButton("Vide");
        demarrer = new JButton("Demarrer");

        options = new String[] {"Largeur", "Profondeur", "Greedy Best First", "Dijkstra","A*"};
        liste = new JComboBox<>(options);


        mur.setBackground(PaletteCouleur.NOIR);
        depart.setBackground(PaletteCouleur.VERT);
        arrivee.setBackground(PaletteCouleur.ROUGE);
        vide.setBackground(PaletteCouleur.BLANC);

        mur.setForeground(PaletteCouleur.BLANC);
        depart.setForeground(PaletteCouleur.BLANC);
        arrivee.setForeground(PaletteCouleur.BLANC);


        mur.addActionListener(ecouteurBoutons);
        depart.addActionListener(ecouteurBoutons);
        arrivee.addActionListener(ecouteurBoutons);
        vide.addActionListener(ecouteurBoutons);
        liste.addActionListener(ecouteurBoutons);
        demarrer.addActionListener(ecouteurBoutons);


        //add(texte);
        add(mur);
        add(depart);
        add(arrivee);
        add(vide);
        add(liste);
        add(demarrer);



    }


    @Override
    public void update(Observable o, Object arg) {

    }
}
