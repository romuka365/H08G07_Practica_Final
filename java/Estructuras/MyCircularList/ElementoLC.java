package Estructuras.MyCircularList;

public class ElementoLC <T> {
    //Variables:
    private T dato;
    private ElementoLC<T> siguiente;

    //Constructores:
    public ElementoLC(T d){
        this.dato = d;
        this.siguiente = null;
    }

    public ElementoLC(T d, ElementoLC<T> s){
        this.dato = d;
        this.siguiente = s;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ElementoLC<T> getSiguiente(){
        return siguiente;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setSiguiente(ElementoLC<T> newSiguiente){
        this.siguiente = newSiguiente;
    }
}
