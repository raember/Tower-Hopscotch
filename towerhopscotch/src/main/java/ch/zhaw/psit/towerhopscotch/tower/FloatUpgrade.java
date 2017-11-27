package ch.zhaw.psit.towerhopscotch.tower;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public final class FloatUpgrade extends TowerUpgrade {
    private float value;

    public FloatUpgrade(int price, float value) {
        super(price);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}