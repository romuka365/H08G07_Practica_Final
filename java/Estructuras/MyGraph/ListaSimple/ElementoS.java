package Estructuras.MyGraph.ListaSimple;

class ElementoS<T> { //Elemento de una lista simplemente enlazada
    T dato; //dato en sí utilizado
    ElementoS<T> siguiente; //referencia al elemento siguiente

    ElementoS(T dato) {
        this.dato = dato; //asignamos el dato dado al dato del elemento
        this.siguiente = null; //como aquí no definimos el siguiente, lo asignamos como null

    }
}
