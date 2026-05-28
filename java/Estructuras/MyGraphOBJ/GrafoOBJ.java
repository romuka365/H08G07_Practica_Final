package Estructuras.MyGraphOBJ;

import Estructuras.Interfaces.GrafoInterfazOBJ;
import Estructuras.MyGraphOBJ.ListaSimple.ListaSimple;
import Estructuras.MyGraphOBJ.ListaSimple.MiIterador;

public class GrafoOBJ<T extends Comparable<T>> implements GrafoInterfazOBJ<T> {
    private int numVertices = 0;
    private ListaSimple<VerticeOBJ<T>> vertices;

    public GrafoOBJ(){
        this.vertices = new ListaSimple<VerticeOBJ<T>>();
    }

    public int getNumVertices() {
        return numVertices;
    }

    public void addVertice(int idOBJ, T dato) {
        numVertices += 1;
        VerticeOBJ<T> v = new VerticeOBJ<>(idOBJ, dato);
        vertices.add(v);
    }

    public void addArista(int idOrigen, T tipoOrigen, int idDestino, T tipoDestino, String predicado) {
        VerticeOBJ<T> vOrigen = getVertice(idOrigen);
        VerticeOBJ<T> vDestino = getVertice(idDestino);

        if (vOrigen != null && vDestino != null) {

            if (vOrigen.getDato().compareTo(tipoOrigen) == 0 && vDestino.getDato().compareTo(tipoDestino) == 0) {

                AristaOBJ a = new AristaOBJ(idDestino, predicado);

                vOrigen.addArista(a);
            }
        }
    }

    public VerticeOBJ<T> getVertice(int id) { //DEVUELVE VÉRTICE
        MiIterador<VerticeOBJ<T>> it = vertices.getIterador();
        while (it.hasNext()) {
            VerticeOBJ<T> b = it.next();
            // CORRECCIÓN: Usamos tu método getIdOBJ()
            if (b != null && b.getIdOBJ() == id) {
                return b; // Si lo encuentra, lo devuelve inmediatamente
            }
        }
        return null;
    }

    public int buscarIdPorDato(T dato) { //DEVUELVE ID DEL VÉRTICE
        // Si el grafo está vacío o nos pasan un dato nulo, no seguimos
        if (vertices.getSize() == 0 || dato == null) {
            return -1;
        }

        MiIterador<VerticeOBJ<T>> it = vertices.getIterador();

        while (it.hasNext()) {
            VerticeOBJ<T> b = it.next();

            // Comprobamos que el vértice y su dato existan
            if (b != null && b.getDato() != null) {
                // Como T implementa Comparable, usamos compareTo para ver si son iguales (resultado == 0)
                if (b.getDato().compareTo(dato) == 0) {
                    return b.getIdOBJ(); // Encontrado: devolvemos su ID
                }
            }
        }
        return -1; // Si recorre todo el grafo y no lo encuentra
    }

    public T buscarDatoPorId(int idOBJ) { //DEVUELVE OBJETO DENTRO DEL VÉRTICE
        if (vertices.getSize() == 0) {
            return null;
        }

        MiIterador<VerticeOBJ<T>> it = vertices.getIterador();

        while (it.hasNext()) {
            VerticeOBJ<T> b = it.next();

            // Si el ID coincide, devolvemos el contenido del vértice directamente
            if (b != null && b.getIdOBJ() == idOBJ) {
                return b.getDato(); // Devuelve el objeto T original
            }
        }
        return null; // Si no existe ningún vértice con ese ID
    }

    @Override
    public String toString() {
        MiIterador<VerticeOBJ<T>> it = vertices.getIterador();
        int i = 0;
        String resultado = "";

        while (it.hasNext()) {
            VerticeOBJ<T> b = it.next();

            if (b != null) {
                resultado += b.toString();
                resultado += imprimeConexiones(b.getIdOBJ()); // CORRECCIÓN: getIdOBJ()
                i++;
                resultado += "\n";
            }
        }
        return resultado + "Hay " + i + " elementos";
    }

    public String imprimeConexiones(int id) {
        VerticeOBJ<T> v = getVertice(id);
        if (v == null) return "";

        String imprimir = " -> Conexiones: ";
        imprimir += v.getStringAristas();
        return imprimir;
    }

    private String limpiarNombre(String dato) {
        if (dato.contains(":")) {
            return dato.split(":")[1];
        }
        return dato;
    }

    private String limpiarTipo(String dato) {
        if (dato.contains(":")) {
            return dato.split(":")[0];
        }
        return dato;
    }
}
