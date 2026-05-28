package Estructuras.MyGraph;

import Estructuras.MyGraph.ListaSimple.MiIterador;

public class CaminoMinimo {
    private Grafo grafo;
    private double[] distancias;
    private int[] predecesores;
    private boolean[] visitados;
    private int totalVertices;

    public CaminoMinimo(Grafo grafo) {
        this.grafo = grafo;
        this.totalVertices = grafo.getMaxId() + 1;
        this.distancias = new double[totalVertices];
        this.predecesores = new int[totalVertices];
        this.visitados = new boolean[totalVertices];
        for (int i = 0; i < totalVertices; i++) {
            distancias[i] = Double.POSITIVE_INFINITY;
            predecesores[i] = -1;
        }
    }

    public void ejecutarDijkstra(String tipoOrg, String nomOrg) {
        int idOrigen = grafo.buscarIdVertice(tipoOrg, nomOrg);
        if (idOrigen == -1) return;

        int nuevoTamanio = grafo.getMaxId() + 1;
        if (nuevoTamanio > totalVertices) {
            totalVertices = nuevoTamanio;
            distancias = new double[totalVertices];
            predecesores = new int[totalVertices];
            visitados = new boolean[totalVertices];
        }

        // Inicialización
        for (int i = 0; i < totalVertices; i++) {
            distancias[i] = Double.POSITIVE_INFINITY;
            predecesores[i] = -1;
            visitados[i] = false;
        }

        distancias[idOrigen] = 0;

        for (int count = 0; count < grafo.getNumVertices(); count++) {
            int u = buscarDistanciaMinima();
            if (u == -1) break;

            visitados[u] = true;
            Vertice vActual = grafo.getVertice(u);

            // Recorremos las aristas del vértice actual
            MiIterador<Arista> it = vActual.getListaAristas().getIterador();
            while (it.hasNext()) {
                Arista a = it.next();
                int v = a.getIdDestino();
                double peso = 1.0; //hacemos peso 1 porque nuestras aristas son todas iguales

                if (!visitados[v] && distancias[u] != Double.POSITIVE_INFINITY
                        && distancias[u] + peso < distancias[v]) {
                    distancias[v] = distancias[u] + peso;
                    predecesores[v] = u;
                }
            }
        }
    }

    private int buscarDistanciaMinima() {
        double min = Double.POSITIVE_INFINITY;
        int minIndex = -1;

        for (int v = 0; v < totalVertices; v++) {
            if (!visitados[v] && distancias[v] <= min && distancias[v] != Double.POSITIVE_INFINITY) {
                min = distancias[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    public String obtenerCamino(String tipoDest, String nomDest) {
        int idDest = grafo.buscarIdVertice(tipoDest, nomDest);
        if (idDest == -1 || idDest >= totalVertices || distancias[idDest] == Double.POSITIVE_INFINITY) {
            return "No hay camino";
        }

        String camino = "";
        int temp = idDest;
        while (temp != -1) {
            Vertice v = grafo.getVertice(temp);
            camino = " -> " + v.getNombre() + camino;
            temp = predecesores[temp];
        }
        return "Distancia: " + distancias[idDest] + " | Camino: " + camino.substring(4);
    }
}