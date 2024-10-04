package fr.tristan.projet.controleurs;

import fr.tristan.projet.Labyrinthe;
import fr.tristan.projet.models.algos.*;
import fr.tristan.projet.ressources.Algos;
import fr.tristan.projet.ressources.Types;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Controleur des boutons*/
public class EcouteurBoutons implements ActionListener
{

    /**Model*/
    private Labyrinthe labyrinthe;

    private Algorithme algo;

    /**Se construit à partir du modele*/
    public EcouteurBoutons(Labyrinthe labyrinthe)
    {
        this.labyrinthe = labyrinthe;
    }




    /**Se declenche à chaque évenement.*/
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object o = e.getSource();
        if(o instanceof JButton)
        {
            JButton bouton = (JButton) o;
            if(bouton.getText().equals("Mur")) labyrinthe.setType_actuel(Types.MUR);
            if(bouton.getText().equals("Vide")) labyrinthe.setType_actuel(Types.VIDE);
            if(bouton.getText().equals("Depart")) labyrinthe.setType_actuel(Types.DEPART);
            if(bouton.getText().equals("Arrivee")) labyrinthe.setType_actuel(Types.ARRIVEE);
            if(bouton.getText().equals("Demarrer"))
            {
                if(!labyrinthe.verification_grille()) return;
                labyrinthe.getLabyrinthe_grille().nettoyer_algo();
                Algos algo_actuel = labyrinthe.getAlgo_actuel();
                switch (algo_actuel)
                {
                    case LARGEUR:
                        algo = new Largeur(labyrinthe);
                        break;
                    case PROFONDEUR:
                        algo = new Profondeur(labyrinthe);
                        break;
                    case GREEDY_BEST_FIRST:
                        algo = new GreedyBestFirst(labyrinthe);
                        break;
                    case DIJKSTRA:
                        algo = new Dijkstra(labyrinthe);
                        break;
                    case ASTAR:
                        algo = new Astar(labyrinthe);
                        break;
                    default:
                        algo = new Astar(labyrinthe);
                        break;
                }

                if(algo == null) return;
                algo.resoudre();

            }



        }
        else if(o instanceof JComboBox<?>) {
            JComboBox<String> liste = (JComboBox<String>) o;
            String actuel = (String) liste.getSelectedItem();

            switch (actuel)
            {
                case "Largeur":
                    labyrinthe.setAlgo_actuel(Algos.LARGEUR);
                    break;
                case "Profondeur":
                    labyrinthe.setAlgo_actuel(Algos.PROFONDEUR);
                    break;
                case "Greedy Best First":
                    labyrinthe.setAlgo_actuel(Algos.GREEDY_BEST_FIRST);
                    break;
                case "Dijkstra":
                    labyrinthe.setAlgo_actuel(Algos.DIJKSTRA);
                    break;
                case "A*":
                    labyrinthe.setAlgo_actuel(Algos.ASTAR);
                    break;
                default:
                    labyrinthe.setAlgo_actuel(Algos.ASTAR);
            }
        }
    }
}
