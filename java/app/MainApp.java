package app;

import Estructuras.MyLinkedList.ListaSE;
import model.*;
import persistence.GameLoader;
import service.CombatService;
import service.GameEngine;
import service.InventoryService;
import service.MovementService;
import ui.PlayerPanelView;
import ui.RoomView;

import java.util.List;
import java.util.Scanner;

public class MainApp { //Ejecutable
    public static void main(String[] args) {
        GameState gameStateInicializar = new GameState();
        GameLoader gameLoader = new GameLoader();
        GameState gameState = gameLoader.loadGameState(gameStateInicializar);

        Player jugador = gameState.getPlayer();
        Enemy enemy = gameState.getEnemy();
        Room habitacionActual = gameState.getRoomPlayer();
        habitacionActual.fillCell(gameState.getFilaPlayer(), gameState.getColumnaPlayer(), gameState.getPlayer());
        habitacionActual.fillCell(gameState.getFilaEnemy(), gameState.getColumnaEnemy(), gameState.getEnemy());


        RoomView roomView = new RoomView();
        roomView.printRoom(habitacionActual);




        /*
        Quiero que un sitio aparte sepa constantemente la posición del jugador, entonces
        (asumo) que después de meterlo en la habitación por primera vez tendré que pasarle
        esos datos a GameState, ahora mismo lo estoy haciendo manualmente desde main, esta
        NO es la manera de hacerlo
        */

        GameEngine gameEngine = new GameEngine();
        MovementService movementService = new MovementService();
        InventoryService inventoryService = new InventoryService();
        CombatService combatService = new CombatService();
        ListaSE opcionesTurno = new ListaSE();
        opcionesTurno.add("recoger");
        opcionesTurno.add("mover");
        opcionesTurno.add("atacar");
        opcionesTurno.add("equipar");
        while (!gameEngine.endOfGame(gameState.getPlayer(), gameState.getTurnos(), gameState)) {
            System.out.println("Te quedan " + gameState.getTurnos() + " turnos.");
            int apartadoTurno = 2;
            while (apartadoTurno > 0) {
                System.out.println("Puedes hacer " + apartadoTurno + " movimientos más antes de terminar el turno.");
                System.out.println("¿Qué quieres hacer?");
                if (movementService.checkDoor(gameState, habitacionActual)) {
                    opcionesTurno.add("abrir");
                }
                System.out.println(opcionesTurno.toString());
                Scanner scanner = new Scanner(System.in);
                String eleccion = scanner.nextLine().toLowerCase().trim();
                if (opcionesTurno.get(eleccion) == null) {
                    System.out.println("No existe esa opción.");
                } else {
                    if (eleccion.compareTo("recoger") == 0) {
                        ListaSE opcionesPosibles = inventoryService.checkObjects(gameState, habitacionActual);
                        System.out.println(opcionesPosibles);
                        inventoryService.takeObject(jugador, opcionesPosibles, gameState, habitacionActual);
                        roomView.printRoom(habitacionActual);
                        apartadoTurno -= 1;
                        System.out.println(jugador.getInventory());

                    } else if (eleccion.compareTo("mover") == 0) {
                        ListaSE movimientosPosibles = movementService.checkMovements(gameState, habitacionActual);
                        System.out.println(movimientosPosibles);
                        movementService.makeMove(jugador, movimientosPosibles, gameState, habitacionActual);
                        roomView.printRoom(habitacionActual);
                        apartadoTurno -= 1;
                    } else if (eleccion.compareTo("atacar") == 0) {
                        ListaSE ataquesPosibles = combatService.checkAttacks(gameState, habitacionActual);
                        System.out.println(ataquesPosibles);
                        combatService.attackEnemy(jugador, ataquesPosibles, gameState, habitacionActual);
                        roomView.printRoom(habitacionActual);
                        apartadoTurno -= 1;
                    } else if (eleccion.compareTo("abrir") == 0) {
                        movementService.switchRoom(gameState, habitacionActual);
                        habitacionActual = gameState.getRoomPlayer();
                        roomView.printRoom(habitacionActual);
                        apartadoTurno -= 2; //Termino el turno inmediatamente
                    }
                }
                opcionesTurno.del("abrir");
            }

            ListaSE ataquesPosiblesEnemigo = combatService.checkAttacksEnemy(gameState, habitacionActual);
            ListaSE movimientosPosiblesEnemigo = movementService.checkMovementsEnemy(gameState, habitacionActual);
            if (ataquesPosiblesEnemigo.getSize() == 0) { //Si el enemigo NO puede atacar al jugador
                //Primero se mueve y luego intenta volver a atacar
                if (movimientosPosiblesEnemigo.getSize() != 0) {
                    System.out.println("El enemigo se puede mover a " + movimientosPosiblesEnemigo);
                    movementService.makeMoveEnemy(enemy, movimientosPosiblesEnemigo, gameState, habitacionActual);
                    roomView.printRoom(habitacionActual);
                    System.out.println("El enemigo se ha desplazado");
                    ataquesPosiblesEnemigo = combatService.checkAttacksEnemy(gameState, habitacionActual); //la rehacemos porque el enemigo se ha movido
                    if (ataquesPosiblesEnemigo.getSize() != 0) {
                        System.out.println("El enemigo puede atacar a " + ataquesPosiblesEnemigo);
                        combatService.attackPlayer(enemy, ataquesPosiblesEnemigo, gameState, habitacionActual);
                        roomView.printRoom(habitacionActual);
                        System.out.println("El enemigo ha atacado");
                    } else {
                        System.out.println("El enemigo no ataca este turno");
                    }
                } else {
                    System.out.println("El enemigo no se mueve este turno");
                }

            } else { //Si puede atacar, lo hace, y luego se mueve
                System.out.println("El enemigo puede atacar a " + ataquesPosiblesEnemigo);
                combatService.attackPlayer(enemy, ataquesPosiblesEnemigo, gameState, habitacionActual);
                roomView.printRoom(habitacionActual);
                if (movimientosPosiblesEnemigo.getSize() != 0) {
                    System.out.println("El enemigo se puede mover a " + movimientosPosiblesEnemigo);
                    movementService.makeMoveEnemy(enemy, movimientosPosiblesEnemigo, gameState, habitacionActual);
                    roomView.printRoom(habitacionActual);
                } else {
                    System.out.println("El enemigo no se mueve esta ronda");
                }

            }
            gameState.setTurnos(gameState.getTurnos() - 1);
            //Aquí ya han jugado el turno el jugador y el enemigo
        }
        if (gameState.getPlayer().isDead() || gameState.getTurnos()<= 0){
            System.out.println("Has perdido");
        }
        else {
            System.out.println("Has ganado");
        }



    }


}
