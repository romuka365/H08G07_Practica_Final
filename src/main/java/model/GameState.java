package model;

public class GameState {
    private Room roomPlayer;
    private int filaPlayer;
    private int columnaPlayer;

    public void setRoomPlayer(Room roomPlayer) {
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
}
