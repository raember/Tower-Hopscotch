package ch.zhaw.psit.towerhopscotch.models;

public class Player {
    public static final int MAX_HEALTH = 30;
    private int health;
    private int gold;

    public Player() {
        this.health = MAX_HEALTH;
        this.gold = 0;
    }

    public int getHealth() {
        return health;
    }

    public int getGold() {
        return gold;
    }

    public void addGold(int amount) {
        gold += amount;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
