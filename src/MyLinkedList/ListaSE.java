package MyLinkedList;

import Interfaces.Iterador;
import Interfaces.ListaInterfaz;

public class ListaSE <T extends Comparable<T>> implements ListaInterfaz<T> {
    //Variables:
    protected ElementoSE<T> primero;
    protected int tamaño;

    //Constructores:
    public ListaSE(){
        this.primero = null;
        this.tamaño = 0;
    }

    public ElementoSE<T> getPrimero() {
        return primero;
    }


    //Métodos:
    @Override
    public void addInicio(T dato){
        ElementoSE<T> nuevo = new ElementoSE<>(dato);
        nuevo.setSiguiente(primero); //Conviertes el primer elemento en el siguiente.
        primero = nuevo; //El elemento que añadimos se convierte en el primero.
        tamaño ++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    //Add es añadir al final.
    public void add(T dato){
        ElementoSE<T> nuevo = new ElementoSE<>(dato);
        if(primero == null){
            primero = nuevo; // Si no hay elementos en la lista, el final es el mismo que el principio.
        }
        else{
            ElementoSE<T> puntero = primero;
            while (puntero.getSiguiente() != null){
                puntero = puntero.getSiguiente();
            }
            puntero.setSiguiente(nuevo); //Añade el elemento al final.
        }
        tamaño ++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    public T get(T dato){
        ElementoSE<T> puntero = primero;
        while (puntero != null){ //Mientras no llegue al final sigue buscando.
            if(puntero.getDato().compareTo(dato) == 0){
                return puntero.getDato(); //Si coincide devuelve el dato
            }
            puntero = puntero.getSiguiente();
        }
        return null;
    }

    @Override
    public T del(T dato){
        ElementoSE<T> puntero = primero;
        ElementoSE<T> anterior = null;
        while (puntero != null){ //Mientras no llegue al final sigue buscando.
            if(puntero.getDato().compareTo(dato) == 0){
                if (anterior == null) {
                    primero = puntero.getSiguiente(); //Si el elemento es el primero.
                }
                else{
                    anterior.setSiguiente(puntero.getSiguiente()); //Si el elemento es el segundo en adelante.
                }
                tamaño--; //Disminuye el tamaño de la lista en 1.
                return puntero.getDato();
            }
            anterior = puntero;
            puntero = puntero.getSiguiente();
        }
        return  null;
    }

    @Override
    public void clear(){ // Vaciamos la lista entera.
        primero = null;
        tamaño = 0;
    }

    @Override
    public boolean isEmpty(){
        return primero == null; //Si no hay primer elemento True, de lo contrario False.
    }

    @Override
    public int getSize(){
        return tamaño; //Devuelve el tamaño de la lista.
    }

    @Override
    public Iterador<T> getIterador(){
        return new IteradorLSE<T>(this.primero);
    }

    @Override
    public String toString() {
        ElementoSE<T> actual = primero;
        String s = "[";

        while (actual != null) {
            s += actual.getDato();
            if (actual.getSiguiente() != null) s += ", ";
            actual = actual.getSiguiente();
        }

        return s + "]";
    }

}

