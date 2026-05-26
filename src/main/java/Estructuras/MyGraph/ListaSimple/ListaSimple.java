package Estructuras.MyGraph.ListaSimple;

public class ListaSimple<T extends Comparable<T>> implements ifaz_lista<T> {
    protected ElementoS<T> cabeza;
    protected int tamanno = 0;

    @Override
    public void add(T dato) { //añadimos un elemento a la lista
        ElementoS<T> annadir = new ElementoS<>(dato); //annadir == añadir
        if (cabeza == null) { //es decir, si la lista está vacía
            cabeza = annadir;
        } else {
            ElementoS<T> i = cabeza; //creo un elemento i que apunte a la cabeza para no modificar la cabeza
            while (i.siguiente != null) i = i.siguiente; // es decir, si el elemento tiene un siguente
            i.siguiente = annadir; //cuando ya no haya más, ponemos el elemento a añadir como siguente
        }
        tamanno += 1; //si añadimos un elemento, debemos aumentar el tamaño

    }

    public void add(int index, T dato) {
        // 1. Validar el índice
        if (index < 0 || index > tamanno) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + index);
        }

        ElementoS<T> nuevo = new ElementoS<>(dato);

        // 2. Caso especial: Insertar al principio
        if (index == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        }
        // 3. Caso general: Insertar en cualquier otra posición
        else {
            ElementoS<T> anterior = cabeza;
            // Avanzamos hasta el nodo justo antes de la posición deseada
            for (int i = 0; i < index - 1; i++) {
                anterior = anterior.siguiente;
            }

            // Reajustamos los punteros
            nuevo.siguiente = anterior.siguiente;
            anterior.siguiente = nuevo;
        }

        tamanno+=1;
    }

    @Override
    public T get(T dato) { //devolvemos un elemento de la lista
        ElementoS<T> i = cabeza;
        while (i != null) { //mientras la lista no esté vacía
            if(i.dato.compareTo(dato) == 0) { //si el dato en el elemento coincide con el dato que busco,compareTo == 0, lo que significa que ha sido encontrado
                return i.dato; //me devuelve el dato pedido si es que está en la lista
            }
            i = i.siguiente; //mientras no encuentre el elemento con el dato, paso al siguiente para seguir buscando
        }
        return null; //si recorre toda la lista y no encuentra el dato, devuelve null
    }

    @Override
    public T del(T dato) { //borramos un elemento de la lista
        ElementoS<T> actual = cabeza, anterior = null; //empezamos mirando el principio de la lista
        while (actual != null) { //mientras que sigamos mirando elementos de la lista
            if (actual.dato.compareTo(dato) == 0) { //NO ENTIENDO EL COMPARETO = 0
                if (anterior == null) {
                    cabeza = actual.siguiente;
                }
                else {
                    anterior.siguiente = actual.siguiente;
                }
                tamanno-=1; //aminoramos el tamaño en 1 después de eliminar un elemento
                return actual.dato; //devolvemos el dato eliminado
                }
                anterior = actual;
                actual = actual.siguiente;
        }
        return null;
    }

    @Override
    public boolean isEmpty() { //devuelve true si no hay ni un elemento
        return cabeza == null; //y devuelve false si hay al menos uno
    }

    @Override
    public int getSize() {
        return tamanno;
    }

    @Override
    public MiIterador<T> getIterador() {
        return new IteradorLS<T>(this.cabeza);
    }

    @Override
    public String toString() {
        if (tamanno == 0) {
            return "[]";
        }

        MiIterador<T> it = this.getIterador();

        String resultado = "[";

        // 4. Recorremos con el while
        while (it.hasNext()) {
            T dato = it.next();

            // Añadimos el dato convertido a texto
            resultado += dato;

            // 5. Si el iterador nos dice que hay OTRO elemento después,
            // añadimos la coma y el espacio
            if (it.hasNext()) {
                resultado += ", ";
            }
        }

        // 6. Cerramos el corchete y devolvemos la cadena final
        resultado += "]";
        return resultado;
    }

}
