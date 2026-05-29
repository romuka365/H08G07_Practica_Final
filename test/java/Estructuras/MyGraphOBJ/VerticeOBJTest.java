package Estructuras.MyGraphOBJ;

public class VerticeOBJTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE VERTICEOBJ ---\n");

        // Usaremos String como tipo genérico para el dato del vértice
        VerticeOBJ<String> vertice1 = new VerticeOBJ<>(1, "Guerrero");

        // 1. Prueba del constructor y Getters básicos
        if (vertice1.getIdOBJ() == 1 && vertice1.getDato().equals("Guerrero")) {
            System.out.println("Prueba 1 (Atributos iniciales): PASADA");
        } else {
            System.out.println("Prueba 1 (Atributos iniciales): FALLADA");
        }

        if (vertice1.getListaAristas() != null) {
            System.out.println("Prueba 2 (Inicialización de lista de aristas): PASADA");
        } else {
            System.out.println("Prueba 2 (Inicialización de lista de aristas): FALLADA");
        }


        // 2. Prueba del método toString()
        if (vertice1.toString().equals("1:Guerrero")) {
            System.out.println("Prueba 3 (Método toString): PASADA");
        } else {
            System.out.println("Prueba 3 (Método toString): FALLADA. Obtenido: " + vertice1.toString());
        }


        // 3. Prueba de añadir aristas y obtenerlas en cadena (addArista y getStringAristas)
        AristaOBJ aristaA = new AristaOBJ(2, "atacaA");
        AristaOBJ aristaB = new AristaOBJ(3, "ayudaA");

        vertice1.addArista(aristaA);
        vertice1.addArista(aristaB);

        // El resultado esperado depende del toString() de AristaOBJ: " predicado (idDestino)"
        // aristaA.toString() -> " atacaA (2)"
        // aristaB.toString() -> " ayudaA (3)"
        // getStringAristas añade un espacio extra al final de cada una en el bucle:
        String resultadoEsperadoAristas = " atacaA (2)   ayudaA (3) ";

        if (vertice1.getStringAristas().equals(resultadoEsperadoAristas)) {
            System.out.println("Prueba 4 (addArista y getStringAristas): PASADA");
        } else {
            System.out.println("Prueba 4 (addArista y getStringAristas): FALLADA.");
            System.out.println("   Esperado: '" + resultadoEsperadoAristas + "'");
            System.out.println("   Obtenido: '" + vertice1.getStringAristas() + "'");
        }


        // 4. Pruebas del método compareTo()
        VerticeOBJ<String> verticeIdentico = new VerticeOBJ<>(1, "Mago"); // Mismo ID, diferente dato
        VerticeOBJ<String> verticeDiferente = new VerticeOBJ<>(2, "Guerrero"); // Diferente ID

        // Escenario A: Mismo ID -> debe devolver 0
        if (vertice1.compareTo(verticeIdentico) == 0) {
            System.out.println("Prueba 5 (compareTo - Mismo ID): PASADA");
        } else {
            System.out.println("Prueba 5 (compareTo - Mismo ID): FALLADA");
        }

        // Escenario B: Diferente ID -> según tu código actual, siempre devuelve -1 si no son iguales
        if (vertice1.compareTo(verticeDiferente) == -1) {
            System.out.println("Prueba 6 (compareTo - Diferente ID): PASADA");
        } else {
            System.out.println("Prueba 6 (compareTo - Diferente ID): FALLADA");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}