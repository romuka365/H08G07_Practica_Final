package model;

public class Cell {
    private int row;
    private int column;
    private boolean occupied;
    private Object objeto;

    public Cell(int row, int column) {
        this.row = row;
        this.column = column;
        this.occupied = false;
        this.objeto = null;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Object getObjeto() {
        return objeto;
    }

    public void setObjeto(Object objeto) {
        this.objeto = objeto;
    }
}