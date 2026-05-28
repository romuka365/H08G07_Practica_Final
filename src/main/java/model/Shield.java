package model;

public class Shield implements Item {
    private String placement; //Dónde se lleva en el cuerpo?
    private int defBuff; //Cuánto aumenta el daño?
    private Boolean equipped; //si el personaje lo tiene equipado
    private Boolean isInInventory;
    private Cell position;

    public Shield() //arma estándar  ?
    {
        placement = "hand";
        defBuff = 1;
        equipped = false;
        isInInventory = false;
        if (isInInventory == false) {
            position = new Cell(1,1);
        }
        else {
            position = null;
        }
    }

    public Shield(String placement, int defBuff, Boolean equipped, Boolean isInInventory, Cell position) {
        this.placement = placement;
        this.defBuff = defBuff;
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

    public int getDefBuff() {
        return defBuff;
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
