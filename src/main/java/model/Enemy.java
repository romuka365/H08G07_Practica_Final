package model;

import Estructuras.MyMatrix.Coordenada;
import java.util.Random;

public class Enemy implements Comparable<Enemy> {
    private Boolean dead;
    private int currentHealth;
    private int maxHealth;
    private int def;
    private int atk;
    private int mov;
    private Coordenada posicion;

    public Enemy() {
        dead = false;
        maxHealth = 5;
        currentHealth = maxHealth;
        def = 2;
        atk = 3;
        posicion = new Coordenada(0, 0);
    }

    public Enemy(Boolean dead, int maxHealth, int def, int atk, Coordenada posicion) {
        this.dead = dead;
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
        this.def = def;
        this.atk = atk;
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

    public int getMov() {
        return mov;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void receiveAtk(int dealtAtk) {
        Random rand = new Random();
        int randomNum = rand.nextInt(2) + 1; //1 o 2
        currentHealth = currentHealth - Math.max(0, dealtAtk * randomNum - def);
    }

    public boolean isDead() {
        if (currentHealth <= 0) {
            dead = true;
        }
        return dead;
    }

    @Override
    public int compareTo(Enemy o) {
        return getPosicion().compareTo(o.getPosicion());
    }
}