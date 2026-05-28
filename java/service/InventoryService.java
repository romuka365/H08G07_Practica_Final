package service;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.MyMatrix;
import model.*;

import java.util.Scanner;

public class InventoryService<T> {
    public void add(Player player, T object){
        ListaSE inventario = player.getInventory();
        inventario.add((Comparable) object);

    }

    public ListaSE checkObjects(GameState gameState, Room room) {
        ListaSE movements = new ListaSE();
        MyMatrix matriz = room.getMatrix();
        int filaPlayer = gameState.getFilaPlayer();
        int columnaPlayer = gameState.getColumnaPlayer();
        //Objeto arrriba
        if(matriz.posicionValida(filaPlayer-1,columnaPlayer)) {
            Cell cellComprobarArriba = room.getDato(filaPlayer-1,columnaPlayer);
            if(cellComprobarArriba.isOccupied() == true){
                movements.add("arriba");
            }
        }
        //Objeto abajo
        if(matriz.posicionValida(filaPlayer+1,columnaPlayer)) {
            Cell cellComprobarAbajo = room.getDato(filaPlayer+1,columnaPlayer);
            if(cellComprobarAbajo.isOccupied() == true){
                movements.add("abajo");
            }
        }
        //Objeto izquierda
        if(matriz.posicionValida(filaPlayer,columnaPlayer-1)) {
            Cell cellComprobarIzquierda = room.getDato(filaPlayer,columnaPlayer-1);
            if(cellComprobarIzquierda.isOccupied() == true){
                movements.add("izquierda");
            }
        }
        //Objeto derecha
        if(matriz.posicionValida(filaPlayer,columnaPlayer+1)) {
            Cell cellComprobarDerecha = room.getDato(filaPlayer,columnaPlayer+1);
            if(cellComprobarDerecha.isOccupied() == true){
                movements.add("derecha");
            }
        }
        if(movements.getSize() == 0) {
            movements.add("no hay movimientos");
        }
        return movements;
    }

    public void takeObject(Player jugador, ListaSE posibles, GameState gameState, Room habitacion) {
        Scanner scanner = new Scanner(System.in);
        String direccion = scanner.nextLine().toLowerCase().trim();
        //Veo si la opción es válida
        if(posibles.get(direccion) != null) {
            if(direccion.compareTo("arriba") == 0){
                Cell recoger = habitacion.getDato(gameState.getFilaPlayer()-1,gameState.getColumnaPlayer());
                T annadir = (T) recoger.getObjeto();
                add(jugador, annadir );
                recoger.removeObjeto(recoger.getObjeto()); //vaciamos la celda porque estamos cogiendo el objeto.
            }
            else if(direccion.compareTo("abajo") == 0){
                Cell recoger = habitacion.getDato(gameState.getFilaPlayer()+1,gameState.getColumnaPlayer());
                T annadir = (T) recoger.getObjeto();
                add(jugador, annadir );
                recoger.removeObjeto(recoger.getObjeto()); //vaciamos la celda porque estamos cogiendo el objeto.
            }
            else if(direccion.compareTo("izquierda") == 0){
                Cell recoger = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()-1);
                T annadir = (T) recoger.getObjeto();
                add(jugador, annadir );
                recoger.removeObjeto(recoger.getObjeto()); //vaciamos la celda porque estamos cogiendo el objeto.
            }
            else if(direccion.compareTo("derecha") == 0){
                Cell recoger = habitacion.getDato(gameState.getFilaPlayer(),gameState.getColumnaPlayer()+1);
                T annadir = (T) recoger.getObjeto();
                add(jugador, annadir );
                recoger.removeObjeto(recoger.getObjeto()); //vaciamos la celda porque estamos cogiendo el objeto.
            }
        }

    }
}
