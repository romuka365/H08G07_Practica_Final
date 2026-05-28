package model;

public class Shield implements Comparable{
    private String placement; //Dónde se lleva en el cuerpo?
    private int defBuff; //Cuánto aumenta el daño?
    private Boolean equipped; //si el personaje lo tiene equipado
    private Boolean isInInventory;

    public Shield() //arma estándar  ?
    {
        placement = "hand";
        defBuff = 1;
    }

    public Shield(String placement, int defBuff) {
        this.placement = placement;
        this.defBuff = defBuff;
    }


    public String getPlacement() {
        return placement;
    }

    public int getDefBuff() {
        return defBuff;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
