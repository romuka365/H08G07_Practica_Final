package Estructuras.MyGraph.ListaSimple;

public class IteradorLS<T> implements MiIterador<T>{
    private ElementoS<T> actual;

    public IteradorLS(ElementoS<T> inicio) {
        this.actual = inicio;
    }

    public boolean hasNext() { //si tiene un siguiente elemento, true
        return actual != null;  //si no lo tiene, false
    }

    public T next() {
        if (!hasNext()) { //si no tiene siguente, devuelve nulo
            return null;
        }
        if (actual == null) {
            return null;
        }
        T dato = actual.dato;
        actual = actual.siguiente;
        return dato;
    }
}
