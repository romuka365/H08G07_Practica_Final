package Estructuras.MyCircularList;

import Estructuras.Interfaces.Iterador;
import Estructuras.Interfaces.ListaCircularInterfaz;

public class ListaCircular <T extends Comparable<T>> implements ListaCircularInterfaz<T> {
    //Variables:
    protected ElementoLC<T> primero;
    protected ElementoLC<T> ultimo;
    protected int tamaño;

    //Constructores:
    public ListaCircular(){
        this.primero = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    //Métodos:
    @Override
    public void addInicio(T dato){
        ElementoLC<T> nuevo = new ElementoLC<>(dato);
        if(isEmpty()){
            primero = ultimo = nuevo;
            ultimo.setSiguiente(primero); //El nuevo elemento apunta hacia si mismo, ya que el ultimo es el mismo que el primero.
        }
        else{
            nuevo.setSiguiente(primero); //Conviertes el primer elemento en el siguiente.
            primero = nuevo; //El elemento que añadimos se convierte en el primero.
            ultimo.setSiguiente(primero);
        }
        tamaño ++; //Aumenta el tamaño de la lista en 1.
    }


    @Override
    public void add(T dato){
        ElementoLC<T> nuevo = new ElementoLC<>(dato);
        if(isEmpty()){
            primero = ultimo = nuevo;
            ultimo.setSiguiente(primero); //El nuevo elemento apunta hacia si mismo, ya que el ultimo es el mismo que el primero.
        }
        else{
            nuevo.setSiguiente(primero); //El nuevo elemento (Que está al final de la lista) apunta al primero.
            ultimo.setSiguiente(nuevo); //El antiguo ultimo elemento ahora apunta al nuevo elemento.
            ultimo = nuevo;
        }
        tamaño++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    public T get(T dato){
        ElementoLC<T> puntero = primero;
        if (isEmpty()) { // Va fuera del while, ya que sino nunca entrariramos en esta condición.
            return null;
        }
        do { //Con el do obligamos a que de una vuelta antes de cumplir la condidición y asi asegurarnos de que recorra toda la lista.
            if(puntero.getDato().compareTo(dato) == 0){
                return puntero.getDato(); //Si coincide devuelve el dato
            }
            puntero = puntero.getSiguiente();
        }
        while (puntero != primero);
        return null;
    }

    @Override
    public T del(T dato) {
        ElementoLC<T> puntero = primero;
        ElementoLC<T> anterior = ultimo;

        if (isEmpty()) { // Va fuera del while, ya que sino nunca entrariramos en esta condición.
            return null;
        }
        do { //Con el do obligamos a que de una vuelta antes de cumplir la condidición y asi asegurarnos de que recorra toda la lista.
            if (puntero.getDato().compareTo(dato) == 0) {
                if(primero == ultimo) { //Si la lista solo tuviera un elemento.
                    primero = ultimo = null;
                }
                else if (puntero == primero) {
                    primero = puntero.getSiguiente(); //Si el elemento es el primero.
                    ultimo.setSiguiente(primero); //El ultimo elemento ahora apunta al nuevo primero elemento.
                }
                else if (puntero == ultimo) {
                    anterior.setSiguiente(primero); //El anterior elemento al ultimo apunta al primero, por lo que es el último ahora.
                    ultimo = anterior;
                }
                else {
                    anterior.setSiguiente(puntero.getSiguiente()); //El anterior apunta al siguiente del elimindado.
                }
                tamaño--; //Disminuye el tamaño de la lista en 1.
                return puntero.getDato();
            }
            anterior = puntero;
            puntero = puntero.getSiguiente();
        }
        while (puntero != primero);
        return  null;
    }

    @Override
    public void clear(){ // Vaciamos la lista entera.
        primero = null;
        ultimo = null;
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
        return new IteradorLC<T>(this.primero);
    }
}
