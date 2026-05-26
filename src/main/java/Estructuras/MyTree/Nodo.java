package Estructuras.MyTree;

public class Nodo <T extends Comparable<T>> {
    //Atributos:
    private T dato;
    private Nodo<T> hijoIz;
    private Nodo<T> hijoDr;

    //Constructor:
    public Nodo(T d) {
        this.dato = d;
        this.hijoIz = null;
        this.hijoDr = null;
    }

    public Nodo (T d, Nodo<T> hi, Nodo<T> hd){
        this.dato = d;
        this.hijoIz = hi;
        this.hijoDr = hd;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public Nodo<T> getHijoIz(){
        return hijoIz;
    }

    public Nodo<T> getHijoDr(){
        return hijoDr;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setHijoIz(Nodo<T> newHijoIz){
        this.hijoIz = newHijoIz;
    }

    public void setHijoDr(Nodo<T> newHijoDr){
        this.hijoDr = newHijoDr;
    }
}
