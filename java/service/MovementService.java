package service;
import java.util.Random;
import java.util.Scanner;

import Estructuras.MyGraphOBJ.GrafoOBJ;
import Estructuras.MyLinkedList.*;
import Estructuras.MyMatrix.MyMatrix;
import model.*;

public class MovementService {
    /*
    public void openDoor(Player player, Key key,Door door){
        if(player.useKey(key, door)){
        }
    }
*/
    public ListaSE checkMovements(GameState gameState,Room room) {
        ListaSE movements = new ListaSE();
        MyMatrix matriz = room.getMatrix();
        int filaPlayer = gameState.getFilaPlayer();
        int columnaPlayer = gameState.getColumnaPlayer();
        //Mover arriba
        if(matriz.posicionValida(filaPlayer-1,columnaPlayer)) {
            Cell cellComprobarArriba = room.getDato(filaPlayer-1,columnaPlayer);
            if(cellComprobarArriba.isOccupied() == false){
                movements.add("arriba");
            }
        }
        //Mover abajo
        if(matriz.posicionValida(filaPlayer+1,columnaPlayer)) {
            Cell cellComprobarAbajo = room.getDato(filaPlayer+1,columnaPlayer);
            if(cellComprobarAbajo.isOccupied() == false){
                movements.add("abajo");
            }
        }
        //Mover izquierda
        if(matriz.posicionValida(filaPlayer,columnaPlayer-1)) {
            Cell cellComprobarIzquierda = room.getDato(filaPlayer,columnaPlayer-1);
            if(cellComprobarIzquierda.isOccupied() == false){
                movements.add("izquierda");
            }
        }
        //Mover derecha
        if(matriz.posicionValida(filaPlayer,columnaPlayer+1)) {
            Cell cellComprobarDerecha = room.getDato(filaPlayer,columnaPlayer+1);
            if(cellComprobarDerecha.isOccupied() == false){
                movements.add("derecha");
            }
        }
        return movements;
    }

    public void makeMove(Player jugador, ListaSE posibles, GameState gameState, Room habitacion) {
        Scanner scanner = new Scanner(System.in);
        String direccion = scanner.nextLine().toLowerCase().trim();
        //Veo si el movimiento es válido
        if(posibles.get(direccion) != null) {
            Cell estaba = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer());
            if(direccion.compareTo("arriba") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaPlayer()-1,gameState.getColumnaPlayer());
                gameState.setFilaPlayer(gameState.getFilaPlayer()-1);
                mover.addObjeto(jugador);
                estaba.removeObjeto(jugador);
            }
            else if(direccion.compareTo("abajo") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaPlayer()+1,gameState.getColumnaPlayer());
                gameState.setFilaPlayer(gameState.getFilaPlayer()+1);
                mover.addObjeto(jugador);
                estaba.removeObjeto(jugador);
            }
            else if(direccion.compareTo("izquierda") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()-1);
                gameState.setColumnaPlayer(gameState.getColumnaPlayer()-1);
                mover.addObjeto(jugador);
                estaba.removeObjeto(jugador);
            }
            else if(direccion.compareTo("derecha") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()+1);
                gameState.setColumnaPlayer(gameState.getColumnaPlayer()+1);
                mover.addObjeto(jugador);
                estaba.removeObjeto(jugador);
            }

        }

    }

    public ListaSE checkMovementsEnemy(GameState gameState,Room room) {
        ListaSE movements = new ListaSE();
        MyMatrix matriz = room.getMatrix();
        int filaEnemy = gameState.getFilaEnemy();
        int columnaEnemy = gameState.getColumnaEnemy();
        //Mover arriba
        if(matriz.posicionValida(filaEnemy-1,columnaEnemy)) {
            Cell cellComprobarArriba = room.getDato(filaEnemy-1,columnaEnemy);
            if(cellComprobarArriba.isOccupied() == false){
                movements.add("arriba");
            }
        }
        //Mover abajo
        if(matriz.posicionValida(filaEnemy+1,columnaEnemy)) {
            Cell cellComprobarAbajo = room.getDato(filaEnemy+1,columnaEnemy);
            if(cellComprobarAbajo.isOccupied() == false){
                movements.add("abajo");
            }
        }
        //Mover izquierda
        if(matriz.posicionValida(filaEnemy,columnaEnemy-1)) {
            Cell cellComprobarIzquierda = room.getDato(filaEnemy,columnaEnemy-1);
            if(cellComprobarIzquierda.isOccupied() == false){
                movements.add("izquierda");
            }
        }
        //Mover derecha
        if(matriz.posicionValida(filaEnemy,columnaEnemy+1)) {
            Cell cellComprobarDerecha = room.getDato(filaEnemy,columnaEnemy+1);
            if(cellComprobarDerecha.isOccupied() == false){
                movements.add("derecha");
            }
        }
        return movements;
    }

    public void makeMoveEnemy(Enemy enemy, ListaSE posibles, GameState gameState, Room habitacion) {
        Random random = new Random();
        //getSize() nos devolverá el tamaño. nextInt(x) dará de 0 a x-1, que coinciden con las posiciones de los elementos de la lista.
        int numeroAleatorio = random.nextInt(posibles.getSize()) + 1;
        ElementoSE<String> actual = posibles.getPrimero();
        for (int i = 0; i < numeroAleatorio; i++) {
            if (actual != null) {
                actual = actual.getSiguiente(); // Saltamos al siguiente nodo
            }
        }
        if (actual != null) {
            String direccion = actual.getDato();
            Cell estaba = habitacion.getDato(gameState.getFilaEnemy(),gameState.getColumnaEnemy());
            if(direccion.compareTo("arriba") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaEnemy()-1,gameState.getColumnaEnemy());
                gameState.setFilaEnemy(gameState.getFilaEnemy()-1);
                mover.addObjeto(enemy);
                estaba.removeObjeto(enemy);
            }
            else if(direccion.compareTo("abajo") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaEnemy()+1,gameState.getColumnaEnemy());
                gameState.setFilaEnemy(gameState.getFilaEnemy()+1);
                mover.addObjeto(enemy);
                estaba.removeObjeto(enemy);
            }
            else if(direccion.compareTo("izquierda") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaEnemy(),gameState.getColumnaEnemy()-1);
                gameState.setColumnaEnemy(gameState.getColumnaEnemy()-1);
                mover.addObjeto(enemy);
                estaba.removeObjeto(enemy);
            }
            else if(direccion.compareTo("derecha") == 0){
                Cell mover = habitacion.getDato(gameState.getFilaEnemy(),gameState.getColumnaEnemy()+1);
                gameState.setColumnaEnemy(gameState.getColumnaEnemy()+1);
                mover.addObjeto(enemy);
                estaba.removeObjeto(enemy);
            }

        }
    }

    public boolean checkDoor(GameState gameState,Room room) {
        boolean hayPuerta = false;
        MyMatrix matriz = room.getMatrix();
        int filaPlayer = gameState.getFilaPlayer();
        int columnaPlayer = gameState.getColumnaPlayer();
        //Ver si en la celda en la que estoy hay una puerta
            Cell cellComprobar = room.getDato(filaPlayer,columnaPlayer);
            return cellComprobar.hasDoor();
    }

    public void switchRoom(GameState gameState,Room room) {
        Cell estaba = room.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer());
        Door abierta = estaba.getDoor();
        gameState.setRoomPlayer(abierta.getIdHabitacionDestino());
        gameState.setFilaPlayer(abierta.getFilaDestino());
        gameState.setColumnaPlayer(abierta.getColumnaDestino());
        GrafoOBJ mapa = gameState.getMap();
        Room habitacionDestino = (Room) mapa.buscarDatoPorId(abierta.getIdHabitacionDestino());
        Cell mover = habitacionDestino.getDato(abierta.getFilaDestino(),abierta.getColumnaDestino());
            mover.addObjeto(gameState.getPlayer());
            estaba.removeObjeto(gameState.getPlayer());
    }

}
