package model;

public class Weapon implements Item {
    private String placement; //Dónde se lleva en el cuerpo?
    private int atkBuff; //Cuánto aumenta el daño?
    private Boolean equipped; //si el personaje lo tiene equipado
    private Boolean isInInventory;
    private Cell position;

    public Weapon() //arma estándar  ?
    {
        placement = "hand";
        atkBuff = 1;
        equipped = false;
        isInInventory = false;
        if (isInInventory == false) {
            position = new Cell(1,1);
        }
        else {
            position = null;
        }
    }

    public Weapon(String placement, int atkBuff, Boolean equipped, Boolean isInInventory, Cell position) {
        this.placement = placement;
        this.atkBuff = atkBuff;
        this.equipped = equipped;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (isInInventory == false) {
            player.addToInventory(this);
            isInInventory = true;
        }
    }

    public String getPlacement() {
        return placement;
    }

    public int getAtkBuff() {
        return atkBuff;
    }

    public Boolean getEquipped() {
        return equipped;
    }

    public void setEquipped(Boolean equipped) {
        this.equipped = equipped;
    }

    public Boolean getIsInInventory() {
        return isInInventory;
    }

    @Override
    public void setIsInInventory(boolean isInInventory) {
        this.isInInventory = isInInventory;
    }

    public Cell getPosition() {
        return position;
    }

    @Override
    public int compareTo(Item o) {
        return getClass().getName().compareTo(o.getClass().getName());
    }
}
