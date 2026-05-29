package Estructuras.MyGraphOBJ;

public class GrafoOBJTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE GRAFOOBJ ---\n");

        GrafoOBJ<String> grafo = new GrafoOBJ<>();

        if (grafo.getNumVertices() == 0) {
            System.out.println("Prueba 1 (Grafo inicialmente vacío): PASADA");
        } else {
            System.out.println("Prueba 1 (Grafo inicialmente vacío): FALLADA");
        }


        grafo.addVertice(10, "Guerrero");
        grafo.addVertice(20, "Mago");

        if (grafo.getNumVertices() == 2) {
            System.out.println("Prueba 2 (Conteo tras añadir vértices): PASADA");
        } else {
            System.out.println("Prueba 2 (Conteo tras añadir vértices): FALLADA. Cuenta: " + grafo.getNumVertices());
        }


        VerticeOBJ<String> v1 = grafo.getVertice(10);
        VerticeOBJ<String> vInexistente = grafo.getVertice(999);

        if (v1 != null && v1.getDato().equals("Guerrero") && vInexistente == null) {
            System.out.println("Prueba 3 (Obtención de vértice por ID): PASADA");
        } else {
            System.out.println("Prueba 3 (Obtención de vértice por ID): FALLADA");
        }


        int idEncontrado = grafo.buscarIdPorDato("Mago");
        String datoEncontrado = grafo.buscarDatoPorId(10);

        if (idEncontrado == 20 && "Guerrero".equals(datoEncontrado)) {
            System.out.println("Prueba 4 (Búsquedas por dato e ID): PASADA");
        } else {
            System.out.println("Prueba 4 (Búsquedas por dato e ID): FALLADA. ID: " + idEncontrado + ", Dato: " + datoEncontrado);
        }


        grafo.addArista(10, "Guerrero", 20, "Mago", "atacaA");

        grafo.addArista(10, "TipoFalso", 20, "Mago", "ignoraA");

        String conexionesV1 = grafo.imprimeConexiones(10);

        if (conexionesV1.contains("atacaA") && !conexionesV1.contains("ignoraA")) {
            System.out.println("Prueba 5 (addArista con control de tipos e imprimeConexiones): PASADA");
        } else {
            System.out.println("Prueba 5 (addArista con control de tipos e imprimeConexiones): FALLADA. Salida: " + conexionesV1);
        }


        String salidaGlobal = grafo.toString();

        if (salidaGlobal.contains("Hay 2 elementos") && salidaGlobal.contains("10:Guerrero")) {
            System.out.println("Prueba 6 (Método toString global): PASADA");
        } else {
            System.out.println("Prueba 6 (Método toString global): FALLADA. Salida:\n" + salidaGlobal);
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}