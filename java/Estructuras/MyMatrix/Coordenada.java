package Estructuras.MyMatrix;

public class Coordenada implements Comparable<Coordenada> {
    //Atributos:
    private int fila;
    private int columna;

    //Constructor:
    public Coordenada(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }

    //Getters:
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }


    //Otros métodos:
    @Override
    public int compareTo(Coordenada o) {
        if (fila != o.fila) return Integer.compare(fila, o.fila);
        return Integer.compare(columna, o.columna);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //Si el objeto corresponde con lo que haya en memoria, true.
        if (o == null || getClass() != o.getClass()) return false; //Si no hay nada en la casilla u Objeto no es
        Coordenada that = (Coordenada) o;
        return fila == that.fila && columna == that.columna; //Dos casillas son iguales si se encuentran en la misma fila y columna
    }

    //ToString:
    @Override
    public String toString() {
        return "(" + fila + ", " + columna + ")";
    }
}
