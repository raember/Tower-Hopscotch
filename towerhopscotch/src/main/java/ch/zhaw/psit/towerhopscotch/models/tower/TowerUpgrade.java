package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * A upgrade for a tower
 * @author Raphael Emberger
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
