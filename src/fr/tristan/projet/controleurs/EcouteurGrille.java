package fr.tristan.projet.controleurs;

import fr.tristan.projet.Labyrinthe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**Controleur de la grille*/
public class EcouteurGrille implements MouseListener
{
    /**Model*/
    private Labyrinthe model;

    /**Construit le controleur à partir du model.*/
    public EcouteurGrille(Labyrinthe labyrinthe)
    {
        this.model = labyrinthe;
    }



    @Override
    public void mouseClicked(MouseEvent e) {


    }

    /**Se declenche quand la souris est préssée.*/
    @Override
    public void mousePressed(MouseEvent e) {
        model.setCase(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
