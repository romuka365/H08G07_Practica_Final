package Estructuras.MyTree;

import Estructuras.MyLinkedList.ListaSE;

public class NodoGeneral<T extends Comparable<T>> implements Comparable<NodoGeneral<T>> {
    //Atributos:
    private T dato;
    private NodoGeneral<T> padre;
    private ListaSE<NodoGeneral<T>> hijos;

    //Constructor:
    public NodoGeneral(T dato) {
        this.dato = dato;
        this.padre = null;
        this.hijos = new ListaSE<>();
    }

    //Getters:
    public T getDato() {
        return dato;
    }

    public NodoGeneral<T> getPadre() {
        return padre;
    }

    public ListaSE<NodoGeneral<T>> getHijos() {
        return hijos;
    }

    //Setters:
    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setPadre(NodoGeneral<T> padre) {
        this.padre = padre;
    }

    //Otros métodos:
    @Override
    public int compareTo(NodoGeneral<T> otro) {
        return this.dato.compareTo(otro.dato);
    }
}
