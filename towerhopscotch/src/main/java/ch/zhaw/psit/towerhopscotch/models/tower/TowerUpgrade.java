package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public abstract class TowerUpgrade {
    private int price;

    protected TowerUpgrade(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public boolean isAffordable(int budget) {
        return price <= budget;
    }
}
