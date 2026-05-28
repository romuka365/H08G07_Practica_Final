package model;

public interface Item extends Comparable<Item> {
    void setIsInInventory(boolean isInInventory);
    Boolean getIsInInventory();
}
