package model;

public class Boots implements Comparable {
    private String placement; //Dónde se lleva en el cuerpo?
    private int movBuff; //Cuánto aumenta el daño?
    private Boolean equipped; //si el personaje lo tiene equipado
    private Boolean isInInventory;

    public Boots() //arma estándar  ?
    {
        placement = "feet";
        movBuff = 1;
        equipped = false;
        isInInventory = false;
    }

    public Boots(String placement, int movBuff, Boolean equipped, Boolean isInInventory) {
        this.placement = placement;
        this.movBuff = movBuff;
    }


    public String getPlacement() {
        return placement;
    }

    public int getMovBuff() {
        return movBuff;
    }

    @Override
    public String toString() {
        return "Boots";
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
