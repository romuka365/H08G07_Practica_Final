package model;


class DoorTest {
    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE DOOR ---\n");


        Door puerta = new Door(3, 4, 10);

        if (puerta.getFilaDestino() == 3) {
            System.out.println("Prueba 1 (Get Fila inicial): PASADA");
        } else {
            System.out.println("Prueba 1 (Get Fila inicial): FALLADA. Se esperaba 3.");
        }

        if (puerta.getColumnaDestino() == 4) {
            System.out.println("Prueba 2 (Get Columna inicial): PASADA");
        } else {
            System.out.println("Prueba 2 (Get Columna inicial): FALLADA. Se esperaba 4.");
        }

        if (puerta.getIdHabitacionDestino() == 10) {
            System.out.println("Prueba 3 (Get ID Habitación inicial): PASADA");
        } else {
            System.out.println("Prueba 3 (Get ID Habitación inicial): FALLADA. Se esperaba 10.");
        }


        puerta.setFilaDestino(5);
        puerta.setColumnaDestino(8);
        puerta.setIdHabitacionDestino(22);


        if (puerta.getFilaDestino() == 5) {
            System.out.println("Prueba 4 (Set Fila destino): PASADA");
        } else {
            System.out.println("Prueba 4 (Set Fila destino): FALLADA. Se esperaba 5.");
        }

        if (puerta.getColumnaDestino() == 8) {
            System.out.println("Prueba 5 (Set Columna destino): PASADA");
        } else {
            System.out.println("Prueba 5 (Set Columna destino): FALLADA. Se esperaba 8.");
        }

        if (puerta.getIdHabitacionDestino() == 22) {
            System.out.println("Prueba 6 (Set ID Habitación destino): PASADA");
        } else {
            System.out.println("Prueba 6 (Set ID Habitación destino): FALLADA. Se esperaba 22.");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }

}