package model;

public class Cell<T> { //casilla para los no bilingües
    private boolean occupied; //para saber si está libre o no para cuando el personaje se quiera mover
    private T objeto; //la celda tiene que saber lo que tiene dentro

    public Cell() {
        this.occupied = false;
        this.objeto = null;
    }

    public void addObjeto(T objeto) {
        this.objeto = objeto;
        this.occupied = true;
    }

    public void removeObjeto(T objeto) {
        this.objeto = objeto;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public T getObjeto() {
        return objeto;
    }

    @Override
    public String toString() {
        return objeto.toString();
    }
}