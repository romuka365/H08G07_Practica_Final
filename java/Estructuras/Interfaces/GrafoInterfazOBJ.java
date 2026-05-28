package Estructuras.Interfaces;

import Estructuras.MyGraphOBJ.VerticeOBJ;

public interface GrafoInterfazOBJ<T> {
    int getNumVertices();
    void addVertice(int idOBJ, T dato);
    void addArista(int idOrigen, T tipoOrigen, int idDestino, T tipoDestino, String predicado);
    VerticeOBJ<T> getVertice(int id);
    int buscarIdPorDato(T dato);
    T buscarDatoPorId(int idOBJ);
}
