package model;//Tema de equipaor cosas ?? Tiene q tener partes del cuerpo o es a la hora de equipar que se mira??
//Cada objeto tiene q tener tmb su posición? idk
import Estructuras.MyLinkedList.*;
import java.util.Random;

public class Player<T extends Comparable<T>> {
    private Boolean death;
    private int currentHealth; //dos tipos de salud porque esta la puede restaurar pero sin pasarse del máximo
    private int maxHealth;
    private int def;
    private int atk;
    private int money;
    private int mov;
    private ListaSE<T> inventory;
    private ListaSE<T> equipped;
    public Player() //Generador para cuando se crea uno estándar
    {
        death = false;
        maxHealth = 10;
        currentHealth = maxHealth;
        def = 3;
        atk = 5;
        money = 0;
        mov = 1;
        inventory = new ListaSE<>();
    }

    public Player(Boolean death, int maxHealth, int def, int atk, int money, int mov, ListaSE<T> inventory)
    {
        this.death = death;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
        this.money = money;
        this.mov = mov;
        this.inventory = inventory;
    }

    public int getCurrentHealth()
    {
        return currentHealth;

    }

    public int  getMaxHealth()
    {
        return maxHealth;
    }

    public int getDef()
    {
        return def;
    }

    public int getAtk()
    {
        return atk;
    }

    public int getMoney()
    {
        return money;
    }

    public int getMov()
    {
        return mov;
    }

    public Boolean getDeath() {
        return death;
    }

    public ListaSE<T> getInventory() {
        return inventory;
    }

    public ListaSE <T> getEquipped() {
        return equipped;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
    }

    public void heal(int health) {
        if(currentHealth + health > maxHealth) {
            int sub = (currentHealth + health)  - maxHealth;
            currentHealth += sub;
        }
        else {
            currentHealth += health;
        }
    }

    public void gainDef(int add) {
        def += add;
    }

    public void loseDef(int lost) {
        def -= lost;
        if (def < 0) {
            def = 0;
        }
    }

    public void gainAtk(int add) {
        atk += add;
    }

    public void loseAtk(int lose) {
        atk -= lose;
        if (atk < 0) {
            atk = 0;
        }
    }

    public void gainMov(int add) {
        mov += add;
    }

    public void loseMov(int lose) {
        mov -= lose;
        if (mov <= 0) {
            mov = 1;
        }
    }

    public void gainMoney(int add) {
        money += add;
    }

    public boolean spendMoney(int lose){
        if (money < lose) //no se puede gastar dinero, entonces que no se pueda hacer la transacción
        {
            return false;
        }
        else {
            money -= lose;
            return true;
        }

    }
    /*

    public void equipWeapon(Weapon weapon){
        if(weapon.getIsInInventory() == true || weapon.getEquipped() == false ){
            int atk = weapon.getAtkBuff();
            gainAtk(atk);
            weapon.setEquipped(true);
        }
    }

    public void removeWeapon(Weapon weapon) {
        if(weapon.getIsInInventory() == true || weapon.getEquipped() == true) {
            int atk = weapon.getAtkBuff();
            loseAtk(atk);
            weapon.setEquipped(false);
        }
    }

    public void equipShield(Shield shield){
        if(shield.getIsInInventory() == true || shield.getEquipped() == false) {
            int def = shield.getDefBuff();
            gainDef(def);
            shield.setEquipped(true);
        }
    }

    public void removeShield(Shield shield) {
        if(shield.getIsInInventory() == true || shield.getEquipped() == true) {
            int def = shield.getDefBuff();
            loseDef(def);
            shield.setEquipped(false);
        }
    }

    public void usePotion(Potion potion){
        if(potion.getIsInInventory() == true || potion.getUsed() == false) {
            int addHealth = potion.getHeal();
            heal(addHealth);
            potion.setUsed(true);
        }
    }

    public void equipBoots(Boots boots){
        if(boots.getIsInInventory() == true || boots.getEquipped() == false) {
            int mov = boots.getMovBuff();
            gainMov(mov);
            boots.setEquipped(true);
        }
    }

    public void removeBoots(Boots boots) {
        if(boots.getIsInInventory() == true || boots.getEquipped() == true) {
            int mov = boots.getMovBuff();
            loseMov(mov);
            boots.setEquipped(false);
        }
    }


    public Boolean useKey(Key key, Door door){
        if(door.getLocked() == true) {
            if(key.getIsInInventory() == true){
                if(key.getDoor() == door){
                    key.setIsInInventory(false);
                    return true;
                }
                return false;
            }
            return false;
        }
        else {
            door.setLocked(false);
            return true;
        }
    }
    */

    public void receiveAtk(int dealtAtk){
        Random rand = new Random();

        //nextInt de Random es exclusivo del valor máximo entonces hay que añadir un 1
        int randomNum = rand.nextInt((1 - 0) + 1) + 0;
        currentHealth = currentHealth -Math.max(0,dealtAtk*(randomNum*2)-def);
    }

    public void isDead() //El personaje se muere, fin del juego
     {
        if(currentHealth <= 0) {
            death = true;
        }
    }
/*
    public void getMoney(Money addMoney) {
        if(addMoney.getIsInInventory() == true) {
            money +=  1;
        }


    }


     */
    @Override
    public String toString() {
        return "Player";
    }

}