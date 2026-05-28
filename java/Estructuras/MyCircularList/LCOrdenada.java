package Estructuras.MyCircularList;

public class LCOrdenada <T extends Comparable<T>> extends ListaCircular<T> {

    //Solamente necesitamos cambiar el método add, para que meta los elementos de manera ordenada.
    @Override
    public void add(T dato) {
        ElementoLC<T> nuevo = new ElementoLC<T>(dato);
        if (isEmpty()) { //Si la lista está vacía.
            primero = ultimo = nuevo;
            ultimo.setSiguiente(primero); //El nuevo elemento apunta hacia si mismo, ya que el ultimo es el mismo que el primero.
            tamaño++; //Incrementamos el tamaño de la lista en 1.
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 1.

        } else if (dato.compareTo(primero.getDato()) < 0) { //Si el dato va antes que el primero.
            nuevo.setSiguiente(primero);
            primero = nuevo;
            ultimo.setSiguiente(primero);
            tamaño++;
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 2.

        } else if (dato.compareTo(ultimo.getDato()) > 0) { //Si el dato va despues del último.
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
            ultimo.setSiguiente(primero);
            tamaño++;
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 3.

        }

        { //Si el dato está entre el primero y el último.
            ElementoLC<T> puntero = primero.getSiguiente();
            ElementoLC<T> anterior = primero;
            while (puntero != primero && dato.compareTo(puntero.getDato()) > 0){
                anterior = puntero;
                puntero = puntero.getSiguiente();
            }
            anterior.setSiguiente(nuevo);
            nuevo.setSiguiente(puntero);
            if (puntero == ultimo) {
                anterior.setSiguiente(nuevo);
                nuevo.setSiguiente(ultimo);
                tamaño++;
                return;
            }
        }
        tamaño++; //Incrementamos el tamaño de la lista en 1.
    }
}