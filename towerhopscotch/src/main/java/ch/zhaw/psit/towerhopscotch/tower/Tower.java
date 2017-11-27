package ch.zhaw.psit.towerhopscotch.tower;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public abstract class Tower {
    private int price;
    private ArrayQueue<FloatUpgrade> fireRangeUpgrades = new ArrayQueue<FloatUpgrade>(10);
    private float fireRange = Float.MIN_VALUE;
    private ArrayQueue<IntUpgrade> fireFrequencyUpgrades = new ArrayQueue<IntUpgrade>(10);
    private int fireFrequency = Integer.MIN_VALUE;
    private ArrayQueue<IntUpgrade> damageUpgrades = new ArrayQueue<IntUpgrade>(10);
    private int damage = Integer.MIN_VALUE;
    private Color color;

    protected Tower(int price, Color color) {
        this.price = price;
        this.color = color;
    }

    public FloatUpgrade getFireRangeUpgrade() {
        return fireRangeUpgrades.isEmpty() ? null : fireRangeUpgrades.get(0);
    }

    public IntUpgrade getfireFrequencyUpgrade() {
        return fireFrequencyUpgrades.isEmpty() ? null : fireFrequencyUpgrades.get(0);
    }

    public IntUpgrade getdamageUpgrade() {
        return damageUpgrades.isEmpty() ? null : damageUpgrades.get(0);
    }

    public boolean tryPurchaseFireRangeUpgrade(int budget) {
        FloatUpgrade upgrade = getFireRangeUpgrade();
        if (upgrade == null || !upgrade.isAffordable(budget)) return false;
        fireRange = upgrade.getValue();
        fireRangeUpgrades.remove(0);
        return true;
    }

    public boolean tryPurchaseFireFrequencyUpgrade(int budget) {
        IntUpgrade upgrade = getfireFrequencyUpgrade();
        if (upgrade == null || !upgrade.isAffordable(budget)) return false;
        fireFrequency = upgrade.getValue();
        fireFrequencyUpgrades.remove(0);
        return true;
    }

    public boolean tryPurchaseDamageUpgrade(int budget) {
        IntUpgrade upgrade = getdamageUpgrade();
        if (upgrade == null || !upgrade.isAffordable(budget)) return false;
        damage = upgrade.getValue();
        damageUpgrades.remove(0);
        return true;
    }
}
