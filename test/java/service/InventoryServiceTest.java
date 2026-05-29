package service;

import Estructuras.MyGraphOBJ.GrafoOBJ;
import Estructuras.MyLinkedList.ListaSE;
import model.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class InventoryServiceTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE INVENTORYSERVICE ---\n");

        InventoryService servicioInventario = new InventoryService<>();

        Player jugador = new Player<>();
        Room<Object> habitacion = new Room<>(1, 3, 3);
        GameState estado = new GameState();

        GrafoOBJ mapaSimulado = new GrafoOBJ<>();
        mapaSimulado.addVertice(1, habitacion);
        estado.setMap(mapaSimulado);

        estado.setFilaPlayer(1);
        estado.setColumnaPlayer(1);
        habitacion.fillCell(1, 1, jugador);

        Boots botasItem = new Boots();
        estado.setFilaEnemy(2);
        habitacion.fillCell(2, 1, botasItem);


        ListaSE objetosDisponibles = servicioInventario.checkObjects(estado, habitacion);

        if (objetosDisponibles.getSize() > 0 && !objetosDisponibles.getPrimero().getDato().equals("no hay objetos")) {
            System.out.println("Prueba 1 (checkObjects - Detectar objeto adyacente): PASADA");
        } else {
            System.out.println("Prueba 1 (checkObjects - Detectar objeto adyacente): FALLADA");
        }


        Boots botasRegalo = new Boots("feet", 3, false, true);
        servicioInventario.add(jugador, botasRegalo);

        if (jugador.getInventory().getSize() == 1) {
            System.out.println("Prueba 2 (add - Inserción directa en inventario): PASADA");
        } else {
            System.out.println("Prueba 2 (add - Inserción directa en inventario): FALLADA");
        }


        InputStream entradaOriginal = System.in;

        String comandoSimulado = "abajo\n";
        System.setIn(new ByteArrayInputStream(comandoSimulado.getBytes()));

        try {
            servicioInventario.takeObject(jugador, objetosDisponibles, estado, habitacion);

            Cell celdaVaciada = habitacion.getDato(2, 1);

            if (jugador.getInventory().getSize() == 2 && !celdaVaciada.isOccupied()) {
                System.out.println("Prueba 3 (takeObject - Flujo de Scanner y limpieza de celda): PASADA");
            } else {
                System.out.println("Prueba 3 (takeObject - Flujo de Scanner y limpieza de celda): FALLADA.");
                System.out.println("   Tamaño inventario: " + jugador.getInventory().getSize());
                System.out.println("   ¿Celda ocupada?: " + celdaVaciada.isOccupied());
            }

        } catch (Exception e) {
            System.out.println("Prueba 3 (takeObject): FALLADA por excepción de código: " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.setIn(entradaOriginal);
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}