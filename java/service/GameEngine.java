package service;
import model.*;

public class GameEngine {
    public boolean endOfGame(Player player, int turnos, GameState gameState) {
        if(player.isDead()){
            return true;
            //Perdido
        }
        else if (turnos <= 0) {
            return true;
            //Perdido
        }
        else if (gameState.getRoomPlayer() == null) {
            return true;
            //Ganado
        }
        return false;
    }
}
