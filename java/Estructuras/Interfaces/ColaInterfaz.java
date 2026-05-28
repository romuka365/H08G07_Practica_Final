package Estructuras.Interfaces;

public interface ColaInterfaz <T>{
    void encolar(T dato);
    T desencolar();
    T frente();
    void clear();
    boolean isEmpty();
    int getSize();
}
