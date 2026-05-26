package Estructuras.Interfaces;

import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.Coordenada;

public interface MatrizInterfaz<T> {
    T get(int fila, int columna);
    void set(int fila, int columna, T valor);
    int getFilas();
    int getColumnas();
    boolean posicionValida(int fila, int columna);
    ListaSE<Coordenada> getVecinos(int fila, int columna);
}
