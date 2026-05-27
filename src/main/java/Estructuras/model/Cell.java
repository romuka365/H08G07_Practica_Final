package model;

public class Cell<T> { //casilla para los no bilingües
    private int row;
    private int column;
    private boolean occupied; //tipo para saber si está libre o no para cuando el personaje se quiera mover
    private T objeto; //la celda tiene que saber lo que tiene dentro
    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
    }

}