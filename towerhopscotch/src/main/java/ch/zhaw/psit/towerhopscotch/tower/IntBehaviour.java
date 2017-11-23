package ch.zhaw.psit.towerhopscotch.tower;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public final class IntBehaviour extends TowerBehaviour {
    private int value;

    public IntBehaviour(int price, int value) {
        super(price);
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
