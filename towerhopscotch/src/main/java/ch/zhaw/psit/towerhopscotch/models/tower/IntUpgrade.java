package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public final class IntUpgrade extends TowerUpgrade {
    private int value;

    public IntUpgrade(int price, int value) {
        super(price);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
