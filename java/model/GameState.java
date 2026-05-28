package model;
import Estructuras.MyGraphOBJ.*;

public class GameState {
    private Player player;
    private Enemy enemy;
    private Room roomPlayer;
    private int filaPlayer;
    private int columnaPlayer;
    private Room roomEnemy;
    private int filaEnemy;
    private int columnaEnemy;
    private int turnos;
    private GrafoOBJ map;

    public void setMap(GrafoOBJ map) {
        this.map = map;
    }

    public GrafoOBJ getMap() {
        return map;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }

    public int getTurnos() {
        return turnos;
    }

    public void setRoomPlayer(int id) {
        VerticeOBJ vertice = map.getVertice(id);
        Room roomPlayer = (Room) vertice.getDato(); //Obtengo la habitación
        this.roomPlayer = roomPlayer;
    }

    public void setFilaPlayer(int filaPlayer) {
        this.filaPlayer = filaPlayer;
    }

    public void setColumnaPlayer(int columnaPlayer) {
        this.columnaPlayer = columnaPlayer;
    }

    public Room getRoomPlayer() {
        return roomPlayer;
    }
    public int getFilaPlayer() {
        return filaPlayer;
    }
    public int getColumnaPlayer() {
        return columnaPlayer;
    }

    public void setRoomEnemy(int id)
    {
        VerticeOBJ vertice = map.getVertice(id);
        Room roomEnemy = (Room) vertice.getDato(); //Obtengo la habitación
        this.roomEnemy = roomEnemy;
    }

    public void setFilaEnemy(int filaEnemy) {
        this.filaEnemy = filaEnemy;
    }

    public void setColumnaEnemy(int columnaEnemy) {
        this.columnaEnemy = columnaEnemy;
    }

    public Room getRoomEnemy() {
        return roomEnemy;
    }
    public int getFilaEnemy() {
        return filaEnemy;
    }
    public int getColumnaEnemy() {
        return columnaEnemy;
    }

}
