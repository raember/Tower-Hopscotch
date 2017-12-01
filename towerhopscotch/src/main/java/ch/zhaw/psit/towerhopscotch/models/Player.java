package ch.zhaw.psit.towerhopscotch.models;

public class Player {
    public static final int MAX_HEALTH = 50;
    private int health;
    private Gold gold;

    public Player() {
        this.health = MAX_HEALTH;
        this.gold = new Gold();
    }

    public int getHealth() {
        return health;
    }

    public Gold getGold() {
        return gold;
    }

    public boolean addGold(int amount) {
        int newValue = gold.getAmount() + amount;
        if (newValue >= 0){
            gold.setAmount(newValue);
            return true;
        }
        return false;
    }

    public void decreaseHealth(int amount) {
        health -= amount;
    }

    public boolean isDead() {
        return health <= 0;
    }
}
