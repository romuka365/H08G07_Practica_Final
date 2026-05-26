package MyStack;

public class ElementoPila <T> {
    //Variables:
    private T dato;
    private ElementoPila<T> siguiente;

    //Constructores:
    public ElementoPila(T d){
        this.dato = d;
        this.siguiente = null;
    }

    public ElementoPila(T d, ElementoPila<T> s){
        this.dato = d;
        this.siguiente = s;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ElementoPila<T> getSiguiente(){
        return siguiente;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setSiguiente(ElementoPila<T> newSiguiente){
        this.siguiente = newSiguiente;
    }
}
