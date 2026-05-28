package Estructuras.MyGraph;

import Estructuras.MyGraph.ListaSimple.MiIterador;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyQueue.Cola;

public class BFSGrafo {
    //Atributos:
    private Grafo grafo;

    //Constructor:
    public BFSGrafo(Grafo grafo) {
        //Si no existe el grafo salta la excepción.
        if (grafo == null) {
            throw new IllegalArgumentException("No existe el grafo.");
        }
        this.grafo = grafo;
    }

    public ListaSE<Vertice> buscarSalida(String tipoOrigen, String nombreOrigen, String tipoSalida) {
        //Si el origen o la salida tienen información null, salta está excepción.
        if (tipoOrigen == null || nombreOrigen == null || tipoSalida == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser null");
        }

        int idOrigen = grafo.buscarIdVertice(tipoOrigen, nombreOrigen); //Obtiene el id del vértice origen.
        if (idOrigen == -1) return new ListaSE<>(); //Si no encuentra el vértice origen, devuelve una lista vacía.

        int n = grafo.getMaxId() + 1;
        boolean[] visitado = new boolean[n]; //Marca vértices como visitados.
        int[] predecesor = new int[n]; //Array que muestra desde que vértice se llego a cada uno.

        //Para cada vértice se marca su predecesor como -1 (No tener predecesor).
        for (int i = 0; i < n; i++) predecesor[i] = -1;

        Cola<Integer> cola = new Cola<>(); //Crea una cola con los vértices pendientes de explorar.
        visitado[idOrigen] = true; //Marca el origen como visitado.
        cola.encolar(idOrigen); //Mete el origen a la cola.

        //Mientras haya vértices en la cola sigue el bucle.
        while (cola.getSize() > 0) {
            int actual = cola.desencolar(); //Saca el vértice actual de la cola
            Vertice v = grafo.getVertice(actual); //Obtiene el vértice actual
            if (v == null) continue; //Si no existe continua.

            //Cuando el vértice sea de tipo salida (El más cercano que encuentre), se reconstruye el camino y lo devuelve.
            if (v.getTipo().equals(tipoSalida)) {
                return reconstruirCamino(predecesor, idOrigen, actual);
            }

            // El iterador se encarga de recorrer todas las aristas del vértice actual.
            MiIterador<Arista> it = v.getListaAristas().getIterador();
            //Mientras haya aristas disponibles continua el buble.
            while (it.hasNext()) {
                Arista a = it.next();
                int destId = a.getIdDestino(); //obtiene el vértice en el que incide la arista.
                //Si no ha sido visitado y el ID sea válido entra en el if.
                if (destId >= 0 && destId < n && !visitado[destId]) {
                    visitado[destId] = true; //Se marca el vértice como visitado.
                    predecesor[destId] = actual; //El actual se convierte en el predecesor.
                    cola.encolar(destId); //Mete el vértice en la cola.
                }
            }
        }

        return new ListaSE<>(); //Si no se encuentra el camino devuelve una lista vacía.
    }

    //Este método solo sería necesario si se quisiera encontrar el camino a un sitio en concreto, para el camino minimo a la salida no es necesario hacer uso de este método.
    public ListaSE<Vertice> getCamino(String tipoOrigen, String nombreOrigen,
                                       String tipoDestino, String nombreDestino) {
        //Si el origen o el destino tienen información null, salta está excepción.
        if (tipoOrigen == null || nombreOrigen == null || tipoDestino == null || nombreDestino == null) {
            throw new IllegalArgumentException("Los parámetros no pueden ser null");
        }

        //Busca los IDs de origen y destino.
        int idOrigen = grafo.buscarIdVertice(tipoOrigen, nombreOrigen);
        int idDestino = grafo.buscarIdVertice(tipoDestino, nombreDestino);
        if (idOrigen == -1 || idDestino == -1) return new ListaSE<>(); //Si no encuentra ID de origen o destino devuelve una lista vacía.

        //Si ambos origen y destino son el mismo vértice, el camino está compuesto de solo ese vértice.
        if (idOrigen == idDestino) {
            ListaSE<Vertice> r = new ListaSE<>();
            r.add(grafo.getVertice(idOrigen));
            return r;
        }

        int n = grafo.getMaxId() + 1;
        boolean[] visitado = new boolean[n]; //Marca vértices como visitados.
        int[] predecesor = new int[n]; //Array que muestra desde que vértice se llego a cada uno.

        //Para cada vértice se marca su predecesor como -1 (No tener predecesor).
        for (int i = 0; i < n; i++) predecesor[i] = -1;

        Cola<Integer> cola = new Cola<>(); //Crea la cola para el BFS.
        visitado[idOrigen] = true; //Se marca el origen como visitado.
        cola.encolar(idOrigen); //Mete el origen a la cola.

        //Mientras haya vértices en la cola sigue el bucle.
        while (cola.getSize() > 0) {
            int actual = cola.desencolar(); //Saca primer vértice de la cola.

            //Si el vértice actual es el de destino, recontruye el camino y lo devuelve.
            if (actual == idDestino) {
                return reconstruirCamino(predecesor, idOrigen, idDestino);
            }

            Vertice v = grafo.getVertice(actual); //Obtiene el vértice actual.
            if (v == null) continue; //Si no existe ese vértice continua.

            // El iterador se encarga de recorrer todas las aristas del vértice actual.
            MiIterador<Arista> it = v.getListaAristas().getIterador();
            //Mientras haya aristas disponibles continua el buble.
            while (it.hasNext()) {
                Arista a = it.next();
                int destId = a.getIdDestino(); //obtiene el vértice en el que incide la arista.
                //Si no ha sido visitado y el ID sea válido entra en el if.
                if (destId >= 0 && destId < n && !visitado[destId]) {
                    visitado[destId] = true; //Se marca el vértice como visitado.
                    predecesor[destId] = actual; //El actual se convierte en el predecesor.
                    cola.encolar(destId); //Mete el vértice en la cola.
                }
            }
        }

        return new ListaSE<>(); //Si no se encuentra el camino devuelve una lista vacía.
    }

    private ListaSE<Vertice> reconstruirCamino(int[] predecesor, int origen, int destino) {
        ListaSE<Vertice> camino = new ListaSE<>(); //Lista de vértices del camino.
        int actual = destino; //Empezamos desde el vértice destino.
        while (actual != -1) { //Mientras un vértice tenga predecesor continua el bucle.
            Vertice v = grafo.getVertice(actual); //Busca el vértice con el id de actual.
            if (v != null) camino.addInicio(v); //Si el vértice existe, se añade al inicio de la lista.
            if (actual == origen) break; //Si llega al origen para.
            actual = predecesor[actual]; //Retrocede actual al anterior vertice de actual.
        }
        return camino; //Devuelve la lista de vertices que componen el camino.
    }
}