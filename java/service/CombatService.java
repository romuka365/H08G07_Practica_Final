package service;

import model.Enemy;
import model.Player;

public class CombatService {
    public void dealAtk(Player attacker, Enemy attacked){
        int atk = attacker.getAtk();
        attacked.receiveAtk(atk);
    }

    public void dealAtk(Enemy attacker, Player attacked){
        int atk = attacker.getAtk();
        attacked.receiveAtk(atk);
    }
}
