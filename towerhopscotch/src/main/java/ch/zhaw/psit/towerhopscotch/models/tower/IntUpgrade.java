package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * @author Raphael Emberger
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
