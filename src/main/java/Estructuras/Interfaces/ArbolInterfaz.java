package Estructuras.Interfaces;

import Estructuras.MyLinkedList.ListaSE;

public interface ArbolInterfaz<T extends Comparable<T>> {
    void add(T dato);
    boolean isEmpty();
    int getAltura();
    int getGrado();
    ListaSE<T> getListaDatosNivel(int nivel);
    boolean isArbolHomogeneo();
    boolean isArbolCompleto();
    boolean isArbolCasiCompleto();
    ListaSE<T> getCamino(T dato);
    ListaSE<T> getListaOrdenCentral();
    ListaSE<T> getListaPreOrden();
    ListaSE<T> getListaPostOrden();
}
