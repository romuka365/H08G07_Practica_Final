package model;

import java.util.Random;

public class Enemy {
    private Boolean dead;
    private int currentHealth; //dos tipos de salud porque esta la puede restaurar pero sin pasarse del máximo
    private int maxHealth;
    private int def;
    private int atk;
    private int mov;

    public Enemy() //Generador para cuando se crea uno estándar
    {
        dead = false;
        maxHealth = 5;
        currentHealth = maxHealth;
        def = 2;
        atk = 3;
    }

    public Enemy(Boolean dead,int maxHealth, int def, int atk) //para cuando nos den los datos
    {
        this.dead = dead;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
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

    public int getMov()
    {
        return mov;
    }

    public void receiveAtk(int dealtAtk){
        Random rand = new Random();

        //nextInt de Random es exclusivo del valor máximo entonces hay que añadir un 1
        int randomNum = rand.nextInt((1 - 0) + 1) + 0;
        currentHealth = currentHealth -Math.max(0,dealtAtk*(randomNum*2)-def);
    }

    public boolean isDead() {
        if(currentHealth <= 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public String toString() {
        return "Enemy";
    }
}
