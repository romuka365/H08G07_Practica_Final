package persistence;

import Estructuras.MyGraphOBJ.GrafoOBJ;
import model.*;
import Estructuras.MyLinkedList.*;
public class GameLoader {
    //Primero load de grafo, luego de GameState
    public GameState loadGameState(GameState gameState){ //METER TMB COMO PARÁMETRO EL MAPA RELLENO

        //Aquí leemos el JSON y creamos al jugador, lo posicionamos en el tablero, generamos todos los enemigos y también los ponemos en el tablero
        //También establecemos el número de turnos totales
        GrafoOBJ mapa = new GrafoOBJ();

        Room habitacion1 = new Room(1,3,3);
        mapa.addVertice(1,habitacion1);

        Room habitacion2 = new Room(2,4,4);
        mapa.addVertice(2,habitacion2);

        mapa.addArista(1,habitacion1,2,habitacion2,"1-2");
        mapa.addArista(2,habitacion2,1,habitacion1,"2-1");
        Door door12 = new Door(0,0,2); //DESTINO D12
        Door door21 = new Door(2,2,1);

        Cell celdaPuertaH12 = habitacion1.getDato(2,2);
        celdaPuertaH12.addDoor(door12);

        Cell celdaPuertaH21 = habitacion2.getDato(0,0);
        celdaPuertaH21.addDoor(door21);

        Room habitacion3 = new Room(3,4,4);
        mapa.addVertice(3,habitacion3);

        mapa.addArista(2,habitacion2,3,habitacion3,"2-3");
        mapa.addArista(3,habitacion3,2,habitacion2,"3-2");
        Door door23 = new Door(0,0,3);
        Door door32 = new Door(0,3,2);

        Cell celdaPuertaH23 = habitacion2.getDato(0,3);
        celdaPuertaH23.addDoor(door23);

        Cell celdaPuertaH32 = habitacion3.getDato(0,0);
        celdaPuertaH32.addDoor(door32);


        gameState.setMap(mapa);

        ListaSE inventario = new ListaSE();
        Player jugador = new Player(false,5,2,3,900,1,inventario);


        Enemy enemy = new Enemy(false,4, 1, 2);

        gameState.setTurnos(7);

        gameState.setPlayer(jugador);
        gameState.setRoomPlayer(1); //ID
        gameState.setFilaPlayer(0); //coord
        gameState.setColumnaPlayer(2); //coord

        gameState.setEnemy(enemy);
        gameState.setRoomEnemy(1);
        gameState.setFilaEnemy(2);
        gameState.setColumnaEnemy(0);

        return gameState;


    }
}
