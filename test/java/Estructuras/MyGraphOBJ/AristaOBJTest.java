package Estructuras.MyGraphOBJ;


public class AristaOBJTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE ARISTAOBJ ---\n");

        AristaOBJ arista1 = new AristaOBJ(5, "atacaA");

        if (arista1.getIdDestino() == 5) {
            System.out.println("Prueba 1 (Get IdDestino): PASADA");
        } else {
            System.out.println("Prueba 1 (Get IdDestino): FALLADA. Se esperaba 5.");
        }

        if (arista1.getPredicado().equals("atacaA")) {
            System.out.println("Prueba 2 (Get Predicado): PASADA");
        } else {
            System.out.println("Prueba 2 (Get Predicado): FALLADA. Se esperaba 'atacaA'.");
        }




        AristaOBJ aristaIdentica = new AristaOBJ(10, "atacaA"); // Mismo predicado, diferente ID
        AristaOBJ aristaMenor = new AristaOBJ(2, "ayudaA");    // "ayudaA" va después de "atacaA" alfabéticamente
        AristaOBJ aristaMayor = new AristaOBJ(1, "abre");      // "abre" va antes de "atacaA" alfabéticamente


        if (arista1.compareTo(aristaIdentica) == 0) {
            System.out.println("Prueba 4 (compareTo - Mismo predicado): PASADA");
        } else {
            System.out.println("Prueba 4 (compareTo - Mismo predicado): FALLADA");
        }


        if (arista1.compareTo(aristaMenor) < 0) {
            System.out.println("Prueba 5 (compareTo - Menor alfabéticamente): PASADA");
        } else {
            System.out.println("Prueba 5 (compareTo - Menor alfabéticamente): FALLADA");
        }


        if (arista1.compareTo(aristaMayor) > 0) {
            System.out.println("Prueba 6 (compareTo - Mayor alfabéticamente): PASADA");
        } else {
            System.out.println("Prueba 6 (compareTo - Mayor alfabéticamente): FALLADA");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}