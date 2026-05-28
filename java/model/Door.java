package model;

public class Door {
//AÑADIR LLAVE
    private int filaDestino;
    private int columnaDestino;
    private int idHabitacionDestino;

    public Door(int filaDestino, int columnaDestino, int idHabitacionDestino) {
        this.filaDestino = filaDestino;
        this.columnaDestino = columnaDestino;
        this.idHabitacionDestino = idHabitacionDestino;
    }

    public void setFilaDestino(int filaDestino) {
        this.filaDestino = filaDestino;
    }

    public void setColumnaDestino(int columnaDestino) {
        this.columnaDestino = columnaDestino;
    }

    public void setIdHabitacionDestino(int idHabitacionDestino) {
        this.idHabitacionDestino = idHabitacionDestino;
    }

    public int getFilaDestino() {
        return filaDestino;
    }

    public int getColumnaDestino() {
        return columnaDestino;
    }

    public int getIdHabitacionDestino() {
        return idHabitacionDestino;
    }

}
