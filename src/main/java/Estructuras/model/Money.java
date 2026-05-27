package model;

public class Money {
    private Boolean isInInventory;
    private Cell position;

    public Money(){
        if(isInInventory == false){
            position = new Cell<>(1,1);
        }
        else{
            position = null;
        }
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

    public void setIsInInventory(Boolean isInInventory) {
        this.isInInventory = isInInventory;
    }
}
