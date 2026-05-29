package model;

class CellTest {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE CELL ---\n");

        Cell<String> celda = new Cell<>();

        if (!celda.isOccupied() && celda.getObjeto() == null && !celda.hasDoor() && celda.getDoor() == null) {
            System.out.println("Prueba 1 (Estado inicial vacío): PASADA");
        } else {
            System.out.println("Prueba 1 (Estado inicial vacío): FALLADA");
        }

        celda.addObjeto("Tesoro");
        if (celda.isOccupied() && celda.getObjeto().equals("Tesoro")) {
            System.out.println("Prueba 2 (Añadir objeto): PASADA");
        } else {
            System.out.println("Prueba 2 (Añadir objeto): FALLADA");
        }

        if (celda.toString().equals("Tesoro")) {
            System.out.println("Prueba 3 (Método toString): PASADA");
        } else {
            System.out.println("Prueba 3 (Método toString): FALLADA");
        }

        Door puertaSimulada = new Door(1,1,1);
        celda.addDoor(puertaSimulada);

        if (celda.hasDoor() && celda.getDoor() == puertaSimulada) {
            System.out.println("Prueba 4 (Añadir y obtener puerta): PASADA");
        } else {
            System.out.println("Prueba 4 (Añadir y obtener puerta): FALLADA");
        }


        celda.removeObjeto(null);
        if (!celda.isOccupied() && celda.getObjeto() == null) {
            System.out.println("Prueba 5 (Remover objeto): PASADA");
        } else {
            System.out.println("Prueba 5 (Remover objeto): FALLADA. ¿Ocupado?: " + celda.isOccupied() + ", Objeto: " + celda.getObjeto());
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }

}