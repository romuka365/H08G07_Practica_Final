package model;

public class RoomTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE ROOM ---\n");

        Room<String> habitacion = new Room<>(10, 3, 4);

        if (habitacion.getId() == 10 && habitacion.getFilas() == 3 && habitacion.getColumnas() == 4) {
            System.out.println("Prueba 1 (Atributos de dimensiones e ID): PASADA");
        } else {
            System.out.println("Prueba 1 (Atributos de dimensiones e ID): FALLADA");
        }

        if (habitacion.getMatrix() != null) {
            System.out.println("Prueba 2 (Inicialización de MyMatrix): PASADA");
        } else {
            System.out.println("Prueba 2 (Inicialización de MyMatrix): FALLADA");
        }


        Cell celdaEsquina = habitacion.getDato(0, 0);
        Cell celdaCentro = habitacion.getDato(1, 2);

        if (celdaEsquina != null && !celdaEsquina.isOccupied() && celdaCentro != null && !celdaCentro.isOccupied()) {
            System.out.println("Prueba 3 (Inicialización automática de Cell en la matriz): PASADA");
        } else {
            System.out.println("Prueba 3 (Inicialización automática de Cell en la matriz): FALLADA");
        }


        habitacion.setId(99);
        if (habitacion.getId() == 99) {
            System.out.println("Prueba 4 (Método setId): PASADA");
        } else {
            System.out.println("Prueba 4 (Método setId): FALLADA. Se obtuvo: " + habitacion.getId());
        }


        habitacion.fillCell(2, 1, "Cofre Oculto");

        Cell celdaModificada = habitacion.getDato(2, 1);

        if (celdaModificada != null && celdaModificada.isOccupied() && "Cofre Oculto".equals(celdaModificada.getObjeto())) {
            System.out.println("Prueba 5 (Método fillCell y lectura getDato): PASADA");
        } else {
            System.out.println("Prueba 5 (Método fillCell y lectura getDato): FALLADA.");
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}