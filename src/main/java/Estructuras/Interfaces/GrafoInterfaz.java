package Estructuras.Interfaces;

import Estructuras.MyGraph.Vertice;

public interface GrafoInterfaz {
    int getNumVertices();
    int getMaxId();
    void addVertice(String tipoVertice, String nombreVertice);
    void addArista(String tipoOrigen, String nombreOrigen, String tipoDestino, String nombreDestino, String predicado);
    Vertice getVertice(int id);
    int buscarIdVertice(String tipoVertice, String nombreVertice);
}
