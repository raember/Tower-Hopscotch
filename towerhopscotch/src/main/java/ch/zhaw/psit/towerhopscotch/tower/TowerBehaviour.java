package ch.zhaw.psit.towerhopscotch.tower;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.List;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public abstract class TowerBehaviour {
    private int price;
    private List<FloatBehaviour> fireRangeBehaviours = new ArrayQueue<FloatBehaviour>(10);
    private float fireRange = Float.MIN_VALUE;
    private List<IntBehaviour> fireFrequencyBehaviours = new ArrayQueue<IntBehaviour>(10);
    private int fireFrequency = Integer.MIN_VALUE;
    private List<IntBehaviour> damageBehaviours = new ArrayQueue<IntBehaviour>(10);
    private int damage = Integer.MIN_VALUE;

    protected TowerBehaviour(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public int pruchase(int budget) {
        return budget > price ? budget - price : budget;
    }
}
