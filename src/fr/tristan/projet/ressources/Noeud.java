package fr.tristan.projet.ressources;

/**Modélise un noeud*/
public class Noeud {
    private int x, y;
    private int gCost, hCost, fCost;
    private Noeud parent;
    private Types type;

    public Noeud(int x, int y, Types type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.parent = null;
    }


    /********************* Methode *********************/
    public void calculFCout() {
        this.fCost = this.gCost + this.hCost;
    }
    @Override
    public String toString() {
        return "(" + x + ";" + y + ", "+ type +")";
    }


    /********************* Setter *********************/
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setGCout(int gCost) {
        this.gCost = gCost;
        calculFCout();
    }
    public void setHCout(int hCost) {
        this.hCost = hCost;
        calculFCout();
    }
    public void setParent(Noeud parent) {
        this.parent = parent;
    }
    public void setType(Types type) {
        this.type = type;
    }




    /********************* Getter *********************/
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getGCost() {
        return gCost;
    }
    public int getHCost() {
        return hCost;
    }
    public int getFCost() {
        return fCost;
    }
    public Noeud getParent() {
        return parent;
    }
    public Types getType() {
        return type;
    }




}