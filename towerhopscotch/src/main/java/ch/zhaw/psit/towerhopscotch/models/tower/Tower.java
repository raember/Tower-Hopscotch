package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.GUI.Assets;
import ch.zhaw.psit.towerhopscotch.GUI.Text;
import ch.zhaw.psit.towerhopscotch.models.Gold;
import ch.zhaw.psit.towerhopscotch.models.entities.enemies.Enemy;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The class represents the tower on the map and is the superclass for the different towers
 * The tower can be upgraded and rendered
 * @author Raphael Emberger, Stefan Bösch
 */
public abstract class Tower {
    private int price;
    private boolean isRemoved;
    private BufferedImage image;
    protected ArrayQueue<FloatUpgrade> fireRangeUpgrades = new ArrayQueue<FloatUpgrade>(10);
    private float fireRange = Float.MIN_VALUE;
    protected ArrayQueue<LongUpgrade> fireFrequencyUpgrades = new ArrayQueue<LongUpgrade>(10);
    private long fireFrequency = Long.MIN_VALUE;
    protected ArrayQueue<IntUpgrade> damageUpgrades = new ArrayQueue<IntUpgrade>(10);
    private int damage = Integer.MIN_VALUE;
    private int level;

    protected Tower(int price, BufferedImage image) {
        this.price = price;
        this.image = image;
        level = 1;
    }

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

    /**
     * Upgrade range, frequency, and the damage for the tower
     * @param gold Gold from the player
     * @return If the upgrades where made
     */
    public boolean upgradeAll(Gold gold){
        FloatUpgrade rangeUpgrade = getFireRangeUpgrade();
        LongUpgrade frequencyUpgrade = getFireFrequencyUpgrade();
        IntUpgrade damageUpgrade = getDamageUpgrade();
        int price = rangeUpgrade.getPrice() + frequencyUpgrade.getPrice() + damageUpgrade.getPrice();
        if (rangeUpgrade != null && gold.getAmount() >= price){
            boolean success1 = tryPurchaseDamageUpgrade(gold);
            boolean success2 = tryPurchaseFireRangeUpgrade(gold);
            boolean success3 = tryPurchaseFireFrequencyUpgrade(gold);

            if (success1 && success2 && success3) {
                levelUp();
                return true;
            }
        }
        return false;
    }

    /**
     * Try to upgrade the fire range
     * @param gold The gold from the player
     * @return True if the upgrade was successfull
     */
    private boolean tryPurchaseFireRangeUpgrade(Gold gold) {
        FloatUpgrade upgrade = getFireRangeUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        fireRange = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        fireRangeUpgrades.remove(0);
        return true;
    }

    /**
     * Try to upgrade the fire frequency
     * @param gold The gold from the player
     * @return True if the upgrade was successfull
     */
    private boolean tryPurchaseFireFrequencyUpgrade(Gold gold) {
        LongUpgrade upgrade = getFireFrequencyUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        fireFrequency = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        fireFrequencyUpgrades.remove(0);
        return true;
    }

    /**
     * Try to upgrade the damage
     * @param gold The gold from the player
     * @return True if the upgrade was successfull
     */
    private boolean tryPurchaseDamageUpgrade(Gold gold) {
        IntUpgrade upgrade = getDamageUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        damage = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        damageUpgrades.remove(0);
        return true;
    }

    public void update() {}

    /**
     * Render the Tower at the position
     * @param g Graphics
     * @param position Tower position
     */
    public void render(Graphics g, Point position) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, Integer.toString(getLevel()), (int) position.getX() + Tile.TILE_WIDTH / 2, (int) position.getY() + Tile.TILE_HEIGHT / 2, true, Color.WHITE, Assets.font16);
    }

    public int getLevel() {
        return level;
    }

    /**
     * Increase tower level
     */
    private void levelUp() {
        if (level < 9){
            level++;
        }
    }

    public void remove() {
        isRemoved = true;
    }

    public boolean isRemoved() {
        return isRemoved;
    }
}
