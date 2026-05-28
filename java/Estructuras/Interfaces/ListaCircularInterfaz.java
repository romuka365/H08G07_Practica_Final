package Estructuras.Interfaces;

public interface ListaCircularInterfaz <T extends Comparable<T>> {
    void addInicio(T dato);
    void add(T dato);
    T get(T dato);
    T del(T dato);
    void clear();
    boolean isEmpty();
    int getSize();
    Iterador<T> getIterador();
}
