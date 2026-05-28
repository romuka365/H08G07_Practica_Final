package ui;

import model.*;

public class PlayerPanelView //VISUAL
{
    public void printPlayerPanel(Player player)
    {
        System.out.println(player.getDeath());
        System.out.println(player.getMaxHealth());
        System.out.println(player.getCurrentHealth());
        System.out.println(player.getDef());
        System.out.println(player.getAtk());
        System.out.println(player.getMoney());
        System.out.println(player.getMov());
        System.out.println(player.getInventory());
    }
}
