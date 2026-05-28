package Estructuras.MyCircularList;

import Estructuras.Interfaces.Iterador;

public class IteradorLC<T extends Comparable <T>> implements Iterador<T> {
    //Variables:
    private ElementoLC<T> actual;
    private ElementoLC<T> primero;
    private boolean primeraVuelta;

    //Constructor:
    public IteradorLC(ElementoLC<T> a){
        this.actual = a; //El actual es el primer elemento al iniciar la iteración.
        this.primero = a;
        this.primeraVuelta = true;
    }

    //Métodos:
    @Override
    public boolean hasNext(){
        if (actual == null){
            return false; //Si no hay actual es que la lista está vacía, por lo que no hay siguiente.
        }
        if(primeraVuelta == true){
         return true; //Si nos encontramos en la primera vuelta de la lista devuelve true.
        }
        return actual != primero; // Una vez que el iterador haya completado la primera vuelta entera, al volver al inicio para.
    }

    @Override
    public T next(){
        if (hasNext() == false){
            return null;
        }
        T dato = actual.getDato();
        actual = actual.getSiguiente();

        if(actual == primero) {
            primeraVuelta = false; //Una vez pasado el primer elemento marcamos la primera vuelta como false para poder cumplir el return de hasNext.
        }
        return dato;
    }
}
