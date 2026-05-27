package Estructuras.Interfaces;

import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyTree.Node;

public interface TreeInterfaz<T extends Comparable<T>> {
    Node<T> getRaiz();
    boolean isEmpty();
    Node<T> addChild(Node<T> padre, T hijo);
    boolean removeChild(Node<T> padre, T dato);
    Node<T> buscar(T dato);
    ListaSE<T> getPreorden();
    ListaSE<T> getPostorden();
    ListaSE<T> getPorNiveles();
    int getAltura();
    int getGrado();
    int getNumNodos();
}
