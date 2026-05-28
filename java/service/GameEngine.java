package service;
import model.*;

public class GameEngine {
    public boolean endOfGame(Player player, int turnos, GameState gameState) {
        if(player.getDeath()){
            return true;
        }
        else if (turnos <= 0) {
            return true;
        }
        else if (gameState.getRoomPlayer() == null) {
            return true;
        }
        return false;
    }
}
