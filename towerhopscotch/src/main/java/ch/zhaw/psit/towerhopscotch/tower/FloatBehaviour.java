package ch.zhaw.psit.towerhopscotch.tower;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public final class FloatBehaviour extends TowerBehaviour {
    private float value;

    public FloatBehaviour(int price, float value) {
        super(price);
        this.value = value;
    }

    public float getValue() {
        return value;
    }
}