package model;
import Estructuras.MyLinkedList.ListaSE;
import Estructuras.MyMatrix.Coordenada;

import java.util.Random;

public class Player {
    private Boolean death;
    private int currentHealth;
    private int maxHealth;
    private int def;
    private int atk;
    private int money;
    private int mov;
    private ListaSE<Item> inventory;
    private Coordenada posicion;

    public Player() {
        death = false;
        maxHealth = 10;
        currentHealth = maxHealth;
        def = 3;
        atk = 5;
        money = 0;
        mov = 1;
        inventory = new ListaSE<>();
        posicion = new Coordenada(0, 0);
    }

    public Player(Boolean death, int maxHealth, int def, int atk, int money, int mov, ListaSE<Item> inventory, Coordenada posicion) {
        this.death = death;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
        this.money = money;
        this.mov = mov;
        this.inventory = inventory;
        this.posicion = posicion;
    }

    public Coordenada getPosicion() {
        return posicion;
    }

    public void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getDef() {
        return def;
    }

    public int getAtk() {
        return atk;
    }

    public int getMoney() {
        return money;
    }

    public int getMov() {
        return mov;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = Math.min(currentHealth, maxHealth);
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setMov(int mov) {
        this.mov = mov;
    }

    public void setDeath(boolean death) {
        this.death = death;
    }

    public Boolean getDeath() {
        return death;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
    }

    public void heal(int health) {
        currentHealth = Math.min(currentHealth + health, maxHealth);
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

    public boolean spendMoney(int lose) {
        if (money < lose) {
            return false;
        } else {
            money -= lose;
            return true;
        }
    }

    public void addToInventory(Item item) {
        inventory.add(item);
    }

    public ListaSE<Item> getInventory() {
        return inventory;
    }

    public void equipWeapon(Weapon weapon) {
        if (weapon.getIsInInventory() || !weapon.getEquipped()) {
            int atk = weapon.getAtkBuff();
            gainAtk(atk);
            weapon.setEquipped(true);
        }
    }

    public void removeWeapon(Weapon weapon) {
        if (weapon.getIsInInventory() || weapon.getEquipped()) {
            int atk = weapon.getAtkBuff();
            loseAtk(atk);
            weapon.setEquipped(false);
        }
    }

    public void equipShield(Shield shield) {
        if (shield.getIsInInventory() || !shield.getEquipped()) {
            int def = shield.getDefBuff();
            gainDef(def);
            shield.setEquipped(true);
        }
    }

    public void removeShield(Shield shield) {
        if (shield.getIsInInventory() || shield.getEquipped()) {
            int def = shield.getDefBuff();
            loseDef(def);
            shield.setEquipped(false);
        }
    }

    public void usePotion(Potion potion) {
        if (potion.getIsInInventory() || !potion.getUsed()) {
            int addHealth = potion.getHeal();
            heal(addHealth);
            potion.setUsed(true);
        }
    }

    public void equipBoots(Boots boots) {
        if (boots.getIsInInventory() || !boots.getEquipped()) {
            int mov = boots.getMovBuff();
            gainMov(mov);
            boots.setEquipped(true);
        }
    }

    public void removeBoots(Boots boots) {
        if (boots.getIsInInventory() || boots.getEquipped()) {
            int mov = boots.getMovBuff();
            loseMov(mov);
            boots.setEquipped(false);
        }
    }

    public Boolean useKey(Key key, Door door) {
        if (door.getLocked()) {
            if (key.getIsInInventory()) {
                if (key.getDoor() == door) {
                    key.setIsInInventory(false);
                    return true;
                }
                return false;
            }
            return false;
        } else {
            door.setLocked(false);
            return true;
        }
    }

    public void receiveAtk(int dealtAtk) {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1; //1 o 2
        currentHealth = currentHealth - Math.max(0, dealtAtk * randomNum - def);
    }

    public boolean isDead() {
        if (currentHealth <= 0) {
            death = true;
        }
        return death;
    }

    public void collectMoney(Money addMoney) {
        if (addMoney.getIsInInventory()) {
            money += 1;
        }
    }

}