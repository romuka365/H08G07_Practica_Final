package model;

public class Weapon implements  Comparable {
    private String placement; //Dónde se lleva en el cuerpo?
    private int atkBuff; //Cuánto aumenta el daño?

    public Weapon() //arma estándar  ?
    {
        placement = "hand";
        atkBuff = 1;

    }

    public Weapon(String placement, int atkBuff) {
        this.placement = placement;
        this.atkBuff = atkBuff;
    }


    public String getPlacement() {
        return placement;
    }

    public int getAtkBuff() {
        return atkBuff;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }



}
