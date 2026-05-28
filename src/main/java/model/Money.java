package model;

public class Money implements Item {
    private Boolean isInInventory;
    private Cell position;

    public Money(){
        isInInventory = false;
        position = new Cell(1,1);
    }

    public Money(Boolean isInInventory, Cell position){
        this.isInInventory = isInInventory;
        this.position = position;
    }

    public void addToInventory(Player player) {
        if (isInInventory == false) {
            player.addToInventory(this);
            isInInventory = true;
        }
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
