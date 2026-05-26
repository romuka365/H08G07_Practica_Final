package Estructuras.MyQueue;

import Estructuras.Interfaces.ColaInterfaz;

public class Cola <T> implements ColaInterfaz<T> {

    //Variables:
    protected ElementoCola<T> frente;
    protected ElementoCola<T> ultimo;
    protected int tamaño;

    //Constructores:
    public Cola(){
        this.frente = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    //Métodos:
    @Override
    public void encolar(T dato){
        ElementoCola<T> nuevo = new ElementoCola<T>(dato);
        if(isEmpty()){
            frente = ultimo = nuevo; // Si no hay elementos en la cola, el final es el mismo que el principio.
        }
        else {
            ultimo.setSiguiente(nuevo); // El último elemento que habia antes pasa a ser el penúltimo.
            ultimo = nuevo; //El nuevo elemento se convierte en el último introducido.
        }
        tamaño ++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    public T desencolar(){
        if(isEmpty()){
            return null;
        }
        T dato = frente.getDato(); //Dato a eliminar (En este caso el primer dato introducido).
        frente = frente.getSiguiente(); //El segundo elemento pasa a ser el nuevo frente de la cola.
        tamaño--; //Disminuye el tamaño de la pila en 1.
        if(frente == null){
            ultimo = null; //Si no hay frente tampoco puede haber ultimo elemento introducido
        }
        return dato;
    }

    @Override
    public T frente(){
        if(isEmpty()){
            return null;
        }
        return frente.getDato(); //Devuelve el frente de la cola.
    }

    @Override
    public void clear(){ // Vaciamos la lista entera.
        frente = null;
        ultimo = null;
        tamaño = 0;
    }

    @Override
    public boolean isEmpty(){
        return frente == null; //Si no hay primer elemento True, de lo contrario False.
    }

    @Override
    public int getSize(){
        return tamaño; //Devuelve el tamaño de la lista.
    }
}
