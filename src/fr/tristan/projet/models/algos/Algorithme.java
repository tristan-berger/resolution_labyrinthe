package fr.tristan.projet.models.algos;

import fr.tristan.projet.Labyrinthe;

public abstract class Algorithme
{
    protected Labyrinthe labyrinthe;

    public Algorithme(Labyrinthe labyrinthe)
    {
        this.labyrinthe = labyrinthe;
    }


    public abstract void resoudre();
}
