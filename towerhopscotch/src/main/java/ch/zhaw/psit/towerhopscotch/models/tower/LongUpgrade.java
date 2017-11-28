package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * Created by Raphael Emberger on 28.11.2017.
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
