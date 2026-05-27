package Estructuras.MyTree;

import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyQueue.Cola;
import Estructuras.Interfaces.Iterador;

public class ArbolGeneral<T extends Comparable<T>> {
    //Atributos:
    private NodoGeneral<T> raiz;

    //Constructor
    public ArbolGeneral(T datoRaiz) {
        this.raiz = new NodoGeneral<>(datoRaiz);
    }

    public NodoGeneral<T> getRaiz() {
        return raiz;
    }

    public boolean isEmpty() {
        return raiz == null; //Si no hay raiz, el árbol está vacío.
    }

    public NodoGeneral<T> addChild(NodoGeneral<T> padre, T hijo) {
        //Si no hay padre, no se puede añadir un hijo.
        if (padre == null) return null;
        NodoGeneral<T> nuevo = new NodoGeneral<>(hijo); //Crea un nodo con los datos del hijo.
        nuevo.setPadre(padre); //Se le asigna el padre.
        padre.getHijos().add(nuevo); //Añade el nuevo nodo a la lista de hijos del padre.
        return nuevo; //Devuelve el nodo hijo.
    }

    public boolean removeChild(NodoGeneral<T> padre, T dato) {
        //Si el árbol está vacío no se puede borrar ningun hijo.
        if (padre == null || padre.getHijos().isEmpty()) return false;

        //Crea un nodo con los mismos datos que el nodo a borrar, para asi buscarlo y poder borrarlo.
        NodoGeneral<T> dummy = new NodoGeneral<>(dato);
        NodoGeneral<T> resultado = padre.getHijos().del(dummy);
        return resultado != null;
    }

    public NodoGeneral<T> buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private NodoGeneral<T> buscarRec(NodoGeneral<T> actual, T dato) { //Método auxiliar recursivo de buscar.
        //Si el nodo actual no existe, no hay nada que buscar.
        if (actual == null) return null;
        //Si los datos del nodo que buscamos coinciden con el actual, lo devuelve.
        if (actual.getDato().compareTo(dato) == 0) return actual;

        //Se crea un iterador para recorrer todos los nodos del árbol
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) {
            NodoGeneral<T> encontrado = buscarRec(it.next(), dato);
            if (encontrado != null) return encontrado;
        }
        return null; //Si ningun nodo coincide con el buscado, devuelve null.
    }


    //Órdenes:
    public ListaSE<T> getPreorden() {
        ListaSE<T> lista = new ListaSE<>();
        preordenRec(raiz, lista);
        return lista;
    }

    private void preordenRec(NodoGeneral<T> actual, ListaSE<T> lista) {
        if (actual == null) return;
        lista.add(actual.getDato());
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) {
            preordenRec(it.next(), lista);
        }
    }

    public ListaSE<T> getPostorden() {
        ListaSE<T> lista = new ListaSE<>();
        postordenRec(raiz, lista);
        return lista;
    }

    private void postordenRec(NodoGeneral<T> actual, ListaSE<T> lista) {
        if (actual == null) return;
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) {
            postordenRec(it.next(), lista);
        }
        lista.add(actual.getDato());
    }


    public ListaSE<T> getPorNiveles() {
        ListaSE<T> lista = new ListaSE<>(); //Se crea una lista donde se guardarán los datos por niveles.
        if (raiz == null) return lista;// Si el árbol está vacío, devuelve la lista vacía.

        Cola<NodoGeneral<T>> cola = new Cola<>(); //Cola que sirve para recorrer el árbol nivel a nivel.
        cola.encolar(raiz); //Empieza por la raiz.

        while (!cola.isEmpty()) { //Mientras queden nodos por visitar sigue el bucle.
            NodoGeneral<T> actual = cola.desencolar(); //Saca el primer nodo de la cola.
            lista.add(actual.getDato()); //Añade el actual a la lista.

            //Iterador para buscar entre los hijos del nodo actual.
            Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
            while (it.hasNext()) {
                cola.encolar(it.next()); //Se añaden a la cola todos los hijos del nodo actual
            }
        }

        return lista; //Devuelve la lista.
    }

    public int getAltura() {
        return alturaRec(raiz); //Empieza en la raiz.
    }

    private int alturaRec(NodoGeneral<T> actual) {
        if (actual == null) return 0; //Si el nodo actual no existe, su altura es 0.
        int maxAltura = 0; //Inicializa la altura del árbol como 0

        //Iterador para recorrer todos los hijos del nodo actual.
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) {
            int alturaHijo = alturaRec(it.next());
            if (alturaHijo > maxAltura) {
                maxAltura = alturaHijo; //Si la altura del hijo es mayor que la del árbol hasta el momento, la sustituye.
            }
        }
        return 1 + maxAltura; //La altura del árbol es el nivel en el que se encuentre el hijo +1 (Raiz).
    }

    public int getGrado() {
        return gradoRec(raiz); //Empieza en la raiz.
    }

    private int gradoRec(NodoGeneral<T> actual) {
        if (actual == null) return 0; //Si el nodo actual no existe, su grado es 0.
        int numHijos = actual.getHijos().getSize(); //Obtiene el nº de hijos del nodo actual.
        int maxGrado = numHijos; // El grado máximo de hijos es el nº de hijos del nodo actual.

        //Iterador para recorrer los hijos del nodo actual.
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) { //Mientras queden hijos sigue el bucle.
            int gradoHijo = gradoRec(it.next());
            if (gradoHijo > maxGrado) { //Si el grado del hijo es mayor al grado mayor actual, se actualiza.
                maxGrado = gradoHijo;
            }
        }
        return maxGrado; //Devuelve el grado maximo del árbol.
    }

    public int getNumNodos() {
        return numNodosRec(raiz); //Empieza en la raiz.
    }

    private int numNodosRec(NodoGeneral<T> actual) {
        if (actual == null) return 0; //Si el nodo actual no existe, cuenta 0
        int count = 1; //Inicializa la cantidad de nodos a 1 (Raiz).
        //Iterador para recorrer los hijos del nodo actual.
        Iterador<NodoGeneral<T>> it = actual.getHijos().getIterador();
        while (it.hasNext()) { //Mientras haya hijos sigue el bucle.
            count += numNodosRec(it.next()); // Suma los nodos de cada subárbol hijo.
        }
        return count; //Devuelve el total de nodos del árbol.
    }
}
