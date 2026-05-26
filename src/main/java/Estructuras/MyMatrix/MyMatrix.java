package Estructuras.MyMatrix;

import Estructuras.MyLinkedList.ListaSE;
import Estructuras.Interfaces.MatrizInterfaz;

public class MyMatrix<T> implements MatrizInterfaz<T> {
    //Atributos:
    private T[] datos;
    private int filas;
    private int columnas;

    //Constructores:
    public MyMatrix(int filas, int columnas) {
        //Si la fila o la columna tomara valor negativo o 0, saltaría esta excepción.
        if (filas <= 0 || columnas <= 0) {
            throw new IllegalArgumentException("Filas y columnas deben ser positivos");
        }
        this.filas = filas;
        this.columnas = columnas;
        this.datos = (T[]) new Object[filas * columnas]; //Los datos de una matriz son el conjunto de filas y columnas.
    }

    public MyMatrix(int filas, int columnas, T valorInicial) {
        this(filas, columnas); //Llama al primer constructor.
        //Inicializa todas las casillas de la matriz con un mismo valor inicial.
        for (int i = 0; i < filas * columnas; i++) {
            datos[i] = valorInicial;
        }
    }

    public T get(int fila, int columna) {
        //Si la posición no es válida salta la excepción
        if (!posicionValida(fila, columna)) {
            throw new IndexOutOfBoundsException("Posicion (" + fila + ", " + columna + ") fuera de la matriz");
        }
        //Devuelve los datos de la casilla.
        return datos[fila * columnas + columna];
    }

    public void set(int fila, int columna, T valor) {
        //Si la posición no es válida salta la excepción
        if (!posicionValida(fila, columna)) {
            throw new IndexOutOfBoundsException("Posicion (" + fila + ", " + columna + ") fuera de la matriz");
        }
        //Modifica los datos de la casilla.
        datos[fila * columnas + columna] = valor;
    }

    public boolean posicionValida(int fila, int columna) {
        //Si la fila o columna es una posición negativa o mayor que el nº de estas, devuelve False, en caso contrario True.
        return fila >= 0 && fila < filas && columna >= 0 && columna < columnas;
    }


    //Getters:
    public int getFilas() {
        return filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public ListaSE<Coordenada> getVecinos(int fila, int columna) {
        ListaSE<Coordenada> vecinos = new ListaSE<>(); //Crea la lista donde se guardan los vecinos.
        int[][] direcciones = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //Las posibles casillas adyacentes se encuentran en esas posiciones (NO cuentan las casillas diagonales).
        //Por cada una de las 4 posiciones se calcula la nueva posición.
        for (int[] d : direcciones) {
            int nf = fila + d[0]; //Se calcula la nueva fila (d[0] aplica el primer elemento del array dirección).
            int nc = columna + d[1]; //Se calcula la nueva fila (d[1] aplica el segundo elemento del array dirección).
            if (posicionValida(nf, nc)) {
                vecinos.add(new Coordenada(nf, nc)); //Si la nueva posición es válida se añade a la lista de vecinos.
            }
        }
        return vecinos; //Devuelve la lista de vecinos.
    }

    //ToString:
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                sb.append(datos[i * columnas + j]);
                if (j < columnas - 1) sb.append(" ");
            }
            if (i < filas - 1) sb.append("\n");
        }
        return sb.toString();
    }
}
