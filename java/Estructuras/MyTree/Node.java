package Estructuras.MyTree;

import Estructuras.MyLinkedList.ListaSE;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {
    //Atributos:
    private T dato;
    private Node<T> padre;
    private ListaSE<Node<T>> hijos;

    //Constructor:
    public Node(T dato) {
        this.dato = dato;
        this.padre = null;
        this.hijos = new ListaSE<>();
    }

    //Getters:
    public T getDato() {
        return dato;
    }

    public Node<T> getPadre() {
        return padre;
    }

    public ListaSE<Node<T>> getHijos() {
        return hijos;
    }

    //Setters:
    public void setDato(T dato) {
        this.dato = dato;
    }

    public void setPadre(Node<T> padre) {
        this.padre = padre;
    }

    //Otros métodos:
    @Override
    public int compareTo(Node<T> otro) {
        return this.dato.compareTo(otro.dato);
    }
}
