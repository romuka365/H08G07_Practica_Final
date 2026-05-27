package model;

public class Potion {
    private int heal; //Cuánto aumenta el daño?
    private Boolean used; //si el personaje lo tiene equipado
    private Boolean isInInventory;
    private Cell position;

    public Potion() //arma estándar  ?
    {
        heal = 1;
        used = false;
        isInInventory = false;
        if (isInInventory == false) {
            position = new Cell(1,1);
        }
        else {
            position = null;
        }
    }

    public Potion(int heal, Boolean used, Boolean isInInventory, Cell position) {
        this.heal = heal;
        this.used = used;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (isInInventory == false) {
            player.addToInventory(this);
            isInInventory = true;
        }
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

    public Boolean getIsInInventory() {
        return isInInventory;
    }

    public void setIsInInventory(Boolean isInInventory) {
        this.isInInventory = isInInventory;
    }

    public Cell getPosition() {
        return position;
    }


}
