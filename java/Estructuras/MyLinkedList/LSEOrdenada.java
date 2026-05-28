package Estructuras.MyLinkedList;

public class LSEOrdenada <T extends Comparable<T>> extends ListaSE<T> {

    //Solamente necesitamos cambiar el método add, para que meta los elementos de manera ordenada.
    @Override
    public void add(T dato) {
        ElementoSE<T> nuevo = new ElementoSE<>(dato);
        if (primero == null || dato.compareTo(primero.getDato()) < 0) { //Si la lista está vacía o el nuevo va antes del primer elemento.
            nuevo.setSiguiente(primero);
            primero = nuevo;
            tamaño++; //Incrementamos el tamaño de la lista en 1.
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 1.
        }

        ElementoSE<T> puntero = primero;
        while (puntero.getSiguiente() != null && dato.compareTo(puntero.getSiguiente().getDato()) > 0) { //Comprueba si hay siguiente y a su vez si este es mayor que él.
            puntero = puntero.getSiguiente();
        }

        nuevo.setSiguiente(puntero.getSiguiente());
        puntero.setSiguiente(nuevo);
        tamaño++; //Incrementamos el tamaño de la lista en 1.
    }
}
