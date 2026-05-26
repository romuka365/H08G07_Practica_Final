package Estructuras.MyGraph;

import Estructuras.MyGraph.ListaSimple.MiIterador;

public class BuscadorGrafo {
    private Grafo grafo;

    public BuscadorGrafo(Grafo grafo) {
        this.grafo = grafo;
    }

    public void ejecutarConsulta() {
        System.out.println("Iniciando búsqueda: Físicos nacidos en la misma ciudad que Einstein...\n");

        // 1. Buscar el nodo de Persona:Albert Einstein
        int idEinstein = grafo.buscarIdVertice("persona", "Albert Einstein");
        if (idEinstein == -1) {
            System.out.println("No se encontró a Einstein en el grafo.");
            return;
        }
        Vertice nodoEinstein = grafo.getVertice(idEinstein);

        // 2. Buscar la arista "nace_en" para encontrar el ID de la ciudad
        int idCiudad = -1;
        MiIterador<Arista> itE = nodoEinstein.getListaAristas().getIterador();
        while (itE.hasNext()) {
            Arista a = itE.next();
            if (a.getPredicado().equalsIgnoreCase("nace_en")) {
                idCiudad = a.getIdDestino();
                break;
            }
        }

        if (idCiudad == -1) {
            System.out.println("No se encontró información de nacimiento para Einstein.");
            return;
        }

        Vertice nodoCiudad = grafo.getVertice(idCiudad);
        System.out.println("Ciudad de nacimiento identificada: " + nodoCiudad.getNombre() + " (ID: " + idCiudad + ")");

        // 3. Buscar TODAS las personas conectadas a esa ciudad
        // Como el grafo es dirigido (Persona -> Ciudad), tenemos que recorrer los vértices
        // para ver quién apunta a esa ciudad.
        System.out.println("Buscando otras personas nacidas en " + nodoCiudad.getNombre() + "...");

        for (int i = 1; i <= grafo.getNumVertices(); i++) {
            Vertice vPotencial = grafo.getVertice(i);

            // Saltamos si es el mismo Einstein o si el nodo es nulo
            if (vPotencial == null || vPotencial.getId() == idEinstein) continue;

            // Comprobamos si esta persona nace en la ciudad
            if (tieneAristaHacia(vPotencial, "nace_en", idCiudad)) {

                // 4. Si nace ahí, buscamos si su ocupación es "Fisico"
                if (tieneOcupacion(vPotencial, "Fisico")) {
                    System.out.println("¡Encontrado!: " + vPotencial.getNombre() + " es Físico y nació en " + nodoCiudad.getNombre());
                }
            }
        }
    }

    // Método auxiliar para verificar si un vértice tiene una arista específica a un destino
    private boolean tieneAristaHacia(Vertice origen, String predicado, int idDestino) {
        MiIterador<Arista> it = origen.getListaAristas().getIterador();
        while (it.hasNext()) {
            Arista a = it.next();
            if (a.getPredicado().equalsIgnoreCase(predicado) && a.getIdDestino() == idDestino) {
                return true;
            }
        }
        return false;
    }

    // Método auxiliar para verificar la ocupación
    private boolean tieneOcupacion(Vertice persona, String ocupacionBuscada) {
        MiIterador<Arista> it = persona.getListaAristas().getIterador();
        while (it.hasNext()) {
            Arista a = it.next();
            if (a.getPredicado().equalsIgnoreCase("ocupacion")) {
                Vertice destino = grafo.getVertice(a.getIdDestino());
                if (destino != null && destino.getNombre().equalsIgnoreCase(ocupacionBuscada)) {
                    return true;
                }
            }
        }
        return false;
    }
}