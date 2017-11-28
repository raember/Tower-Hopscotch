package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Raphael Emberger on 13.11.2017.
 */
public abstract class Tower {
    private int price;
    private Point position = new Point(0,0);
    private long currentTime = System.nanoTime();
    private long nextAttack;
    private ArrayList<Enemy> shotEnemies = new ArrayList<Enemy>();
    protected ArrayQueue<FloatUpgrade> fireRangeUpgrades = new ArrayQueue<FloatUpgrade>(10);
    private float fireRange = Float.MIN_VALUE;
    protected ArrayQueue<LongUpgrade> fireFrequencyUpgrades = new ArrayQueue<LongUpgrade>(10);
    private long fireFrequency = Long.MIN_VALUE;
    protected ArrayQueue<IntUpgrade> damageUpgrades = new ArrayQueue<IntUpgrade>(10);
    private int damage = Integer.MIN_VALUE;
    private Color color;

    protected Tower(int price, Color color) {
        this.price = price;
        this.color = color;
    }

    public Point getPosition() {return position;}
    public void setPosition(Point position) {this.position = position;}

    public FloatUpgrade getFireRangeUpgrade() {
        return fireRangeUpgrades.isEmpty() ? null : fireRangeUpgrades.get(0);
    }

    public LongUpgrade getfireFrequencyUpgrade() {
        return fireFrequencyUpgrades.isEmpty() ? null : fireFrequencyUpgrades.get(0);
    }

    public float getFireRange() {return fireRange;}
    public long getFireFrequency() {return fireFrequency;}
    public int getDamage() {return damage;}
    public int getPrice() {return price;}

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
        LongUpgrade upgrade = getfireFrequencyUpgrade();
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

    public boolean canReach(Point position) {
        return this.position.distance(position)/ Tile.TILE_WIDTH < fireRange;
    }

    public void update(long absNanoTime) {
        long deltaNanoTime = absNanoTime - currentTime;
        nextAttack = Math.min(nextAttack - deltaNanoTime, 0);
        currentTime = absNanoTime;
        shotEnemies.clear();
    }

    protected void attack(Enemy enemy) {
        if (nextAttack == 0) {
            enemy.setHealth(enemy.getHealth() - damage);
            shotEnemies.add(enemy);
            nextAttack = fireFrequency;
        }
    }
}
