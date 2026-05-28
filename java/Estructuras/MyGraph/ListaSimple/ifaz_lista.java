package Estructuras.MyGraph.ListaSimple;

public interface ifaz_lista<T> { //en una lista puedo hacer lo siguiente:
    void add(T dato); //añadir un elemento
    T get(T dato); //devolver un elemento
    T del(T dato); //eliminar un elemento
    boolean isEmpty(); //decir si la lista está vacía
    int getSize(); //devolver el tamaño de la lista
    MiIterador<T> getIterador();
}
