package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * @author Raphael Emberger
 */
public class LongUpgrade extends TowerUpgrade {
    private long value;

    public LongUpgrade(int price, long value) {
        super(price);
        this.value = value;
    }

    public long getValue() {
        return value;
    }
}
