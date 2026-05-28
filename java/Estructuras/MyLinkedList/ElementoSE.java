package Estructuras.MyLinkedList;

public class ElementoSE <T> {
    //Variables:
    private T dato;
    private ElementoSE<T> siguiente;

    //Constructores:
    public ElementoSE(T d){
        this.dato = d;
        this.siguiente = null;
    }

    public ElementoSE(T d, ElementoSE<T> s){
        this.dato = d;
        this.siguiente = s;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ElementoSE<T> getSiguiente(){
        return siguiente;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setSiguiente(ElementoSE<T> newSiguiente){
        this.siguiente = newSiguiente;
    }
}
