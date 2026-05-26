package Estructuras.Interfaces;

public interface PilaInterfaz <T>{
    void push(T dato);
    T pop();
    T top();
    void clear();
    boolean isEmpty();
    int getSize();
}
