package model;

public class Potion {
    private int heal; //Cuánto aumenta el daño?
    private Boolean used; //si el personaje lo tiene equipado
    public Potion() //arma estándar  ?
    {
        heal = 1;
        used = false;
    }

    public Potion(int heal, Boolean used) {
        this.heal = heal;
        this.used = used;
    }


    public int getHeal() {
        return heal;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean equipped) {
        this.used = used;
    }

}
