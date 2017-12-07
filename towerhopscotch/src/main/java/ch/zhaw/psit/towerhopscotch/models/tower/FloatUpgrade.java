package ch.zhaw.psit.towerhopscotch.models.tower;

/**
 * @author Raphael Emberger
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