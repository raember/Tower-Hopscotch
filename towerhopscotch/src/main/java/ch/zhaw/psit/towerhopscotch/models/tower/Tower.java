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

public abstract class Tower {
    private int price;
    private long currentTime = System.nanoTime();
    private long nextAttack;
    private long shotDuration;
    private long shot;
    private boolean isRemoved;
    private ArrayList<Enemy> shotEnemies = new ArrayList<Enemy>();
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

    public boolean tryPurchaseFireRangeUpgrade(Gold gold) {
        FloatUpgrade upgrade = getFireRangeUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        fireRange = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        fireRangeUpgrades.remove(0);
        return true;
    }

    public boolean tryPurchaseFireFrequencyUpgrade(Gold gold) {
        LongUpgrade upgrade = getFireFrequencyUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        fireFrequency = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        fireFrequencyUpgrades.remove(0);
        return true;
    }

    public boolean tryPurchaseDamageUpgrade(Gold gold) {
        IntUpgrade upgrade = getDamageUpgrade();
        if (upgrade == null || !upgrade.isAffordable(gold.getAmount())) return false;
        damage = upgrade.getValue();
        gold.setAmount(gold.getAmount() - upgrade.getPrice());
        price += upgrade.getPrice();
        damageUpgrades.remove(0);
        return true;
    }

    public boolean canReach(Point positionEnemy, Point towerPosition) {
        return towerPosition.distance(positionEnemy) / Tile.TILE_WIDTH <= fireRange;
    }

    public void update(long absNanoTime,Point towerPosition, List<Enemy> enemiesOnMap) {
        long deltaNanoTime = absNanoTime - currentTime;
        nextAttack = Math.max(nextAttack - deltaNanoTime, 0);
        currentTime = absNanoTime;
        updateInternal(nextAttack == 0, filterEnemies(enemiesOnMap, towerPosition));
        updateLeech();
    }

    protected void updateLeech() {
    }

    protected List<Enemy> filterEnemies(List<Enemy> enemies, Point towerPosition) {
        return enemies.stream().filter(e -> canReach(new Point((int) e.getX(), (int) e.getY()), towerPosition)).collect(Collectors.toList());
    }

    protected void updateInternal(boolean canShoot, List<Enemy> enemiesInVicinity) {
        if (canShoot) {
            shotEnemies.clear();
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
    
    public void render(Graphics g, Point position) {
        g.drawImage(image, (int) position.getX(), (int) position.getY(), Tile.TILE_WIDTH, Tile.TILE_HEIGHT, null);
        Text.drawString(g, Integer.toString(level), (int) position.getX() + Tile.TILE_WIDTH / 2, (int) position.getY() + Tile.TILE_HEIGHT / 2, true, Color.WHITE, Assets.font16);
        
        
        float[] dashingPattern2 = {10f, 4f};
        Stroke stroke1 = new BasicStroke(4f, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 1.0f, dashingPattern2, 0.0f);
        
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(stroke1);
        
        int offset = Tile.TILE_WIDTH / 2;
        for (Enemy enemy : shotEnemies) {
            Tower tower = enemy.getOnLayer().getTowerAtPosition(position);
            if (tower != null && tower.equals(this)){
                g.drawLine(position.x + offset, position.y + offset,
                        (int) enemy.getX() + offset, (int) enemy.getY() + offset);
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
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
