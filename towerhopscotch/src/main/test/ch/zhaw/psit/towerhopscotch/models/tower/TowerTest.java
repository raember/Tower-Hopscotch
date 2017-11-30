package ch.zhaw.psit.towerhopscotch.models.tower;

import ch.zhaw.psit.towerhopscotch.models.Player;
import ch.zhaw.psit.towerhopscotch.models.tiles.Tile;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Created by Raphael Emberger on 28.11.2017.
 */
public class TowerTest {

    private Tower tower;

    @Before
    public void setup() {
        tower = new MonoTower();
    }

    @Test
    public void getFireRangeUpgrade() throws Exception {
        float range = tower.getFireRange();
        FloatUpgrade upgrade = tower.getFireRangeUpgrade();
        assertTrue(upgrade.getValue() > range);
        assertTrue(upgrade.getPrice() > 0);
    }

    @Test
    public void getfireFrequencyUpgrade() throws Exception {
        long frequency = tower.getFireFrequency();
        LongUpgrade upgrade = tower.getFireFrequencyUpgrade();
        assertTrue(upgrade.getValue() < frequency);
        assertTrue(upgrade.getPrice() > 0);
    }

    @Test
    public void getDamageUpgrade() throws Exception {
        int damage = tower.getDamage();
        IntUpgrade upgrade = tower.getDamageUpgrade();
        assertTrue(upgrade.getValue() > damage);
        assertTrue(upgrade.getPrice() > 0);
    }

    @Test
    public void tryPurchaseFireRangeUpgrade() throws Exception {
        float range = tower.getFireRange();
        FloatUpgrade upgrade = tower.getFireRangeUpgrade();
        Player player = new Player();
        player.addGold(upgrade.getPrice());
        assertTrue(tower.tryPurchaseFireRangeUpgrade(player));
        assertEquals(0, player.getGold());
        FloatUpgrade newUpgrade = tower.getFireRangeUpgrade();
        assertNotEquals(upgrade, newUpgrade);
        assertTrue(range < tower.getFireRange());
        assertTrue(tower.getFireRange() < newUpgrade.getValue());
    }

    @Test
    public void tryPurchaseFireFrequencyUpgrade() throws Exception {
        long frequency = tower.getFireFrequency();
        LongUpgrade upgrade = tower.getFireFrequencyUpgrade();
        Player player = new Player();
        int price = upgrade.getPrice();
        player.addGold(upgrade.getPrice());
        assertTrue(tower.tryPurchaseFireFrequencyUpgrade(player));
        assertEquals(0, player.getGold());
        LongUpgrade newUpgrade = tower.getFireFrequencyUpgrade();
        assertNotEquals(upgrade, newUpgrade);
        assertTrue(frequency > tower.getFireFrequency());
        assertTrue(tower.getFireFrequency() > newUpgrade.getValue());
    }

    @Test
    public void tryPurchaseDamageUpgrade() throws Exception {
        int damage = tower.getDamage();
        IntUpgrade upgrade = tower.getDamageUpgrade();
        Player player = new Player();
        int price = upgrade.getPrice();
        player.addGold(upgrade.getPrice());
        assertTrue(tower.tryPurchaseDamageUpgrade(player));
        assertEquals(0, player.getGold());
        IntUpgrade newUpgrade = tower.getDamageUpgrade();
        assertNotEquals(upgrade, newUpgrade);
        assertTrue(damage < tower.getDamage());
        assertTrue(tower.getDamage() < newUpgrade.getValue());
    }

    @Test
    public void canReach() throws Exception {
        int range = (int) (tower.getFireRange() * Tile.TILE_WIDTH);
        Point a = new Point(0, 0);
        Point b = new Point(range, 0);
        Point c = new Point(range + 1, 0);
        tower.setPosition(a);
        assertTrue(tower.canReach(b));
        assertFalse(tower.canReach(c));
    }

}