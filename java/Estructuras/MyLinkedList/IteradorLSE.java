package Estructuras.MyLinkedList;

import Estructuras.Interfaces.Iterador;

public class IteradorLSE<T> implements Iterador<T> {
    //Variables:
    private ElementoSE<T> actual;

    //Constructor:
    public IteradorLSE(ElementoSE<T> a){
        this.actual = a;
    }

    //Métodos:
    @Override
    public boolean hasNext(){
        return actual != null; //Si actual es null False, de lo contrario True.
    }

    @Override
    public T next(){
        if (hasNext() == false){
            return null;
        }
        T dato = actual.getDato();
        actual = actual.getSiguiente();
        return dato;
    }
}
