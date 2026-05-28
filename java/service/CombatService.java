package service;

import Estructuras.MyLinkedList.ElementoSE;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.MyMatrix;
import model.*;

import java.util.Scanner;

public class CombatService {
    public void dealAtk(Player attacker, Enemy attacked){
        int atk = attacker.getAtk();
        attacked.receiveAtk(atk);
    }

    public void dealAtk(Enemy attacker, Player attacked){
        int atk = attacker.getAtk();
        attacked.receiveAtk(atk);
    }

    public ListaSE checkAttacks(GameState gameState, Room room) {
        ListaSE movements = new ListaSE();
        MyMatrix matriz = room.getMatrix();
        int filaPlayer = gameState.getFilaPlayer();
        int columnaPlayer = gameState.getColumnaPlayer();
        //Enemigo arriba
        if (matriz.posicionValida(filaPlayer - 1, columnaPlayer)) {
            Cell cellComprobarArriba = room.getDato(filaPlayer - 1, columnaPlayer);
            if (cellComprobarArriba.isOccupied() == true && cellComprobarArriba.getObjeto() instanceof Enemy) {
                movements.add("arriba");
            }
        }
        //Enemigo abajo
        if (matriz.posicionValida(filaPlayer + 1, columnaPlayer)) {
            Cell cellComprobarAbajo = room.getDato(filaPlayer + 1, columnaPlayer);
            if (cellComprobarAbajo.isOccupied() == true && cellComprobarAbajo.getObjeto() instanceof Enemy) {
                movements.add("abajo");
            }
        }
        //Enemigo izquierda
        if (matriz.posicionValida(filaPlayer, columnaPlayer - 1)) {
            Cell cellComprobarIzquierda = room.getDato(filaPlayer, columnaPlayer - 1);
            if (cellComprobarIzquierda.isOccupied() == true && cellComprobarIzquierda.getObjeto() instanceof Enemy) {
                movements.add("izquierda");
            }
        }
        //Enemigo derecha
        if (matriz.posicionValida(filaPlayer, columnaPlayer + 1)) {
            Cell cellComprobarDerecha = room.getDato(filaPlayer, columnaPlayer + 1);
            if (cellComprobarDerecha.isOccupied() == true && cellComprobarDerecha.getObjeto() instanceof Enemy) {
                movements.add("derecha");
            }
        }
        if (movements.getSize() == 0) {
            movements.add("no hay enemigos");
        }
        return movements;
    }

    public void attackEnemy(Player jugador, ListaSE posibles, GameState gameState, Room habitacion) {
        Scanner scanner = new Scanner(System.in);
        String direccion = scanner.nextLine().toLowerCase().trim();
        //Veo si la opción es válida
        if(posibles.get(direccion) != null) {
            if(direccion.compareTo("arriba") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaPlayer()-1,gameState.getColumnaPlayer());
                Enemy victima = (Enemy) atacar.getObjeto();
                dealAtk(jugador, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("abajo") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaPlayer()+1,gameState.getColumnaPlayer());
                Enemy victima = (Enemy) atacar.getObjeto();
                dealAtk(jugador, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("izquierda") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()-1);
                Enemy victima = (Enemy) atacar.getObjeto();
                dealAtk(jugador, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("derecha") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()+1);
                Enemy victima = (Enemy) atacar.getObjeto();
                dealAtk(jugador, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
        }

    }

    public ListaSE checkAttacksEnemy(GameState gameState, Room room) {
        ListaSE movements = new ListaSE();
        MyMatrix matriz = room.getMatrix();
        int filaEnemy = gameState.getFilaEnemy();
        int columnaEnemy = gameState.getColumnaEnemy();
        //Enemigo arriba
        if (matriz.posicionValida(filaEnemy - 1, columnaEnemy)) {
            Cell cellComprobarArriba = room.getDato(filaEnemy - 1, columnaEnemy);
            if (cellComprobarArriba.isOccupied() == true && cellComprobarArriba.getObjeto() instanceof Player) {
                movements.add("arriba");
            }
        }
        //Enemigo abajo
        if (matriz.posicionValida(filaEnemy + 1, columnaEnemy)) {
            Cell cellComprobarAbajo = room.getDato(filaEnemy + 1, columnaEnemy);
            if (cellComprobarAbajo.isOccupied() == true && cellComprobarAbajo.getObjeto() instanceof Player) {
                movements.add("abajo");
            }
        }
        //Enemigo izquierda
        if (matriz.posicionValida(filaEnemy, columnaEnemy - 1)) {
            Cell cellComprobarIzquierda = room.getDato(filaEnemy, columnaEnemy - 1);
            if (cellComprobarIzquierda.isOccupied() == true && cellComprobarIzquierda.getObjeto() instanceof Player) {
                movements.add("izquierda");
            }
        }
        //Enemigo derecha
        if (matriz.posicionValida(filaEnemy, columnaEnemy + 1)) {
            Cell cellComprobarDerecha = room.getDato(filaEnemy, columnaEnemy + 1);
            if (cellComprobarDerecha.isOccupied() == true && cellComprobarDerecha.getObjeto() instanceof Player) {
                movements.add("derecha");
            }
        }
        return movements;
    }

    public void attackPlayer(Enemy enemy, ListaSE posibles, GameState gameState, Room habitacion) {
        ElementoSE<String> cabeza = posibles.getPrimero();
        String direccion = cabeza.getDato();
        //Veo si la opción es válida
        if(posibles.get(direccion) != null) {
            if(direccion.compareTo("arriba") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaEnemy()-1,gameState.getColumnaEnemy());
                Player victima = (Player) atacar.getObjeto();
                dealAtk(enemy, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("abajo") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaEnemy()+1,gameState.getColumnaEnemy());
                Player victima = (Player) atacar.getObjeto();
                dealAtk(enemy, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("izquierda") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaEnemy(),gameState.getColumnaEnemy()-1);
                Player victima = (Player) atacar.getObjeto();
                dealAtk(enemy, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
            else if(direccion.compareTo("derecha") == 0){
                Cell atacar = habitacion.getDato(gameState.getFilaEnemy(),gameState.getColumnaEnemy()+1);
                Player victima = (Player) atacar.getObjeto();
                dealAtk(enemy, victima);
                if(victima.isDead()){
                    atacar.removeObjeto(victima);
                }
            }
        }

    }
}
