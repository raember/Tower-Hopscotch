package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public LongUpgrade getFireFrequencyUpgrade() {
        return fireFrequencyUpgrades.isEmpty() ? null : fireFrequencyUpgrades.get(0);
    }

    public float getFireRange() {return fireRange;}

    protected void setFireRange(float value) {
        fireRange = value;
    }
    public long getFireFrequency() {return fireFrequency;}

    protected void setFireFrequency(long value) {
        fireFrequency = value;
    }
    public int getDamage() {return damage;}

    protected void setDamage(int value) {
        damage = value;
    }
    public int getPrice() {return price;}

    public IntUpgrade getDamageUpgrade() {
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
        LongUpgrade upgrade = getFireFrequencyUpgrade();
        if (upgrade == null || !upgrade.isAffordable(budget)) return false;
        fireFrequency = upgrade.getValue();
        fireFrequencyUpgrades.remove(0);
        return true;
    }

    public boolean tryPurchaseDamageUpgrade(int budget) {
        IntUpgrade upgrade = getDamageUpgrade();
        if (upgrade == null || !upgrade.isAffordable(budget)) return false;
        damage = upgrade.getValue();
        damageUpgrades.remove(0);
        return true;
    }

    public boolean canReach(Point position) {
        return this.position.distance(position) / Tile.TILE_WIDTH <= fireRange;
    }

    public void update(long absNanoTime, List<Enemy> enemiesOnMap) {
        long deltaNanoTime = absNanoTime - currentTime;
        nextAttack = Math.min(nextAttack - deltaNanoTime, 0);
        currentTime = absNanoTime;
        List<Enemy> enemiesInVicinity = enemiesOnMap.stream().filter(e -> canReach(new Point((int) e.getX(), (int) e.getY()))).collect(Collectors.toList());
        updateInternal(nextAttack == 0, enemiesInVicinity);
    }

    protected void updateInternal(boolean canShoot, List<Enemy> enemiesInVicinity) {
        if (canShoot) {
            boolean shotEnamies = false;
            for (Enemy enemy : enemiesInVicinity) {
                shotEnamies |= shoot(enemy);
            }
            if (shotEnamies) resetCooldown();
        }
    }

    protected void attack(Enemy enemy) {
        if (shoot(enemy)) resetCooldown();
    }

    protected boolean shoot(Enemy enemy) {
        if (nextAttack == 0) {
            enemy.setHealth(enemy.getHealth() - damage);
            shotEnemies.add(enemy);
            return true;
        }
        return false;
    }

    protected void resetCooldown() {
        nextAttack = fireFrequency;
    }

    public abstract void render(Graphics g);
}
