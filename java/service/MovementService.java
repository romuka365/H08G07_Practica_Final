package service;
import java.util.Scanner;
import Estructuras.MyLinkedList.ListaSE;
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

}
