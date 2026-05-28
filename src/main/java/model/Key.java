package model;

public class Key implements Item {
    private Door door;
    private Boolean isInInventory;
    private Cell position;

    public Key() //arma estándar  ?
    {
        door = new Door();
        isInInventory = false;
        if (isInInventory == false) {
            position = new Cell(1,1);
        }
        else {
            position = null;
        }
    }

    public Key(Door door,Boolean isInInventory, Cell position) {
        this.door = door;
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (isInInventory == false) {
            player.addToInventory(this);
            isInInventory = true;
        }
    }

    public Door getDoor() {
        return door;
    }

    public Boolean getIsInInventory() {
        return isInInventory;
    }

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
