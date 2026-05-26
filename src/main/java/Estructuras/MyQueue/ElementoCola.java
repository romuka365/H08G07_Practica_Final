package Estructuras.MyQueue;

public class ElementoCola <T>{
    //Variables:
    private T dato;
    private ElementoCola<T> siguiente;

    //Constructores:
    public ElementoCola(T d){
        this.dato = d;
        this.siguiente = null;
    }

    public ElementoCola(T d, ElementoCola<T> s){
        this.dato = d;
        this.siguiente = s;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ElementoCola<T> getSiguiente(){
        return siguiente;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setSiguiente(ElementoCola<T> newSiguiente){
        this.siguiente = newSiguiente;
    }
}
