package app;

import Estructuras.MyLinkedList.ListaSE;
import model.*;
import persistence.GameLoader;
import service.GameEngine;
import service.InventoryService;
import service.MovementService;
import ui.PlayerPanelView;
import ui.RoomView;

import java.util.List;
import java.util.Scanner;

public class MainApp { //Ejecutable
    public static void main(String[] args) {
        Room habitacion = new Room(2,3);
        RoomView roomView = new RoomView();
        roomView.printRoom(habitacion);
        //CREACIÓN DE HABITACIÓN E IMPRESIÓN


        ListaSE inventario = new ListaSE();
        inventario.add("hola");
        Player jugador = new Player(false,5,2,3,900,1,inventario);
        PlayerPanelView ppv = new PlayerPanelView();
        ppv.printPlayerPanel(jugador);
        //CREACIÓN DE JUGADOR E IMPRESIÓN

        habitacion.fillCell(0,2, jugador);
        roomView.printRoom(habitacion);
        //INSERCIÓN DE JUGADOR EN HABITACIÓN E IMPRESIÓN

        Boots botas = new Boots("feet",2,false,false);
        habitacion.fillCell(1,1, botas);
        roomView.printRoom(habitacion);
        //INSERCIÓN DE BOTAS EN HABITACIÓN E IMPRESIÓN

        /*
        Quiero que un sitio aparte sepa constantemente la posición del jugador, entonces
        (asumo) que después de meterlo en la habitación por primera vez tendré que pasarle
        esos datos a GameState, ahora mismo lo estoy haciendo manualmente desde main, esta
        NO es la manera de hacerlo
        */
        int  turnos = 3;
        GameState gameState = new GameState();
        gameState.setRoomPlayer(habitacion);
        gameState.setFilaPlayer(0);
        gameState.setColumnaPlayer(2);
        GameEngine gameEngine = new GameEngine();
        MovementService movementService = new MovementService();
        InventoryService inventoryService = new InventoryService();
        ListaSE opcionesTurno = new ListaSE();
        opcionesTurno.add("recoger");
        opcionesTurno.add("mover");
        while(!gameEngine.endOfGame(jugador, turnos, gameState)){
            System.out.println("Te quedan "+turnos+" turnos");
            int apartadoTurno = 2;
            while(apartadoTurno >0){
                System.out.println("¿Qué quieres hacer?");
                System.out.println(opcionesTurno.toString());
                Scanner scanner = new Scanner(System.in);
                String eleccion = scanner.nextLine().toLowerCase().trim();
                if(opcionesTurno.get(eleccion) == null){
                    System.out.println("No existe el opcion");
                }
                else{
                    if (eleccion.compareTo("recoger") == 0){
                        ListaSE opcionesPosibles = inventoryService.checkObjects(gameState,habitacion);
                        System.out.println(opcionesPosibles);
                        inventoryService.takeObject(jugador,opcionesPosibles,gameState,habitacion);
                        roomView.printRoom(habitacion);
                        apartadoTurno-= 1;
                        System.out.println(jugador.getInventory());

                    }
                    else if (eleccion.compareTo("mover") == 0){
                        ListaSE movimientosPosibles = movementService.checkMovements(gameState,habitacion);
                        System.out.println(movimientosPosibles);
                        movementService.makeMove(jugador, movimientosPosibles,gameState,habitacion);
                        roomView.printRoom(habitacion);
                        apartadoTurno-= 1;
                    }
                }

            }
            turnos -=1;
        }




    }
}
