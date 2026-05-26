package Estructuras.MyStack;

import Estructuras.Interfaces.PilaInterfaz;

public class Pila <T> implements PilaInterfaz<T> {

    //Variables:
    protected ElementoPila<T> cabeza;
    protected int tamaño;

    //Constructores:
    public Pila(){
        this.cabeza = null;
        this.tamaño = 0;
    }

    //Métodos:
    @Override
    public void push(T dato){
        ElementoPila<T> nuevo = new ElementoPila<>(dato);
        nuevo.setSiguiente(cabeza); //Conviertes el primer elemento en el siguiente.
        cabeza = nuevo; //El elemento que añadimos se convierte en el primero.
        tamaño ++; //Aumenta el tamaño de la pila en 1.
    }

    @Override
    public T pop(){
        if(isEmpty()){
            return null;
        }

        T dato = cabeza.getDato(); //Dato a eliminar (En este caso el último dato introducido).
        cabeza = cabeza.getSiguiente(); //El segundo elemento pasa a ser la nueva cabeza de la pila.
        tamaño--; //Disminuye el tamaño de la pila en 1.
        return dato;
    }

    @Override
    public T top(){
        if(isEmpty()){
            return null;
        }
        return cabeza.getDato();
    }

    @Override
    public void clear(){ // Vaciamos la lista entera.
        cabeza = null;
        tamaño = 0;
    }

    @Override
    public boolean isEmpty(){
        return cabeza == null; //Si no hay primer elemento True, de lo contrario False.
    }

    @Override
    public int getSize(){
        return tamaño; //Devuelve el tamaño de la lista.
    }
}
