package model;

import Estructuras.MyMatrix.MyMatrix;

public class Room {
    private MyMatrix<Cell> celdas;
    private int filas;
    private int columnas;

    public Room(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.celdas = new MyMatrix<>(filas, columnas);
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                celdas.set(f, c, new Cell(f, c));
            }
        }
    }

    public Cell getCell(int fila, int columna) {
        return celdas.get(fila, columna);
    }

    public MyMatrix<Cell> getMatrix() {
        return celdas;
    }

    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }
}
