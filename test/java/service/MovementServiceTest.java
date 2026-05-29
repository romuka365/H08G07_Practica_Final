package service;

import Estructuras.MyGraphOBJ.GrafoOBJ;
import Estructuras.MyLinkedList.ListaSE;
import model.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MovementServiceTest {

    public static void main(String[] args) {
        System.out.println("--- INICIANDO PRUEBAS DE LA CLASE MOVEMENTSERVICE ---\n");

        MovementService servicioMovimiento = new MovementService();

        Player jugador = new Player<>();
        Enemy enemigo = new Enemy();
        Room<Object> habitacionActual = new Room<>(1, 3, 3);

        GameState estadoJuego = new GameState();
        GrafoOBJ mapaSimulado = new GrafoOBJ<>();
        mapaSimulado.addVertice(1, habitacionActual);
        estadoJuego.setMap(mapaSimulado);

        estadoJuego.setFilaPlayer(1);
        estadoJuego.setColumnaPlayer(1);
        habitacionActual.fillCell(1, 1, jugador);

        estadoJuego.setFilaEnemy(2);
        estadoJuego.setColumnaEnemy(2);
        habitacionActual.fillCell(2, 2, enemigo);


        ListaSE movimientosJugador = servicioMovimiento.checkMovements(estadoJuego, habitacionActual);

        if (movimientosJugador.getSize() > 0) {
            System.out.println("Prueba 1 (checkMovements - Jugador calcula celdas libres): PASADA");
        } else {
            System.out.println("Prueba 1 (checkMovements - Jugador calcula celdas libres): FALLADA");
        }


        InputStream entradaOriginal = System.in;
        String direccionSimulada = "arriba\n"; // Ordenamos mover hacia la fila 0
        System.setIn(new ByteArrayInputStream(direccionSimulada.getBytes()));

        try {
            servicioMovimiento.makeMove(jugador, movimientosJugador, estadoJuego, habitacionActual);

            Cell celdaAntigua = habitacionActual.getDato(1, 1);
            Cell celdaNueva = habitacionActual.getDato(0, 1);

            if (estadoJuego.getFilaPlayer() == 0 && !celdaAntigua.isOccupied() && celdaNueva.isOccupied()) {
                System.out.println("Prueba 2 (makeMove - Jugador cambia de celda y actualiza coordenadas): PASADA");
            } else {
                System.out.println("Prueba 2 (makeMove - Jugador cambia de celda y actualiza coordenadas): FALLADA");
            }
        } catch (Exception e) {
            System.out.println("Prueba 2 (makeMove): FALLADA por excepción: " + e.getMessage());
        } finally {
            System.setIn(entradaOriginal);
        }


        ListaSE movimientosEnemigo = servicioMovimiento.checkMovementsEnemy(estadoJuego, habitacionActual);

        if (movimientosEnemigo.getSize() > 0) {
            System.out.println("Prueba 3 (checkMovementsEnemy - Enemigo calcula celdas libres): PASADA");
        } else {
            System.out.println("Prueba 3 (checkMovementsEnemy - Enemigo calcula celdas libres): FALLADA");
        }

        try {

            servicioMovimiento.makeMoveEnemy(enemigo, movimientosEnemigo, estadoJuego, habitacionActual);
            System.out.println("Prueba 4 (makeMoveEnemy - Movimiento aleatorio ejecutado): PASADA");
        } catch (Exception e) {
            System.out.println("Prueba 4 (makeMoveEnemy): FALLADA por excepción: " + e.getMessage());
        }


        estadoJuego.setFilaPlayer(0);
        estadoJuego.setColumnaPlayer(0);

        Door puerta = new Door(1, 1, 2);
        Cell celdaConPuerta = habitacionActual.getDato(0, 0);
        celdaConPuerta.addDoor(puerta);
        celdaConPuerta.addObjeto(jugador);

        Room<Object> habitacionDestino = new Room<>(2, 3, 3);
        mapaSimulado.addVertice(2, habitacionDestino);

        if (servicioMovimiento.checkDoor(estadoJuego, habitacionActual)) {
            System.out.println("Prueba 5 (checkDoor - Detectar puerta bajo los pies): PASADA");
        } else {
            System.out.println("Prueba 5 (checkDoor - Detectar puerta bajo los pies): FALLADA");
        }

        try {
            servicioMovimiento.switchRoom(estadoJuego, habitacionActual);

            Cell celdaDestinoDePuerta = habitacionDestino.getDato(1, 1);

            if (estadoJuego.getRoomPlayer() == habitacionDestino &&
                    estadoJuego.getFilaPlayer() == 1 &&
                    estadoJuego.getColumnaPlayer() == 1 &&
                    celdaDestinoDePuerta.isOccupied()) {
                System.out.println("Prueba 6 (switchRoom - Viaje entre habitaciones por grafo): PASADA");
            } else {
                System.out.println("Prueba 6 (switchRoom - Viaje entre habitaciones por grafo): FALLADA");
            }
        } catch (Exception e) {
            System.out.println("Prueba 6 (switchRoom): FALLADA por excepción: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- PRUEBAS FINALIZADAS ---");
    }
}